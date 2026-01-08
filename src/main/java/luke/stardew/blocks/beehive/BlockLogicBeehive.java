package luke.stardew.blocks.beehive;

import luke.stardew.StardewMod;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.Global;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntityFurnaceBlast;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BlockLogicBeehive extends BlockLogicRotatable {
    protected final boolean isActive;
    public static boolean keepBeehiveInventory = false;

    public BlockLogicBeehive(Block<?> block, boolean active) {
        super(block, Materials.WOOD);
        this.isActive = active;
        block.withEntity(TileEntityBeehive::new);
    }

    @Override
    public ItemStack[] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
        return switch (dropCause) {
            case PICK_BLOCK, EXPLOSION, PROPER_TOOL, SILK_TOUCH ->
                new ItemStack[]{new ItemStack(StardewBlocks.BEEHIVE_IDLE)};
            default -> null;
        };
    }

    @Override
    public void animationTick(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Random rand) {
        if (this.isActive) {
            double posX = (double) tilePos.x() + (double) 0.5F;
            double posY = (double) tilePos.y() + (double) (rand.nextFloat() * 6.0F) / (double) 16.0F;
            double posZ = (double) tilePos.z() + (double) 0.5F;
            double f3 = 0.52;
            float f4 = rand.nextFloat() * 0.6F - 0.3F;
            switch (BlockLogicRotatable.getDirectionFromMeta(world.getBlockData(tilePos))) {
                case WEST:
                    world.spawnParticle("smoke", posX - f3, posY, posZ + (double) f4, 0.0F, 0.0F, 0.0F, 0);
                    world.spawnParticle("largeSmoke", posX - f3, posY, posZ + (double) f4, 0.0F, 0.0F, 0.0F, 0);
                    break;
                case EAST:
                    world.spawnParticle("smoke", posX + f3, posY, posZ + (double) f4, 0.0F, 0.0F, 0.0F, 0);
                    world.spawnParticle("largeSmoke", posX + f3, posY, posZ + (double) f4, 0.0F, 0.0F, 0.0F, 0);
                    break;
                case NORTH:
                    world.spawnParticle("smoke", posX + (double) f4, posY, posZ - f3, 0.0F, 0.0F, 0.0F, 0);
                    world.spawnParticle("largeSmoke", posX + (double) f4, posY, posZ - f3, 0.0F, 0.0F, 0.0F, 0);
                    break;
                case SOUTH:
                    world.spawnParticle("smoke", posX + (double) f4, posY, posZ + f3, 0.0F, 0.0F, 0.0F, 0);
                    world.spawnParticle("largeSmoke", posX + (double) f4, posY, posZ + f3, 0.0F, 0.0F, 0.0F, 0);
            }

        }
    }

    @Override
    public boolean onInteracted(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Player player, @Nullable Side side, double xHit, double yHit) {
        if (!world.isClientSide) {
            TileEntity var10 = world.getTileEntity(tilePos);
            if (var10 instanceof TileEntityFurnaceBlast beehive) {
                player.displayBlastFurnaceScreen(beehive);
            }
        }

        return true;
    }

    public static void updateBeehiveBlockState(@NotNull World world, @NotNull TilePos tilePos, boolean lit) {
        int meta = world.getBlockData(tilePos);
        TileEntity tileEntity = world.getTileEntity(tilePos);
        if (tileEntity == null) {
            String msg = "Beehive is missing Tile Entity at " + tilePos + ", block will be removed!";
            if (Global.BUILD_CHANNEL.isUnstableBuild()) {
                throw new RuntimeException(msg);
            } else {
                world.setBlockTypeNotify(tilePos, Blocks.AIR);
                StardewMod.LOGGER.warn(msg);
            }
        } else {
            keepBeehiveInventory = true;
            if (lit) {
                world.setBlockTypeNotify(tilePos, StardewBlocks.BEEHIVE_ACTIVE);
            } else {
                world.setBlockTypeNotify(tilePos, StardewBlocks.BEEHIVE_IDLE);
            }

            keepBeehiveInventory = false;
            world.setBlockDataNotify(tilePos, meta);
            tileEntity.validate();
            world.setTileEntity(tilePos, tileEntity);
        }
    }
}
