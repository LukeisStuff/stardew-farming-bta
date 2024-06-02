package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.generate.feature.WorldFeatureLabyrinth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(value = WorldFeatureLabyrinth.class, remap = false)
public class WorldFeatureLabyrinthMixin {
	@Shadow
	private int wallBlockA;

	@Shadow
	private boolean treasureGenerated;

	@Shadow
	private int dungeonSize;

	@Inject(method = "pickCheckLootItem(Ljava/util/Random;)Lnet/minecraft/core/item/ItemStack;", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"), cancellable = true)
	private void addLoot(Random random, CallbackInfoReturnable<ItemStack> cir){
		if (random.nextInt(16) == 0 && random.nextInt(10) == 0){
			cir.setReturnValue(new ItemStack(StardewBlocks.saplingApple, random.nextInt(1) + 1));
		}
		if (wallBlockA == Block.sandstone.id && !treasureGenerated && dungeonSize > 7){
			cir.setReturnValue(new ItemStack(StardewItems.armorCanOfWormsGolden));
		}
	}
}
