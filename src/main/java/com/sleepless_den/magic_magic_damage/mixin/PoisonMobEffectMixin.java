package com.sleepless_den.magic_magic_damage.mixin;

import net.minecraft.world.effect.PoisonMobEffect;
import net.minecraft.world.entity.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PoisonMobEffect.class)
public class PoisonMobEffectMixin {

    @Inject(method = "shouldApplyEffectTickThisTick", at = @At("HEAD"), cancellable = true)
    private void magicMagicDamage$modifyPoisonDamageFrequency(int duration, int amplifier, CallbackInfoReturnable<Boolean> cir) {
        int i = 40 >> amplifier;
        cir.setReturnValue(i == 0 || duration % i == 0);
    }

    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    private float magicMagicDamage$modifyPoisonDamage(float amount) {
        return amount * 2f;
    }

    @Inject(method = "applyEffectTick", at = @At("HEAD"), cancellable = true)
    private void magicMagicDamage$modifyPoisonThreshold(LivingEntity entity, int amplifier, CallbackInfoReturnable<Boolean> cir) {
        if (entity.getHealth() <= 2.0) {
            cir.setReturnValue(true);
        }
    }
}