package luke.stardew.entities.goat;

import luke.stardew.StardewMod;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.animal.Creature;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemBucketEmpty;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.item.tag.ItemTags;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;

public class MobGoat extends MobAnimal implements Creature {
	public MobGoat(World world) {
		super(world);
		this.textureIdentifier = NamespaceID.getPermanent("stardew", "goat");
		this.setSize(1.0f, 1.4f);
		this.mobDrops.add(new WeightedRandomLootObject(Items.LEATHER.getDefaultStack(), 0, 2));
		this.mobDrops.add(new WeightedRandomLootObject(Blocks.WOOL.getDefaultStack(), 1, 2));
	}

	@Override
	public String getLivingSound() {
		return StardewMod.MOD_ID + ":mob.goat.idle";
	}

	@Override
	public String getHurtSound() {
		return StardewMod.MOD_ID + ":mob.goat.death";
	}

	@Override
	public String getDeathSound() {
		return StardewMod.MOD_ID + ":mob.goat.death";
	}

	@Override
	public float getSoundVolume() {
		return 0.6f;
	}

	@Override
	public boolean isFavouriteItem(ItemStack itemStack) {
		if (itemStack != null && itemStack.getItem().hasTag(ItemTags.COWS_FAVOURITE_ITEM)) return true;
		return itemStack != null && itemStack.itemID < Blocks.blocksList.length && Blocks.blocksList[itemStack.itemID].hasTag(BlockTags.SHEEPS_FAVOURITE_BLOCK);
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
}
