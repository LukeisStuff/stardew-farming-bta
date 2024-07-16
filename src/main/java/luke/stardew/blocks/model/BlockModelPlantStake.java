package luke.stardew.blocks.model;

import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.block.Block;

public class BlockModelPlantStake<T extends Block> extends BlockModelStandard<T> {
	public final IconCoordinate getTexture = TextureRegistry.getTexture("stardew:block/plantStake");

	public BlockModelPlantStake(Block block) {
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
		IconCoordinate texIndex = getTexture;
		if (renderBlocks.overrideBlockTexture != null) {
			texIndex = renderBlocks.overrideBlockTexture;
		}

		double minU = texIndex.getIconUMin();
		double maxU = texIndex.getIconUMax();
		double minV = texIndex.getIconVMin();
		double maxV = texIndex.getIconVMax();
		double minX = (double) x + 0.5 - 0.45;
		double maxX = (double) x + 0.5 + 0.45;
		double minZ = (double) z + 0.5 - 0.45;
		double maxZ = (double) z + 0.5 + 0.45;
		double yd = (float)y - 0.0625F;
		tessellator.addVertexWithUV(minX, yd + 1.0 + 0.0, minZ, minU, minV);
		tessellator.addVertexWithUV(minX, yd + 0.0, minZ, minU, maxV);
		tessellator.addVertexWithUV(maxX, yd + 0.0, maxZ, maxU, maxV);
		tessellator.addVertexWithUV(maxX, yd + 1.0 + 0.0, maxZ, maxU, minV);
		tessellator.addVertexWithUV(maxX, yd + 1.0 + 0.0, maxZ, minU, minV);
		tessellator.addVertexWithUV(maxX, yd + 0.0, maxZ, minU, maxV);
		tessellator.addVertexWithUV(minX, yd + 0.0, minZ, maxU, maxV);
		tessellator.addVertexWithUV(minX, yd + 1.0 + 0.0, minZ, maxU, minV);
		tessellator.addVertexWithUV(minX, yd + 1.0 + 0.0, maxZ, minU, minV);
		tessellator.addVertexWithUV(minX, yd + 0.0, maxZ, minU, maxV);
		tessellator.addVertexWithUV(maxX, yd + 0.0, minZ, maxU, maxV);
		tessellator.addVertexWithUV(maxX, yd + 1.0 + 0.0, minZ, maxU, minV);
		tessellator.addVertexWithUV(maxX, yd + 1.0 + 0.0, minZ, minU, minV);
		tessellator.addVertexWithUV(maxX, yd + 0.0, minZ, minU, maxV);
		tessellator.addVertexWithUV(minX, yd + 0.0, maxZ, maxU, maxV);
		tessellator.addVertexWithUV(minX, yd + 1.0 + 0.0, maxZ, maxU, minV);
		return true;
	}

	public boolean shouldItemRender3d() {
		return false;
	}
}
