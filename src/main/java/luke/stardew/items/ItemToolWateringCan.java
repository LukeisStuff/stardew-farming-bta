package luke.stardew.items;

import luke.stardew.StardewParticleMaker;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.Random;

public class ItemToolWateringCan extends Item {
    public ItemToolWateringCan(String translationKey, String namespaceID, int id, ToolMaterial material) {
        super(translationKey, namespaceID, id);
        this.setMaxStackSize(1);
        this.setMaxDamage(material.getDurability());
    }

    @Override
    public boolean onUseOnBlock(@NotNull ItemStack selfStack, @NotNull World world, @org.jetbrains.annotations.Nullable Player player, @NotNull TilePosc blockPos, @NotNull Side side, double xHit, double yHit) {
        return this.waterBlock(selfStack, player, world, blockPos.x(), blockPos.y(), blockPos.z());
    }

    @Override
    public void onUseByActivator(@NotNull ItemStack selfStack, @NotNull World world, @NotNull TileEntityActivator activator, @NotNull Random random, @NotNull TilePosc blockPos, @NotNull Direction direction, double offX, double offY, double offZ) {
        this.waterBlock(selfStack, null, world, blockPos.x() + direction.getOffsetX(), blockPos.y() + direction.getOffsetY(), blockPos.z() + direction.getOffsetZ());
    }

    public boolean waterBlock(ItemStack itemstack, @Nullable Player player, World world, int blockX, int blockY, int blockZ) {
        int blockToWater = world.getBlockId(blockX, blockY, blockZ);
        int meta = world.getBlockMetadata(blockX, blockY, blockZ);

        if (blockToWater == Blocks.FARMLAND_DIRT.id() && meta == 0) {
            water(world, blockX, blockY, blockZ, itemstack, player, false, Blocks.FARMLAND_DIRT.id(), 1);
            return true;
        }
        if (blockToWater == Blocks.MUD_BAKED.id()) {
            water(world, blockX, blockY, blockZ, itemstack, player, false, Blocks.MUD.id(), meta);
            return true;
        }
        if (blockToWater == Blocks.SPONGE_DRY.id()) {
            water(world, blockX, blockY, blockZ, itemstack, player, false, Blocks.SPONGE_WET.id(), meta);
            return true;
        }
        if (blockToWater == Blocks.FIRE.id()) {
            water(world, blockX, blockY, blockZ, itemstack, player, true, 0, 0);
            return true;
        }
        if (blockToWater == Blocks.BRAZIER_ACTIVE.id()) {
            water(world, blockX, blockY, blockZ, itemstack, player, true, Blocks.BRAZIER_INACTIVE.id(), meta);
            return true;
        }
        if (blockToWater == StardewBlocks.CANDLE_ACTIVE.id()) {
            water(world, blockX, blockY, blockZ, itemstack, player, true, StardewBlocks.CANDLE.id(), meta);
            return true;
        }
        if (blockToWater == Blocks.PUMICE_WET.id()) {
            water(world, blockX, blockY, blockZ, itemstack, player, true, Blocks.PUMICE_DRY.id(), meta);
            return true;
        }
        if (blockToWater == Blocks.MAGMA.id()) {
            water(world, blockX, blockY, blockZ, itemstack, player, true, Blocks.COBBLE_NETHERRACK.id(), meta);
            return true;
        } else {
            return false;
        }
    }

    private void water(World world, int blockX, int blockY, int blockZ, ItemStack itemstack, Player player, boolean playFizz, int blockToBecome, int meta) {
        for (int i = 0; i < 16; ++i) {
            StardewParticleMaker.spawnBlockBreakParticles(world, "splash", blockX, blockY, blockZ);
            if (playFizz) {
                StardewParticleMaker.spawnBlockBreakParticles(world, "smoke", blockX, blockY, blockZ);
            }
        }
        if (!world.isClientSide) {
            world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, blockX + 0.5F, blockY + 0.5F, blockZ + 0.5F, "liquid.splash", 0.2F, 1.0F);
            if (playFizz) {
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, blockX + 0.5, blockY + 0.5, blockZ + 0.5, "random.fizz", 0.3F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
            }
            world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, blockToBecome, meta);
            itemstack.damageItem(1, player);
        }
    }
}
