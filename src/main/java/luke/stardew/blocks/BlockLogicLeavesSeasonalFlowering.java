package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.IBonemealable;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Season;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Random;
import java.util.function.Supplier;

public class BlockLogicLeavesSeasonalFlowering extends BlockLogicLeavesSeasonal implements IBonemealable {
	public static final int MASK_GROWTH_DATA = 240;
	public static final int MAX_GROWTH_STATE = 1;
	protected static Item fruit;
	public final Block<?> floweringLeaves;

	public BlockLogicLeavesSeasonalFlowering(Block<?> block, @NonNull Supplier<Block<?>> sapling, Season season, Item fruit, Block<?> floweringLeaves) {
		super(block, sapling, season);
		this.fruit = fruit;
		this.floweringLeaves = floweringLeaves;
	}

	@Override
	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int meta, TileEntity tileEntity) {
		int growthRate = getGrowthRate(meta);
		if (dropCause != EnumDropCause.PICK_BLOCK && dropCause != EnumDropCause.SILK_TOUCH) {
			return growthRate == 0 ? null : new ItemStack[]{new ItemStack(fruit, world.rand.nextInt(2) + 1)};
		} else {
			return new ItemStack[]{new ItemStack(this)};
		}
	}

	@Override
	public void onBlockLeftClicked(World world, int x, int y, int z, Player player, Side side, double xHit, double yHit) {
		this.onBlockRightClicked(world, x, y, z, player, null, 0.0F, 0.0F);
	}

	@Override
	public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xPlaced, double yPlaced) {
		return this.harvest(world, x, y, z, player);
	}

	public boolean harvest(World world, int x, int y, int z, @Nullable Player player) {
		int meta = world.getBlockMetadata(x, y, z);
		int growthRate = getGrowthRate(meta);
		if (growthRate > 0) {
			if (player != null) {
				world.playSoundAtEntity(player, player, "item.pickup", 1.0F, 1.0F);
			}

			if (!world.isClientSide) {
				this.dropBlockWithCause(world, EnumDropCause.WORLD, x, y, z, meta, null, null);
			}

			world.setBlockMetadataWithNotify(x, y, z, setGrowthRate(meta, 0));
			world.scheduleBlockUpdate(x, y, z, this.floweringLeaves.id(), this.tickDelay());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onActivatorInteract(World world, int x, int y, int z, TileEntityActivator activator, Direction direction) {
		this.harvest(world, x, y, z, null);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		int meta = world.getBlockMetadata(x, y, z);
		int growthRate = getGrowthRate(meta);
		if (world.getSeasonManager().getCurrentSeason() == season) {
			if (rand.nextInt(20) == 0 && growthRate == 0) {
				world.setBlockMetadataWithNotify(x, y, z, setGrowthRate(meta, MAX_GROWTH_STATE));
				world.scheduleBlockUpdate(x, y, z, this.floweringLeaves.id(), this.tickDelay());
			}
		} else if (growthRate > 0) {
			world.setBlockMetadataWithNotify(x, y, z, meta & 15);
			world.scheduleBlockUpdate(x, y, z, this.floweringLeaves.id(), this.tickDelay());
		}

	}

	@Override
	public boolean onBonemealUsed(ItemStack itemstack, @Nullable Player player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		int meta = world.getBlockMetadata(blockX, blockY, blockZ);
		if (getGrowthRate(meta) != 0) {
			return false;
		} else {
			if (!world.isClientSide) {
				if (world.getSeasonManager().getCurrentSeason() != season) {
					return true;
				}

				world.setBlockMetadataWithNotify(blockX, blockY, blockZ, setGrowthRate(meta, MAX_GROWTH_STATE));
				if (player == null || player.getGamemode().consumeBlocks()) {
					--itemstack.stackSize;
				}
			}

			return true;
		}
	}

	public static int getGrowthRate(int meta) {
		return (meta & MASK_GROWTH_DATA) >> 4;
	}

	public static int setGrowthRate(int meta, int growthRate) {
		return meta & -241 | growthRate << 4 & MASK_GROWTH_DATA;
	}
}
