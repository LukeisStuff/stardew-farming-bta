package luke.stardew.blocks;

import luke.stardew.WorldFeatureTreeAppleGolden;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicSaplingBase;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeFancy;

import java.util.Random;

public class BlockSaplingAppleGolden extends BlockLogicSaplingBase {
	public BlockSaplingAppleGolden(Block<?> block) {
		super(block);
	}

	public void growTree(World world, int i, int j, int k, Random random) {
		WorldFeature treeBig = new WorldFeatureTreeFancy(StardewBlocks.leavesAppleGolden.id(), StardewBlocks.logAppleGolden.id());
		WorldFeature treeSmall = new WorldFeatureTreeAppleGolden(StardewBlocks.leavesAppleGolden.id(), StardewBlocks.logAppleGolden.id(), 4);
		world.setBlock(i, j, k, 0);
		if (!treeSmall.place(world, random, i, j, k) && !treeBig.place(world, random, i, j, k)) {
			world.setBlock(i, j, k, this.id());
		}

	}
}
