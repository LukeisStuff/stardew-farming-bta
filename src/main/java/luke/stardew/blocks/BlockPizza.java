package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicEdible;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

import java.util.ArrayList;

public class BlockPizza extends BlockLogicEdible {
	public BlockPizza(Block<?> block) {
		super(block, 8, 3, () -> StardewItems.foodPizza);
	}

	public void setBlockBoundsBasedOnState(WorldSource world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		float f = 0.0625F;
		float f1 = (float) (1 + l * 1.5) / 16.0f;
		float f2 = 0.25f;
		this.setBlockBounds(f1, 0.0F, f, 1.0F - f, f2, 1.0F - f);
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		return dropCause != EnumDropCause.PICK_BLOCK && meta != 0 ? null : new ItemStack[]{new ItemStack(StardewItems.foodPizza)};
	}

	public void setBlockBoundsForItemRender() {
		float f = 0.0625F;
		float f1 = 0.25f;
		this.setBlockBounds(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
	}

	@Override
	public void getCollidingBoundingBoxes(World world, int x, int y, int z, AABB aabb, ArrayList<AABB> aabbList) {
		int meta = world.getBlockMetadata(x, y, z);
		float f = 0.0625F;
		float f1 = (float) (1 + meta * 1.5) / 16.0f;
		float f2 = 0.3125F;

		aabbList.add(AABB.getTemporaryBB(x + f1, y, z + f, (x + 1) - f, y + f2 - f, (z + 1) - f));
	}

	/*
	public AABB getSelectedBoundingBoxFromPool(WorldSource world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		float f = 0.0625F;
		float f1 = (float) (1 + l * 1.5) / 16.0f;
		float f2 = 0.25f;
		return AABB.getBoundingBoxFromPool((float) x + f1, y, (float) z + f, (float) (x + 1) - f, (float) y + f2, (float) (z + 1) - f);
	}*/

}
