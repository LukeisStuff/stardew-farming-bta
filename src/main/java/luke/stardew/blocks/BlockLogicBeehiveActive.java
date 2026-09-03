package luke.stardew.blocks;

import luke.stardew.StardewMod;
import luke.stardew.achievements.StardewAchievements;
import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import net.minecraft.core.world.season.Seasons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BlockLogicBeehiveActive extends BlockLogicRotatable {
    public final boolean isActive;

    public BlockLogicBeehiveActive(Block<?> block, boolean flag) {
        super(block, Materials.WOOD);
        block.setTicking(true);
        this.isActive = flag;
    }

    @Override
    public @NotNull ItemStack @Nullable [] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, @NotNull TilePosc tilePos, int data, @Nullable TileEntity tileEntity) {
        return switch (dropCause) {
            case PICK_BLOCK, EXPLOSION, PROPER_TOOL, SILK_TOUCH ->
                new ItemStack[]{new ItemStack(StardewBlocks.BEEHIVE)};
            default -> null;
        };
    }

    @Override
    public void animationTick(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Random rand) {
        int meta = world.getBlockData(tilePos);
        double h = 0.5;
        double q = 0.25;
        double random = (world.rand.nextInt(1) - Math.random());
        if (meta == 2) {
            if (rand.nextInt(2) == 0) {
                world.spawnParticle("bee", tilePos.x() + h, tilePos.y() - random, tilePos.z() - q, 0.0, 0.0, 0.0, 0, false);
                if (rand.nextInt(2) == 0) {
                    world.playSoundEffect(null, SoundCategory.ENTITY_SOUNDS, tilePos.x() + 0.5, tilePos.y() + 0.5, tilePos.z() + 0.5, StardewMod.MOD_ID + ":mob.bee", 0.4F, rand.nextFloat() * 0.4F + 0.8F);
                }
            }
        } else if (meta == 3) {
            if (rand.nextInt(2) == 0) {
                world.spawnParticle("bee", tilePos.x() + h, tilePos.y() - random, tilePos.z() + 1 + q, 0.0, 0.0, 0.0, 0, false);
                if (rand.nextInt(2) == 0) {
                    world.playSoundEffect(null, SoundCategory.ENTITY_SOUNDS, tilePos.x() + 0.5, tilePos.y() + 0.5, tilePos.z() + 0.5, StardewMod.MOD_ID + ":mob.bee", 0.4F, rand.nextFloat() * 0.4F + 0.8F);
                }
            }
        } else if (meta == 4) {
            if (rand.nextInt(2) == 0) {
                world.spawnParticle("bee", tilePos.x() - q, tilePos.y() - random, tilePos.z() + h, 0.0, 0.0, 0.0, 0, false);
                if (rand.nextInt(2) == 0) {
                    world.playSoundEffect(null, SoundCategory.ENTITY_SOUNDS, tilePos.x() + 0.5, tilePos.y() + 0.5, tilePos.z() + 0.5, StardewMod.MOD_ID + ":mob.bee", 0.4F, rand.nextFloat() * 0.4F + 0.8F);
                }
            }
        } else if (meta == 5) {
            if (rand.nextInt(2) == 0) {
                world.spawnParticle("bee", tilePos.x() + 1 + q, tilePos.y() - random, tilePos.z() + h, 0.0, 0.0, 0.0, 0, false);
                if (rand.nextInt(2) == 0) {
                    world.playSoundEffect(null, SoundCategory.ENTITY_SOUNDS, tilePos.x() + 0.5, tilePos.y() + 0.5, tilePos.z() + 0.5, StardewMod.MOD_ID + ":mob.bee", 0.4F, rand.nextFloat() * 0.4F + 0.8F);
                }
            }
        } else {
            if (rand.nextInt(2) == 0) {
                world.spawnParticle("bee", tilePos.x(), tilePos.y(), tilePos.z(), 0.0, 0.0, 0.0, 0, false);
                if (rand.nextInt(2) == 0) {
                    world.playSoundEffect(null, SoundCategory.ENTITY_SOUNDS, tilePos.x() + 0.5, tilePos.y() + 0.5, tilePos.z() + 0.5, StardewMod.MOD_ID + ":mob.bee", 0.4F, rand.nextFloat() * 0.4F + 0.8F);
                }
            }
        }
    }


    @Override
    public void onActivatorInteracted(@NotNull World world, @NotNull TilePosc tilePos, @NotNull TileEntityActivator activator, @NotNull Direction direction) {
        int l = world.getBlockData(tilePos);

        if (this.isActive) {
            world.setBlockTypeDataNotify(tilePos, StardewBlocks.BEEHIVE, l);
            world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, tilePos.x(), tilePos.y(), tilePos.z(), "random.pop", 0.2F, 0.5F);
            world.dropItem(tilePos, new ItemStack(StardewItems.HONEY, world.rand.nextInt(2) + 1));
        }
    }

    @Override
    public void updateTick(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Random rand, boolean isRandomTick) {
        super.updateTick(world, tilePos, rand, isRandomTick);

        if (world.getSeasonManager().getCurrentSeason() != Seasons.OVERWORLD_WINTER) {
            int blockData = world.getBlockData(tilePos);
            if (rand.nextInt(50) == 0) {
                world.setBlockTypeDataNotify(tilePos, StardewBlocks.BEEHIVE_HONEY, blockData);
            }
        }
    }


    @Override
    public boolean onInteracted(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Player player, @Nullable Side side, double xHit, double yHit) {
        int l = world.getBlockData(tilePos);
        if (this.isActive) {
            world.setBlockTypeDataNotify(tilePos, StardewBlocks.BEEHIVE, l);
            world.playSoundAtEntity(player, player, "random.pop", 0.2F, 0.5F);
            player.inventory.insertItem(new ItemStack(StardewItems.HONEY, world.rand.nextInt(2) + 1), true);
            player.triggerAchievement(StardewAchievements.BEEHIVE);
            return true;
        }
        return false;
    }

}
