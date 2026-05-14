package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicFluid;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.block.material.Materials;
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

import java.util.Random;

public class BlockLogicWaxCandle extends BlockLogic {
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
    public boolean renderAsNormalBlockOnCondition(WorldSource world, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean canPlaceAt(@NotNull World world, @NotNull TilePosc tilePos) {
        return world.getSupport(tilePos.down(new TilePos()), Side.BOTTOM).canSupport(this.getSupport(world, tilePos, Side.BOTTOM), Side.TOP);
    }

    @Override
    public boolean onInteracted(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Player player, @Nullable Side side, double xHit, double yHit) {
       ItemStack heldItem = player.getHeldItem();

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
                world.setBlockTypeDataNotify(tilePos, StardewBlocks.CANDLE_ACTIVE, 0);
                heldItem.damageItem(1, player);
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, tilePos.x() + 0.5, tilePos.y() + 0.5, tilePos.z() + 0.5, "fire.ignite", 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
            }

            return true;
        }

        if (heldItem == null && this.burning) {
            world.setBlockTypeDataNotify(tilePos, StardewBlocks.CANDLE, 0);
            return true;
        }

        return false;
    }

    @Override
    public void animationTick(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Random rand) {
        if (this.burning) {
            double xPos = tilePos.x() + (8.0f / 16f);
            double yPos = tilePos.y() + (11.0f / 16f);
            double zPos = tilePos.z() + (8.0f / 16f);

            world.spawnParticle("smoke", xPos, yPos, zPos, 0.0F, 0.0F, 0.0F, 0, false);
            world.spawnParticle("flame", xPos, yPos, zPos, 0.0F, 0.0F, 0.0F, 0, false);
        }
    }

    @Override
    public void onActivatorInteract(World world, int x, int y, int z, TileEntityActivator activator, Direction direction) {
        if (this.burning) {
            world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.CANDLE.id(), 0);
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
    public boolean canStay(@NotNull World world, @NotNull TilePosc tilePos) {
        return this.canPlaceAt(world, tilePos);
    }

    @Override
    public void animationTick(World world, int x, int y, int z, Random rand) {
        if (this.burning && rand.nextInt(2) == 0) {
            world.spawnParticle("smoke", x + 0.5, y + 0.7, z + 0.5, 0.0, 0.0, 0.0, 0, true);
            world.spawnParticle("flame", x + 0.5, y + 0.7, z + 0.5, 0.0, 0.0, 0.0, 0, true);
        }

    }

    @Override
    public @NotNull ItemStack @Nullable [] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
        return new ItemStack[]{new ItemStack(StardewItems.CANDLE)};
    }
}
