package luke.stardew.items;

import luke.stardew.interfaces.IPlayerEffects;
import luke.stardew.misc.PlayerEffect;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class ItemCoffee extends ItemFood {
	public ItemCoffee(String name, int id, int healAmount, int ticksPerHeal, boolean favouriteWolfMeat, int maxStackSize) {
		super(name, id, healAmount, ticksPerHeal, favouriteWolfMeat, maxStackSize);
	}

	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (itemstack.consumeItem(entityplayer)) {
			entityplayer.eatFood(this);
			entityplayer.inventory.insertItem(new ItemStack(Item.jar, 1), true);
			((IPlayerEffects)entityplayer).stardew_farming_bta$addEffect(PlayerEffect.speedBoost, 240);
		}

		return itemstack;
	}
}
