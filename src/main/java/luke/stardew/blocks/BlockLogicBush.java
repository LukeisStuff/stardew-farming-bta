package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFlower;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class BlockLogicBush extends BlockLogicFlower {

	public BlockLogicBush(Block<?> block) {
		super(block);
		block.setTicking(true);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_SPRING) {
			world.setBlockMetadataWithNotify(x, y, z, 0);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_SUMMER) {
			world.setBlockMetadataWithNotify(x, y, z, 1);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_FALL) {
			world.setBlockMetadataWithNotify(x, y, z, 2);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_WINTER) {
			world.setBlockMetadataWithNotify(x, y, z, 3);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_WINTER_ENDLESS) {
			world.setBlockMetadataWithNotify(x, y, z, 3);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_HELL) {
			world.setBlockMetadataWithNotify(x, y, z, 4);
		}
	}

	@Override
	public void onBlockPlacedByMob(World world, int x, int y, int z, @NotNull Side side, Mob mob, double xPlaced, double yPlaced) {
		if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_SPRING) {
			world.setBlockMetadataWithNotify(x, y, z, 0);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_SUMMER) {
			world.setBlockMetadataWithNotify(x, y, z, 1);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_FALL) {
			world.setBlockMetadataWithNotify(x, y, z, 2);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_WINTER) {
			world.setBlockMetadataWithNotify(x, y, z, 3);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_WINTER_ENDLESS) {
			world.setBlockMetadataWithNotify(x, y, z, 3);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_HELL) {
			world.setBlockMetadataWithNotify(x, y, z, 4);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.NULL) {
			world.setBlockMetadataWithNotify(x, y, z, 4);
		}
	}

	@Override
	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		switch (dropCause) {
			case PICK_BLOCK:
			case SILK_TOUCH:
				return new ItemStack[]{new ItemStack(this)};
			default:
				if (meta == 0) {
					int random = (world.rand.nextInt(3));
					if (random == 0) {
						return new ItemStack[]{new ItemStack(StardewItems.SEEDS_CARROT)};
					} else if (random == 1) {
						return new ItemStack[]{new ItemStack(StardewItems.SEEDS_BLUEBERRY)};
					} else
						return new ItemStack[]{new ItemStack(StardewItems.SEEDS_PINEAPPLE)};
				}
				if (meta == 1) {
					int random = (world.rand.nextInt(4));
					if (random == 0) {
						return new ItemStack[]{new ItemStack(StardewItems.SEEDS_TOMATO)};
					} else if (random == 1) {
						return new ItemStack[]{new ItemStack(StardewItems.SEEDS_POTATO)};
					} else if (random == 2) {
						return new ItemStack[]{new ItemStack(StardewItems.SEEDS_STRAWBERRY)};
					} else
						return new ItemStack[]{new ItemStack(StardewItems.SEEDS_WATERMELON)};
				}
				if (meta == 2) {
					if (world.rand.nextInt(2) == 0) {
						return new ItemStack[]{new ItemStack(StardewItems.SEEDS_CORN)};
					} else
						return new ItemStack[]{new ItemStack(StardewItems.SEEDS_GRAPES)};
				}
				if (meta == 3) {
					int random = (world.rand.nextInt(3));
					if (random == 0) {
						return new ItemStack[]{new ItemStack(StardewItems.SEEDS_CAULIFLOWER)};
					} else if (random == 1) {
						return new ItemStack[]{new ItemStack(StardewItems.BEANS_COFFE)};
					} else
						return new ItemStack[]{new ItemStack(StardewItems.SEEDS_CRANBERRIES)};
				}
				if (meta == 4) {
					return null;
				}
				return null;
		}
	}
}
