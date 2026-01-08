package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicEdible;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;
import org.jspecify.annotations.NonNull;

public class BlockLogicCakeChocolate extends BlockLogicEdible {
    public BlockLogicCakeChocolate(@NonNull Block<?> block) {
        super(block, 6, () -> StardewItems.FOOD_CAKE_CHOCOLATE);
    }

    @Override
    public @NonNull AABBdc getBoundsFromState(@NonNull WorldSource source, @NonNull TilePosc tilePos) {
        int data = source.getBlockData(tilePos);
        float gap = 0.0625F;
        float width = (float)(1 + data * 2) / 16.0F;
        float height = 0.5F;
        return new AABBd(width, 0.0F, gap, 0.9375F, height, 0.9375F);
    }

    public int getHealAmount(@NonNull World world, @NonNull TilePosc tilePos) {
        return 3;
    }
}
