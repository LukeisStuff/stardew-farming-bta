package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFullyRotatable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

public class BlockLogicThatch extends BlockLogicFullyRotatable {
	public BlockLogicThatch(Block<?> block, Material material) {
		super(block, material);

	}

	@Override
	public boolean renderAsNormalBlockOnCondition(WorldSource world, int x, int y, int z) {
		return false;
	}

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
