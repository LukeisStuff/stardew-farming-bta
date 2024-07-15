package luke.stardew.blocks.model;

import net.minecraft.client.render.block.model.BlockModelLeaves;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.block.Block;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class BlockModelGoldenAppleLeavesBloom<T extends Block> extends BlockModelLeaves<T> {
	private final IconCoordinate grownAppleOverlay = TextureRegistry.getTexture("stardew:block/leaves_apple_golden_overlay");
	private final IconCoordinate floweringAppleOverlay = TextureRegistry.getTexture("stardew:block/leaves_apple_golden_flowering_overlay");

	public BlockModelGoldenAppleLeavesBloom(Block block) {
		super(block, "stardew:block/leaves_apple_golden");
	}

	public boolean render(Tessellator tessellator, int x, int y, int z) {
		super.render(tessellator, x, y, z);
		int growthRate = (renderBlocks.blockAccess.getBlockMetadata(x, y, z) & 240) >> 4;
		if (growthRate > 0) {
			renderBlocks.overrideBlockTexture = this.grownAppleOverlay;
		} else {
			renderBlocks.overrideBlockTexture = this.floweringAppleOverlay;
		}

		this.renderStandardBlock(tessellator, this.block, x, y, z, 1.0F, 1.0F, 1.0F);
		renderBlocks.overrideBlockTexture = null;
		return true;
	}

	public void renderBlockOnInventory(Tessellator tessellator, int metadata, float brightness, float alpha, @Nullable Integer lightmapCoordinate) {
		super.renderBlockOnInventory(tessellator, metadata, brightness, alpha, lightmapCoordinate);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		IconCoordinate appleCoord = this.grownAppleOverlay;
		GL11.glColor4f(brightness, brightness, brightness, alpha);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		this.renderBottomFace(tessellator, this.block, 0.0, 0.0, 0.0, appleCoord);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		this.renderTopFace(tessellator, this.block, 0.0, 0.0, 0.0, appleCoord);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		this.renderNorthFace(tessellator, this.block, 0.0, 0.0, 0.0, appleCoord);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		this.renderSouthFace(tessellator, this.block, 0.0, 0.0, 0.0, appleCoord);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		this.renderWestFace(tessellator, this.block, 0.0, 0.0, 0.0, appleCoord);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		this.renderEastFace(tessellator, this.block, 0.0, 0.0, 0.0, appleCoord);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
}

