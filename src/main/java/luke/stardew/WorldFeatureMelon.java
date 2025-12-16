package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

public class WorldFeatureMelon extends WorldFeature {

    @Override
    public boolean place(World world, Random random, int x, int y, int z) {
        Biome biome = world.getBlockBiome(x, y, z);

        if (!(biome == Biomes.OVERWORLD_CAATINGA_PLAINS || biome == Biomes.OVERWORLD_CAATINGA || biome == Biomes.OVERWORLD_RAINFOREST || biome == Biomes.OVERWORLD_SWAMPLAND || biome == Biomes.OVERWORLD_SWAMPLAND_MUDDY)) {
            return false;
        }

        for (int l = 0; l < 64; l++) {
            int i1 = x + random.nextInt(8) - random.nextInt(8);
            int j1 = y + random.nextInt(4) - random.nextInt(4);
            int k1 = z + random.nextInt(8) - random.nextInt(8);
            if (world.isAirBlock(i1, j1, k1) && world.getBlockId(i1, k1 - 1, k1) == Blocks.GRASS.id() && StardewBlocks.WATERMELON.canPlaceBlockAt(world, i1, j1, k1)) {
                world.setBlockWithNotify(i1, j1, k1, StardewBlocks.WATERMELON.id());
            }
        }
        return true;
    }
}
