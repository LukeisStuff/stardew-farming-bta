package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLeavesBase;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;

import java.util.Random;

public class BlockLeavesAppleGolden extends BlockLeavesBase {
	public BlockLeavesAppleGolden(String key, int id) {
		super(key, id, Material.leaves);
	}

	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if (world.seasonManager.getCurrentSeason() != null && world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_WINTER && rand.nextInt(40) == 0) {
			world.spawnParticle("fallingleaf", x, (double)y - 0.10000000149011612, z, 0.0, 0.0, 0.0, 0);
		}
	}

	protected Block getSapling() {
		return StardewBlocks.saplingAppleGolden;
	}
}
