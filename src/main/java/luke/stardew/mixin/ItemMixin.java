package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Item.class, remap = false)
public abstract class ItemMixin {

	@Inject(method = "onUseItemOnBlock", at = @At(value = "HEAD"))
	private void addStickFunctionality(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced, CallbackInfoReturnable<Boolean> cir){
		if (entityplayer.getCurrentEquippedItem().itemID == Item.stick.id && world.getBlockId(blockX, blockY, blockZ) == Block.farmlandDirt.id && world.getBlockId(blockX, blockY + 1, blockZ) == 0 && side == Side.TOP){
			entityplayer.getCurrentEquippedItem().consumeItem(entityplayer);
			world.setBlockWithNotify(blockX, blockY + 1, blockZ, StardewBlocks.plantStake.id);
			entityplayer.swingItem();
			world.playBlockSoundEffect(entityplayer, (float)blockX + 0.5F, (float)blockY + 0.5F, (float)blockZ + 0.5F, Block.dirt, EnumBlockSoundEffectType.PLACE);
		}
	}
}

