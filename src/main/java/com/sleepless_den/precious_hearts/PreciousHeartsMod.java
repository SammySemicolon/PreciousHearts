package com.sleepless_den.precious_hearts;

import com.sleepless_den.precious_hearts.config.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.*;
import net.neoforged.fml.config.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import static com.sleepless_den.precious_hearts.registry.AttachmentTypeRegistry.ATTACHMENT_TYPES;
import static com.sleepless_den.precious_hearts.registry.HeartGainConditionRegistry.CONDITIONS;

@Mod(PreciousHeartsMod.PRECIOUS_HEARTS)
public class PreciousHeartsMod
{
    public static final String PRECIOUS_HEARTS = "precious_hearts";
    public static final ResourceLocation PRECIOUS_HEARTS_MODIFIER = path("health_affliction");

    private static final Logger LOGGER = LogUtils.getLogger();

    public PreciousHeartsMod(IEventBus modEventBus, ModContainer modContainer) {
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);

        ATTACHMENT_TYPES.register(modEventBus);
        CONDITIONS.register(modEventBus);

    }

    public static ResourceLocation path(String path) {
        return ResourceLocation.fromNamespaceAndPath(PRECIOUS_HEARTS, path);
    }
}
