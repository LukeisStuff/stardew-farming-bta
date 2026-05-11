package luke.stardew.mixin;

import net.minecraft.core.block.entity.TileEntityFurnace;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(value = TileEntityFurnace.class, remap = false)
public abstract class TileEntityFurnaceMixin {

    @Unique
    private ItemStack previousInput;

    @Inject(method = "smeltItem()V", at = @At("HEAD"))
    private void captureInput(CallbackInfo ci) {
        TileEntityFurnace furnace = (TileEntityFurnace) (Object) this;
        previousInput = furnace.getItem(0) == null ? null : Objects.requireNonNull(furnace.getItem(0)).copy();
    }

    @Inject(method = "smeltItem()V", at = @At("TAIL"))
    private void restoreBucket(CallbackInfo ci) {
        TileEntityFurnace furnace = (TileEntityFurnace) (Object) this;

        if (previousInput == null) return;

        if (Objects.equals(previousInput.getItem(), Items.BUCKET_MILK)) {
            furnace.setItem(0, new ItemStack(Items.BUCKET));
        }
    }
}

