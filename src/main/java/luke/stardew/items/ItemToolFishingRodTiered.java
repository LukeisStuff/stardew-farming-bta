package luke.stardew.items;

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
		if (entityplayer.fishEntity != null) {
			int i = entityplayer.fishEntity.catchFish();
			itemstack.damageItem(i, entityplayer);
		} else {
			world.playSoundAtEntity(entityplayer, entityplayer, "random.bow", 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
			if (!world.isClientSide) {
				world.entityJoinedWorld(new EntityBobber(world, entityplayer));
			}
		}
		entityplayer.swingItem();
		return itemstack;
	}
}
