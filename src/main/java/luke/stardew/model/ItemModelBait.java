package luke.stardew.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ItemModelBait extends ItemModelStandard {
    protected IconCoordinate baitFull = TextureRegistry.getTexture("stardew:item/armor_can_worms_empty");

    public ItemModelBait(Item item, String namespace) {
        super(item, namespace);
    }

    @Override
    public @NonNull IconCoordinate getIcon(@Nullable Entity entity, ItemStack itemStack) {
        return itemStack.getMetadata() >= itemStack.getItem().getMaxDamage() ? this.baitFull : super.getIcon(entity, itemStack);
    }
}
