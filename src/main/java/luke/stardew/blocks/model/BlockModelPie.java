package luke.stardew.blocks.model;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockEdible;
import net.minecraft.core.util.helper.Side;

public class BlockModelPie<T extends Block> extends BlockModelStandard<T> {
	protected IconCoordinate sideTexture = TextureRegistry.getTexture("stardew:block/pie_side");
	protected IconCoordinate insideTexture = TextureRegistry.getTexture("stardew:block/pie_inside");
	protected IconCoordinate topTexture = TextureRegistry.getTexture("stardew:block/pie_top");
	public int maxSlices;

	public BlockModelPie(Block block) {
		super(block);
		float f = 0.0625F;
		float f1 = 0.375F;
		this.withCustomItemBounds((double)f, 0.0, (double)f, (double)(1.0F - f), (double)f1, (double)(1.0F - f));
	}

	public void renderSliceSide(Tessellator tessellator, Block block, int x, int y, int z, Side side, boolean overrideTex) {
		if (overrideTex) {
			renderBlocks.overrideBlockTexture = this.insideTexture;
		}

		this.renderSide(tessellator, block, x, y, z, side, 0);
		if (overrideTex) {
			renderBlocks.overrideBlockTexture = null;
		}

	}

	private void renderSlice(Tessellator tessellator, Block block, int x, int y, int z, int sliceX, int sliceZ) {
		double onePix = 0.0625;
		double sliceWidth = onePix * 8.0;
		double xMin = 0.0 + sliceWidth * (double)sliceX;
		double xMax = xMin + sliceWidth;
		double zMin = 0.0 + sliceWidth * (double)sliceZ;
		double zMax = zMin + sliceWidth;
		double offsetXMin = sliceX == 0 ? onePix : 0.0;
		double offsetXMax = sliceX == 1 ? onePix : 0.0;
		double offsetZMin = sliceZ == 0 ? onePix : 0.0;
		double offsetZMax = sliceZ == 1 ? onePix : 0.0;
		boolean insideSouth = sliceZ == 0;
		boolean insideNorth = sliceZ == 1;
		boolean insideEast = sliceX == 0;
		boolean insideWest = sliceX == 1;
		this.maxSlices = ((BlockEdible)block).maxBites;
		block.setBlockBounds(xMin + offsetXMin, 0.0, zMin + offsetZMin, xMax - offsetXMax, 0.375, zMax - offsetZMax);
		this.renderSide(tessellator, block, x, y, z, Side.TOP, 0);
		this.renderSide(tessellator, block, x, y, z, Side.BOTTOM, 0);
		this.renderSliceSide(tessellator, block, x, y, z, Side.SOUTH, insideSouth);
		this.renderSliceSide(tessellator, block, x, y, z, Side.WEST, insideWest);
		renderBlocks.flipTexture = true;
		this.renderSliceSide(tessellator, block, x, y, z, Side.NORTH, insideNorth);
		this.renderSliceSide(tessellator, block, x, y, z, Side.EAST, insideEast);
		renderBlocks.flipTexture = false;
	}

	public boolean render(Tessellator tessellator, int x, int y, int z) {
		this.block.setBlockBoundsBasedOnState(renderBlocks.blockAccess, x, y, z);
		int meta = renderBlocks.blockAccess.getBlockMetadata(x, y, z);
		renderBlocks.enableAO = true;
		renderBlocks.cache.setupCache(this.block, renderBlocks.blockAccess, x, y, z);
		int slices = 0;

		for(int xSlice = 0; xSlice < 2; ++xSlice) {
			for(int zSlice = 0; zSlice < 2; ++zSlice) {
				++slices;
				if (meta < slices) {
					this.renderSlice(tessellator, this.block, x, y, z, xSlice, zSlice);
				}
			}
		}

		renderBlocks.enableAO = false;
		return true;
	}
}
