package luke.stardew.items;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemFoodStackable;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class ItemJam extends ItemFoodStackable {
	protected int healAmount;
	public ItemJam(String name, int id, int healAmount, int maxStackSize) {
		super(name, id, healAmount, false, maxStackSize);
		this.healAmount = healAmount;
		this.maxStackSize = maxStackSize;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (entityplayer.getHealth() < entityplayer.getMaxHealth() && itemstack.consumeItem(entityplayer)) {
			entityplayer.heal(this.healAmount);
			return new ItemStack(Item.jar);
		}
			return itemstack;
		}

	public static boolean fillJar(EntityPlayer player, ItemStack itemToGive) {
		if (player.inventory.getCurrentItem().stackSize <= 1) {
			player.inventory.setInventorySlotContents(player.inventory.currentItem, itemToGive);
		} else {
			player.inventory.insertItem(itemToGive, true);
		}

		if (itemToGive.stackSize < 1) {
			player.swingItem();
			player.inventory.getCurrentItem().consumeItem(player);
			return true;
		} else {
			return false;
		}
	}

	public int getHealAmount() {
		return this.healAmount;
	}

}
