package luke.stardew.items;

import luke.stardew.interfaces.IPlayerEffects;
import luke.stardew.misc.PlayerEffect;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class ItemCoffee extends ItemFood {
	public ItemCoffee(String name, int id, int healAmount, int ticksPerHeal) {
		super(name, id, healAmount, ticksPerHeal, false, 1);
	}

	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (entityplayer.getHealth() < entityplayer.getMaxHealth()) {
			super.onUseItem(itemstack, world, entityplayer);
			((IPlayerEffects)entityplayer).stardew_farming_bta$addEffect(PlayerEffect.speedBoost, 240);
			return new ItemStack(Item.bucket);
		} else {
			return itemstack;
		}
	}
}
