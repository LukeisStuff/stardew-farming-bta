package luke.stardew.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ItemModelStandard.class)
abstract public class itemModelFixMixin extends ItemModel {


    public itemModelFixMixin(Item item) {
        super(item);
    }

    @Definition(id = "getTexture", method = "Lnet/minecraft/client/render/texture/stitcher/TextureRegistry;getTexture(Ljava/lang/String;)Lnet/minecraft/client/render/texture/stitcher/IconCoordinate;")
    @Expression("getTexture(?)")
    @ModifyExpressionValue(method = "<init>(Lnet/minecraft/core/item/Item;Ljava/lang/String;)V", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private static IconCoordinate fixNamespace(IconCoordinate original, @Local(type = Item.class) Item item) {
        return TextureRegistry.getTexture(item.namespaceID);
    }
}
