package luke.stardew.items;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class ItemToolWateringCan extends Item {
	public ItemToolWateringCan(String name, int id, ToolMaterial material) {
		super(name, id);
		this.setMaxStackSize(1);
		this.setMaxDamage(material.getDurability());
	}

	public boolean onUseItemOnBlock(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		int blockToWater = world.getBlockId(blockX, blockY, blockZ);
		int meta = world.getBlockMetadata(blockX, blockY, blockZ);
		if (blockToWater == Block.farmlandDirt.id) {
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (float)blockX + 0.5F, (float)blockY + 0.5F, (float)blockZ + 0.5F, "liquid.splash", 0.2F, 1.0F);
			if (!world.isClientSide) {
				world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta + 1);
				itemstack.damageItem(1, entityplayer);
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		if (blockToWater == Block.mudBaked.id) {
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (float)blockX + 0.5F, (float)blockY + 0.5F, (float)blockZ + 0.5F, "liquid.splash", 0.2F, 1.0F);
			if (!world.isClientSide) {
				world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, Block.mud.id, meta );
				itemstack.damageItem(1, entityplayer);
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		if (blockToWater == Block.spongeDry.id) {
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (float)blockX + 0.5F, (float)blockY + 0.5F, (float)blockZ + 0.5F, "liquid.splash", 0.2F, 1.0F);
			if (!world.isClientSide) {
				world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, Block.spongeWet.id, meta );
				itemstack.damageItem(1, entityplayer);
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		if (blockToWater == Block.fire.id) {
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (float)blockX + 0.5F, (float)blockY + 0.5F, (float)blockZ + 0.5F, "liquid.splash", 0.2F, 1.0F);
			world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, (double)blockX + 0.5, (double)blockY + 0.5, (double)blockZ + 0.5, "random.fizz", 0.3F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			if (!world.isClientSide) {
				world.setBlockWithNotify(blockX, blockY, blockZ, 0);
				itemstack.damageItem(1, entityplayer);
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
        return false;
    }
}
