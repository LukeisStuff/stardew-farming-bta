package luke.stardew.model;

import luke.stardew.blocks.BlockLogicLeavesAppleFlowering;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelLeaves;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.phys.AABB;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

@Environment(EnvType.CLIENT)
public class BlockModelLeavesAppleFlowering<T extends BlockLogic> extends BlockModelLeaves<T> {
	private final IconCoordinate grownAppleOverlay;
	private final IconCoordinate floweringAppleOverlay;

	public BlockModelLeavesAppleFlowering(Block<T> block, String baseTexturePath, String overlayName) {
		super(block, baseTexturePath);
		this.grownAppleOverlay = TextureRegistry.getTexture("stardew:block/leaves/" + overlayName + "_overlay");
		this.floweringAppleOverlay = TextureRegistry.getTexture("stardew:block/leaves/" + overlayName + "_flowering_overlay");
	}

	@Override
	public boolean render(Tessellator tessellator, int x, int y, int z) {
		super.render(tessellator, x, y, z);
		int growthRate = BlockLogicLeavesAppleFlowering.getGrowthRate(renderBlocks.blockAccess.getBlockMetadata(x, y, z));
		renderBlocks.overrideBlockTexture = growthRate > 0 ? this.grownAppleOverlay : this.floweringAppleOverlay;

		this.renderStandardBlock(tessellator, this.block.getBoundsRaw(), x, y, z, 1.0F, 1.0F, 1.0F);
		renderBlocks.overrideBlockTexture = null;
		return true;
	}

	@Override
	public void renderBlockOnInventory(Tessellator tessellator, int metadata, float brightness, float alpha, @Nullable Integer lightmapCoordinate) {
		super.renderBlockOnInventory(tessellator, metadata, brightness, alpha, lightmapCoordinate);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		AABB bounds = this.block.getBoundsRaw();
		IconCoordinate appleCoord = this.grownAppleOverlay;  // Use grown for inventory (consistent)

		GL11.glColor4f(brightness, brightness, brightness, alpha);

		// Render all 6 faces of apple overlay
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		this.renderBottomFace(tessellator, bounds, 0.0F, 0.0F, 0.0F, appleCoord);
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		this.renderTopFace(tessellator, bounds, 0.0F, 0.0F, 0.0F, appleCoord);
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		this.renderNorthFace(tessellator, bounds, 0.0F, 0.0F, 0.0F, appleCoord);
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		this.renderSouthFace(tessellator, bounds, 0.0F, 0.0F, 0.0F, appleCoord);
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		this.renderWestFace(tessellator, bounds, 0.0F, 0.0F, 0.0F, appleCoord);
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		this.renderEastFace(tessellator, bounds, 0.0F, 0.0F, 0.0F, appleCoord);
		tessellator.draw();

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
}
