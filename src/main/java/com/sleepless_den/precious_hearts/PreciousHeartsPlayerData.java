package com.sleepless_den.precious_hearts;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sleepless_den.precious_hearts.config.*;
import com.sleepless_den.precious_hearts.heart_gain.*;
import com.sleepless_den.precious_hearts.heart_gain.conditions.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.ai.attributes.*;

import java.util.*;

public class PreciousHeartsPlayerData {

    public static Codec<PreciousHeartsPlayerData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.INT.fieldOf("heartModifier").forGetter(c -> c.heartModifier),
            ResourceLocation.CODEC.listOf().fieldOf("heartSources").forGetter(c -> c.heartSources)
    ).apply(obj, PreciousHeartsPlayerData::new));

    private int heartModifier;
    private final List<ResourceLocation> heartSources = new ArrayList<>();

    public PreciousHeartsPlayerData() {
    }

    public PreciousHeartsPlayerData(int heartModifier, List<ResourceLocation> heartSources) {
        this.heartModifier = heartModifier;
    }

    public int getHeartModifier() {
        return heartModifier;
    }

    public List<ResourceLocation> getHeartSources() {
        return heartSources;
    }

    public boolean tryAddHeartModifier(ServerPlayer player, HeartGainAdvancement<?> advancement) {
        final ResourceLocation id = advancement.getId();
        if (!getHeartSources().contains(id)) {
            heartSources.add(id);
            heartModifier += CommonConfig.HEART_GAIN_PER_TASK.getConfigValue();
            int maximum = CommonConfig.HEART_MAXIMUM_AMOUNT.getConfigValue();
            var attribute = player.getAttribute(Attributes.MAX_HEALTH);
            var health = attribute.getBaseValue() + heartModifier;
            if (health > maximum) {
                heartModifier = (int) (-attribute.getBaseValue() + maximum);
            }
            return true;
        }
        return false;
    }

    public boolean removeMostRecentHeartModifier(ServerPlayer player) {
        if (!heartSources.isEmpty()) {
            heartSources.removeLast();
        }
        heartModifier -= CommonConfig.HEART_LOSS_ON_DEATH.getConfigValue();
        int minimum = CommonConfig.HEART_MINIMUM_AMOUNT.getConfigValue();
        var attribute = player.getAttribute(Attributes.MAX_HEALTH);
        var health = attribute.getBaseValue() + heartModifier;
        if (health < minimum) {
            heartModifier = (int) (-attribute.getBaseValue() + minimum);
            return false;
        }
        return true;
    }
}