package luke.stardew.items;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class ItemToolWateringCan extends Item {
	public ItemToolWateringCan(String translationKey, String namespaceID, int id, ToolMaterial material) {
		super(translationKey, namespaceID, id);
		this.setMaxStackSize(1);
		this.setMaxDamage(material.getDurability());
	}

	public boolean onUseItemOnBlock(ItemStack itemstack, Player entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		int blockToWater = world.getBlockId(blockX, blockY, blockZ);
		int meta = world.getBlockMetadata(blockX, blockY, blockZ);
		if (blockToWater == Blocks.FARMLAND_DIRT.id()) {
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (float) blockX + 0.5F, (float) blockY + 0.5F, (float) blockZ + 0.5F, "liquid.splash", 0.2F, 1.0F);
			if (!world.isClientSide) {
				world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta + 1);
				itemstack.damageItem(1, entityplayer);
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		if (blockToWater == Blocks.MUD_BAKED.id()) {
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (float) blockX + 0.5F, (float) blockY + 0.5F, (float) blockZ + 0.5F, "liquid.splash", 0.2F, 1.0F);
			if (!world.isClientSide) {
				world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, Blocks.MUD.id(), meta);
				itemstack.damageItem(1, entityplayer);
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		if (blockToWater == Blocks.SPONGE_DRY.id()) {
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (float) blockX + 0.5F, (float) blockY + 0.5F, (float) blockZ + 0.5F, "liquid.splash", 0.2F, 1.0F);
			if (!world.isClientSide) {
				world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, Blocks.SPONGE_WET.id(), meta);
				itemstack.damageItem(1, entityplayer);
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		if (blockToWater == Blocks.FIRE.id()) {
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (float) blockX + 0.5F, (float) blockY + 0.5F, (float) blockZ + 0.5F, "liquid.splash", 0.2F, 1.0F);
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (double) blockX + 0.5, (double) blockY + 0.5, (double) blockZ + 0.5, "random.fizz", 0.3F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			if (!world.isClientSide) {
				world.setBlockWithNotify(blockX, blockY, blockZ, 0);
				itemstack.damageItem(1, entityplayer);
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		if (blockToWater == Blocks.BRAZIER_ACTIVE.id()) {
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (float) blockX + 0.5F, (float) blockY + 0.5F, (float) blockZ + 0.5F, "liquid.splash", 0.2F, 1.0F);
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (double) blockX + 0.5, (double) blockY + 0.5, (double) blockZ + 0.5, "random.fizz", 0.3F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			if (!world.isClientSide) {
				world.setBlockWithNotify(blockX, blockY, blockZ, Blocks.BRAZIER_INACTIVE.id());
				itemstack.damageItem(1, entityplayer);
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		if (blockToWater == StardewBlocks.CANDLE_ACTIVE.id()) {
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (float) blockX + 0.5F, (float) blockY + 0.5F, (float) blockZ + 0.5F, "liquid.splash", 0.2F, 1.0F);
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (double) blockX + 0.5, (double) blockY + 0.5, (double) blockZ + 0.5, "random.fizz", 0.3F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			if (!world.isClientSide) {
				world.setBlockWithNotify(blockX, blockY, blockZ, StardewBlocks.CANDLE.id());
				itemstack.damageItem(1, entityplayer);
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		if (blockToWater == Blocks.PUMICE_WET.id()) {
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (float) blockX + 0.5F, (float) blockY + 0.5F, (float) blockZ + 0.5F, "liquid.splash", 0.2F, 1.0F);
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (double) blockX + 0.5, (double) blockY + 0.5, (double) blockZ + 0.5, "random.fizz", 0.3F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			if (!world.isClientSide) {
				world.setBlockWithNotify(blockX, blockY, blockZ, Blocks.PUMICE_DRY.id());
				itemstack.damageItem(1, entityplayer);
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		if (blockToWater == Blocks.COBBLE_NETHERRACK_IGNEOUS.id()) {
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (float) blockX + 0.5F, (float) blockY + 0.5F, (float) blockZ + 0.5F, "liquid.splash", 0.2F, 1.0F);
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (double) blockX + 0.5, (double) blockY + 0.5, (double) blockZ + 0.5, "random.fizz", 0.3F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			if (!world.isClientSide) {
				world.setBlockWithNotify(blockX, blockY, blockZ, Blocks.OBSIDIAN.id());
				itemstack.damageItem(1, entityplayer);
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		return false;
	}
}
