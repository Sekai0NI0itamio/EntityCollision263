package itamio.entitycollision.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Port of CorgiTaco's MixinLivingEntity: skip client-side wall checks and
 * entity pushing (Mojang bug MC-228976) so client-side collision checks don't
 * run on the render thread.
 */
@Mixin(LivingEntity.class)
public class LivingEntityCollisionMixin {

    @Inject(method = "isInWall", at = @At("HEAD"), cancellable = true)
    private void entitycollision$noClientWallCheck(CallbackInfoReturnable<Boolean> cir) {
        if (((Entity) (Object) this).level().isClientSide()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "pushEntities", at = @At("HEAD"), cancellable = true)
    private void entitycollision$noPushEntitiesClient(CallbackInfo ci) {
        if (((Entity) (Object) this).level().isClientSide()) {
            ci.cancel();
        }
    }
}
