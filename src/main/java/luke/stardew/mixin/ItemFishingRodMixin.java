package luke.stardew.mixin;

import luke.stardew.interfaces.IEntityBobberMixin;
import luke.stardew.items.StardewItems;
import net.minecraft.core.entity.EntityBobber;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFishingRod;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemFishingRod.class, remap = false)
public class ItemFishingRodMixin extends Item {
	public ItemFishingRodMixin(String name, int id) {
		super(name, id);
	}

	@Inject(method = "onUseItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/player/EntityPlayer;swingItem()V"))
	private void addBaitFunctions(ItemStack itemstack, World world, EntityPlayer entityplayer, CallbackInfoReturnable<ItemStack> cir){
		if (entityplayer.bobberEntity != null){
			ItemStack canSlot = entityplayer.inventory.armorItemInSlot(1);
			if (canSlot != null && ((canSlot.itemID == StardewItems.armorCanOfWorms.id && canSlot.getMetadata() < canSlot.getMaxDamage()) || canSlot.itemID == StardewItems.armorCanOfWormsGolden.id)) {
				if (canSlot.itemID == StardewItems.armorCanOfWorms.id){
					entityplayer.inventory.armorItemInSlot(1).damageItem(1, entityplayer);
				}
				((IEntityBobberMixin)entityplayer.bobberEntity).stardew_farming_bta$setBait(true);
			} else if (entityplayer.inventory.consumeInventoryItem(StardewItems.worm.id)){
				((IEntityBobberMixin)entityplayer.bobberEntity).stardew_farming_bta$setBait(true);
			}
		}
		else {
			world.playSoundAtEntity(entityplayer, entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isClientSide) {
				world.entityJoinedWorld(new EntityBobber(world, entityplayer));
			}
		}
	}
}
