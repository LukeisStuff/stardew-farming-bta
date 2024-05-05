package luke.stardew.blocks;

import net.minecraft.core.block.BlockTransparent;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

public class BlockHoney extends BlockTransparent {
	public BlockHoney(String key, int id, boolean renderInside){
		super(key, id, Material.leaves, renderInside);
		setTicking(true);
	}

	public boolean isSolidRender() {
		return false;
	}

	public boolean shouldSideBeRendered(WorldSource blockAccess, int x, int y, int z, int side) {
		return super.shouldSideBeRendered(blockAccess, x, y, z, 1 - side);
	}

	public int getRenderBlockPass() {
		return 1;
	}

	public AABB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		float f = 0.125F;
		return AABB.getBoundingBoxFromPool(x + f, y + f, z + f, (float)(x + 1) - f, (float)(y + 1) - f, (float)(z + 1) - f);
	}

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
