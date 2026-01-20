package luke.stardew.mixin;

import luke.stardew.StardewTags;
import net.minecraft.core.block.BlockLogicFarmland;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockLogicFarmland.class, remap = false)
public abstract class BlockLogicFarmlandMixin {

    @Inject(method = "isCropsNearby", at = @At("RETURN"), cancellable = true)
    private void addTaggedCrops(World world, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (Boolean.TRUE.equals(cir.getReturnValue())) return;

        int id = world.getBlockId(x, y + 1, z);
        if (Blocks.hasTag(id, StardewTags.NEARBY_CROP)) {
            cir.setReturnValue(true);
        }
    }
}
