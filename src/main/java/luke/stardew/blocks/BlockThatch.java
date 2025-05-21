package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicAxisAligned;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

public class BlockThatch extends BlockLogicAxisAligned {
	public BlockThatch(Block<?> block, Material material) {
		super(block, material);

	}

	@Override
	public boolean renderAsNormalBlockOnCondition(WorldSource world, int x, int y, int z) {
		return false;
	}

	/*public boolean shouldSideBeRendered(WorldSource blockAccess, int x, int y, int z, int side) {
		return super.shouldSideBeRendered(blockAccess, x, y, z, 1 - side); //FIXME Ice rendering???
	}*/

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity.yd < 0.0D) {
			entity.fallDistance = entity.fallDistance / 4;
		}
	}

	@Override
	public AABB getCollisionBoundingBoxFromPool(WorldSource world,int x, int y, int z){
		return AABB.getTemporaryBB(x, y, z, (x + 1), y+.9f, (z + 1));
	}
}
