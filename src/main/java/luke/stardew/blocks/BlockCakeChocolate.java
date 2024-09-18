package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.BlockEdible;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.WorldSource;

public class BlockCakeChocolate extends BlockEdible {
	public BlockCakeChocolate(String key, int id) {
		super(key, id, 6, 3, () -> StardewItems.foodCakeChocolate);
	}

	@Override
	public void setBlockBoundsBasedOnState(WorldSource world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		float f = 0.0625f;
		float f1 = (float)(1 + l * 2) / 16.0f;
		float f2 = 0.5f;
		this.setBlockBounds(f1, 0.0, f, 1.0f - f, f2, 1.0f - f);
	}

	@Override
	public AABB getCollisionBoundingBoxFromPool(WorldSource world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		float f = 0.0625f;
		float f1 = (float)(1 + l * 2) / 16.0f;
		float f2 = 0.5f;
		return AABB.getBoundingBoxFromPool((float)x + f1, y, (float)z + f, (float)(x + 1) - f, (float)y + f2 - f, (float)(z + 1) - f);
	}

	@Override
	public AABB getSelectedBoundingBoxFromPool(WorldSource world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		float f = 0.0625f;
		float f1 = (float)(1 + l * 2) / 16.0f;
		float f2 = 0.5f;
		return AABB.getBoundingBoxFromPool((float)x + f1, y, (float)z + f, (float)(x + 1) - f, (float)y + f2, (float)(z + 1) - f);
	}
}
