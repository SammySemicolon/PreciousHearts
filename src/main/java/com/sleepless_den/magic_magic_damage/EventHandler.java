package com.sleepless_den.magic_magic_damage;

import net.minecraft.util.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.data.internal.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.helpers.*;

@EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void onHurt(LivingDamageEvent.Post event) {
        final DamageSource source = event.getSource();
        if (source.is(Tags.DamageTypes.IS_MAGIC)) {
            final LivingEntity entity = event.getEntity();
            final AttributeInstance toughness = entity.getAttribute(Attributes.ARMOR_TOUGHNESS);
            if (toughness != null) {
                double discardChance = Mth.clamp(toughness.getValue() * 2.5f,0, 50) / 100f;
                if (discardChance > 0 && entity.getRandom().nextFloat() < discardChance) {
                    return;
                }
            }
            float damage = event.getNewDamage();
            int addedStacks = 1 + Mth.floor(damage / 4);

            final MobEffectInstance instance = entity.getEffect(MagicMagicDamageMobEffects.ARMOR_CORROSION);
            if (instance == null) {
                entity.addEffect(new MobEffectInstance(MagicMagicDamageMobEffects.ARMOR_CORROSION, 300, addedStacks-1));
            }
            else {
                EntityHelper.amplifyEffect(instance, entity, addedStacks);
                EntityHelper.extendEffect(instance, entity, 20, 600);
            }
        }
    }

}
