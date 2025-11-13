package luke.stardew.model;

import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.Side;

public class BlockModelCropsGrowing<T extends BlockLogic> extends BlockModelStandard<T> {
	protected final IconCoordinate[] growthStageTop;
	protected final IconCoordinate[] growthStageSide;
	protected final IconCoordinate leafTexture;

	public BlockModelCropsGrowing(Block<T> block, String cropName) {
		super(block);

		String basePath = "stardew:block/crops_" + cropName + "/";

		this.growthStageTop = new IconCoordinate[4];
		this.growthStageSide = new IconCoordinate[4];

		for (int i = 0; i < 4; i++) {
			int stage = i + 1;
			this.growthStageTop[i] = TextureRegistry.getTexture(basePath + "stage" + stage + "_top");
			this.growthStageSide[i] = TextureRegistry.getTexture(basePath + "stage" + stage + "_side");
		}

		this.leafTexture = TextureRegistry.getTexture(basePath + "stage0");
	}

	@Override
	public boolean render(Tessellator tessellator, int x, int y, int z) {
		float brightness = 1.0F;
		if (LightmapHelper.isLightmapEnabled()) {
			tessellator.setLightmapCoord(this.block.getLightmapCoord(renderBlocks.blockAccess, x, y, z));
		} else {
			brightness = this.getBlockBrightness(renderBlocks.blockAccess, x, y, z);
		}

		tessellator.setColorOpaque_F(brightness, brightness, brightness);
		int meta = renderBlocks.blockAccess.getBlockMetadata(x, y, z);

		IconCoordinate leafTex = leafTexture;
		if (renderBlocks.overrideBlockTexture != null) {
			leafTex = renderBlocks.overrideBlockTexture;
		}

		double uMin = leafTex.getIconUMin();
		double uMax = leafTex.getIconUMax();
		double vMin = leafTex.getIconVMin();
		double vMax = leafTex.getIconVMax();

		double xMin = x + 0.5 - 0.25;
		double xMax = x + 0.5 + 0.25;
		double yMin = y + 0.0;
		double yMax = y + 0.1875;
		double zMin = z + 0.5 - 0.5;
		double zMax = z + 0.5 + 0.5;
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
		if (meta >= 1 && meta <= 4) {
			this.renderStandardBlock(tessellator, this.block.getBlockBoundsFromState(renderBlocks.blockAccess, x, y, z), x, y, z);
		}

		return true;
	}

	@Override
	public boolean shouldItemRender3d() {
		return false;
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		if (data < 1 || data > 4) {
			data = 1;
		}
		int index = data - 1;
		return side == Side.TOP || side == Side.BOTTOM ? growthStageTop[index] : growthStageSide[index];
	}
}
