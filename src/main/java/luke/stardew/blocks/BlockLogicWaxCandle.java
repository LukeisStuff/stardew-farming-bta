package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicFluid;
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
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return world.isBlockNormalCube(x, y - 1, z) || world.canPlaceOnSurfaceOfBlock(x, y - 1, z);
    }

    @Override
    public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xPlaced, double yPlaced) {
        ItemStack heldItem = player.getHeldItem();
        if (heldItem != null && heldItem.getItem() instanceof ItemFireStriker && !this.burning) {
            boolean adjacentFluid =
                StardewBlocks.isBlockLogic(world, x + 1, y, z, BlockLogicFluid.class) ||
                    StardewBlocks.isBlockLogic(world, x - 1, y, z, BlockLogicFluid.class) ||
                    StardewBlocks.isBlockLogic(world, x, y, z + 1, BlockLogicFluid.class) ||
                    StardewBlocks.isBlockLogic(world, x, y, z - 1, BlockLogicFluid.class);
            if (!adjacentFluid) {
                world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.CANDLE_ACTIVE.id(), 0);
                heldItem.damageItem(1, player);
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, x + 0.5, y + 0.5, z + 0.5, "fire.ignite", 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
                return true;
            } else {
                return false;
            }
        } else if (heldItem == null && this.burning) {
            world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.CANDLE.id(), 0);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onActivatorInteract(World world, int x, int y, int z, TileEntityActivator activator, Direction direction) {
        if (this.burning) {
            world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.CANDLE.id(), 0);
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        if (!this.canBlockStay(world, x, y, z)) {
            this.dropBlockWithCause(world, EnumDropCause.WORLD, x, y, z, world.getBlockMetadata(x, y, z), null, null);
            world.setBlockWithNotify(x, y, z, 0);
        }

    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return world.canPlaceOnSurfaceOfBlock(x, y - 1, z);
    }

    @Override
    public void animationTick(World world, int x, int y, int z, Random rand) {
        if (this.burning && rand.nextInt(2) == 0) {
            world.spawnParticle("smoke", x + 0.5, y + 0.7, z + 0.5, 0.0, 0.0, 0.0, 0);
            world.spawnParticle("flame", x + 0.5, y + 0.7, z + 0.5, 0.0, 0.0, 0.0, 0);
        }

    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        return new ItemStack[]{new ItemStack(StardewBlocks.CANDLE)};
    }
}
