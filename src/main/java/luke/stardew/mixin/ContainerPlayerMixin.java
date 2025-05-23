package luke.stardew.mixin;

import luke.stardew.StardewMod;
import luke.stardew.items.ItemFruit;
import luke.stardew.items.StardewItems;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.Container;
import net.minecraft.core.player.inventory.container.ContainerCrafting;
import net.minecraft.core.player.inventory.menu.MenuAbstract;
import net.minecraft.core.player.inventory.menu.MenuInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;

@Mixin(value = MenuInventory.class, remap = false)
public abstract class ContainerPlayerMixin extends MenuAbstract {

	@Shadow
	public Container resultSlots;
	@Shadow
	public ContainerCrafting craftSlots;

	@Inject(method = "slotsChanged", at = @At(value = "TAIL"))
	private void setInv(Container container, CallbackInfo ci){
		//resultSlots.setItem(0, Registries.RECIPES.findMatchingRecipe(craftSlots));
		if (Registries.RECIPES.findMatchingRecipe(craftSlots) != null && resultSlots.getItem(0).itemID == StardewItems.jarJam.id && !resultSlots.getItem(0).getData().containsKey("itemIds")) {
			setDescription(resultSlots.getItem(0));
		}
	}

	@Unique
	private void setDescription(ItemStack stackResult){
		StringBuilder itemIds = new StringBuilder();
		Set<Item> itemSet = new HashSet<>();

		for (int i = 0; i < craftSlots.getContainerSize(); i++) {
			ItemStack stackCurrent = craftSlots.getItem(i);
			if (stackCurrent != null){
				Item item = stackCurrent.getItem();
				if (item.hasTag(StardewItems.IS_FRUIT)) {
					String key = item.getKey();

					if (!itemSet.contains(item))  {
						itemIds.append(key).append(", ");
						itemSet.add(item);
					}
				}
			}
		}
		stackResult.getData().putString("itemIds", itemIds.toString());
	}
}
