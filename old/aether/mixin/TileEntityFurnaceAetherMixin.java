package luke.stardew.compat.aether.mixin;

import net.minecraft.core.block.entity.TileEntityFurnace;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamport.aether.item.AetherItems;

import java.util.Objects;

@Pseudo
@Mixin(value = TileEntityFurnace.class, remap = false)
public abstract class TileEntityFurnaceAetherMixin {

    @Inject(method = "smeltItem()V", at = @At("TAIL"))
    private void restoreAetherBucket(CallbackInfo ci) {
        TileEntityFurnace furnace = (TileEntityFurnace) (Object) this;

        if (previousInput == null) return;

        if (Objects.equals(previousInput.getItem(), AetherItems.BUCKET_SKYROOT_MILK)) {
            furnace.setItem(0, new ItemStack(AetherItems.BUCKET_SKYROOT));
        }
    }
}
