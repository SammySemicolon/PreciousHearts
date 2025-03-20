package com.sleepless_den.precious_hearts.heart_gain;

import com.sleepless_den.precious_hearts.*;
import com.sleepless_den.precious_hearts.config.*;
import com.sleepless_den.precious_hearts.heart_gain.conditions.*;
import com.sleepless_den.precious_hearts.registry.*;
import com.sleepless_den.precious_hearts.reload_listener.*;
import net.minecraft.*;
import net.minecraft.advancements.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;

public class HeartGainHandler {

    public static void setBaseHealth(Player player) {
        final AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
        attribute.setBaseValue(CommonConfig.HEART_BASE_AMOUNT.getConfigValue());
    }

    public static void updateHealth(ServerPlayer player, HeartGainPlayerData data) {
        var attribute = player.getAttribute(Attributes.MAX_HEALTH);
        var modifier = new AttributeModifier(PreciousHeartsMod.PRECIOUS_HEARTS_MODIFIER, data.getHeartModifier(), AttributeModifier.Operation.ADD_VALUE);
        attribute.addOrReplacePermanentModifier(modifier);
    }

    public static void removeHealth(ServerPlayer player) {
        var data = player.getData(PreciousHeartsAttachmentTypes.HEART_GAIN_DATA);
        if (data.removeMostRecentHeartModifier(player)) {
            int randomId = player.getRandom().nextInt(39);
            for (ServerPlayer otherPlayer : player.getServer().getPlayerList().getPlayers()) {
                otherPlayer.displayClientMessage(Component.translatable("message.precious_hearts.drain." + randomId, player.getDisplayName()).withStyle(ChatFormatting.DARK_RED), false);
            }
        }
        updateHealth(player, data);
    }

    public static void addHealth(ServerPlayer player, HeartGainAdvancement<?> advancement) {
        var data = player.getData(PreciousHeartsAttachmentTypes.HEART_GAIN_DATA);

        if (data.tryAddHeartModifier(player, advancement)) {
            int randomId = player.getRandom().nextInt(9);
            for (ServerPlayer otherPlayer : player.getServer().getPlayerList().getPlayers()) {
                otherPlayer.displayClientMessage(Component.translatable("message.precious_hearts.gain." + randomId, player.getDisplayName()).withStyle(ChatFormatting.GREEN), false);
            }
        }
        updateHealth(player, data);
    }

    public static void rewardAdvancement(ServerPlayer player, AdvancementHolder advancementHolder) {
        var advancements = HeartGainConditionsReloadListener.getAllAdvancements(CompleteAdvancementCondition.class);
        for (HeartGainAdvancement<CompleteAdvancementCondition> advancement : advancements) {
            final CompleteAdvancementCondition condition = advancement.getCondition();
            if (condition.getAdvancementId().equals(advancementHolder.id())) {
                addHealth(player, advancement);
            }
        }
    }

    public static void rewardEntityKill(ServerPlayer player, LivingEntity target) {
        var advancements = HeartGainConditionsReloadListener.getAllAdvancements(KillMobCondition.class);
        for (HeartGainAdvancement<KillMobCondition> advancement : advancements) {
            final KillMobCondition condition = advancement.getCondition();
            if (condition.getEntity().value().equals(target.getType())) {
                addHealth(player, advancement);
            }
        }
    }
    public static void rewardItemEating(Level level, ServerPlayer player, ItemStack food) {
        var advancements = HeartGainConditionsReloadListener.getAllAdvancements(EatItemCondition.class);
        for (HeartGainAdvancement<EatItemCondition> advancement : advancements) {
            final EatItemCondition condition = advancement.getCondition();
            if (condition.getItem().value().equals(food.getItem())) {
                addHealth(player, advancement);
            }
        }
    }

    public static void rewardPotionSplash(ServerPlayer player, MobEffectInstance effect) {
        var advancements = HeartGainConditionsReloadListener.getAllAdvancements(SplashPotionCondition.class);
        for (HeartGainAdvancement<SplashPotionCondition> advancement : advancements) {
            final SplashPotionCondition condition = advancement.getCondition();
            if (condition.getEffect().is(effect.getEffect()) && condition.getAmplifier() >= effect.getAmplifier()) {
                addHealth(player, advancement);
            }
        }
    }
}
