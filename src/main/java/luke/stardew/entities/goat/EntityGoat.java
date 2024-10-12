package luke.stardew.entities.goat;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.animal.EntityAnimal;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemBucketEmpty;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;

public class EntityGoat extends EntityAnimal {
	public EntityGoat(World world) {
		super(world);
		this.textureIdentifier = new NamespaceID("stardew", "goat");
		this.setSize(1.0f, 1.4f);
		this.mobDrops.add(new WeightedRandomLootObject(Item.leather.getDefaultStack(), 0, 2));
		this.mobDrops.add(new WeightedRandomLootObject(Block.wool.getDefaultStack(), 1, 2));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
	}

	@Override
	public String getLivingSound() {
		return "stardew.goatidle";
	}

	@Override
	protected String getHurtSound() {
		return "stardew.goatdeath";
	}

	@Override
	protected String getDeathSound() {
		return "stardew.goatdeath";
	}

	@Override
	protected float getSoundVolume() {
		return 0.6f;
	}

	@Override
	public boolean interact(EntityPlayer entityplayer) {
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		if (itemstack != null && itemstack.itemID == Item.bucket.id) {
			ItemBucketEmpty.useBucket(entityplayer, new ItemStack(Item.bucketMilk));
			return true;
		}
		return super.interact(entityplayer);
	}

	@Override
	public int getSkinVariant() {
		int skinVariantCount = 4;
		return this.entityData.getByte(1) % skinVariantCount;
	}
}
