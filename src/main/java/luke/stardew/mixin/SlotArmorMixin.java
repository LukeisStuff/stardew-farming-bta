package luke.stardew.mixin;

import luke.stardew.items.StardewItems;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotArmor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SlotArmor.class, remap = false)
public class SlotArmorMixin extends Slot {
	@Shadow
	@Final
	private int armorType;

	public SlotArmorMixin(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}

	@Inject(method = "canPutStackInSlot", at = @At("HEAD"), cancellable = true)
	private void addEquipableCanOfWorms(ItemStack itemstack, CallbackInfoReturnable cir){
		if (itemstack.getItem().id == StardewItems.armorCanOfWorms.id || itemstack.getItem().id == StardewItems.armorCanOfWormsGolden.id) {
			cir.setReturnValue(armorType == 2);
		}
	}
}
