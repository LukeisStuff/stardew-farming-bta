package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.WeightedRandomBag;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.generate.feature.WorldFeatureDungeon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(value = WorldFeatureDungeon.class, remap = false)
public class WorldFeatureDungeonMixin {
	@Shadow
	public WeightedRandomBag<WeightedRandomLootObject> chestLoot;

	@Inject(method = "<init>", at = @At(value = "TAIL"))
	private void addLoot(CallbackInfo ci){
		this.chestLoot.addEntry(new WeightedRandomLootObject(StardewBlocks.saplingApple.getDefaultStack()), 50);
	}
}
