package com.sleepless_den.precious_hearts.client;

import com.mojang.blaze3d.systems.*;
import com.sleepless_den.precious_hearts.*;
import com.sleepless_den.precious_hearts.registry.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.player.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.player.*;
import net.neoforged.neoforge.client.event.*;
import org.joml.*;
import org.lwjgl.opengl.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.shader.*;

import java.lang.Math;

public class DeathAbsorptionRenderHandler {
    public static int fadeOut = 40;
    public static int glow;

    public static void tick(ClientTickEvent event) {
        final LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            var data = player.getData(PreciousHeartsAttachmentTypes.DEATH_ABSORPTION_DATA);
        }
    }

    public static void renderDeathAbsorption(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        var minecraft = Minecraft.getInstance();
        var poseStack = guiGraphics.pose();
        if (!minecraft.options.hideGui) {
            var player = minecraft.player;
            if (!player.isCreative() && !player.isSpectator()) {
                double limit = player.getAttribute(PreciousHeartsAttributes.DEATH_ABSORPTION_LIMIT).getValue() / 2;
                var data = player.getData(PreciousHeartsAttachmentTypes.DEATH_ABSORPTION_DATA);
                int deathAbsorption = data.getDeathAbsorption();
                int left = guiGraphics.guiWidth() / 2 - 91;
                int top = guiGraphics.guiHeight() - minecraft.gui.leftHeight - 2;
                int offset = Mth.floor(deathAbsorption / 20f) * 8;
                minecraft.gui.leftHeight += offset;
                poseStack.pushPose();
                RenderSystem.setShaderTexture(0, getStaffChargeTexture());
                RenderSystem.depthMask(true);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                ExtendedShaderInstance shaderInstance = (ExtendedShaderInstance) LodestoneShaders.SCREEN_DISTORTED_TEXTURE.getInstance().get();
                shaderInstance.safeGetUniform("YFrequency").set(15f);
                shaderInstance.safeGetUniform("XFrequency").set(15f);
                shaderInstance.safeGetUniform("Speed").set(550f);
                shaderInstance.safeGetUniform("Intensity").set(120f);
                var builder = VFXBuilders.createScreen().setShader(() -> shaderInstance);

                int size = 13;
                int ticker = deathAbsorption;
                for (int i = 0; i < limit; i++) {
                    int row = (int) (i / 10f);
                    int x = left + (i % 10) * 8;
                    int y = top - row * 4;

                    int xTextureOffset = 31;
                    if (ticker > 1) {
                        xTextureOffset -= 15;
                    }
                    if (ticker > 0) {
                        xTextureOffset -= 15;
                    }
                    ticker -= 2;
                    shaderInstance.safeGetUniform("UVCoordinates").set(new Vector4f(xTextureOffset / 45f, (xTextureOffset + size) / 45f, 0, 15 / 45f));
                    shaderInstance.safeGetUniform("TimeOffset").set(i * 150f);

                    builder.setPositionWithWidth(x - 2, y - 2, size, size).setUVWithWidth(xTextureOffset, 0, size, size, 45);
                    builder.blit(poseStack);
                    if (glow > 0 && glow < 20) {
                        float alpha = (10 - Math.abs(10 - glow)) / 10f;
                        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                        builder.setAlpha(alpha).blit(poseStack).setAlpha(1);
                        RenderSystem.defaultBlendFunc();
                    }
                }
                shaderInstance.setUniformDefaults();
                RenderSystem.disableBlend();
                poseStack.popPose();
            }
        }
    }

    public static ResourceLocation getStaffChargeTexture() {
        return PreciousHeartsMod.path("textures/death_absorption.png");
    }
}