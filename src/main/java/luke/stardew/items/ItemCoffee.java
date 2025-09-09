package luke.stardew.items;

import luke.stardew.interfaces.IPlayerEffects;
import luke.stardew.misc.PlayerEffect;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.World;

public class ItemCoffee extends ItemFood {
	public ItemCoffee(String name, String namespaceID, int id, int healAmount, int ticksPerHeal) {
		super(name, namespaceID, id, healAmount, ticksPerHeal, false, 1);
	}

	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, Player entityplayer) {
		super.onUseItem(itemstack, world, entityplayer);
		((IPlayerEffects) entityplayer).stardew_farming_bta$addEffect(PlayerEffect.speedBoost, 240);
		return new ItemStack(Items.BUCKET);
	}
}
