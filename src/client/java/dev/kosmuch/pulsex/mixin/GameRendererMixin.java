package dev.kosmuch.pulsex.mixin;

import dev.kosmuch.pulsex.config.PulseConfig;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Inject(method = "tiltViewWhenHurt", at = @At("HEAD"), cancellable = true)
    private void pulseX$disableHurtTilt(MatrixStack matrices, float tickProgress, CallbackInfo callback) {
        if (PulseConfig.isNoCameraShake()) callback.cancel();
    }

    @Inject(method = "bobView", at = @At("HEAD"), cancellable = true)
    private void pulseX$disableViewBobbing(MatrixStack matrices, float tickProgress, CallbackInfo callback) {
        if (PulseConfig.isNoCameraShake()) callback.cancel();
    }
}
