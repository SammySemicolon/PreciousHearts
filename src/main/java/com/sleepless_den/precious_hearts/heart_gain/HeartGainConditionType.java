package com.sleepless_den.precious_hearts.heart_gain;

import com.mojang.serialization.*;
import com.sleepless_den.precious_hearts.heart_gain.conditions.*;
import com.sleepless_den.precious_hearts.registry.*;
import net.minecraft.resources.*;

public class HeartGainConditionType {

    public static final Codec<HeartGainConditionType> TYPE_CODEC = ResourceLocation.CODEC.xmap(HeartGainConditionType::getType, HeartGainConditionType::getId);

    public static HeartGainConditionType getType(ResourceLocation id) {
        final HeartGainConditionType heartGainConditionType = HeartGainConditionRegistry.CONDITIONS_REGISTRY.get(id);
        if (heartGainConditionType == null) {
            throw new IllegalArgumentException("Unknown Heart Gain Condition Type: " + id);
        }
        return heartGainConditionType;
    }

    public final Codec<? extends HeartGainCondition> codec;

    public HeartGainConditionType(Codec<? extends HeartGainCondition> codec) {
        this.codec = codec;
    }

    public ResourceLocation getId() {
        return HeartGainConditionRegistry.CONDITIONS_REGISTRY.getKey(this);
    }
}
