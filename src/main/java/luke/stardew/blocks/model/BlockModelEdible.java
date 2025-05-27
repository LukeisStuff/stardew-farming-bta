package luke.stardew.blocks.model;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.Side;

public class BlockModelEdible<T extends BlockLogic> extends BlockModelExtended<T> {

	public BlockModelEdible(Block<T> block, float height) {
		super(block);
		float f = 0.0625F;
		this.withCustomItemBounds(f, 0.0, f, 1.0F - f, height, 1.0F - f);
	}

	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		return data > 0 && side == Side.WEST ? this.getIcon(0) : super.getBlockTextureFromSideAndMetadata(side, data);
	}
}
