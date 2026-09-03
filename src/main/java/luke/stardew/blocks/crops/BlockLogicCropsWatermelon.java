package luke.stardew.blocks.crops;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFarmland;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.IBonemealable;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import net.minecraft.core.world.season.Season;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.Random;

public class BlockLogicCropsWatermelon extends BlockLogicCropBase implements IBonemealable {

    public BlockLogicCropsWatermelon(Block<?> block) {
        super(block);
    }

    @Override
    public @NotNull AABBdc getBoundsFromState(@NotNull WorldSource source, @NotNull TilePosc tilePos) {
        int meta = source.getBlockData(tilePos);
        float onePix = 0.0625F;
        float size = 0.0F;
        if (meta == 0) {
            size = 6.0F * onePix;
        } else if (meta == 1) {
            size = 8.0F * onePix;
        } else if (meta == 2) {
            size = 10.0F * onePix;
        } else if (meta == 3) {
            size = 12.0F * onePix;
        } else if (meta == 4) {
            size = 14.0F * onePix;
        }

        return new AABBd(0.5F - size / 2.0F, 0.0, 0.5F - size / 2.0F, 0.5F + size / 2.0F, size, 0.5F + size / 2.0F);
    }

    @Override
    public void updateTick(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Random rand, boolean isRandomTick) {
        super.updateTick(world, tilePos, rand, isRandomTick);;
        Season current = world.getSeasonManager().getCurrentSeason();

        if (world.getBlockLightValue(tilePos.up(new TilePos())) >= 9 && season.contains(current)) {
            int meta = world.getBlockData(tilePos);
            if (meta < 6) {
                float f = this.getGrowthRate(world, tilePos);
                if (rand.nextInt((int) (100.0F / f)) == 0) {
                    ++meta;
                    if (meta == 5) {
                        world.setBlockTypeDataNotify(tilePos, StardewBlocks.WATERMELON, 1);
                    } else {
                        world.setBlockDataNotify(tilePos, meta);
                    }
                }
            }
        }
    }

    public void fertilize(World world, TilePosc tilePosc) {
        world.setBlockTypeDataNotify(tilePosc, StardewBlocks.WATERMELON, 1);
    }

    @Override
    public float getGrowthRate(World world, TilePosc tilePos) {
        float growthRate = 1.0F;

        for (int dx = tilePos.x() - 1; dx <= tilePos.x() + 1; ++dx) {
            for (int dz = tilePos.z() - 1; dz <= tilePos.z() + 1; ++dz) {
                TilePos tilepos = new TilePos(dx, tilePos.y() - 1, dz);
                Block<?> block = world.getBlockType(tilepos);
                float growthRateMod = 0.0F;
                if (block.id() == Blocks.FARMLAND_DIRT.id()) {
                    growthRateMod = 1.0F;
                    if (world.getBlockData(tilepos) > 0) {
                        growthRateMod = 3.0F;
                    }
                }

                if (dx != tilePos.x() || dz != tilePos.z()) {
                    growthRateMod /= 4.0F;
                }

                growthRate += growthRateMod;
            }
        }

        boolean isFertilized = BlockLogicFarmland.isFertilized(world.getBlockData(new TilePos(tilePos.x(), tilePos.y() - 1, tilePos.z())));
        if (!isFertilized) {
            if (world.getSeasonManager().getCurrentSeason() != null) {
                growthRate *= world.getSeasonManager().getCurrentSeason().cropGrowthFactor;
            }
        } else {
            growthRate *= 1.5F;
        }

        return growthRate;
    }

    @Override
    public ItemStack[] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int meta, TileEntity tileEntity) {
        return new ItemStack[]{new ItemStack(StardewItems.SEEDS_WATERMELON, 1)};
    }

    @Override
    public @Nullable AABBdc getCollisionAABB(@NotNull WorldSource source, @NotNull TilePosc tilePos) {
        int meta = source.getBlockData(tilePos);
        return meta == 0 ? null : this.getBoundsFromState(source, tilePos).translate(tilePos.x(), tilePos.y(), tilePos.z(), new AABBd());
    }


    @Override
    public boolean onBonemealUsed(@NotNull ItemStack itemStack, @Nullable Player player, @NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
        if (world.getBlockData(tilePos) >= 5) {
            return false;
        } else {
            if (!world.isClientSide) {
                this.fertilize(world, tilePos);
                if (player == null || player.getGamemode().hasBlockConsumption()) {
                    --itemStack.stackSize;
                }
            }

            return true;
        }
    }

}
