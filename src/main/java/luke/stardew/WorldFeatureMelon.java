package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.generate.feature.WorldFeatureInterface;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class WorldFeatureMelon implements WorldFeatureInterface {

    public boolean place(@NotNull World world, @NotNull Random random, @NotNull TilePosc tilePosc) {
        Biome biome = world.getBlockBiome(tilePosc);

        if (!(biome == Biomes.OVERWORLD_CAATINGA_PLAINS || biome == Biomes.OVERWORLD_CAATINGA || biome == Biomes.OVERWORLD_RAINFOREST || biome == Biomes.OVERWORLD_SWAMPLAND || biome == Biomes.OVERWORLD_SWAMPLAND_MUDDY)) {
            return false;
        }

        for (int l = 0; l < 64; l++) {
            var block = tilePosc.add(
                random.nextInt(8) - random.nextInt(8),
                random.nextInt(4) - random.nextInt(4),
                random.nextInt(8) - random.nextInt(8),
                new TilePos()
            );

            if (world.isAirBlock(block) && world.getBlockType(block.down(new TilePos())) == Blocks.GRASS && StardewBlocks.WATERMELON.canPlaceAt(world, block)) {
                world.setBlockTypeNotify(block, StardewBlocks.WATERMELON);
            }
        }
        return true;
    }
}
