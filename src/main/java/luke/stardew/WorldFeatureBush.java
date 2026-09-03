package luke.stardew;

import luke.stardew.blocks.BlockLogicBush;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFlowerStackable;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.MethodParametersAnnotation;
import net.minecraft.core.world.generate.feature.WorldFeatureFlowers;
import net.minecraft.core.world.generate.feature.WorldFeatureInterface;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import net.minecraft.core.world.season.Seasons;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class WorldFeatureBush implements WorldFeatureInterface {
    private final int count;

    @MethodParametersAnnotation(
        names = {"count"}
    )

    public WorldFeatureBush(int count) {
        this.count = count;
    }

    public boolean place(@NotNull World world, @NotNull Random random, @NotNull TilePosc tilePosc) {
        for (int i = 0; i < this.count; ++i) {
            var block = tilePosc.add(
                random.nextInt(8) - random.nextInt(8),
                random.nextInt(4) - random.nextInt(4),
                random.nextInt(8) - random.nextInt(8),
                new TilePos()
            );

            if (!world.isAirBlock(block)) continue;
            if (!StardewBlocks.BUSH.canStay(world, block)) continue;

            int meta;
            if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_SPRING) {
                meta = 0;
            } else if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_SUMMER) {
                meta = 1;
            } else if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_FALL) {
                meta = 2;
            } else if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_WINTER || world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_WINTER_ENDLESS) {
                meta = 3;
            } else {
                meta = 4;
            }

            if (Block.hasLogicClass(Blocks.getBlock(StardewBlocks.BUSH.id()), BlockLogicBush.class)) {
                int stackSize = WorldFeatureFlowers.getStackSize(random);
                meta = BlockLogicFlowerStackable.setStackCount(meta, stackSize);
            }

            world.setBlockTypeDataNotify(block, StardewBlocks.BUSH, meta);
        }
        return true;
    }
}
