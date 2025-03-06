package com.sleepless_den.magic_magic_damage;

import net.minecraft.resources.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import team.lodestar.lodestone.helpers.ColorHelper;

public class ArmorCorrosion extends MobEffect {
    public ArmorCorrosion() {
        super(MobEffectCategory.BENEFICIAL, 13998533);
        var id = ResourceLocation.fromNamespaceAndPath(MagicMagicDamageMod.MAGIC_MAGIC_DAMAGE, "armor_corrosion");
        addAttributeModifier(Attributes.ARMOR, id, -0.5f, AttributeModifier.Operation.ADD_VALUE);
    }
}