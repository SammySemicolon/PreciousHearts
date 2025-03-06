package com.sleepless_den.precious_hearts.mixin;

import com.sleepless_den.precious_hearts.heart_gain.*;
import net.minecraft.server.level.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.*;

@Mixin(ThrownPotion.class)
public class ThrownPotionMixin {

    @ModifyArgs(method = "applySplash", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z"))
    private void preciousHearts$applySplash(Args args) {
        MobEffectInstance effectInstance = args.get(0);
        Entity entity = args.get(1);
        if (entity instanceof ServerPlayer player) {
            HeartGainHandler.rewardPotionSplash(player, effectInstance);
        }
    }

    @ModifyArgs(method = "applySplash", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffect;applyInstantenousEffect(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/LivingEntity;ID)V"))
    private void preciousHearts$applyInstantaneousEffect(Args args) {
        MobEffectInstance effectInstance = args.get(0);
        Entity entity = args.get(1);
        if (entity instanceof ServerPlayer player) {
            HeartGainHandler.rewardPotionSplash(player, effectInstance);
        }
    }
}
