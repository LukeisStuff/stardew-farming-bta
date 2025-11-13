package luke.stardew.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.Side;

@Environment(EnvType.CLIENT)
public class BlockModelEdible<T extends BlockLogic> extends BlockModelStandard<T> {
	private final IconCoordinate inner;

	public BlockModelEdible(Block<T> block, float height, String path) {
		super(block);
		float f = 0.0625F;
		this.withCustomItemBounds(f, 0.0, f, 1.0F - f, height, 1.0F - f);
		this.inner = TextureRegistry.getTexture("stardew:block/" + path + "/inner");
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		return data > 0 && side == Side.WEST ? this.inner : super.getBlockTextureFromSideAndMetadata(side, data);
	}
}
