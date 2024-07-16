package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockPlantStake extends Block {
	public BlockPlantStake(String key, int id, Material material) {
		super(key, id, material);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return world.getBlockId(x, y - 1, z) == farmlandDirt.id;
	}

	@Override
	public boolean onBlockRightClicked(World world, int x, int y, int z, EntityPlayer player, Side side, double xHit, double yHit) {
		if (player.getCurrentEquippedItem().itemID == StardewItems.seedsGrapes.id && world.getBlockId(x, y - 1, z) == farmlandDirt.id) {
			player.getCurrentEquippedItem().consumeItem(player);
			world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.cropsGrapeBottom.id, 0);
		}
		return super.onBlockRightClicked(world, x, y, z, player, side, xHit, yHit);
	}
}
