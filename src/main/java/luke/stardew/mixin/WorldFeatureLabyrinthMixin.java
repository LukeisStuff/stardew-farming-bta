package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.generate.feature.WorldFeatureLabyrinth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(value = WorldFeatureLabyrinth.class, remap = false)
public class WorldFeatureLabyrinthMixin {
	@Unique
	boolean isHot = false;

	@Shadow
	boolean treasureGenerated;

	@Shadow
	int dungeonSize;

	@Inject(method = "generate(Lnet/minecraft/core/world/World;Ljava/util/Random;III)Z", at = @At("HEAD"), cancellable = true)
	private void generate(World world, Random random, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
		Biome biome = world.getBlockBiome(x, y, z);
		if (biome == Biomes.OVERWORLD_DESERT || biome == Biomes.OVERWORLD_OUTBACK || biome == Biomes.OVERWORLD_OUTBACK_GRASSY) {
			this.isHot = true;
		}
	}

	@Inject(method = "pickCheckLootItem(Ljava/util/Random;)Lnet/minecraft/core/item/ItemStack;", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"), cancellable = true)
	private void addLoot(Random random, CallbackInfoReturnable<ItemStack> cir) {
		if (random.nextInt(16) == 0 && random.nextInt(10) == 0) {
			cir.setReturnValue(new ItemStack(StardewBlocks.saplingApple, random.nextInt(1) + 1));
		}
	}

	@Inject(method = "pickCheckLootItem(Ljava/util/Random;)Lnet/minecraft/core/item/ItemStack;", at = @At(value = "FIELD", target = "Lnet/minecraft/core/world/generate/feature/WorldFeatureLabyrinth;treasureGenerated:Z", ordinal = 1, shift = At.Shift.AFTER), cancellable = true)
	private void addTreasure(Random random, CallbackInfoReturnable<ItemStack> cir) {
		if (isHot) {
			cir.setReturnValue(new ItemStack(StardewItems.armorCanOfWormsGolden));
		}
	}

}
