package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;
import net.minecraft.core.world.season.Seasons;

import java.util.Random;

@Deprecated
public class WorldFeatureTreeAppleGolden extends WorldFeatureTree {
	public WorldFeatureTreeAppleGolden(int leavesID, int logID, int heightMod) {
		super(leavesID, logID, heightMod);
	}

	public void placeLeaves(World world, int x, int y, int z, Random rand) {
		if (rand.nextInt(20) == 0) {
			world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.LEAVES_APPLE_GOLDEN_FLOWERING.id(), world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_WINTER ? 1 : 0);
		} else {
			world.setBlockWithNotify(x, y, z, StardewBlocks.LEAVES_APPLE_GOLDEN.id());
		}

	}

	public boolean isLeaf(int id) {
		return id == StardewBlocks.LEAVES_APPLE_GOLDEN_FLOWERING.id() || id == StardewBlocks.LEAVES_APPLE_GOLDEN.id();
	}
}
