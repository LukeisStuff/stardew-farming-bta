package luke.stardew.items.models;

import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static luke.stardew.StardewMod.MOD_ID;

public class ItemModelCanOfWorms extends ItemModelExtended {

	public ItemModelCanOfWorms(Item item) {
		super(item);
	}

	public @NotNull IconCoordinate getIcon(@Nullable Entity entity, ItemStack itemStack) {
		return itemStack.getMetadata() >= itemStack.getItem().getMaxDamage() ? super.getIcon(entity, itemStack) : this.getIcon(0);
	}
}
