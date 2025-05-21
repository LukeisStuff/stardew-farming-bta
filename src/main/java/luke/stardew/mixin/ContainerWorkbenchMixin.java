package luke.stardew.mixin;

import luke.stardew.items.ItemFruit;
import luke.stardew.items.StardewItems;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.Container;
import net.minecraft.core.player.inventory.container.ContainerCrafting;
import net.minecraft.core.player.inventory.menu.MenuAbstract;
import net.minecraft.core.player.inventory.menu.MenuCrafting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MenuCrafting.class, remap = false)
public abstract class ContainerWorkbenchMixin extends MenuAbstract {
	@Shadow
	public Container resultSlots;
	@Shadow
	public ContainerCrafting craftSlots;

	@Inject(method = "slotsChanged", at = @At(value = "TAIL"))
	private void setInv(Container container, CallbackInfo ci){
		craftSlots.setItem(0, Registries.RECIPES.findMatchingRecipe(craftSlots));
		if (Registries.RECIPES.findMatchingRecipe(craftSlots) != null && resultSlots.getItem(0).itemID == StardewItems.jarJam.id && !resultSlots.getItem(0).getData().containsKey("itemIds")) {
			setDescription(resultSlots.getItem(0));
		}
	}

	@Unique
	private void setDescription(ItemStack stackResult){
		StringBuilder itemIds = new StringBuilder();

		for (int i = 0; i < craftSlots.getContainerSize(); i++) {
			ItemStack stackCurrent = craftSlots.getItem(i);

			if (stackCurrent != null){
				if (stackCurrent.getItem() instanceof ItemFruit) {
					itemIds.append(stackCurrent.getItem().id).append('#');
				}
			}
		}
		stackResult.getData().putString("itemIds", itemIds.toString());
	}
}
