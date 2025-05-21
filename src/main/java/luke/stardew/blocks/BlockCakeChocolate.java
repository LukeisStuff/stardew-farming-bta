package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicEdible;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

import java.util.ArrayList;

public class BlockCakeChocolate extends BlockLogicEdible {
	private float height;

	public BlockCakeChocolate(Block<?> block, float height, Item dropItem) {
		super(block, 6, 3, () -> dropItem);
		this.height = height;
	}

	@Override
	public AABB getBlockBoundsFromState(WorldSource world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		float f = 0.0625f;
		float f1 = (float)(1 + l * 2) / 16.0f;
		return AABB.getTemporaryBB(f1, 0.0, f, 1.0f - f, this.height, 1.0f - f);
	}
}
