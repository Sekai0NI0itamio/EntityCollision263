package itamio.entitycollision.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Port of CorgiTaco's MixinLivingEntity: skip client-side wall checks
 * and entity pushing (MC-228976). Extends EntityCollisionMixin to
 * inherit its shadowed level() accessor.
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityCollisionMixin extends EntityCollisionMixin {
    @Inject(method = "isInWall", at = @At("HEAD"), cancellable = true)
    private void entitycollision263$noClientWallCheck(CallbackInfoReturnable<Boolean> cir) {
        if (this.level().isClientSide) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "pushEntities", at = @At("HEAD"), cancellable = true)
    private void entitycollision263$noPushEntitiesClient(CallbackInfo ci) {
        if (this.level().isClientSide) {
            ci.cancel();
        }
    }
}
