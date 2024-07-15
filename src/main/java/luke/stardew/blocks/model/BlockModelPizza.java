package luke.stardew.blocks.model;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;

public class BlockModelPizza<T extends Block> extends BlockModelStandard<T> {
	protected IconCoordinate pizzaInner = TextureRegistry.getTexture("stardew:block/pizza_inner");

	public BlockModelPizza(Block block) {
		super(block);
		float f = 0.0625F;
		float f1 = 0.25f;
		this.withCustomItemBounds(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
	}

	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		return data > 0 && side == Side.WEST ? this.pizzaInner : this.atlasIndices[side.getId()];
	}
}
