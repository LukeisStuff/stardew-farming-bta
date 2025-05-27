package luke.stardew.blocks;

import luke.stardew.WorldFeatureTreeSeasonal;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicSaplingBase;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeFancy;

import java.util.Random;

public class BlockLogicSaplingSeasonal extends BlockLogicSaplingBase {
	private final Block<?> logBlock;
	private final Block<?> leafBlock;
	private final Block<?> leafFloweringBlock;
	private final int flowerRarity;

	public BlockLogicSaplingSeasonal(Block<?> block, Block<?> logBlock, Block<?> leafBlock, Block<?> leafFloweringBlock, int flowerRarity) {
		super(block);
		this.logBlock = logBlock;
		this.leafBlock = leafBlock;
		this.leafFloweringBlock = leafFloweringBlock;
		this.flowerRarity = flowerRarity;
	}

	public void growTree(World world, int i, int j, int k, Random random) {
		WorldFeature treeBig = new WorldFeatureTreeFancy(this.leafBlock.id(), this.logBlock.id());
		WorldFeature treeSmall = new WorldFeatureTreeSeasonal(this.leafBlock.id(), this.leafFloweringBlock.id(), this.logBlock.id(), 4, this.flowerRarity);
		world.setBlock(i, j, k, 0);
		if (!treeSmall.place(world, random, i, j, k) && !treeBig.place(world, random, i, j, k)) {
			world.setBlock(i, j, k, this.id());
		}

	}
}
