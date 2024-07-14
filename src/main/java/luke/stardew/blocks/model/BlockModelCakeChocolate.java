package luke.stardew.blocks.model;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;

public class BlockModelCakeChocolate<T extends Block> extends BlockModelStandard<T> {
	protected IconCoordinate cakeInner = TextureRegistry.getTexture("stardew:block/chocoCakeSideBit");

	public BlockModelCakeChocolate(Block block) {
		super(block);
		float f = 0.0625F;
		float f1 = 0.5F;
		this.withCustomItemBounds(f, 0.0, f, 1.0F - f, f1, 1.0F - f);
	}

	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		return data > 0 && side == Side.WEST ? this.cakeInner : this.atlasIndices[side.getId()];
	}
}
