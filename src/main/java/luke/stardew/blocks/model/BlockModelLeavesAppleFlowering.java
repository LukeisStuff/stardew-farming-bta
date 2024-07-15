package luke.stardew.blocks.model;

import net.minecraft.client.render.block.model.BlockModelLeaves;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class BlockModelLeavesAppleFlowering<T extends Block> extends BlockModelStandard<T> {
	protected static boolean fancyGraphics;

	public final IconCoordinate[] growthStageTextures = new IconCoordinate[]{
		TextureRegistry.getTexture("stardew:block/leaves_apple_flowering_fancy"),
		TextureRegistry.getTexture("stardew:block/leaves_apple_flowering"),
		TextureRegistry.getTexture("stardew:block/leaves_apple_grown_fancy"),
		TextureRegistry.getTexture("stardew:block/leaves_apple_grown")

	};

	public BlockModelLeavesAppleFlowering(Block block) {
		super(block);
	}

	public static void setGraphicsLevel(boolean graphicsLevel) {
		fancyGraphics = graphicsLevel;
	}

	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
			if (data < 16) {
				if (!fancyGraphics) {
					return this.growthStageTextures[1];
				}
				return this.growthStageTextures[0];
			}
			else if (!fancyGraphics) {
				return this.growthStageTextures[3];
			}
			return this.growthStageTextures[2];
	}

	public boolean shouldSideBeRendered(WorldSource blockAccess, int x, int y, int z, int side) {
		int i1 = blockAccess.getBlockId(x, y, z);
		return !fancyGraphics && i1 == this.block.id ? false : super.shouldSideBeRendered(blockAccess, x, y, z, side);
	}
}
