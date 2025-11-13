package luke.stardew.blocks;

import luke.stardew.StardewMod;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicLeavesBase;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Supplier;

public class BlockLogicLeavesSeasonal extends BlockLogicLeavesBase {
	public @NotNull Supplier<Block<?>> saplingSupplier;

	public BlockLogicLeavesSeasonal(Block<?> block, @NotNull Supplier<Block<?>> sapling) {
		super(block, Material.leaves, null);
		this.saplingSupplier = sapling;
	}

	@Override
	public Block<?> getSapling() {
		return saplingSupplier.get();
	}

	@Override
	public void animationTick(World world, int x, int y, int z, Random rand) {
		if (world.seasonManager.getCurrentSeason() != null && world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_FALL && rand.nextInt(40) == 0) {
			world.spawnParticle(StardewMod.MOD_ID + "$fallingleaf", x, y - 0.10000000149011612, z, 0.0, 0.0, 0.0, 0);
		}
	}
}
