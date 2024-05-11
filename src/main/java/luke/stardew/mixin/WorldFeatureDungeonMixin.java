package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.generate.feature.WorldFeatureDungeon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(value = WorldFeatureDungeon.class, remap = false)
public class WorldFeatureDungeonMixin {
	@Inject(method = "pickCheckLootItem(Ljava/util/Random;)Lnet/minecraft/core/item/ItemStack;", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"), cancellable = true)
	private void addLoot(Random random, CallbackInfoReturnable<ItemStack> cir){
		if (random.nextInt(16) == 0 && random.nextInt(10) == 0){
			cir.setReturnValue(new ItemStack(StardewBlocks.saplingApple, random.nextInt(1) + 1));
		}
	}
}
