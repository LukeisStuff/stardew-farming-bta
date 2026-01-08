package luke.stardew;

import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;
import net.minecraft.core.world.season.Seasons;

import java.util.Random;

public class WorldFeatureTreeSeasonal extends WorldFeatureTree {
    public final int leavesFloweringID;
    public final int rarity;

    public WorldFeatureTreeSeasonal(int leavesID, int leavesFloweringID, int logID, int heightMod, int rarity) {
        super(leavesID, logID, heightMod);
        this.leavesFloweringID = leavesFloweringID;
        this.rarity = rarity;
    }

    @Override
    public void placeLeaves(World world, int x, int y, int z, Random rand) {
        if (rand.nextInt(this.rarity) == 0) {
            world.setBlockAndMetadataWithNotify(x, y, z, this.leavesFloweringID, world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_FALL ? 1 : 0);
        } else {
            world.setBlockWithNotify(x, y, z, this.leavesID);
        }

    }

    @Override
    public boolean isLeaf(int id) {
        return id == leavesFloweringID || id == leavesID;
    }
}

