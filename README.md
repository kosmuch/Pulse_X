# Pulse_X

**Pulse_X** is an independent client-side visual utility mod for **Minecraft 1.21.11 + Fabric**.

- **Author:** Kosmuch
- **Minecraft:** 1.21.11
- **Loader:** Fabric
- **Java:** 21
- **License:** MIT

Pulse_X is an original project. It is not affiliated with or a copy of PulseVisuals.

## Current features

Open the settings screen with **Right Shift**.

- **No Camera Shake** — skips hurt-camera tilt and view bobbing.
- **Hide Nausea Overlay** — hides the nausea HUD overlay.
- **No Blindness Vignette** — suppresses the HUD vignette while Blindness is active.
- **No Darkness Vignette** — suppresses the HUD vignette while Darkness is active.
- **Persistent settings** — options are saved in `config/pulse_x.properties`.

These are client-side visual changes only. They do not remove status effects, change server-side gameplay, or guarantee removal of every fog, lighting, or gameplay consequence caused by an effect. The blindness/darkness options target the HUD vignette specifically.

## Build

Requirements: JDK 21 and Gradle 9.8.2 or newer compatible with the configured Loom version.

```bash
gradle build
```

The remapped mod JAR is created in `build/libs/`. GitHub Actions also builds the project on pushes and stores the JAR files as a workflow artifact.

## Install

1. Build the project or download the JAR from the repository's Actions artifacts.
2. Put the remapped `pulse_x-*.jar` into the Minecraft `mods` folder.
3. Launch Minecraft 1.21.11 with Fabric Loader and Fabric API.

## Project information

Created by **Kosmuch**. Source code is distributed under the MIT License.
