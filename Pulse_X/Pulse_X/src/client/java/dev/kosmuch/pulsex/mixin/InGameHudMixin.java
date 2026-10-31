package dev.kosmuch.pulsex.mixin;

import dev.kosmuch.pulsex.config.PulseConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Inject(method = "renderNauseaOverlay", at = @At("HEAD"), cancellable = true)
    private void pulseX$hideNauseaOverlay(DrawContext context, float nauseaStrength, CallbackInfo callback) {
        if (PulseConfig.isHideNauseaOverlay()) callback.cancel();
    }

    @Inject(method = "renderVignetteOverlay", at = @At("HEAD"), cancellable = true)
    private void pulseX$hideNegativeEffectVignette(DrawContext context, Entity entity, CallbackInfo callback) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        if (PulseConfig.isNoBlindnessVignette() && player.hasStatusEffect(StatusEffects.BLINDNESS)) {
            callback.cancel();
            return;
        }
        if (PulseConfig.isNoDarknessVignette() && player.hasStatusEffect(StatusEffects.DARKNESS)) {
            callback.cancel();
        }
    }
}
