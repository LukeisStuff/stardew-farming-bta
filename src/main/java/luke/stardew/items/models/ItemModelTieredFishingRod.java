package luke.stardew.items.models;

import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemModelTieredFishingRod extends ItemModelExtended {

	public ItemModelTieredFishingRod(Item item) {
		super(item);
	}

	@Override
	public @NotNull IconCoordinate getIcon(@Nullable Entity entity, ItemStack itemStack) {
		return entity instanceof Player && itemStack == ((Player) entity).getHeldItem() && ((Player) entity).bobberEntity != null ? this.getIcon(0) : super.getIcon(entity, itemStack);
	}
}
