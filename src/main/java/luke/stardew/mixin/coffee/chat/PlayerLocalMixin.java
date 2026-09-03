package luke.stardew.mixin.coffee.chat;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import luke.stardew.StardewMod;
import luke.stardew.misc.TweakMyMessage;
import net.minecraft.client.entity.player.PlayerLocal;
import org.spongepowered.asm.mixin.Mixin;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;

@Mixin(value = PlayerLocal.class)
public class PlayerLocalMixin {

    @WrapMethod(method = "sendChatMessage")
    private void tweakSend(String s, Operation<Void> original) {
        var hasEffect = (IHasEffects<?>) this;
        original.call( hasEffect.getContainer().hasAttribute(StardewMod.TWEAK_SPEED_ATTRIBUTE) && StardewMod.TWEAK_SPEED_ATTRIBUTE.calculate(hasEffect) > 1 ? TweakMyMessage.tweakMessage(s) : s);
    }

}
