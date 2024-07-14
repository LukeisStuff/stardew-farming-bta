package luke.stardew.blocks.model;

import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;

public class BlockModelCropsCauliflower<T extends Block> extends BlockModelStandard<T> {
	public static final IconCoordinate[] GROWTH_STAGE_TEXTURES_TOP = new IconCoordinate[]{
		TextureRegistry.getTexture("stardew:block/cauliflower_crop_1"),
		TextureRegistry.getTexture("stardew:block/cauliflower_crop_2"),
		TextureRegistry.getTexture("stardew:block/cauliflower_crop_3"),
		TextureRegistry.getTexture("stardew:block/cauliflower_crop_4")
	};

	public static final IconCoordinate[] GROWTH_STAGE_TEXTURES_SIDE = new IconCoordinate[]{
		TextureRegistry.getTexture("stardew:block/cauliflower_crop_side_1"),
		TextureRegistry.getTexture("stardew:block/cauliflower_crop_side_2"),
		TextureRegistry.getTexture("stardew:block/cauliflower_crop_side_3"),
		TextureRegistry.getTexture("stardew:block/cauliflower_crop_side_4")
	};

	public static final IconCoordinate LEAF_TEXTURE = TextureRegistry.getTexture("stardew:block/cauliflower_crop_leaf.png");

	public BlockModelCropsCauliflower(Block block) {
		super(block);
	}

	public boolean render(Tessellator tessellator, int x, int y, int z) {
		this.block.setBlockBoundsBasedOnState(renderBlocks.blockAccess, x, y, z);
		float brightness = 1.0F;
		if (LightmapHelper.isLightmapEnabled()) {
			tessellator.setLightmapCoord(this.block.getLightmapCoord(renderBlocks.blockAccess, x, y, z));
		} else {
			brightness = this.getBlockBrightness(renderBlocks.blockAccess, x, y, z);
		}

		tessellator.setColorOpaque_F(brightness, brightness, brightness);
		int meta = renderBlocks.blockAccess.getBlockMetadata(x, y, z);
		IconCoordinate leafTexture = LEAF_TEXTURE;
		if (renderBlocks.overrideBlockTexture != null) {
			leafTexture = renderBlocks.overrideBlockTexture;
		}

		double uMin = leafTexture.getIconUMin();
		double uMax = leafTexture.getIconUMax();
		double vMin = leafTexture.getIconVMin();
		double vMax = leafTexture.getIconVMax();
		double xMin = (double)x + 0.5 - 0.25;
		double xMax = (double)x + 0.5 + 0.25;
		double yMin = (double)y + 0.0;
		double yMax = (double)y + 0.1875;
		double zMin = (double)z + 0.5 - 0.5;
		double zMax = (double)z + 0.5 + 0.5;
		double extra = 0.625;
		tessellator.addVertexWithUV(xMin - extra, yMax, zMax, uMin, vMin);
		tessellator.addVertexWithUV(xMax, yMin, zMax, uMin, vMax);
		tessellator.addVertexWithUV(xMax, yMin, zMin, uMax, vMax);
		tessellator.addVertexWithUV(xMin - extra, yMax, zMin, uMax, vMin);
		tessellator.addVertexWithUV(xMin - extra, yMax, zMin, uMin, vMin);
		tessellator.addVertexWithUV(xMax, yMin, zMin, uMin, vMax);
		tessellator.addVertexWithUV(xMax, yMin, zMax, uMax, vMax);
		tessellator.addVertexWithUV(xMin - extra, yMax, zMax, uMax, vMin);
		tessellator.addVertexWithUV(xMax + extra, yMax, zMin, uMin, vMin);
		tessellator.addVertexWithUV(xMin, yMin, zMin, uMin, vMax);
		tessellator.addVertexWithUV(xMin, yMin, zMax, uMax, vMax);
		tessellator.addVertexWithUV(xMax + extra, yMax, zMax, uMax, vMin);
		tessellator.addVertexWithUV(xMax + extra, yMax, zMax, uMin, vMin);
		tessellator.addVertexWithUV(xMin, yMin, zMax, uMin, vMax);
		tessellator.addVertexWithUV(xMin, yMin, zMin, uMax, vMax);
		tessellator.addVertexWithUV(xMax + extra, yMax, zMin, uMax, vMin);
		if (meta >= 1) {
			this.renderStandardBlock(tessellator, this.block, x, y, z);
		}

		return true;
	}

	public boolean shouldItemRender3d() {
		return false;
	}

	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		if (data < 1 || data > 4) {
			data = 1;
		}

		return side != Side.TOP && side != Side.BOTTOM ? GROWTH_STAGE_TEXTURES_SIDE[data - 1] : GROWTH_STAGE_TEXTURES_TOP[data - 1];
	}
}

