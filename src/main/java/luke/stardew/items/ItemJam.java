package luke.stardew.items;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class ItemJam extends ItemFood {
	private final int healAmount;

	public ItemJam(String name, int id, int healAmount, int ticksPerHeal, boolean favouriteWolfMeat, int maxStackSize) {
		super(name, id, healAmount, ticksPerHeal, favouriteWolfMeat, maxStackSize);
		this.healAmount = healAmount;
		this.maxStackSize = maxStackSize;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (entityplayer.getHealth() < entityplayer.getMaxHealth() && itemstack.consumeItem(entityplayer)) {
			entityplayer.heal(this.healAmount);
			entityplayer.inventory.insertItem(new ItemStack(Item.jar, 1), true);
		}
			return itemstack;
		}

	public int getHealAmount() {
		return this.healAmount;
	}

}
