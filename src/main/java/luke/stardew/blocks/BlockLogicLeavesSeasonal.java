package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicLeavesBase;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.world.season.Season;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class BlockLogicLeavesSeasonal extends BlockLogicLeavesBase {
    protected final @NotNull Supplier<Block<?>> saplingSupplier;
    protected final Season season;

    public BlockLogicLeavesSeasonal(Block<?> block, @NotNull Supplier<Block<?>> sapling, Season season) {
        super(block, Materials.LEAVES, sapling.get());
        this.saplingSupplier = sapling;
        this.season = season;
    }

}
