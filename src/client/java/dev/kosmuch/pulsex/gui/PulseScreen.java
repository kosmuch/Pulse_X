package dev.kosmuch.pulsex.gui;

import dev.kosmuch.pulsex.config.PulseConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public final class PulseScreen extends Screen {
    private static final int BUTTON_WIDTH = 250;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_GAP = 25;

    public PulseScreen() {
        super(Text.translatable("screen.pulse_x.title"));
    }

    @Override
    protected void init() {
        int x = (this.width - BUTTON_WIDTH) / 2;
        int y = Math.max(76, this.height / 2 - 65);

        addToggle("pulse_x.option.no_camera_shake", PulseConfig::isNoCameraShake,
                PulseConfig::setNoCameraShake, x, y);
        y += BUTTON_GAP;
        addToggle("pulse_x.option.hide_nausea", PulseConfig::isHideNauseaOverlay,
                PulseConfig::setHideNauseaOverlay, x, y);
        y += BUTTON_GAP;
        addToggle("pulse_x.option.no_blindness_vignette", PulseConfig::isNoBlindnessVignette,
                PulseConfig::setNoBlindnessVignette, x, y);
        y += BUTTON_GAP;
        addToggle("pulse_x.option.no_darkness_vignette", PulseConfig::isNoDarknessVignette,
                PulseConfig::setNoDarknessVignette, x, y);

        addDrawableChild(ButtonWidget.builder(Text.translatable("gui.done"), button -> close())
                .dimensions(x, y + BUTTON_GAP + 8, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());
    }

    private void addToggle(String translationKey, BooleanSupplier getter, Consumer<Boolean> setter, int x, int y) {
        ButtonWidget button = ButtonWidget.builder(toggleText(translationKey, getter.getAsBoolean()), widget -> {
            boolean enabled = !getter.getAsBoolean();
            setter.accept(enabled);
            PulseConfig.save();
            widget.setMessage(toggleText(translationKey, enabled));
        }).dimensions(x, y, BUTTON_WIDTH, BUTTON_HEIGHT).build();
        addDrawableChild(button);
    }

    private Text toggleText(String translationKey, boolean enabled) {
        return Text.translatable(translationKey)
                .append(Text.literal(": "))
                .append(Text.translatable(enabled ? "pulse_x.state.on" : "pulse_x.state.off"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        context.fill(this.width / 2 - 165, 25, this.width / 2 + 165, this.height - 25, 0xB8101420);
        context.drawCenteredTextWithShadow(this.textRenderer,
                Text.translatable("screen.pulse_x.title"), this.width / 2, 39, 0xFFFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer,
                Text.translatable("screen.pulse_x.subtitle"), this.width / 2, 54, 0xFFB8C6DC);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(null);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
