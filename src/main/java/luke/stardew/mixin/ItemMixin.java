package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.helper.NamespaceObject;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Item.class, remap = false)
public abstract class ItemMixin implements NamespaceObject {

	@Inject(method = "onUseItemOnBlock", at = @At(value = "HEAD"))
	private void addStickFunctionality(ItemStack itemstack, Player player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced, CallbackInfoReturnable<Boolean> cir){
		if (player.getCurrentEquippedItem().itemID == Items.STICK.id && world.getBlockId(blockX, blockY, blockZ) == Blocks.FARMLAND_DIRT.id() && world.getBlockId(blockX, blockY + 1, blockZ) == 0 && side == Side.TOP){
			player.getCurrentEquippedItem().consumeItem(player);
			world.setBlockWithNotify(blockX, blockY + 1, blockZ, StardewBlocks.PLANT_STAKE.id());
			player.swingItem();
			world.playBlockSoundEffect(player, (float)blockX + 0.5F, (float)blockY + 0.5F, (float)blockZ + 0.5F, Blocks.DIRT, EnumBlockSoundEffectType.PLACE);
		}
	}

	@Shadow
	@Final
	public NamespaceID namespaceID;

	@Override
	public NamespaceID id() {
		return this.namespaceID;
	}

	@Override
	public String cleanValue() {
		return this.namespaceID.value().replaceFirst("item/", "");
	}
}

