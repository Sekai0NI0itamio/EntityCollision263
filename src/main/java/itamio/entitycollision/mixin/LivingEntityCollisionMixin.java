package itamio.entitycollision.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Port of CorgiTaco's MixinLivingEntity: skip client-side wall checks
 * and entity pushing (MC-228976). In 1.20.1, Level.isClientSide is a field.
 */
@Mixin(LivingEntity.class)
public class LivingEntityCollisionMixin {
    @Shadow
    public Level level;

    @Inject(method = "isInWall", at = @At("HEAD"), cancellable = true)
    private void entitycollision263$noClientWallCheck(CallbackInfoReturnable<Boolean> cir) {
        if (this.level.isClientSide) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "pushEntities", at = @At("HEAD"), cancellable = true)
    private void entitycollision263$noPushEntitiesClient(CallbackInfo ci) {
        if (this.level.isClientSide) {
            ci.cancel();
        }
    }
}
