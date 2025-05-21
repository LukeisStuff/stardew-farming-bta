package luke.stardew.items;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.interfaces.IPlayerEffects;
import luke.stardew.misc.PlayerEffect;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import java.util.Random;

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
