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
import net.minecraft.core.world.pos.TilePosc;
import net.minecraft.core.world.season.Season;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Random;
import java.util.function.Supplier;

public class BlockLogicLeavesSeasonalFlowering extends BlockLogicLeavesSeasonal implements IBonemealable {
    public static final int MASK_GROWTH_DATA = 240;
    public static final int MAX_GROWTH_STATE = 1;

    private final Supplier<Item> fruit;
    private final Block<?> floweringLeaves;

    public BlockLogicLeavesSeasonalFlowering(@NonNull Block<?> block, @NonNull Supplier<Block<?>> sapling, Season season, Supplier<Item> fruit, Block<?> floweringLeaves) {
        super(block, sapling, season);
        this.fruit = fruit;
        this.floweringLeaves = floweringLeaves;
    }

    @Override
    public ItemStack @Nullable [] getBreakResult(@NonNull World world, @NonNull EnumDropCause dropCause, @NonNull TilePosc tilePos, int data, @Nullable TileEntity tileEntity) {
        int growthRate = getGrowthRate(data);
        if (dropCause != EnumDropCause.PICK_BLOCK && dropCause != EnumDropCause.SILK_TOUCH) {
            return growthRate == 0 ? null : new ItemStack[]{new ItemStack(fruit.get(), world.rand.nextInt(2) + 1)};
        } else {
            return new ItemStack[]{new ItemStack(this.block)};
        }
    }

    @Override
    public boolean onInteracted(@NonNull World world, @NonNull TilePosc tilePos, @NonNull Player player, @Nullable Side side, double xHit, double yHit) {
        return harvest(world, tilePos, player);
    }

    @Override
    public void onAttacked(@NonNull World world, @NonNull TilePosc tilePos, @NonNull Player player, @NonNull Side side, double xHit, double yHit) {
        // Left-click harvesting (same as right-click)
        harvest(world, tilePos, player);
    }

    private boolean harvest(@NonNull World world, @NonNull TilePosc tilePos, @Nullable Player player) {
        int meta = world.getBlockData(tilePos);
        int growthRate = getGrowthRate(meta);

        if (growthRate > 0) {
            if (player != null) {
                world.playSoundAtEntity(player, player, "item.pickup", 1.0F, 1.0F);
            }

            if (!world.isClientSide) {
                dropWithCause(world, EnumDropCause.WORLD, tilePos, meta, null, player);
            }

            world.setBlockDataNotify(tilePos, setGrowthRate(meta, 0));
            world.scheduleBlockUpdate(tilePos, floweringLeaves, tickDelay());
            return true;
        }
        return false;
    }

    @Override
    public void onActivatorInteracted(@NonNull World world, @NonNull TilePosc tilePos, @NonNull TileEntityActivator activator, @NonNull Direction direction) {
        harvest(world, tilePos, null);
    }

    @Override
    public void updateTick(@NonNull World world, @NonNull TilePosc tilePos, @NonNull Random rand, boolean isRandomTick) {
        super.updateTick(world, tilePos, rand, isRandomTick);

        int meta = world.getBlockData(tilePos);
        int growthRate = getGrowthRate(meta);

        Season currentSeason = world.getSeasonManager().getCurrentSeason();

        if (currentSeason == season) {
            if (rand.nextInt(20) == 0 && growthRate == 0) {
                world.setBlockDataNotify(tilePos, setGrowthRate(meta, MAX_GROWTH_STATE));
                world.scheduleBlockUpdate(tilePos, floweringLeaves, tickDelay());
            }
        } else if (growthRate > 0) {
            world.setBlockDataNotify(tilePos, meta & 15); // Reset growth bits
            world.scheduleBlockUpdate(tilePos, floweringLeaves, tickDelay());
        }
    }

    @Override
    public boolean onBonemealUsed(@NonNull ItemStack itemStack, @Nullable Player player, @NonNull World world, @NonNull TilePosc tilePos, @NonNull Side side, double xHit, double yHit) {
        int meta = world.getBlockData(tilePos);
        if (getGrowthRate(meta) != 0) {
            return false;
        }

        if (!world.isClientSide) {
            Season currentSeason = world.getSeasonManager().getCurrentSeason();

            // Only allow bonemeal in the correct season (or always, depending on design)
            if (currentSeason != season) {
                return true; // Consume bonemeal but do nothing
            }

            world.setBlockDataNotify(tilePos, setGrowthRate(meta, MAX_GROWTH_STATE));

            if (player == null || player.getGamemode().hasBlockConsumption()) {
                --itemStack.stackSize;
            }
        }

        return true;
    }

    public static int getGrowthRate(int meta) {
        return (meta & MASK_GROWTH_DATA) >> 4;
    }

    public static int setGrowthRate(int meta, int growthRate) {
        return (meta & ~MASK_GROWTH_DATA) | ((growthRate << 4) & MASK_GROWTH_DATA);
    }
}
