package luke.stardew.items.models;

import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static luke.stardew.StardewMod.MOD_ID;

public class ItemModelCanOfWorms extends ItemModelStandard {
	protected IconCoordinate canFull = TextureRegistry.getTexture(MOD_ID + ":item/canOfWorms_empty");

	public ItemModelCanOfWorms(Item item, String namespace) {
		super(item, namespace);
	}

	public @NotNull IconCoordinate getIcon(@Nullable Entity entity, ItemStack itemStack) {
		return itemStack.getMetadata() >= itemStack.getItem().getMaxDamage() ? this.canFull : super.getIcon(entity, itemStack);
	}
}
