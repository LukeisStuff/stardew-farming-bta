package luke.stardew.mixin;

import luke.stardew.StardewAchievements;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.slot.SlotCrafting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SlotCrafting.class, remap = false)
public class SlotCraftingMixin {
	@Shadow
	private EntityPlayer thePlayer;
	@Inject(method = "onPickupFromSlot(Lnet/minecraft/core/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/item/ItemStack;onCrafting(Lnet/minecraft/core/world/World;Lnet/minecraft/core/entity/player/EntityPlayer;)V", shift = At.Shift.AFTER))
	private void addCraftingAchievements(ItemStack itemstack, CallbackInfo ci){
		if (itemstack.itemID == StardewBlocks.saplingAppleGolden.id) {
			thePlayer.addStat(StardewAchievements.GAPPLE, 1);
		}
		if (itemstack.itemID == StardewBlocks.candle.id) {
			thePlayer.addStat(StardewAchievements.CANDLE, 1);
		}
	}
}
