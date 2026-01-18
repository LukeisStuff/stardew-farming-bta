package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
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
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Random;

public class BlockLogicCropsCauliflower extends BlockLogicCropBase implements IBonemealable {
    public BlockLogicCropsCauliflower(@NonNull Block<?> block) {
        super(block);
    }

    @Override
    public @NonNull AABBdc getBoundsFromState(@NonNull WorldSource source, @NonNull TilePosc tilePos) {
        int meta = source.getBlockData(tilePos);
        float size = 0.0F;
        if (meta == 0) {
            size = 0.375F;
        } else if (meta == 1) {
            size = 0.5F;
        } else if (meta == 2) {
            size = 0.625F;
        } else if (meta == 3) {
            size = 0.75F;
        } else if (meta == 4) {
            size = 0.875F;
        }

        return new AABBd(0.5F - size / 2.0F, 0.0F, 0.5F - size / 2.0F, 0.5F + size / 2.0F, size, 0.5F + size / 2.0F);
    }

    @Override
    public void updateTick(@NonNull World world, @NonNull TilePosc tilePos, @NonNull Random rand, boolean isRandomTick) {
        super.updateTick(world, tilePos, rand, isRandomTick);
        if (world.getBlockLightValue(tilePos.up(new TilePos())) >= 9) {
            int meta = world.getBlockData(tilePos);
            if (meta < 6) {
                float f = this.getGrowthRate(world, tilePos);
                if (rand.nextInt((int) (100.0F / f)) == 0) {
                    ++meta;
                    if (meta == 5) {
                        world.setBlockTypeDataNotify(tilePos, StardewBlocks.CAULIFLOWER, 1);
                    } else {
                        world.setBlockDataNotify(tilePos, meta);
                    }
                }
            }
        }
    }

    @Override
    public @NonNull ItemStack @Nullable [] getBreakResult(@NonNull World world, @NonNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
        return new ItemStack[]{new ItemStack(StardewItems.SEEDS_CAULIFLOWER, 1)};
    }

    @Override
    public @Nullable AABBdc getCollisionAABB(@NonNull WorldSource source, @NonNull TilePosc tilePos) {
        int meta = source.getBlockData(tilePos);
        return meta == 0 ? null : this.getBoundsFromState(source, tilePos).translate(tilePos.x(), tilePos.y(), tilePos.z(), new AABBd());
    }

    @Override
    public boolean onBonemealUsed(@NonNull ItemStack itemStack, @Nullable Player player, @NonNull World world, @NonNull TilePosc tilePos, @NonNull Side side, double xHit, double yHit) {
        if (world.getBlockData(tilePos) >= 5) {
            return false;
        } else {
            if (!world.isClientSide) {
                world.setBlockTypeDataNotify(tilePos, StardewBlocks.CAULIFLOWER, 1);
                if (player == null || player.getGamemode().hasBlockConsumption()) {
                    --itemStack.stackSize;
                }
            }

            return true;
        }
    }
}
