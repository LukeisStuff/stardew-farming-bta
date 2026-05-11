package luke.stardew.compat.aether.mixin;

import net.minecraft.core.data.DataLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamport.aether.AetherRecipes;

@Pseudo
@Mixin(value = AetherRecipes.class, remap = false)
public abstract class AetherRecipeStardewMixin {

    @Inject(method = "aetherMachinesRecipes()V", at = @At("TAIL"))
    private static void addIncubatorRecipes(CallbackInfo ci) {
        DataLoader.loadRecipesFromFile("/assets/stardew/recipes/incubator.json");
    }
}
