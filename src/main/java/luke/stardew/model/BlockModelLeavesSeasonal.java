package luke.stardew.model;

import net.minecraft.client.render.block.model.BlockModelLeaves;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicLeavesCherryFlowering;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

import static luke.stardew.StardewMod.MOD_ID;

public class BlockModelLeavesSeasonal<T extends BlockLogic> extends BlockModelLeaves<T> {
	public IconCoordinate grownAppleOverlay = TextureRegistry.getTexture(MOD_ID + ":block/leaves_apple_overlay");
	public IconCoordinate floweringAppleOverlay = TextureRegistry.getTexture(MOD_ID + ":block/leaves_apple_flowering_overlay");

	public BlockModelLeavesSeasonal(Block<T> block) {
		super(block, "minecraft:block/leaves/oak"); //Dummy texture, should be overridden
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		return fancyGraphics ? this.blockTextures.get(3) : this.blockTextures.get(0);
	}

	@Override
	public boolean render(Tessellator tessellator, int x, int y, int z) {
		super.render(tessellator, x, y, z);
		int growthRate = BlockLogicLeavesCherryFlowering.getGrowthRate(renderBlocks.blockAccess.getBlockMetadata(x, y, z));
		if (growthRate > 0) {
			renderBlocks.overrideBlockTexture = this.blockTextures.get(2);
		} else {
			renderBlocks.overrideBlockTexture = this.blockTextures.get(1);
		}

		this.renderStandardBlock(tessellator, this.block.getBoundsRaw(), x, y, z, 1.0F, 1.0F, 1.0F);
		renderBlocks.overrideBlockTexture = null;
		return true;
	}

	@Override
	public void renderBlockOnInventory(Tessellator tessellator, int metadata, float brightness, float alpha, @Nullable Integer lightmapCoordinate) {
		super.renderBlockOnInventory(tessellator, metadata, brightness, alpha, lightmapCoordinate);
		AABB aabb = this.block.getBoundsRaw();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		IconCoordinate appleCoord = this.blockTextures.get(2);
		GL11.glColor4f(brightness, brightness, brightness, alpha);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		this.renderBottomFace(tessellator, aabb, 0.0, 0.0, 0.0, appleCoord);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		this.renderTopFace(tessellator, aabb, 0.0, 0.0, 0.0, appleCoord);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		this.renderNorthFace(tessellator, aabb, 0.0, 0.0, 0.0, appleCoord);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		this.renderSouthFace(tessellator, aabb, 0.0, 0.0, 0.0, appleCoord);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		this.renderWestFace(tessellator, aabb, 0.0, 0.0, 0.0, appleCoord);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		this.renderEastFace(tessellator, aabb, 0.0, 0.0, 0.0, appleCoord);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
}

