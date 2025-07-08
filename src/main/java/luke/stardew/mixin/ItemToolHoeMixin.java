package luke.stardew.mixin;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.entity.player.Player;
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
public abstract class ItemToolHoeMixin extends ItemTool {
	public ItemToolHoeMixin(String name, String namespaceID, int id, int damageDealt, ToolMaterial toolMaterial, Tag<Block<?>> tagEffectiveAgainst) {
		super(name, namespaceID, id, damageDealt, toolMaterial, tagEffectiveAgainst);
	}

	@Inject(method = "onUseItemOnBlock", at = @At(value = "HEAD", target = "Lnet/minecraft/core/world/World;playBlockSoundEffect(Lnet/minecraft/core/entity/Entity;DDDLnet/minecraft/core/block/Block;Lnet/minecraft/core/enums/EnumBlockSoundEffectType;)V"))
	public void dropWorms(ItemStack itemstack, Player player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced, CallbackInfoReturnable<Boolean> cir){
		int i1 = world.getBlockId(blockX, blockY, blockZ);
		if (i1 == Blocks.GRASS.id()) {
			if (!world.isClientSide) {
				if (world.rand.nextInt(8) == 0) {
					world.dropItem(blockX, blockY + 1, blockZ, new ItemStack(StardewItems.WORM, world.rand.nextInt(2) + 2));
				}
				player.swingItem();
			}
			player.swingItem();
		}
		if (i1 == Blocks.DIRT.id()) {
			if (!world.isClientSide) {
				if (world.rand.nextInt(8) == 0) {
					world.dropItem(blockX, blockY + 1, blockZ, new ItemStack(StardewItems.WORM, world.rand.nextInt(2) + 2));
				}
				player.swingItem();
			}
			player.swingItem();
		}
		if (i1 == Blocks.GRASS_RETRO.id()) {
			if (!world.isClientSide) {
				if (world.rand.nextInt(8) == 0) {
					world.dropItem(blockX, blockY + 1, blockZ, new ItemStack(StardewItems.WORM, world.rand.nextInt(2) + 2));
				}
				player.swingItem();
			}
			player.swingItem();
		}
		if (i1 == Blocks.MUD.id()) {
			if (!world.isClientSide) {
				if (world.rand.nextInt(8) == 0) {
					world.dropItem(blockX, blockY + 1, blockZ, new ItemStack(StardewItems.WORM, world.rand.nextInt(2) + 2));
				}
				player.swingItem();
			}
			player.swingItem();
		}
		if (i1 == Blocks.PATH_DIRT.id()) {
			if (!world.isClientSide) {
				if (world.rand.nextInt(8) == 0) {
					world.dropItem(blockX, blockY + 1, blockZ, new ItemStack(StardewItems.WORM, world.rand.nextInt(2) + 2));
				}
				player.swingItem();
			}
			player.swingItem();
		}
	}
}
