package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicFluid;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.support.ISupport;
import net.minecraft.core.block.support.ISupportable;
import net.minecraft.core.block.support.PartialSupport;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemFireStriker;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;
import org.joml.Vector3i;

import java.util.Random;

public class BlockLogicWaxCandle extends BlockLogic implements ISupportable {
    public final boolean burning;

    public BlockLogicWaxCandle(Block<?> block, boolean flag) {
        super(block, Materials.DECORATION);
        block.setTicking(true);
        this.burning = flag;
        this.setBlockBounds(0.40625F, 0.0F, 0.40625F, 0.59375F, 0.5F, 0.59375F);
    }

    @Override
    public boolean isSolidRender() {
        return false;
    }

    @Override
    public boolean isCubeShaped() {
        return false;
    }


    @Override
    public boolean onInteracted(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Player player, @Nullable Side side, double xHit, double yHit) {
       ItemStack heldItem = player.getHeldItem();

        int data = world.getBlockData(tilePos);

        if (heldItem != null && heldItem.getItem().equals(StardewItems.CANDLE)) {
            if (data < 3) {
                world.setBlockDataNotify(tilePos, data+1);
                return true;
            }

            return true;
        }

        if (heldItem != null && heldItem.getItem() instanceof ItemFireStriker) {
            boolean adjacentFluid = false;

            TilePos pos = new TilePos();
            for (Side side1 : Side.values()) {
                if (world.getBlockMaterial(tilePos.add(side1, pos)).isLiquid()) {
                    adjacentFluid = true;
                }
            }

            if (adjacentFluid) return false;

            if (!burning) {
                world.setBlockTypeDataNotify(tilePos, StardewBlocks.CANDLE_ACTIVE, data);
                heldItem.damageItem(1, player);
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, tilePos.x() + 0.5, tilePos.y() + 0.5, tilePos.z() + 0.5, "fire.ignite", 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
            }

            return true;
        }

        if (heldItem == null && this.burning) {
            world.setBlockTypeDataNotify(tilePos, StardewBlocks.CANDLE, data);
            return true;
        }

        return false;
    }

    @Override
    public void animationTick(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Random rand) {
        var rot = getRotX(tilePos) * ((float)Math.PI / 2F);

        Vector3d[] pos = {
            new Vector3d(8d   / 16d,  11d / 16d, 8d  / 16d)
                .add(-.5, -.5, -.5)
                .rotateY(rot)
                .add(.5, .5, .5),

            new Vector3d(4.5d / 16d,  6d  / 16d, 12d / 16d)
                .add(-.5, -.5, -.5)
                .rotateY(rot)
                .add(.5, .5, .5),

            new Vector3d(12.5d  / 16d,  9d  / 16d, 11.5d / 16d)
                .add(-.5, -.5, -.5)
                .rotateY(rot)
                .add(.5, .5, .5),

            new Vector3d(5.5d / 16d,  10d / 16d, 3d  / 16d)
                .add(-.5, -.5, -.5)
                .rotateY(rot)
                .add(.5, .5, .5),

        };

        for (int i = 0; i <= world.getBlockData(tilePos); i++) {
            if (this.burning && rand.nextInt(2) == 0) {
                var off = pos[i];

                world.spawnParticle("smoke", tilePos.x() + off.x(), tilePos.y() + off.y(), tilePos.z() + off.z(), 0.0, 0.0, 0.0, 0, false);
                world.spawnParticle("flame", tilePos.x() + off.x(), tilePos.y() + off.y(), tilePos.z() + off.z(), 0.0, 0.0, 0.0, 0, false);
            }
        }
    }

    public static int getRotX(TilePosc tilePos) {
        return new Random(tilePos.x() * (tilePos.y() * 27L) * tilePos.z()).nextInt(4);
    }

    @Override
    public void onActivatorInteracted(@NotNull World world, @NotNull TilePosc tilePos, @NotNull TileEntityActivator activator, @NotNull Direction direction) {
        if (this.burning) {
            world.setBlockTypeNotify(tilePos, StardewBlocks.CANDLE);
        }
    }


    @Override
    public void onNeighborChanged(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Block<?> block) {
        if (!this.canStay(world, tilePos)) {
            this.dropWithCause(world, EnumDropCause.WORLD, tilePos, world.getBlockData(tilePos), null, null);
            world.setBlockTypeNotify(tilePos, Blocks.AIR);
        }

    }

    @Override
    public @NotNull ItemStack @Nullable [] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
        return new ItemStack[]{new ItemStack(StardewItems.CANDLE, data)};
    }

    @Override
    public boolean canPlaceAt(@NotNull World world, @NotNull TilePosc tilePos) {
        return this.isSupported(world, tilePos, Side.BOTTOM);
    }

    @Override
    public boolean canStay(@NotNull World world, @NotNull TilePosc tilePos) {
        return this.isSupported(world, tilePos, Side.BOTTOM);
    }

    @Override
    public @NotNull ISupport getSupport(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side) {
        return PartialSupport.INSTANCE;
    }

    @Override
    public @NotNull ISupport getSupportConstraint(@NotNull World world, @NotNull TilePosc tilePosc, @NotNull Side side) {
        return PartialSupport.INSTANCE.center();
    }

}
