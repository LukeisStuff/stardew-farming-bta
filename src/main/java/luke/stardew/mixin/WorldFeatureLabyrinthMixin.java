package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.generate.feature.WorldFeatureLabyrinth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(value = WorldFeatureLabyrinth.class, remap = false)
public abstract class WorldFeatureLabyrinthMixin {

    @Unique
    private boolean isHot = false;

    @Inject(method = "place", at = @At("HEAD"))
    private void beforePlace(World world, Random random, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        Biome biome = world.getBlockBiome(x, y, z);
        isHot = biome == Biomes.OVERWORLD_DESERT || biome == Biomes.OVERWORLD_OUTBACK || biome == Biomes.OVERWORLD_OUTBACK_GRASSY;
    }

    @Inject(method = "pickCheckLootItem", at = @At("HEAD"), cancellable = true)
    private void pickAppleSapling(Random random, CallbackInfoReturnable<ItemStack> cir) {
        if (random.nextInt(160) == 0) {
            cir.setReturnValue(new ItemStack(StardewBlocks.SAPLING_APPLE));
        }
    }

    @Inject(method = "pickCheckLootItem", at = @At("RETURN"), cancellable = true)
    private void pickHotTreasure(Random random, CallbackInfoReturnable<ItemStack> cir) {
        if (isHot) {
            cir.setReturnValue(new ItemStack(StardewItems.ARMOR_CAN_WORMS_GOLD));
        }
    }

}
