package com.sleepless_den.magic_magic_damage.mixin;

import net.minecraft.world.effect.WitherMobEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WitherMobEffect.class)
public class WitherMobEffectMixin {

    @Inject(method = "shouldApplyEffectTickThisTick", at = @At("HEAD"), cancellable = true)
    private void magicMagicDamage$modifyWitherDamageFrequency(int duration, int amplifier, CallbackInfoReturnable<Boolean> cir) {
        int i = 80 >> amplifier;
        cir.setReturnValue(i == 0 || duration % i == 0);
    }
    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    private float magicMagicDamage$modifyWitherDamage(float amount) {
        return amount * 2f;
    }
}
