package luke.stardew.mixin;

import luke.stardew.StardewTags;
import net.minecraft.core.block.BlockLogicFarmland;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockLogicFarmland.class, remap = false)
public abstract class BlockLogicFarmlandMixin {

    @Inject(method = "isCropsNearby", at = @At("RETURN"), cancellable = true)
    private void addTaggedCrops(World world, TilePosc tilePos, CallbackInfoReturnable<Boolean> cir) {
        if (Boolean.TRUE.equals(cir.getReturnValue())) return;

        if (world.getBlockType(tilePos.up(new TilePos())).hasTag(StardewTags.NEARBY_CROP)) {
            cir.setReturnValue(true);
        }
    }
}
