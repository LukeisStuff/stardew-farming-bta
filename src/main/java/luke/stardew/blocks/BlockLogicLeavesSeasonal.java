package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicLeavesBase;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.world.season.Season;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public class BlockLogicLeavesSeasonal extends BlockLogicLeavesBase {
    protected final @NonNull Supplier<Block<?>> saplingSupplier;
    protected final Season season;

    public BlockLogicLeavesSeasonal(Block<?> block, @NonNull Supplier<Block<?>> sapling, Season season) {
        super(block, Materials.LEAVES, sapling.get());
        this.saplingSupplier = sapling;
        this.season = season;
    }

}
