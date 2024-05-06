package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

public class WorldFeatureMelon extends WorldFeature {
	public WorldFeatureMelon() {
	}
	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		for(int l = 0; l < 64; ++l) {
			int i1 = x + random.nextInt(8) - random.nextInt(8);
			int j1 = y + random.nextInt(4) - random.nextInt(4);
			int k1 = z + random.nextInt(8) - random.nextInt(8);
			if (world.getBlockBiome(x, y, z) == Biomes.OVERWORLD_RAINFOREST) {
				if (world.isAirBlock(i1, j1, k1) && world.getBlockId(i1, j1 - 1, k1) == Block.grass.id && StardewBlocks.watermelon.canPlaceBlockAt(world, i1, j1, k1)) {
					if (random.nextInt(50) == 0) {
						world.setBlockAndMetadata(i1, j1, k1, StardewBlocks.watermelon.id, random.nextInt(4));
					} else {
						world.setBlockWithNotify(i1, j1, k1, StardewBlocks.watermelon.id);
					}
				}
			}
			if (world.getBlockBiome(x, y, z) == Biomes.OVERWORLD_SWAMPLAND) {
				if (world.isAirBlock(i1, j1, k1) && world.getBlockId(i1, j1 - 1, k1) == Block.mud.id && StardewBlocks.watermelon.canPlaceBlockAt(world, i1, j1, k1)) {
					if (random.nextInt(50) == 0) {
						world.setBlockAndMetadata(i1, j1, k1, StardewBlocks.watermelon.id, random.nextInt(4));
					} else {
						world.setBlockWithNotify(i1, j1, k1, StardewBlocks.watermelon.id);
					}
				}
			}
			if (world.getBlockBiome(x, y, z) == Biomes.OVERWORLD_SWAMPLAND) {
				if (world.isAirBlock(i1, j1, k1) && world.getBlockId(i1, j1 - 1, k1) == Block.grass.id && StardewBlocks.watermelon.canPlaceBlockAt(world, i1, j1, k1)) {
					if (random.nextInt(50) == 0) {
						world.setBlockAndMetadata(i1, j1, k1, StardewBlocks.watermelon.id, random.nextInt(4));
					} else {
						world.setBlockWithNotify(i1, j1, k1, StardewBlocks.watermelon.id);
					}
				}
			}
		}
		return true;
	}
}
