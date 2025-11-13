package luke.stardew.items;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.IArmorItem;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.player.inventory.slot.Slot;
import org.jetbrains.annotations.Nullable;


public class ItemCanOfWorms extends Item implements IArmorItem {
	public ItemCanOfWorms(String translationKey, String namespaceID, int id) {
		super(translationKey, namespaceID, id);
		this.setMaxStackSize(1);
		this.setMaxDamage(192);
	}

	@Override
	public boolean hasInventoryInteraction() {
		return true;
	}

	@Override
	public ItemStack onInventoryInteract(Player player, Slot slot, ItemStack stackInSlot, boolean isItemGrabbed) {
		ItemStack canItem = isItemGrabbed ? player.inventory.getHeldItemStack() : stackInSlot;
		int totalSpace = this.getMaxDamage();
		int wormCount = this.getWormCount(canItem);
		int freeSpace = totalSpace - wormCount;
		int amount;
		if (isItemGrabbed) {
			if (stackInSlot == null) {
				int amount2 = Math.min(16, wormCount);
				ItemStack arrowStack = new ItemStack(StardewItems.WORM, amount2, 0);
				if (amount2 > 0 && slot.mayPlace(arrowStack)) {
					this.setWormCount(canItem, wormCount - amount2);
					stackInSlot = arrowStack;
				}
			} else if (stackInSlot.itemID == StardewItems.WORM.id && (amount = Math.min(freeSpace, stackInSlot.stackSize)) > 0) {
				this.setWormCount(canItem, wormCount + amount);
				stackInSlot.stackSize -= amount;
			}
		} else {
			ItemStack grabbedItem = player.inventory.getHeldItemStack();
			if (grabbedItem != null && grabbedItem.itemID == StardewItems.WORM.id) {
				int amount3 = Math.min(grabbedItem.stackSize, freeSpace);
				if (amount3 > 0) {
					grabbedItem.stackSize -= amount3;
					this.setWormCount(canItem, wormCount + amount3);
					if (grabbedItem.stackSize <= 0) {
						player.inventory.setHeldItemStack(null);
					}
				}
			} else if (grabbedItem == null && (amount = Math.min(64, wormCount)) > 0) {
				this.setWormCount(canItem, wormCount - amount);
				player.inventory.setHeldItemStack(new ItemStack(StardewItems.WORM, amount, 0));
			}
		}
		return stackInSlot;
	}

	public int getWormCount(ItemStack stack) {
		return stack.getMaxDamage() - stack.getMetadata();
	}

	public void setWormCount(ItemStack stack, int count) {
		stack.setMetadata(stack.getMaxDamage() - count);
	}

	@Override
	public boolean showFullDurability() {
		return true;
	}

	@Override
	public @Nullable ArmorMaterial getArmorMaterial() {
		return null;
	}

	@Override
	public int getArmorPiece() {
		return IArmorItem.PIECE_LEGS;
	}
}
