package luke.stardew.model;

import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.helper.Side;

public class BlockModelCrops<T extends BlockLogic> extends BlockModelStandard<T> {
	private final IconCoordinate[] stageTextures;
	private final int maxStages;

	public BlockModelCrops(Block<T> block, String folderName, int stages) {
		super(block);
		this.maxStages = stages;

		String basePath = "stardew:block/crops_" + folderName + "/";

		this.stageTextures = new IconCoordinate[stages];
		for (int i = 0; i < stages; i++) {
			this.stageTextures[i] = TextureRegistry.getTexture(basePath + "stage" + i);
		}
	}

	public BlockModelCrops(Block<T> block, String folderName, int stages, String side) {
		super(block);
		this.maxStages = stages;

		String basePath = "stardew:block/crops_" + folderName + "/";

		this.stageTextures = new IconCoordinate[stages];
		for (int i = 0; i < stages; i++) {
			this.stageTextures[i] = TextureRegistry.getTexture(basePath + "stage" + i + "_" + side);
		}
	}

	@Override
	public boolean render(Tessellator tessellator, int x, int y, int z) {
		float brightness = 1.0F;
		if (!LightmapHelper.isLightmapEnabled()) {
			brightness = this.getBlockBrightness(renderBlocks.blockAccess, x, y, z);
		} else {
			tessellator.setLightmapCoord(this.block.getLightmapCoord(renderBlocks.blockAccess, x, y, z));
		}

		int color = BlockColorDispatcher.getInstance().getDispatch(this.block).getWorldColor(renderBlocks.blockAccess, x, y, z);
		float r = (color >> 16 & 255) / 255.0F;
		float g = (color >> 8 & 255) / 255.0F;
		float b = (color & 255) / 255.0F;
		tessellator.setColorOpaque_F(brightness * r, brightness * g, brightness * b);

		int metadata = renderBlocks.blockAccess.getBlockMetadata(x, y, z);
		IconCoordinate texIndex = this.getBlockTextureFromSideAndMetadata(Side.BOTTOM, metadata);
		if (renderBlocks.overrideBlockTexture != null) {
			texIndex = renderBlocks.overrideBlockTexture;
		}

		double minU = texIndex.getIconUMin();
		double maxU = texIndex.getIconUMax();
		double minV = texIndex.getIconVMin();
		double maxV = texIndex.getIconVMax();
		double minX = x + 0.5 - 0.45;
		double maxX = x + 0.5 + 0.45;
		double minZ = z + 0.5 - 0.45;
		double maxZ = z + 0.5 + 0.45;
		double yd = y - 0.0625F;

		tessellator.addVertexWithUV(minX, yd + 1.0, minZ, minU, minV);
		tessellator.addVertexWithUV(minX, yd + 0.0, minZ, minU, maxV);
		tessellator.addVertexWithUV(maxX, yd + 0.0, maxZ, maxU, maxV);
		tessellator.addVertexWithUV(maxX, yd + 1.0, maxZ, maxU, minV);

		tessellator.addVertexWithUV(maxX, yd + 1.0, maxZ, minU, minV);
		tessellator.addVertexWithUV(maxX, yd + 0.0, maxZ, minU, maxV);
		tessellator.addVertexWithUV(minX, yd + 0.0, minZ, maxU, maxV);
		tessellator.addVertexWithUV(minX, yd + 1.0, minZ, maxU, minV);

		tessellator.addVertexWithUV(minX, yd + 1.0, maxZ, minU, minV);
		tessellator.addVertexWithUV(minX, yd + 0.0, maxZ, minU, maxV);
		tessellator.addVertexWithUV(maxX, yd + 0.0, minZ, maxU, maxV);
		tessellator.addVertexWithUV(maxX, yd + 1.0, minZ, maxU, minV);

		tessellator.addVertexWithUV(maxX, yd + 1.0, minZ, minU, minV);
		tessellator.addVertexWithUV(maxX, yd + 0.0, minZ, minU, maxV);
		tessellator.addVertexWithUV(minX, yd + 0.0, maxZ, maxU, maxV);
		tessellator.addVertexWithUV(minX, yd + 1.0, maxZ, maxU, minV);

		return true;
	}

	@Override
	public boolean shouldItemRender3d() {
		return false;
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		int idx = MathHelper.clamp(data, 0, maxStages - 1);
		return stageTextures[idx];
	}
}
