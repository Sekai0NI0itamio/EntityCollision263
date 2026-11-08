# Entity Collision FPS Fix — Modern Port for Minecraft 26.3 (Fabric)

![Mod loader: Fabric](https://img.shields.io/badge/modloader-Fabric-1976d2?style=for-the-badge)
![Minecraft: 26.3](https://img.shields.io/badge/Minecraft-26.3-green?style=for-the-badge)
![GitHub license](https://img.shields.io/github/license/Sekai0NI0itamio/EntityCollision263?style=for-the-badge)

**Removes client-side entity collision checks and boosts your FPS.**

In vanilla Minecraft, entities on the client run collision checks
(`isInWall`, entity pushing) on the render thread — a known Mojang bug
([MC-228976](https://bugs.mojang.com/browse/MC-228976)) that wastes
performance every frame, especially in crowded areas with lots of entities.

This mod simply skips those client-side checks. Server-side behaviour —
what actually matters for gameplay — is completely unchanged. The result:
smoother FPS in farms, villages, and anywhere entities pile up.

Now with a **modern port for Minecraft 26.3 (Fabric)**, updated for
26.3's accessor-based level API (`level().isClientSide()`).

### What it does

- `Entity.isInWall()` returns `false` immediately on the client
- `LivingEntity.isInWall()` returns `false` immediately on the client
- `LivingEntity.pushEntities()` is cancelled on the client
- No server-side changes at all — safe for multiplayer

### Modern port notes (26.3)

- Two tiny client-only mixins, same behaviour as the original
- Java 25 · Fabric Loader 0.20.1+ · Fabric API 0.165.0+26.3
- Built and verified in CI via GitHub Actions

### Installation

1. Install Fabric Loader for Minecraft 26.3
2. Install Fabric API
3. Drop `entitycollision263-*.jar` into your `mods` folder

Client-side only — you don't need it on the server.

### Original mod & credit

This is an unofficial modern port by **Itamio** of **Entity Collision FPS
Fix** by **CorgiTaco** — the original (1.3M+ downloads on Modrinth, last
updated for 1.19.2) has not been updated past 1.19.2.

- Original mod: https://modrinth.com/mod/entity-collision-fps-fix
- Original source: https://github.com/CorgiTaco/No-Client-Side-Entity-Collision-Checks
- License: CC0-1.0 — see `LICENSE`

All credit for the original concept, design, and logic goes to CorgiTaco
and the original authors. This port exists to bring it to Minecraft 26.3.
See `NOTICE`.
