package luke.stardew;

import luke.stardew.blocks.BlockLogicBush;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFlowerStackable;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.MethodParametersAnnotation;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.WorldFeatureFlowers;
import net.minecraft.core.world.season.Seasons;

import java.util.Random;

public class WorldFeatureBush extends WorldFeature {
    private final int count;

    @MethodParametersAnnotation(
        names = {"count"}
    )
    public WorldFeatureBush(int count) {
        this.count = count;
    }

    @Override
    public boolean place(World world, Random random, int x, int y, int z) {
        for (int i = 0; i < this.count; ++i) {
            int px = x + random.nextInt(8) - random.nextInt(8);
            int py = y + random.nextInt(4) - random.nextInt(4);
            int pz = z + random.nextInt(8) - random.nextInt(8);

            if (!world.isAirBlock(px, py, pz)) continue;
            if (!StardewBlocks.BUSH.canBlockStay(world, px, py, pz)) continue;

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

            world.setBlockAndMetadata(px, py, pz, StardewBlocks.BUSH.id(), meta);
        }
        return true;
    }
}
