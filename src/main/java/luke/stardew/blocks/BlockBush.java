package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.BlockFlower;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;

import java.util.Random;

public class BlockBush extends BlockFlower {

	public BlockBush(String key, int id) {
		super(key, id);
		setTicking(true);
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

	public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
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

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		switch (dropCause) {
			case PICK_BLOCK:
			case SILK_TOUCH:
				return new ItemStack[]{new ItemStack(this)};
			default:
				if (meta == 0) {
					int random = (world.rand.nextInt(3));
					if (random == 0) {
						return new ItemStack[]{new ItemStack(StardewItems.seedsCarrot)};
					} else if (random == 1) {
						return new ItemStack[]{new ItemStack(StardewItems.seedsBlueberry)};
					} else
						return new ItemStack[]{new ItemStack(StardewItems.seedsPineapple)};
				}
				if (meta == 1) {
					int random = (world.rand.nextInt(4));
					if (random == 0) {
						return new ItemStack[]{new ItemStack(StardewItems.seedsTomato)};
					} else if (random == 1) {
						return new ItemStack[]{new ItemStack(StardewItems.seedsPotato)};
					} else if (random == 2) {
						return new ItemStack[]{new ItemStack(StardewItems.seedsStrawberry)};
					} else
						return new ItemStack[]{new ItemStack(StardewItems.seedsWatermelon)};
				}
				if (meta == 2) {
					if (world.rand.nextInt(2) == 0) {
						return new ItemStack[]{new ItemStack(StardewItems.seedsCorn)};
					} else
						return new ItemStack[]{new ItemStack(StardewItems.seedsGrapes)};
				}
				if (meta == 3) {
					int random = (world.rand.nextInt(3));
					if (random == 0) {
						return new ItemStack[]{new ItemStack(StardewItems.seedsCauliflower)};
					} else if (random == 1) {
						return new ItemStack[]{new ItemStack(StardewItems.beansCoffee)};
					} else
						return new ItemStack[]{new ItemStack(StardewItems.seedsCranberries)};
				}
				if (meta == 4) {
					return null;
				}
				return null;
		}
	}
}
