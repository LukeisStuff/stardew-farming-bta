package luke.stardew.blocks.model;

import luke.stardew.blocks.BlockCandle;
import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;

public class BlockModelWaxCandle<T extends BlockCandle> extends BlockModelStandard<T> {
	public BlockModelWaxCandle(Block block) {
		super(block);
	}

	public boolean render(Tessellator tessellator, int x, int y, int z) {
		this.block.setBlockBoundsBasedOnState(renderBlocks.blockAccess, x, y, z);
		float minX = (float)x + 0.5F - 0.09375F;
		float minY = (float)y + 0.0F;
		float minZ = (float)z + 0.5F - 0.09375F;
		float maxX = (float)x + 0.5F + 0.09375F;
		float maxY = (float)y + 0.5F;
		float maxZ = (float)z + 0.5F + 0.09375F;
		float wickMaxY = maxY + 0.375F;
		float brightness = 1.0F;
		if (LightmapHelper.isLightmapEnabled()) {
			tessellator.setLightmapCoord(this.block.getLightmapCoord(renderBlocks.blockAccess, x, y, z));
		} else {
			brightness = this.getBlockBrightness(renderBlocks.blockAccess, x, y, z);
		}
		IconCoordinate texIndex = this.getParticleTexture(Side.TOP, 0);
		if (renderBlocks.overrideBlockTexture != null) {
			texIndex = renderBlocks.overrideBlockTexture;
		}

		double texV = texIndex.getIconVMin();
		double onePix = 0.0625;
		double uOffset =  onePix * 3.0;
		double sideMinU = texIndex.getSubIconU(uOffset);
		double sideMaxU = texIndex.getSubIconU(uOffset + onePix * 3.0);
		double sideMinV = texIndex.getSubIconV(onePix * 3.0);
		double sideMaxV = texIndex.getSubIconV(onePix * 3.0 + onePix * 8.0);
		double topMinU = texIndex.getSubIconU(uOffset);
		double topMaxU = texIndex.getSubIconU(uOffset + onePix * 3.0);
		double topMaxV = texIndex.getSubIconV(onePix * 3.0);
		double bottomMinU = texIndex.getSubIconU(onePix * 3.0);
		double bottomMaxU = texIndex.getSubIconU(onePix * 3.0 + onePix * 3.0);
		double bottomMaxV = texIndex.getSubIconV(onePix * 3.0);
		double wickMinU = texIndex.getSubIconU(onePix * 6.0 + uOffset);
		double wickMaxU = texIndex.getSubIconU(onePix * 6.0 + uOffset + onePix * 3.0);
		double wickMaxV = texIndex.getSubIconV(onePix * 6.0);
		tessellator.setColorOpaque_F(brightness, brightness, brightness);
		renderBlocks.enableAO = true;
		tessellator.addVertexWithUV(maxX, minY, minZ, sideMinU, sideMaxV);
		tessellator.addVertexWithUV(minX, minY, minZ, sideMaxU, sideMaxV);
		tessellator.addVertexWithUV(minX, maxY, minZ, sideMaxU, sideMinV);
		tessellator.addVertexWithUV(maxX, maxY, minZ, sideMinU, sideMinV);
		tessellator.addVertexWithUV(minX, minY, maxZ, sideMinU, sideMaxV);
		tessellator.addVertexWithUV(maxX, minY, maxZ, sideMaxU, sideMaxV);
		tessellator.addVertexWithUV(maxX, maxY, maxZ, sideMaxU, sideMinV);
		tessellator.addVertexWithUV(minX, maxY, maxZ, sideMinU, sideMinV);
		tessellator.addVertexWithUV(maxX, minY, maxZ, sideMinU, sideMaxV);
		tessellator.addVertexWithUV(maxX, minY, minZ, sideMaxU, sideMaxV);
		tessellator.addVertexWithUV(maxX, maxY, minZ, sideMaxU, sideMinV);
		tessellator.addVertexWithUV(maxX, maxY, maxZ, sideMinU, sideMinV);
		tessellator.addVertexWithUV(minX, minY, minZ, sideMinU, sideMaxV);
		tessellator.addVertexWithUV(minX, minY, maxZ, sideMaxU, sideMaxV);
		tessellator.addVertexWithUV(minX, maxY, maxZ, sideMaxU, sideMinV);
		tessellator.addVertexWithUV(minX, maxY, minZ, sideMinU, sideMinV);
		tessellator.addVertexWithUV(minX, maxY, maxZ, topMinU, topMaxV);
		tessellator.addVertexWithUV(maxX, maxY, maxZ, topMaxU, topMaxV);
		tessellator.addVertexWithUV(maxX, maxY, minZ, topMaxU, texV);
		tessellator.addVertexWithUV(minX, maxY, minZ, topMinU, texV);
		tessellator.addVertexWithUV(minX, minY, minZ, bottomMinU, texV);
		tessellator.addVertexWithUV(maxX, minY, minZ, bottomMaxU, texV);
		tessellator.addVertexWithUV(maxX, minY, maxZ, bottomMaxU, bottomMaxV);
		tessellator.addVertexWithUV(minX, minY, maxZ, bottomMinU, bottomMaxV);
		tessellator.addVertexWithUV(minX, maxY, minZ, wickMinU, wickMaxV);
		tessellator.addVertexWithUV(maxX, maxY, maxZ, wickMaxU, wickMaxV);
		tessellator.addVertexWithUV(maxX, wickMaxY, maxZ, wickMaxU, texV);
		tessellator.addVertexWithUV(minX, wickMaxY, minZ, wickMinU, texV);
		tessellator.addVertexWithUV(minX, wickMaxY, minZ, wickMaxU, texV);
		tessellator.addVertexWithUV(maxX, wickMaxY, maxZ, wickMinU, texV);
		tessellator.addVertexWithUV(maxX, maxY, maxZ, wickMinU, wickMaxV);
		tessellator.addVertexWithUV(minX, maxY, minZ, wickMaxU, wickMaxV);
		tessellator.addVertexWithUV(minX, maxY, maxZ, wickMinU, wickMaxV);
		tessellator.addVertexWithUV(maxX, maxY, minZ, wickMaxU, wickMaxV);
		tessellator.addVertexWithUV(maxX, wickMaxY, minZ, wickMaxU, texV);
		tessellator.addVertexWithUV(minX, wickMaxY, maxZ, wickMinU, texV);
		tessellator.addVertexWithUV(minX, wickMaxY, maxZ, wickMaxU, texV);
		tessellator.addVertexWithUV(maxX, wickMaxY, minZ, wickMinU, texV);
		tessellator.addVertexWithUV(maxX, maxY, minZ, wickMinU, wickMaxV);
		tessellator.addVertexWithUV(minX, maxY, maxZ, wickMaxU, wickMaxV);
		return true;
	}

	public boolean shouldItemRender3d() {
		return false;
	}
}
