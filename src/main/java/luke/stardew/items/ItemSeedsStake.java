package luke.stardew.items;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.ItemSeeds;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class ItemSeedsStake extends ItemSeeds {
	public ItemSeedsStake(String name, String namespaceId, int id, Block<?> cropsBlock) {
		super(name, namespaceId, id, cropsBlock);
	}

	@Override
	public boolean onUseItemOnBlock(ItemStack itemstack, Player player, World world, int x, int y, int z, Side side, double xPlaced, double yPlaced) {
		if (world.getBlock(x, y, z) == StardewBlocks.PLANT_STAKE) {
			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == StardewItems.SEEDS_GRAPES.id && world.getBlockId(x, y - 1, z) == Blocks.FARMLAND_DIRT.id()) {
				player.getCurrentEquippedItem().consumeItem(player);
				world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.CROPS_GRAPE_BOTTOM.id(), 0);
				player.swingItem();
				world.playBlockSoundEffect(player, x + 0.5F, y + 0.5F, z + 0.5F, StardewBlocks.CROPS_GRAPE_BOTTOM, EnumBlockSoundEffectType.PLACE);
			}
			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == StardewItems.BEANS_COFFEE.id && world.getBlockId(x, y - 1, z) == Blocks.FARMLAND_DIRT.id()) {
				player.getCurrentEquippedItem().consumeItem(player);
				world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.CROPS_BEANS_BOTTOM.id(), 0);
				player.swingItem();
				world.playBlockSoundEffect(player, x + 0.5F, y + 0.5F, z + 0.5F, StardewBlocks.CROPS_BEANS_BOTTOM, EnumBlockSoundEffectType.PLACE);
			}
		}

		return false;
	}
}
