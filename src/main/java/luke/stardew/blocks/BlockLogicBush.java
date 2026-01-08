package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFlower;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import net.minecraft.core.world.season.Seasons;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Random;

public class BlockLogicBush extends BlockLogicFlower {
    public BlockLogicBush(Block<?> block) {
        super(block);
        block.setTicking(true);
    }

    @Override
    public void updateTick(@NonNull World world, @NonNull TilePosc tilePos, @NonNull Random rand, boolean isRandomTick) {
        super.updateTick(world, tilePos, rand, isRandomTick);
        if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_SPRING) {
            world.setBlockData(tilePos, 0);
        } else if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_SUMMER) {
            world.setBlockData(tilePos, 1);
        } else if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_FALL) {
            world.setBlockData(tilePos, 2);
        } else if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_WINTER || world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_WINTER) {
            world.setBlockData(tilePos, 3);
        } else {
            world.setBlockData(tilePos, 4);
        }
    }

    @Override
    public int getPlacedData(@Nullable Player player, @NonNull ItemStack itemStack, @NonNull World world, @NonNull TilePosc tilePos, @NonNull Side side, double xHit, double yHit) {
        if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_SPRING) {
            return 0;
        } else if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_SUMMER) {
            return 1;
        } else if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_FALL) {
            return 2;
        } else if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_WINTER || world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_WINTER) {
            return 3;
        } else {
            return 4;
        }
    }

    @Override
    public ItemStack @Nullable [] getBreakResult(@NonNull World world, @NonNull EnumDropCause dropCause, @NonNull TilePosc tilePos, int data, @Nullable TileEntity tileEntity) {
        switch (dropCause) {
            case PICK_BLOCK, SILK_TOUCH:
                return new ItemStack[]{new ItemStack(this)};
            default:
                if (data == 0) {
                    int random = (world.rand.nextInt(3));
                    if (random == 0) {
                        return new ItemStack[]{new ItemStack(StardewItems.SEEDS_CARROT)};
                    } else if (random == 1) {
                        return new ItemStack[]{new ItemStack(StardewItems.SEEDS_BLUEBERRY)};
                    } else
                        return new ItemStack[]{new ItemStack(StardewItems.SEEDS_PINEAPPLE)};
                }
                if (data == 1) {
                    int random = (world.rand.nextInt(3));
                    if (random == 0) {
                        return new ItemStack[]{new ItemStack(StardewItems.SEEDS_TOMATO)};
                    } else if (random == 1) {
                        return new ItemStack[]{new ItemStack(StardewItems.SEEDS_POTATO)};
                    } else
                        return new ItemStack[]{new ItemStack(StardewItems.SEEDS_STRAWBERRY)};
                }
                if (data == 2) {
                    if (world.rand.nextInt(2) == 0) {
                        return new ItemStack[]{new ItemStack(StardewItems.SEEDS_CORN)};
                    } else
                        return new ItemStack[]{new ItemStack(StardewItems.SEEDS_GRAPES)};
                }
                if (data == 3) {
                    int random = (world.rand.nextInt(2));
                    if (random == 0) {
                        return new ItemStack[]{new ItemStack(StardewItems.BEANS_COFFEE)};
                    } else
                        return new ItemStack[]{new ItemStack(StardewItems.SEEDS_CRANBERRIES)};
                }
                if (data == 4) {
                    return null;
                }
                return null;
        }
    }
}
