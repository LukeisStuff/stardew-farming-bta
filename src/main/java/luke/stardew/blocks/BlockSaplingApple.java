package luke.stardew.blocks;

import luke.stardew.WorldFeatureTreeApple;
import net.minecraft.core.block.BlockSaplingBase;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeFancy;

import java.util.Random;

public class BlockSaplingApple extends BlockSaplingBase {
	public BlockSaplingApple(String key, int id) {
		super(key, id);
	}

	public void growTree(World world, int i, int j, int k, Random random) {
		WorldFeature treeBig = new WorldFeatureTreeFancy(StardewBlocks.leavesApple.id, StardewBlocks.logApple.id);
		WorldFeature treeSmall = new WorldFeatureTreeApple(StardewBlocks.leavesApple.id, StardewBlocks.logApple.id, 4);
		world.setBlock(i, j, k, 0);
		if (!treeSmall.generate(world, random, i, j, k) && !treeBig.generate(world, random, i, j, k)) {
			world.setBlock(i, j, k, this.id);
		}

	}
}
