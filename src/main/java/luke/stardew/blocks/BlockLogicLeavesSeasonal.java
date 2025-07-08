package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicLeavesBase;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Season;
import net.minecraft.core.world.season.Seasons;

import java.util.Random;

public class BlockLogicLeavesSeasonal extends BlockLogicLeavesBase {

	public BlockLogicLeavesSeasonal(Block<?> block, Block<?> saplingToDrop) {
		super(block, Material.leaves, saplingToDrop);
	}

	public void animationTick(World world, int x, int y, int z, Random rand) {
		if (world.getSeasonManager().getCurrentSeason() != null && world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_FALL && rand.nextInt(40) == 0) {
			world.spawnParticle("fallingleaf", x, (double)y - 0.10000000149011612, z, 0.0, 0.0, 0.0, 0);
		}

	}

	protected Block<?> getSapling() {
		if (block == StardewBlocks.LEAVES_APPLE) {
			return StardewBlocks.SAPLING_APPLE;
		}
		if (block == StardewBlocks.LEAVES_APPLE_FLOWERING) {
			return StardewBlocks.SAPLING_APPLE;
		}
		if (block == StardewBlocks.LEAVES_APPLE_GOLDEN) {
			return StardewBlocks.SAPLING_APPLE_GOLDEN;
		}
		if (block == StardewBlocks.LEAVES_APPLE_GOLDEN_FLOWERING) {
			return StardewBlocks.SAPLING_APPLE_GOLDEN;
		}
        return null;
    }

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int meta, TileEntity tileEntity) {
		Season season = world.getSeasonManager().getCurrentSeason();
		float dropRate = season != null ? 20.0F / season.saplingDropFactor : 20.0F;
		if (dropCause != EnumDropCause.PICK_BLOCK && dropCause != EnumDropCause.SILK_TOUCH) {
			int numDropped = 1;
			return world.rand.nextInt(MathHelper.floor(dropRate)) != 0 ? null : new ItemStack[]{new ItemStack(getSapling(), numDropped)};
		} else {
			return new ItemStack[]{new ItemStack(getSapling())};
		}
	}
}
