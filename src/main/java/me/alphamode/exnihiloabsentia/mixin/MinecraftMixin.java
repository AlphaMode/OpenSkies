package me.alphamode.exnihiloabsentia.mixin;

import me.alphamode.exnihiloabsentia.client.ClientTickHandler;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow public abstract boolean isPaused();

    @Shadow
    private float pausePartialTick;

    @Shadow
    public abstract float getFrameTime();

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GameRenderer;render(FJZ)V"))
    private void onFrameStart(boolean tick, CallbackInfo ci) {
        ClientTickHandler.renderTick(isPaused() ? pausePartialTick : getFrameTime());
    }
}
