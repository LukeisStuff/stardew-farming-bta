package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicGrass;
import net.minecraft.core.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Random;

@Mixin(value = BlockLogicGrass.class, remap = false)
public abstract class BlockLogicGrassMixin {
    @Unique
    private static final Random random = new Random();

    @ModifyArg(method = "updateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;setBlockTypeNotify(Lnet/minecraft/core/world/pos/TilePosc;Lnet/minecraft/core/block/Block;)Z", ordinal = 2))
    private @NotNull Block<?> fernOrBush(Block<?> block) {
        if (block == Blocks.TALLGRASS_FERN) {
            if (random.nextInt(10) == 0) {
                return StardewBlocks.BUSH;
            }
            return Blocks.TALLGRASS_FERN;
        }

        return block;
    }
}
