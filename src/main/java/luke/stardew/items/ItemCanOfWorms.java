package luke.stardew.items;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.slot.Slot;


public class ItemCanOfWorms extends Item {
	public ItemCanOfWorms(String name, int id) {
		super(name, id);
		this.setMaxStackSize(1);
		this.setMaxDamage(192);
	}

	@Override
	public boolean hasInventoryInteraction() {
		return true;
	}

	@Override
	public ItemStack onInventoryInteract(EntityPlayer player, Slot slot, ItemStack stackInSlot, boolean isItemGrabbed) {
		ItemStack canItem = isItemGrabbed ? player.inventory.getHeldItemStack() : stackInSlot;
		int totalSpace = this.getMaxDamage();
		int wormCount = this.getWormCount(canItem);
		int freeSpace = totalSpace - wormCount;
		if (isItemGrabbed) {
			int amount;
			if (stackInSlot == null) {
				ItemStack arrowStack;
				int amount2 = Math.min(64, wormCount);
				if (amount2 > 0 && slot.canPutStackInSlot(arrowStack = new ItemStack(StardewItems.worm, amount2, 0))) {
					this.setWormCount(canItem, wormCount - amount2);
					stackInSlot = arrowStack;
				}
			} else if (stackInSlot.itemID == StardewItems.worm.id && (amount = Math.min(freeSpace, stackInSlot.stackSize)) > 0) {
				this.setWormCount(canItem, wormCount + amount);
				stackInSlot.stackSize -= amount;
			}
		} else {
			int amount;
			ItemStack grabbedItem = player.inventory.getHeldItemStack();
			if (grabbedItem != null && grabbedItem.itemID == StardewItems.worm.id) {
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
				player.inventory.setHeldItemStack(new ItemStack(StardewItems.worm, amount, 0));
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
}
