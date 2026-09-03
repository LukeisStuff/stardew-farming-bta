package luke.stardew.mixin.coffee;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import luke.stardew.StardewMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.hud.component.HudComponent;
import net.minecraft.client.gui.hud.component.layout.LayoutSnap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;

import java.util.Random;

@Mixin(value = LayoutSnap.class)
public class LayoutSnapMixin {
    @Unique
    private final Random rand = new Random();

    @WrapMethod(method = "getComponentX")
    private int getCompX(HudComponent component, int xSizeScreen, Operation<Integer> original) {
        final var minecraft = Minecraft.getMinecraft();
        var hasEffect = (IHasEffects<?>) minecraft.thePlayer;

        if (hasEffect.getContainer().hasAttribute(StardewMod.TWEAK_SPEED_ATTRIBUTE)) {
            final var tweaking = StardewMod.TWEAK_SPEED_ATTRIBUTE.calculate(hasEffect);
            final double pos = (original.call(component, xSizeScreen) + (((double) tweaking / 2) * rand.nextFloat() * (rand.nextBoolean() ? 1 : -1)));
            return (int) Math.floor(pos);
        }

        return original.call(component, xSizeScreen);
    }

}

