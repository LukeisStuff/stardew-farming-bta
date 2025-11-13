package luke.stardew.items.models;

import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemModelCanOfWorms extends ItemModelExtended {

	public ItemModelCanOfWorms(Item item) {
		super(item);
	}

	@Override
	public @NotNull IconCoordinate getIcon(@Nullable Entity entity, ItemStack itemStack) {
		return itemStack.getMetadata() >= itemStack.getItem().getMaxDamage() ? super.getIcon(entity, itemStack) : this.getIcon(0);
	}
}
