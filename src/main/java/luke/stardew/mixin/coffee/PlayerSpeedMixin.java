package luke.stardew.mixin.coffee;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import luke.stardew.StardewMod;
import net.minecraft.core.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;

@Mixin(value = Player.class)
public class PlayerSpeedMixin {

    @Definition(id = "baseSpeed", field = "Lnet/minecraft/core/entity/player/Player;baseSpeed:F")
    @Expression("this.baseSpeed")
    @ModifyExpressionValue(method = "onLivingUpdate", at = @At("MIXINEXTRAS:EXPRESSION"))
    float speedModify(float original) {
        var hasEffect = (IHasEffects<?>) this;

        if (hasEffect.getContainer().hasAttribute(StardewMod.TWEAK_SPEED_ATTRIBUTE)) {
            final var modifier = 1 + StardewMod.TWEAK_SPEED_ATTRIBUTE.calculate(hasEffect) * 0.30;
            return (float) (original * modifier);
        }

        return original;
    }
}
