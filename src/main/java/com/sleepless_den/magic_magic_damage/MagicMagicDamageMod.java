package com.sleepless_den.magic_magic_damage;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(MagicMagicDamageMod.MAGIC_MAGIC_DAMAGE)
public class MagicMagicDamageMod
{
    public static final String MAGIC_MAGIC_DAMAGE = "magic_magic_damage";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MagicMagicDamageMod(IEventBus modEventBus, ModContainer modContainer) {
        MagicMagicDamageMobEffects.EFFECTS.register(modEventBus);
    }

    public static ResourceLocation path(String path) {
        return ResourceLocation.fromNamespaceAndPath(MAGIC_MAGIC_DAMAGE, path);
    }
}
