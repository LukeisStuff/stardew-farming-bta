package luke.stardew.blocks.model;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;

import java.util.ArrayList;
import java.util.List;

public class BlockModelExtended<T extends BlockLogic> extends BlockModelStandard<T> {
	public final List<IconCoordinate> icons = new ArrayList<>();
	public BlockModelExtended(Block<T> block) {
		super(block);
	}

	public IconCoordinate getIcon(int index) {
		if (index < 0 || index >= this.icons.size()) {
			return BlockModelStandard.BLOCK_TEXTURE_UNASSIGNED;
		}

		return this.icons.get(index);
	}

	public void addIcon(String texKey) {
		addIcon(TextureRegistry.getTexture(texKey));
	}

	public void addIcon(IconCoordinate coordinate) {
		this.icons.add(coordinate);
	}
}
