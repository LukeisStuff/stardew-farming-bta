package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicLeavesBase;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Season;
import org.jspecify.annotations.NonNull;
import turniplabs.halplibe.helper.EnvironmentHelper;

import java.util.Random;
import java.util.function.Supplier;

import static luke.stardew.StardewMod.MOD_ID;

public class BlockLogicLeavesSeasonal extends BlockLogicLeavesBase {
	public @NonNull Supplier<Block<?>> saplingSupplier;
	protected static Season season;

	public BlockLogicLeavesSeasonal(Block<?> block, @NonNull Supplier<Block<?>> sapling, Season season) {
		super(block, Material.leaves, null);
		this.saplingSupplier = sapling;
		this.season = season;
	}

	@Override
	public void animationTick(World world, int x, int y, int z, Random rand) {
		if (world.getSeasonManager().getCurrentSeason() != null && world.getSeasonManager().getCurrentSeason() == season && rand.nextInt(40) == 0 && !EnvironmentHelper.isServerEnvironment()) {
			world.spawnParticle(MOD_ID + "$fallingleaf", x, (double) y - (double) 0.1F, z, 0.0F, 0.0F, 0.0F, 0);
		}
	}
}
