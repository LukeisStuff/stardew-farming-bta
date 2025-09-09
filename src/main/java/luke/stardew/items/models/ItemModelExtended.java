package luke.stardew.items.models;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemModelExtended extends ItemModelStandard {
	public final List<IconCoordinate> icons = new ArrayList<>();

	public ItemModelExtended(Item item) {
		super(item, null);
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
