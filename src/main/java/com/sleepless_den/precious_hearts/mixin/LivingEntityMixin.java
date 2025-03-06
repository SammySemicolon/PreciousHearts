package com.sleepless_den.precious_hearts.mixin;

import com.sleepless_den.precious_hearts.heart_gain.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.food.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "eat(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/ItemStack;", at = @At("HEAD"))
    private void malum$eat(Level level, ItemStack food, FoodProperties foodProperties, CallbackInfoReturnable<ItemStack> cir) {
        LivingEntity livingEntity = (LivingEntity) ((Object)(this));
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            if (food.getFoodProperties(livingEntity) != null) {
                HeartGainHandler.rewardItemEating(level, serverPlayer, food);
            }
        }
    }
}