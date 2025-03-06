package com.sleepless_den.magic_magic_damage.mixin;

import com.sleepless_den.magic_magic_damage.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(DamageSources.class)
public class DamageSourcesMixin {

    @Inject(method = "mobAttack", at = @At("HEAD"), cancellable = true)
    private void magicMagicDamage$mobAttack(LivingEntity mob, CallbackInfoReturnable<DamageSource> cir) {
        if (mob instanceof EnderMan || mob instanceof Endermite || mob instanceof Witch|| mob instanceof Phantom) { //TODO: convert this to an entity tag
            cir.setReturnValue(((DamageSources)((Object)this)).source(MagicMagicDamageDamageTypes.MAGIC_MOB_ATTACK, mob));
        }
    }
}
