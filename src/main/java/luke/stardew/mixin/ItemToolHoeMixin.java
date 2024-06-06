package luke.stardew.mixin;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemTool;
import net.minecraft.core.item.tool.ItemToolHoe;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemToolHoe.class, remap = false)
public class ItemToolHoeMixin extends ItemTool {
	protected ItemToolHoeMixin(String name, int id, int damageDealt, ToolMaterial toolMaterial, Tag<Block> tagEffectiveAgainst) {
		super(name, id, damageDealt, toolMaterial, tagEffectiveAgainst);
	}

	@Inject(method = "onItemUse", at = @At(value = "HEAD", target = "Lnet/minecraft/core/world/World;playBlockSoundEffect(Lnet/minecraft/core/entity/Entity;DDDLnet/minecraft/core/block/Block;Lnet/minecraft/core/enums/EnumBlockSoundEffectType;)V"))
	private void dropWorms(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced, CallbackInfoReturnable<Boolean> cir){
		int i1 = world.getBlockId(blockX, blockY, blockZ);
		if (i1 == Block.grass.id) {
			if (!world.isClientSide) {
				if (world.rand.nextInt(8) == 0) {
					world.dropItem(blockX, blockY + 1, blockZ, new ItemStack(StardewItems.worm, world.rand.nextInt(2) + 2));
				}
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		if (i1 == Block.dirt.id) {
			if (!world.isClientSide) {
				if (world.rand.nextInt(8) == 0) {
					world.dropItem(blockX, blockY + 1, blockZ, new ItemStack(StardewItems.worm, world.rand.nextInt(2) + 2));
				}
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		if (i1 == Block.grassRetro.id) {
			if (!world.isClientSide) {
				if (world.rand.nextInt(8) == 0) {
					world.dropItem(blockX, blockY + 1, blockZ, new ItemStack(StardewItems.worm, world.rand.nextInt(2) + 2));
				}
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		if (i1 == Block.mud.id) {
			if (!world.isClientSide) {
				if (world.rand.nextInt(8) == 0) {
					world.dropItem(blockX, blockY + 1, blockZ, new ItemStack(StardewItems.worm, world.rand.nextInt(2) + 2));
				}
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
		if (i1 == Block.pathDirt.id) {
			if (!world.isClientSide) {
				if (world.rand.nextInt(8) == 0) {
					world.dropItem(blockX, blockY + 1, blockZ, new ItemStack(StardewItems.worm, world.rand.nextInt(2) + 2));
				}
				entityplayer.swingItem();
			}
			entityplayer.swingItem();
		}
	}
}
