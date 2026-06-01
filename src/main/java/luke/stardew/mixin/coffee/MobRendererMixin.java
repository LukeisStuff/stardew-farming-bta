package luke.stardew.mixin.coffee;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import luke.stardew.StardewMod;
import net.minecraft.client.render.entity.MobRendererBiped;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.useless.dragonfly.models.entity.StaticEntityModel;
import org.useless.dragonfly.models.entity.mojang.StaticEntityModelMojang;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;

import java.util.Random;

@Mixin(MobRendererBiped.class)
public class MobRendererMixin<T extends Mob> {

    private final Random rand = new Random();

    @WrapMethod(method = "setupAnimations")
    private @Nullable StaticEntityModel tweak(@NotNull T entity, @Nullable StaticEntityModel model, float partialTick, int layer, Operation<StaticEntityModel> original) {
        final var outModel = original.call(entity, model, partialTick, layer);

        final var tweak = StardewMod.TWEAK_SPEED_ATTRIBUTE.calculate(((IHasEffects<?>) entity));


        if (tweak > 0 && outModel instanceof StaticEntityModelMojang modelMojang) {
            for (var bone : modelMojang.bones) {
                var transform = outModel.getTransform(bone.bone().name);
                transform.posX += ((double) tweak /6) * (rand.nextFloat() * (rand.nextBoolean() ? 1 : -1));
            }
        }

        return outModel;
    }

}
