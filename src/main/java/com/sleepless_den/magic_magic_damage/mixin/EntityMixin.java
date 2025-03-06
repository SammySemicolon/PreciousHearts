package com.sleepless_den.magic_magic_damage.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow public abstract int getRemainingFireTicks();

    @ModifyExpressionValue(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isInLava()Z", ordinal = 0))
    private boolean magicMagicDamage$modifyFireDamageFrequency(boolean original) {
        return original || getRemainingFireTicks() % 40 == 0;
    }

    @ModifyArg(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 1)
    private float magicMagicDamage$modifyFireDamage(float amount) {
        return amount * 2f;
    }
}