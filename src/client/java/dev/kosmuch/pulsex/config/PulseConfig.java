package dev.kosmuch.pulsex.config;

import dev.kosmuch.pulsex.PulseXClient;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class PulseConfig {
    private static final Path CONFIG_FILE =
            FabricLoader.getInstance().getConfigDir().resolve("pulse_x.properties");

    private static boolean noCameraShake = false;
    private static boolean hideNauseaOverlay = false;
    private static boolean noBlindnessVignette = false;
    private static boolean noDarknessVignette = false;

    private PulseConfig() {
    }

    public static void load() {
        if (!Files.exists(CONFIG_FILE)) {
            save();
            return;
        }

        Properties properties = new Properties();
        try (Reader reader = Files.newBufferedReader(CONFIG_FILE, StandardCharsets.UTF_8)) {
            properties.load(reader);
            noCameraShake = Boolean.parseBoolean(properties.getProperty("noCameraShake", "false"));
            hideNauseaOverlay = Boolean.parseBoolean(properties.getProperty("hideNauseaOverlay", "false"));
            noBlindnessVignette = Boolean.parseBoolean(properties.getProperty("noBlindnessVignette", "false"));
            noDarknessVignette = Boolean.parseBoolean(properties.getProperty("noDarknessVignette", "false"));
        } catch (IOException exception) {
            PulseXClient.LOGGER.error("Could not load Pulse_X settings.", exception);
        }
    }

    public static void save() {
        Properties properties = new Properties();
        properties.setProperty("noCameraShake", Boolean.toString(noCameraShake));
        properties.setProperty("hideNauseaOverlay", Boolean.toString(hideNauseaOverlay));
        properties.setProperty("noBlindnessVignette", Boolean.toString(noBlindnessVignette));
        properties.setProperty("noDarknessVignette", Boolean.toString(noDarknessVignette));

        try {
            Files.createDirectories(CONFIG_FILE.getParent());
            try (Writer writer = Files.newBufferedWriter(CONFIG_FILE, StandardCharsets.UTF_8)) {
                properties.store(writer, "Pulse_X settings - Kosmuch");
            }
        } catch (IOException exception) {
            PulseXClient.LOGGER.error("Could not save Pulse_X settings.", exception);
        }
    }

    public static boolean isNoCameraShake() { return noCameraShake; }
    public static void setNoCameraShake(boolean value) { noCameraShake = value; }
    public static boolean isHideNauseaOverlay() { return hideNauseaOverlay; }
    public static void setHideNauseaOverlay(boolean value) { hideNauseaOverlay = value; }
    public static boolean isNoBlindnessVignette() { return noBlindnessVignette; }
    public static void setNoBlindnessVignette(boolean value) { noBlindnessVignette = value; }
    public static boolean isNoDarknessVignette() { return noDarknessVignette; }
    public static void setNoDarknessVignette(boolean value) { noDarknessVignette = value; }
}
