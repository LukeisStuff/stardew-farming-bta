package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.world.World;

public class BlockLogicCropTallStake extends BlockLogicCropTall {
	public BlockLogicCropTallStake(Block<?> block) {
		super(block);
	}

	@Override
	public void onHarvest(World world, int x, int y, int z, int meta) {
		if (this.growTopMeta < 0) {
			world.setBlockAndMetadataWithNotify(x, y - 1, z, StardewBlocks.plantStake.id(), 0);
			world.setBlockAndMetadataWithNotify(x, y, z, 0, 0);
		}else {
			world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.plantStake.id(), 0);
			world.setBlockAndMetadataWithNotify(x, y + 1, z, 0, 0);
		}
	}
}
