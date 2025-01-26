package luke.stardew.items;

import luke.stardew.interfaces.IEntityBobberMixin;
import net.minecraft.core.entity.EntityBobber;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.world.World;

public class ItemToolFishingRodTiered extends Item {

	public ItemToolFishingRodTiered(String name, int id, ToolMaterial material) {
		super(name, id);
		this.setMaxStackSize(1);
		this.setMaxDamage(material.getDurability());
	}

	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		ItemStack canSlot = entityplayer.inventory.armorItemInSlot(1);

		if (entityplayer.bobberEntity != null) {
			int damage = entityplayer.bobberEntity.yoink();
			itemstack.damageItem(damage, entityplayer);
		} else {
			world.playSoundAtEntity(entityplayer, entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isClientSide) {
				// Create bobber entity
				EntityBobber bobber = new EntityBobber(world, entityplayer);
				world.entityJoinedWorld(bobber);
				entityplayer.bobberEntity = bobber; // Directly set the bobber to entityplayer
			}

			if (canSlot != null && ((canSlot.itemID == StardewItems.armorCanOfWorms.id && canSlot.getMetadata() < canSlot.getMaxDamage()) || canSlot.itemID == StardewItems.armorCanOfWormsGolden.id)) {
				if (canSlot.itemID == StardewItems.armorCanOfWorms.id){
					entityplayer.inventory.armorItemInSlot(1).damageItem(1, entityplayer);
				}
				// Double check if bobberEntity is not null before casting and using it
				if (entityplayer.bobberEntity instanceof IEntityBobberMixin) {
					((IEntityBobberMixin)entityplayer.bobberEntity).stardew_farming_bta$setBait(true);
				}
			} else if (entityplayer.inventory.consumeInventoryItem(StardewItems.worm.id)){
				if (entityplayer.bobberEntity instanceof IEntityBobberMixin) {
					((IEntityBobberMixin)entityplayer.bobberEntity).stardew_farming_bta$setBait(true);
				}
			}
		}

		entityplayer.swingItem();
		return itemstack;
	}
}
