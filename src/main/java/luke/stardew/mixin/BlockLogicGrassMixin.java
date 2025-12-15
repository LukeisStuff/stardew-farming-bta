package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.BlockLogicGrass;
import net.minecraft.core.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Random;

@Mixin(value = BlockLogicGrass.class, remap = false)
public abstract class BlockLogicGrassMixin {
    @Unique
    private static final Random random = new Random();

    @ModifyArg(method = "updateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;setBlockWithNotify(IIII)Z"), index = 3)
    private int fernOrBush(int id) {
        if (id == Blocks.TALLGRASS_FERN.id()) {
            return random.nextBoolean() ? Blocks.TALLGRASS_FERN.id() : StardewBlocks.BUSH.id();
        }
        return id;
    }
}
