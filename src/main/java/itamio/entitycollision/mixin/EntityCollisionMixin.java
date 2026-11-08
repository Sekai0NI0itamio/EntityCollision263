package itamio.entitycollision.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Port of CorgiTaco's MixinEntity: skip client-side wall-collision checks
 * (Mojang bug MC-228976). In 1.20.1, Entity.level is private with a
 * public level() accessor; Level.isClientSide is a field.
 */
@Mixin(Entity.class)
public abstract class EntityCollisionMixin {
    @Shadow
    public abstract Level level();

    @Inject(method = "isInWall", at = @At("HEAD"), cancellable = true)
    private void entitycollision263$noClientWallCheck(CallbackInfoReturnable<Boolean> cir) {
        if (this.level().isClientSide) {
            cir.setReturnValue(false);
        }
    }
}
