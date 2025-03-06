package com.sleepless_den.precious_hearts.mixin;

import com.sleepless_den.precious_hearts.heart_gain.conditions.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.server.level.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.function.*;

@Mixin(SimpleCriterionTrigger.class)
public class SimpleCriterionTriggerMixin {

    @Inject(method = "trigger", at = @At("HEAD"))
    private<T extends SimpleCriterionTrigger.SimpleInstance> void preciousHearts$trigger(ServerPlayer player, Predicate<T> testTrigger, CallbackInfo ci) {
        CompleteAdvancementCondition.trigger((SimpleCriterionTrigger<T>) (Object) this, player, testTrigger);
    }
}
