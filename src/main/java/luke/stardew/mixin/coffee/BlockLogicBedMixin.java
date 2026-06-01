package luke.stardew.mixin.coffee;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import luke.stardew.StardewMod;
import net.minecraft.core.block.BlockLogicBed;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumSleepStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;

@Mixin(value = BlockLogicBed.class)
public class BlockLogicBedMixin {

    @Definition(id = "sleepInBedAt", method = "Lnet/minecraft/core/entity/player/Player;sleepInBedAt(III)Lnet/minecraft/core/enums/EnumSleepStatus;")
    @Expression("?.sleepInBedAt(?, ?, ?)")
    @WrapOperation(method = "onInteracted", at = @At("MIXINEXTRAS:EXPRESSION"))
    private EnumSleepStatus canSleep(Player player, int x, int y, int z, Operation<EnumSleepStatus> original) {
        final var tweak = StardewMod.TWEAK_SPEED_ATTRIBUTE.calculate((IHasEffects<?>) player);

        if (tweak > 1) {
            player.sendStatusMessageTranslated("messages.bed.noAllowSleeping");
            return EnumSleepStatus.OTHER_PROBLEM;
        }

        return original.call(player, x, y, z);
    }

}
