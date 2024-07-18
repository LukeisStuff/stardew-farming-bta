package luke.stardew.mixin;

import luke.stardew.items.ItemFruit;
import luke.stardew.items.ItemJam;
import luke.stardew.items.StardewItems;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.ContainerWorkbench;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.InventoryCrafting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = ContainerWorkbench.class, remap = false)
public abstract class ContainerWorkbenchMixin {

	@Shadow
	public IInventory craftResult;

	@Shadow
	public InventoryCrafting craftMatrix;

	@Inject(method = "onCraftMatrixChanged", at = @At(value = "TAIL"))
	private void gettingDescription(CallbackInfo ci){
		ItemStack stack = craftResult.getStackInSlot(0);
		if (stack != null){
			if (stack.itemID == StardewItems.jarJam.id) {
				List<Item> items = new ArrayList<>();

				for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
					if (craftMatrix.getStackInSlot(i) != null){
						if (craftMatrix.getStackInSlot(i).getItem() instanceof ItemFruit) {
							items.add(craftMatrix.getStackInSlot(i).getItem());
						} //TODO BlockFruit
					}
				}
				((ItemJam) stack.getItem()).setIngredients(items);
				System.out.println(stack.getItemDescription());
			}
		}
	}
}
