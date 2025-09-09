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

	public void initCastTexture() {
		//NamespaceID id = this.icon.namespaceId;
		//For some reason icons omit the atlas
		//rodCast = TextureRegistry.getTexture(id.namespace() + ":item/" + id.value() + "_cast");
	}

	public @NotNull IconCoordinate getIcon(@Nullable Entity entity, ItemStack itemStack) {
		return entity instanceof Player && itemStack == ((Player) entity).getHeldItem() && ((Player) entity).bobberEntity != null ? this.getIcon(0) : super.getIcon(entity, itemStack);
	}
}
