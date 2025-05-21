package luke.stardew.entities.goat;

import com.mojang.nbt.tags.CompoundTag;
import luke.stardew.StardewMod;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemBucketEmpty;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityGoat extends MobAnimal {
	public EntityGoat(World world) {
		super(world);
		this.textureIdentifier = NamespaceID.getPermanent("stardew", "goat");
		this.setSize(1.0f, 1.4f);
		this.mobDrops.add(new WeightedRandomLootObject(Items.LEATHER.getDefaultStack(), 0, 2));
		this.mobDrops.add(new WeightedRandomLootObject(Blocks.WOOL.getDefaultStack(), 1, 2));
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		super.addAdditionalSaveData(tag);
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		super.readAdditionalSaveData(tag);
	}

	@Override
	public String getLivingSound() {
		return StardewMod.MOD_ID +  ":mob.goat.idle";
	}

	@Override
	protected String getHurtSound() {
		return StardewMod.MOD_ID +  ":mob.goat.death";
	}

	@Override
	protected String getDeathSound() {
		return StardewMod.MOD_ID +  ":mob.goat.death";
	}

	@Override
	protected float getSoundVolume() {
		return 0.6f;
	}

	@Override
	public boolean interact(Player player) {
		ItemStack itemstack = player.inventory.getCurrentItem();
		if (itemstack != null && itemstack.itemID == Items.BUCKET.id) {
			ItemBucketEmpty.useBucket(player, new ItemStack(Items.BUCKET_MILK));
			return true;
		}
		return super.interact(player);
	}

	@Override
	public int getSkinVariant() {
		int skinVariantCount = 4;
		return this.entityData.getByte(1) % skinVariantCount;
	}
}
