package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.WorldSource;

public class BlockLogicCropGrowing extends BlockLogicCropBase{
	public BlockLogicCropGrowing(Block<?> block) {
		super(block);
	}

	@Override
	public AABB getBlockBoundsFromState(WorldSource world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		float onePix = 0.0625F;
		float size = 0.0F;
		if (meta == 0) {
			size = 6.0F * onePix;
		} else if (meta == 1) {
			size = 8.0F * onePix;
		} else if (meta == 2) {
			size = 10.0F * onePix;
		} else if (meta == 3) {
			size = 12.0F * onePix;
		} else if (meta == 4) {
			size = 14.0F * onePix;
		}

		return AABB.getTemporaryBB(0.5F - size / 2.0F, 0.0, 0.5F - size / 2.0F, 0.5F + size / 2.0F, size, 0.5F + size / 2.0F);
	}
}
