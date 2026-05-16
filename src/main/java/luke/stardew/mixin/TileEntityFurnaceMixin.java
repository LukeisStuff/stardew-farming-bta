package luke.stardew.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import luke.stardew.items.StardewItems;
import net.minecraft.core.block.entity.TileEntityFurnace;
import net.minecraft.core.item.ItemBucket;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = TileEntityFurnace.class, remap = false)
public abstract class TileEntityFurnaceMixin {

    @Definition(id = "furnaceItemStacks", field = "Lnet/minecraft/core/block/entity/TileEntityFurnace;furnaceItemStacks:[Lnet/minecraft/core/item/ItemStack;")
    @Expression("this.furnaceItemStacks[0] = null")
    @WrapOperation(method = "smeltItem()V", at = @At("MIXINEXTRAS:EXPRESSION"))
    private void restoreBucket(ItemStack[] array, int index, ItemStack value, Operation<Void> original, @Local(type = ItemStack.class, name = "itemstack") ItemStack itemStack) {
        var bucket = array[index];

        if (
            bucket.getItem() instanceof ItemBucket
            && ItemBucket.getState(bucket).equals(ItemBucket.STATE_MILK)
            && itemStack.getItem().equals(StardewItems.CHEESE)
        ) {
            bucket.stackSize++;
            ItemBucket.setState(bucket, ItemBucket.STATE_EMPTY);
            return;
        }

       original.call(array, index, value);
    }
}

