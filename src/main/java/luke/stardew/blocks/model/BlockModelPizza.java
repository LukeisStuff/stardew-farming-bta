package luke.stardew.blocks.model;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.Side;

import static luke.stardew.StardewMod.MOD_ID;

public class BlockModelPizza<T extends BlockLogic> extends BlockModelStandard<T> {
	protected IconCoordinate pizzaInner = TextureRegistry.getTexture(MOD_ID + ":block/pizza_inner");

	public BlockModelPizza(Block<T> block, float height) {
		super(block);
		float f = 0.0625F;
		this.withCustomItemBounds(f, 0.0F, f, 1.0F - f, height, 1.0F - f);
	}

	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		//FIXME
		return BlockModelStandard.BLOCK_TEXTURE_MISSING;
		//return data > 0 && side == Side.WEST ? this.pizzaInner : this.atlasIndices[side.getId()];
	}
}
