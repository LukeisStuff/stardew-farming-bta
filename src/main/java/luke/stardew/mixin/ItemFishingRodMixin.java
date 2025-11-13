package luke.stardew.mixin;

import luke.stardew.interfaces.IEntityBobberMixin;
import luke.stardew.items.StardewItems;
import net.minecraft.core.entity.EntityFishingBobber;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFishingRod;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemFishingRod.class, remap = false)
public abstract class ItemFishingRodMixin extends Item {
	protected ItemFishingRodMixin(NamespaceID namespaceID, int id) {
		super(namespaceID, id);
	}

	@Inject(method = "onUseItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/player/Player;swingItem()V"))
	public void addBaitFunctions(ItemStack itemstack, World world, Player player, CallbackInfoReturnable<ItemStack> cir) {
		if (player.bobberEntity != null) {
			ItemStack canSlot = player.inventory.armorItemInSlot(1);
			if (canSlot != null && ((canSlot.itemID == StardewItems.ARMOR_CAN_OF_WORMS.id && canSlot.getMetadata() < canSlot.getMaxDamage()) || canSlot.itemID == StardewItems.ARMOR_CAN_OF_WORMS_GOLDEN.id)) {
				if (canSlot.itemID == StardewItems.ARMOR_CAN_OF_WORMS.id) {
					player.inventory.armorItemInSlot(1).damageItem(1, player);
				}
				((IEntityBobberMixin) player.bobberEntity).stardew_farming_bta$setBait(true);
			} else if (player.inventory.consumeInventoryItem(StardewItems.WORM.id)) {
				((IEntityBobberMixin) player.bobberEntity).stardew_farming_bta$setBait(true);
			}
		} else {
			world.playSoundAtEntity(player, player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isClientSide) {
				world.entityJoinedWorld(new EntityFishingBobber(world, player));
			}
		}
	}
}
