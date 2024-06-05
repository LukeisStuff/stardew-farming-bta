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



	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public boolean shouldRotateAroundWhenRendering() {
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		ItemStack canSlot = entityplayer.inventory.armorItemInSlot(1);

		if (entityplayer.fishEntity != null) {
			int i = entityplayer.fishEntity.catchFish();
			itemstack.damageItem(i, entityplayer);
		} else {
			world.playSoundAtEntity(entityplayer, entityplayer, "random.bow", 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
			if (!world.isClientSide) {
				world.entityJoinedWorld(new EntityBobber(world, entityplayer));
			}

			if (canSlot != null && ((canSlot.itemID == StardewItems.armorCanOfWorms.id && canSlot.getMetadata() < canSlot.getMaxDamage()) || canSlot.itemID == StardewItems.armorCanOfWormsGolden.id)) {
				if (canSlot.itemID == StardewItems.armorCanOfWorms.id){
					entityplayer.inventory.armorItemInSlot(1).damageItem(1, entityplayer);
				}
				((IEntityBobberMixin)entityplayer.fishEntity).stardew_farming_bta$setBait(true);
			} else if (entityplayer.inventory.consumeInventoryItem(StardewItems.worm.id)){
				((IEntityBobberMixin)entityplayer.fishEntity).stardew_farming_bta$setBait(true);
			}
		}
		entityplayer.swingItem();
		return itemstack;
	}
}
