package luke.stardew.items;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class ItemJam extends ItemFood {
	protected int healAmount;
	public ItemJam(String name, int id, int healAmount, int maxStackSize) {
		super(name, id, healAmount, false);
		this.healAmount = healAmount;
		this.maxStackSize = maxStackSize;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (entityplayer.getHealth() < entityplayer.getMaxHealth()) {
			entityplayer.heal(this.healAmount);
			return new ItemStack(Item.jar);
		} else {
			return itemstack;
		}
	}

	public int getHealAmount() {
		return this.healAmount;
	}

	public int getStackSize() {
		return this.maxStackSize;
	}

}
