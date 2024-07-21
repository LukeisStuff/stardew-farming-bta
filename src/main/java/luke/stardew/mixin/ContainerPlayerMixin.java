package luke.stardew.mixin;

import luke.stardew.items.ItemFruit;
import luke.stardew.items.StardewItems;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.ContainerPlayer;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.InventoryCrafting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ContainerPlayer.class, remap = false)
public abstract class ContainerPlayerMixin extends Container{

	@Shadow
	public IInventory craftResult;
	@Shadow
	public InventoryCrafting craftMatrix;

	@Inject(method = "onCraftMatrixChanged", at = @At(value = "TAIL"))
	private void setInv(IInventory iinventory, CallbackInfo ci){
		craftResult.setInventorySlotContents(0, Registries.RECIPES.findMatchingRecipe(craftMatrix));
		if (Registries.RECIPES.findMatchingRecipe(craftMatrix) != null && craftResult.getStackInSlot(0).itemID == StardewItems.jarJam.id && !craftResult.getStackInSlot(0).getData().containsKey("itemIds")) {
			setDescription(craftResult.getStackInSlot(0));
		}
	}

	@Unique
	private void setDescription(ItemStack stackResult){
		StringBuilder itemIds = new StringBuilder();

		for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
			ItemStack stackCurrent = craftMatrix.getStackInSlot(i);

			if (stackCurrent != null){
				if (stackCurrent.getItem() instanceof ItemFruit) {
					itemIds.append(stackCurrent.getItem().id).append('#');
				}
			}
		}
		stackResult.getData().putString("itemIds", itemIds.toString());
	}
}
