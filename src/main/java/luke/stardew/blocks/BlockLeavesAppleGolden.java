package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicLeavesBase;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;

import java.util.Random;

public class BlockLeavesAppleGolden extends BlockLogicLeavesBase {
	public BlockLeavesAppleGolden(Block<?> block) {
		super(block, Material.leaves, StardewBlocks.saplingAppleGolden);
	}

	@Override
	public void animationTick(World world, int x, int y, int z, Random rand) {
		if (world.seasonManager.getCurrentSeason() != null && world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_WINTER && rand.nextInt(40) == 0) {
			world.spawnParticle("fallingleaf", x, (double)y - 0.10000000149011612, z, 0.0, 0.0, 0.0, 0);
		}
	}
}
