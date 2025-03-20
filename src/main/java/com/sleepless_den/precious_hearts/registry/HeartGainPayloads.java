package com.sleepless_den.precious_hearts.registry;

import com.sleepless_den.precious_hearts.*;
import com.sleepless_den.precious_hearts.packets.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.network.event.*;
import net.neoforged.neoforge.network.registration.*;
import team.lodestar.lodestone.registry.common.*;

@EventBusSubscriber(modid = PreciousHeartsMod.PRECIOUS_HEARTS, bus = EventBusSubscriber.Bus.MOD)
public class HeartGainPayloads {

    public static LodestoneNetworkPayloads.PayloadRegistryHelper PRECIOUS_HEARTS_CHANNEL = new LodestoneNetworkPayloads.PayloadRegistryHelper(PreciousHeartsMod.PRECIOUS_HEARTS);

    @SubscribeEvent
    public static void registerNetworkStuff(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        PRECIOUS_HEARTS_CHANNEL.playToClient(registrar, "sync_death_absorption", SyncDeathAbsorptionDataPayload.class, SyncDeathAbsorptionDataPayload::new);
    }
}