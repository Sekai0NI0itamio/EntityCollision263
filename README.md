# Entity Collision FPS Fix (26.3, Fabric)

Client-side entity collision checks removed to boost FPS. Unofficial port for
Minecraft 26.3 (Fabric) of "Entity Collision FPS Fix" by CorgiTaco.

- Original: CorgiTaco, Modrinth https://modrinth.com/mod/entity-collision-fps-fix
- Source: https://github.com/CorgiTaco/No-Client-Side-Entity-Collision-Checks
- License: CC0-1.0 (see `LICENSE`)
- Fixes Mojang bug MC-228976 (client-side entity collision checks running on
  the render thread)

## What it does

Two client-only mixins, ported from the original:

- `Entity.isInWall()` returns `false` immediately on the client.
- `LivingEntity.isInWall()` returns `false` immediately on the client, and
  `LivingEntity.pushEntities()` is cancelled on the client.

Server-side behavior is unchanged.

## 26.3 API notes

In 26.3, `Entity.level` is a `level()` accessor and `Level.isClientSide` is an
`isClientSide()` method, so the mixins obtain the level via
`((Entity) (Object) this).level().isClientSide()` instead of the original
`this.level.isClientSide` field shadow. Method names `isInWall()` and
`pushEntities()` are unchanged.

## Credit

All credit for the original concept, design, and logic goes to CorgiTaco and
the original authors. Code updated for Minecraft 26.3 by Itamio. See `NOTICE`.
