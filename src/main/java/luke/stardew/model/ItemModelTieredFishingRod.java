package luke.stardew.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ItemModelTieredFishingRod extends ItemModelStandard {
    protected final String material;
    protected IconCoordinate rodCast;

    public ItemModelTieredFishingRod(Item item, String namespace, String material) {
        super(item, namespace);
        this.material = material;
        this.rodCast = TextureRegistry.getTexture("stardew:item/tool_fishingrod_" + material + "_cast");
    }

    @Override
    public @NotNull IconCoordinate getIcon(@Nullable Entity entity, ItemStack itemStack) {
        if (entity instanceof Player player && itemStack == player.getHeldItem() && player.bobberEntity != null) {
            return this.rodCast;
        }
        return super.getIcon(entity, itemStack);
    }
}
