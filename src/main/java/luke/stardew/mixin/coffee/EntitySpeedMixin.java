package luke.stardew.mixin.coffee;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import luke.stardew.StardewMod;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;

@Mixin(value = Mob.class)
public class EntitySpeedMixin {

    @Shadow
    protected float moveSpeed;
    @Unique
    float initialSpeed = 0;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(World world, CallbackInfo ci) {
        this.initialSpeed = this.moveSpeed;
    }

    @WrapMethod(method = "tick")
    private void tick(Operation<Void> original) {
        final var entity = ((IHasEffects<?>) this);

        if (entity.getContainer().hasAttribute(StardewMod.TWEAK_SPEED_ATTRIBUTE)) {
            this.moveSpeed = (float) (initialSpeed * (1 + (StardewMod.TWEAK_SPEED_ATTRIBUTE.calculate(entity) * 0.30)));
        }

        original.call();
    }

}
