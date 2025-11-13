package luke.stardew.items;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.World;

public class ItemJam extends ItemFood {
	public final int healAmount;

	public ItemJam(String name, String namespaceID, int id, int healAmount, int ticksPerHeal, boolean favouriteWolfMeat, int maxStackSize) {
		super(name, namespaceID, id, healAmount, ticksPerHeal, favouriteWolfMeat, maxStackSize);
		this.healAmount = healAmount;
		this.maxStackSize = maxStackSize;
	}

	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, Player entityplayer) {
		if (entityplayer.getHealth() < entityplayer.getMaxHealth() && itemstack.consumeItem(entityplayer)) {
			entityplayer.heal(this.healAmount);
			entityplayer.inventory.insertItem(new ItemStack(Items.JAR, 1), true);
		}
		return itemstack;
	}

}
