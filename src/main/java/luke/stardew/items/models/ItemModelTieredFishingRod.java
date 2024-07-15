package luke.stardew.items.models;

import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static luke.stardew.StardewMod.MOD_ID;

public class ItemModelTieredFishingRod extends ItemModelStandard {
	protected IconCoordinate rodCast;

	public ItemModelTieredFishingRod(Item item, String namespace, String type) {
		super(item, namespace);
		rodCast = TextureRegistry.getTexture(MOD_ID + ":item/fishingrod_" + type + "_cast");
	}

	public @NotNull IconCoordinate getIcon(@Nullable Entity entity, ItemStack itemStack) {
		return entity instanceof EntityPlayer && itemStack == ((EntityPlayer)entity).getHeldItem() && ((EntityPlayer)entity).bobberEntity != null ? this.rodCast : super.getIcon(entity, itemStack);
	}
}
