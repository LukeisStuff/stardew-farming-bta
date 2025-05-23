package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;
import net.minecraft.core.world.season.Seasons;

import java.util.Random;

public class WorldFeatureTreeSeasonal extends WorldFeatureTree {
	protected final int leavesFloweringID;

	public WorldFeatureTreeSeasonal(int leavesID, int leavesFloweringID, int logID, int heightMod) {
		super(leavesID, logID, heightMod);
		this.leavesFloweringID = leavesFloweringID;
	}

	public void placeLeaves(World world, int x, int y, int z, Random rand) {
		if (rand.nextInt(5) == 0) {
			world.setBlockAndMetadataWithNotify(x, y, z, this.leavesFloweringID, world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_FALL ? 1 : 0);
		} else {
			world.setBlockWithNotify(x, y, z, this.leavesID);
		}

	}

	public boolean isLeaf(int id) {
		return id == leavesFloweringID || id == leavesID;
	}
}

