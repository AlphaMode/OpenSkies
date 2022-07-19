package me.alphamode.exnihiloabsentia.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.math.Matrix4f;
import me.alphamode.exnihiloabsentia.client.renderers.SkyblockSkyRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

/**
 * This Mixin implements the Garden of Glass skybox and some in-world overlays
 */
@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @Shadow
    @Nullable
    private VertexBuffer starBuffer;

    @Shadow
    @Final
    private RenderBuffers renderBuffers;
    @Shadow
    @Nullable
    private ClientLevel level;
    @Unique
    private static final Matrix4f SUN_SCALE = Matrix4f.createScaleMatrix(2F, 1F, 2F);

    @Unique
    private static final Matrix4f MOON_SCALE = Matrix4f.createScaleMatrix(1.5F, 1F, 1.5F);

    @Unique
    private static boolean isGogSky() {
        Level world = Minecraft.getInstance().level;
        return world.dimension() == Level.OVERWORLD;
    }

    /**
     * Render planets and other extras, after the first invoke to ms.rotate(Y) after getRainStrength is called
     */
    @Inject(
            method = "renderSky",
            slice = @Slice(
                    from = @At(
                            ordinal = 0, value = "INVOKE",
                            target = "Lnet/minecraft/client/multiplayer/ClientLevel;getRainLevel(F)F"
                    )
            ),
            at = @At(
                    shift = At.Shift.AFTER,
                    ordinal = 0,
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/PoseStack;mulPose(Lcom/mojang/math/Quaternion;)V"
            ),
            require = 0
    )
    private void renderExtras(PoseStack ms, Matrix4f projMat, float partialTicks, Camera camera,
                              boolean foggy, Runnable resetFog, CallbackInfo ci) {
        if (isGogSky()) {
            SkyblockSkyRenderer.renderExtra(ms, Minecraft.getInstance().level, partialTicks, 0);
        }
    }

    /**
     * Make the sun bigger by scaling it to double size
     */
    @ModifyVariable(
            method = "renderSky",
            slice = @Slice(
                    from = @At(ordinal = 1, value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;getTimeOfDay(F)F"),
                    to = @At(ordinal = 0, value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V")
            ),
            at = @At(value = "CONSTANT", args = "floatValue=30.0"),
            ordinal = 1,
            require = 0
    )
    private Matrix4f makeSunBigger(Matrix4f matrix) {
        if (isGogSky()) {
            matrix = matrix.copy();
            matrix.multiply(SUN_SCALE);
        }
        return matrix;
    }

    /**
     * Make the moon bigger by scaling it to triple size (the matrix is already scaled on the call above)
     */
    @ModifyVariable(
            method = "renderSky",
            slice = @Slice(
                    from = @At(ordinal = 0, value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V"),
                    to = @At(ordinal = 1, value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V")
            ),
            at = @At(value = "CONSTANT", args = "floatValue=20.0"),
            ordinal = 1,
            require = 0
    )
    private Matrix4f makeMoonBigger(Matrix4f matrix) {
        if (isGogSky()) {
            matrix.multiply(MOON_SCALE);
        }
        return matrix;
    }

    /**
     * Render lots of extra stars
     */
    @Inject(
            method = "renderSky",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;getStarBrightness(F)F"),
            require = 0
    )
    private void renderExtraStars(PoseStack ms, Matrix4f projMat, float partialTicks, Camera camera,
                                  boolean foggy, Runnable resetFog, CallbackInfo ci) {
        if (isGogSky()) {
            SkyblockSkyRenderer.renderStars(starBuffer, ms, projMat, partialTicks, resetFog);
        }
    }
}