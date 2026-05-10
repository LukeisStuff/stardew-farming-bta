package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicEdible;
import net.minecraft.core.item.Item;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.function.Supplier;

public class BlockLogicEdibleCustom extends BlockLogicEdible {
    public final float height;

    public BlockLogicEdibleCustom(Block<?> block, float height, Supplier<Item> dropItemSupplier) {
        super(block, 6, dropItemSupplier);
        this.height = height;
    }

    @Override
    public AABBdc getBoundsFromState(@NotNull WorldSource source, @NotNull TilePosc tilePos) {
        int l = source.getBlockData(tilePos);
        float f = 0.0625f;
        float f1 = (1 + l * 2) / 16.0f;
        return new AABBd(f1, 0.0, f, 1.0f - f, this.height, 1.0f - f);
    }

    @Override
    public int getHealAmount(@NotNull World world, @NotNull TilePosc tilePosc) {
        return 3;
    }
}
