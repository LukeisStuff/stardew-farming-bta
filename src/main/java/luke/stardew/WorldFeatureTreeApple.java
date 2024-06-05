package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;
import net.minecraft.core.world.season.Seasons;

import java.util.Random;

public class WorldFeatureTreeApple extends WorldFeatureTree {
	public WorldFeatureTreeApple(int leavesID, int logID, int heightMod) {
		super(leavesID, logID, heightMod);
	}

	public void placeLeaves(World world, int x, int y, int z, Random rand) {
		if (rand.nextInt(5) == 0) {
			world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.leavesAppleFlowering.id, world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_FALL ? 1 : 0);
		} else {
			world.setBlockWithNotify(x, y, z, StardewBlocks.leavesApple.id);
		}

	}

	public boolean isLeaf(int id) {
		return id == StardewBlocks.leavesAppleFlowering.id || id == StardewBlocks.leavesApple.id;
	}
}

