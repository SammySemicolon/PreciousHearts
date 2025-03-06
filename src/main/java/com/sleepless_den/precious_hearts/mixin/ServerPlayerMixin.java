package com.sleepless_den.precious_hearts.mixin;

import com.mojang.authlib.*;
import com.sleepless_den.precious_hearts.heart_gain.conditions.*;
import net.minecraft.server.*;
import net.minecraft.server.level.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void preciousHearts$setupFakeAdvancements(MinecraftServer server, ServerLevel level, GameProfile gameProfile, ClientInformation clientInformation, CallbackInfo ci) {
         CompleteAdvancementCondition.createFakeAdvancements((ServerPlayer) (Object) this);
    }
}
