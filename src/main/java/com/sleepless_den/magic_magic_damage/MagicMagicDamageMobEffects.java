package com.sleepless_den.magic_magic_damage;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MagicMagicDamageMobEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MagicMagicDamageMod.MAGIC_MAGIC_DAMAGE);

    public static final DeferredHolder<MobEffect, MobEffect> ARMOR_CORROSION = EFFECTS.register("armor_corrosion", ArmorCorrosion::new);

}
