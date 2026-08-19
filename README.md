# Render Distance Mod

NeoForge 1.21.1 mod that raises vanilla **32-chunk** caps for client render/simulation distance and server view/tracking distance.

Vanilla Minecraft clamps several distance values at 32 chunks. This mod lifts those limits up to a configurable cap (default **64**, range **32–256**).

## Requirements

- Minecraft **1.21.1**
- NeoForge **21.1.225+**
- Java **21**

## Features

- Client render distance and simulation distance sliders accept values above 32.
- Server view distance and player chunk-ticket tracking follow the same cap.
- High-distance chunk task levels are clamped so the server does not crash.
- Optional [Sodium](https://modrinth.com/mod/sodium) support: video-settings sliders use the same cap.
- Optional compatibility with Sable Physics nearby chunk loading.

Put the same version of the mod on the **client and the dedicated server**. A higher client slider still cannot load more chunks than the server is willing to send.

## Installation

1. Install NeoForge 21.1.x for Minecraft 1.21.1.
2. Download the jar from [Releases](https://github.com/Dead4W/entity_render_distance_mod/releases) or build it locally.
3. Place `renderdistancemod-<version>.jar` into the `mods` folder.
4. Launch the game and raise **Options → Video Settings → Render Distance** / **Simulation Distance**.

## Configuration

File: `config/renderdistancemod-common.toml`

| Option     | Default | Range   | Description                                      |
| ---------- | ------- | ------- | ------------------------------------------------ |
| `chunkCap` | `64`    | 32–256  | Maximum chunk distance for render and tracking.  |

Restart the game after changing the config so client option ranges reload.

High values cost CPU, RAM, and network bandwidth. Raise the cap gradually.

## Building

```bash
./gradlew build
```

The output jar is `build/libs/renderdistancemod-1.0.0.jar`.

CI builds on every push and pull request: [`.github/workflows/build.yml`](.github/workflows/build.yml).

## License

Mod source and binaries: **All Rights Reserved** © 2026 Dead4W. See [`LICENSE`](LICENSE).

NeoForge MDK template files remain under the MIT license in [`TEMPLATE_LICENSE.txt`](TEMPLATE_LICENSE.txt).
