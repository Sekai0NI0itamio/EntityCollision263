package itamio.entitycollision.mixin;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Port of CorgiTaco's MixinEntity: skip client-side wall-collision checks
 * (Mojang bug MC-228976) to avoid doing entity collision work on the render
 * thread. In 26.3 {@code Entity.level} is a {@code level()} accessor and
 * {@code Level.isClientSide} is an {@code isClientSide()} method.
 */
@Mixin(Entity.class)
public class EntityCollisionMixin {

    @Inject(method = "isInWall", at = @At("HEAD"), cancellable = true)
    private void entitycollision263$noClientWallCheck(CallbackInfoReturnable<Boolean> cir) {
        if (((Entity) (Object) this).level().isClientSide()) {
            cir.setReturnValue(false);
        }
    }
}
