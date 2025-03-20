package com.sleepless_den.precious_hearts;

import com.sleepless_den.precious_hearts.death_absorption.*;
import com.sleepless_den.precious_hearts.heart_gain.*;
import com.sleepless_den.precious_hearts.reload_listener.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.player.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.event.*;
import net.neoforged.neoforge.event.entity.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.*;
import net.neoforged.neoforge.event.level.*;
import net.neoforged.neoforge.event.tick.*;

@EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void registerListeners(AddReloadListenerEvent event) {
        HeartGainConditionsReloadListener.register(event);
    }

    @SubscribeEvent
    public static void wakeUp(PlayerWakeUpEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            DeathAbsorptionHandler.recoverDeathAbsorption(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void entityJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            HeartGainHandler.setBaseHealth(player);
            DeathAbsorptionHandler.markDirty(player);
        }
    }

    @SubscribeEvent
    public static void entityJoin(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            DeathAbsorptionHandler.syncDeathAbsorption(player);
        }
    }

    @SubscribeEvent
    public static void kill(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof ServerPlayer player) {
            HeartGainHandler.rewardEntityKill(player, event.getEntity());
        }
        if (event.getEntity() instanceof ServerPlayer player) {
            if (DeathAbsorptionHandler.preventDeath(player)) {
                player.setHealth(1);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void grantAdvancement(AdvancementEvent.AdvancementEarnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            HeartGainHandler.rewardAdvancement(player, event.getAdvancement());
        }
    }

    @SubscribeEvent
    public static void playerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            HeartGainHandler.removeHealth(player);
        }
    }
}