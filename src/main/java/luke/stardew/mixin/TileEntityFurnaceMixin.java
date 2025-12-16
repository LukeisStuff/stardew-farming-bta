package luke.stardew.mixin;


import net.minecraft.core.block.entity.TileEntityFurnace;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = TileEntityFurnace.class, remap = false)
public class TileEntityFurnaceMixin {
    @Redirect(
        method = "smeltItem()V",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/core/item/ItemStack;stackSize:I",
            opcode = 181
        )
    )
    private void redirectStackSizeDecrement(ItemStack input, int newStackSize) {
        TileEntityFurnace furnace = (TileEntityFurnace) (Object) this;
        if (input != null && input.getItem().equals(Items.BUCKET_MILK)) {
            furnace.setItem(0, new ItemStack(Items.BUCKET, 1));
            if (furnace.worldObj != null && !furnace.worldObj.isClientSide) {
                furnace.worldObj.markBlockNeedsUpdate(furnace.x, furnace.y, furnace.z);
            }
        } else {
            assert input != null;
            input.stackSize = newStackSize;
        }
    }
}
