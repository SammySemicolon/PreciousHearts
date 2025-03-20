package com.sleepless_den.precious_hearts;

import com.sleepless_den.precious_hearts.client.*;
import com.sleepless_den.precious_hearts.death_absorption.*;
import net.neoforged.api.distmarker.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.*;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientSetupEvents {

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.EXPERIENCE_LEVEL, PreciousHeartsMod.path("death_absorption"),
                DeathAbsorptionRenderHandler::renderDeathAbsorption);
    }
}
