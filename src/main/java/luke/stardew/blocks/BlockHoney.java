package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicTransparent;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;

import java.util.ArrayList;

public class BlockHoney extends BlockLogicTransparent {
	public BlockHoney(Block<?> block){
		super(block, Material.leaves);
		block.setTicking(true);
	}

	@Override
	public boolean isSolidRender() {
		return false;
	}

	@Override
	public void getCollidingBoundingBoxes(World world, int x, int y, int z, AABB aabb, ArrayList<AABB> aabbList) {
		float f = 0.125F;

		aabbList.add(AABB.getTemporaryBB(x + f, y + f, z + f, (x + 1) - f, (y + 1) - f, (z + 1) - f));
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		entity.xd *= 0.4;
		entity.yd *= 0.1;
		entity.zd *= 0.4;
		if (entity.fallDistance > 1.5F) {
			entity.fallDistance = 0.0F;
			world.playBlockSoundEffect((Entity)null, x, y, z, StardewBlocks.blockHoney, EnumBlockSoundEffectType.ENTITY_LAND);
		}
	}
}
