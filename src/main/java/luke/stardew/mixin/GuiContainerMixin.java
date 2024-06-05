package luke.stardew.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import luke.stardew.items.StardewItems;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.ContainerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiContainer.class, remap = false)
public class GuiContainerMixin extends GuiScreen {
	@Shadow
	public Container inventorySlots;

	@Inject(method = "clickInventory(III)V", at = @At(value = "FIELD", target = "Lnet/minecraft/client/entity/player/EntityPlayerSP;inventory:Lnet/minecraft/core/player/inventory/InventoryPlayer;", ordinal = 0, shift = At.Shift.AFTER))
	private void addCanOfWormsShiftClick(CallbackInfo ci, @Local Item itemInSlot, @Local(ordinal = 4) LocalIntRef target){
		if (this.inventorySlots instanceof ContainerPlayer && (itemInSlot == StardewItems.armorCanOfWormsGolden || itemInSlot == StardewItems.armorCanOfWorms)) {
			target.set(2);
		}
	}
}
