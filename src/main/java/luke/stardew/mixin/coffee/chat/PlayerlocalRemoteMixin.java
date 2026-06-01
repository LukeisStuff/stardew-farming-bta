package luke.stardew.mixin.coffee.chat;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import luke.stardew.StardewMod;
import luke.stardew.misc.TweakMyMessage;
import net.minecraft.client.entity.player.PlayerLocalMultiplayer;
import org.spongepowered.asm.mixin.Mixin;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;

@Mixin(PlayerLocalMultiplayer.class)
public class PlayerlocalRemoteMixin {

    @WrapMethod(method = "sendChatMessage")
    private void tweakSend(String s, Operation<Void> original) {
        original.call(StardewMod.TWEAK_SPEED_ATTRIBUTE.calculate((IHasEffects<?>) this) > 1 ? TweakMyMessage.tweakMessage(s) : s);
    }
}
