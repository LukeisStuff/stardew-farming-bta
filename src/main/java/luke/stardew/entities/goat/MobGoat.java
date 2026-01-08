package luke.stardew.entities.goat;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.AgedMob;
import net.minecraft.core.entity.MobAge;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemBucketEmpty;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.item.tag.ItemTags;
import net.minecraft.core.world.World;
import org.jspecify.annotations.NonNull;

import static luke.stardew.StardewMod.MOD_ID;

public class MobGoat extends MobAnimal implements AgedMob {
    private final @NonNull MobAge age;

    public MobGoat(World world) {
        super(world);
        this.setTextureIdentifier(MOD_ID, "goat");
        this.setSize(1.0f, 1.4f);
        this.mobDrops.add(new WeightedRandomLootObject(Items.LEATHER.getDefaultStack(), 0, 2));
        this.mobDrops.add(new WeightedRandomLootObject(Blocks.WOOL.getDefaultStack(), 1, 2));
        this.age = MobAge.newRandom(this, 280, 14, 224);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.getMobAge().tick(this.world);
    }

    public @NonNull MobAge getMobAge() {
        return this.age;
    }

    @Override
    public void addAdditionalSaveData(@NonNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        this.age.writeTag(tag);
    }

    @Override
    public void readAdditionalSaveData(@NonNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.age.readTag(tag);
    }

    @Override
    public String getLivingSound() {
        return "stardew:mob.goat.idle";
    }

    @Override
    public String getHurtSound() {
        return "stardew:mob.goat.death";
    }

    @Override
    public String getDeathSound() {
        return "stardew:mob.goat.death";
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
    public boolean interact(@NonNull Player player) {
        ItemStack itemstack = player.inventory.getCurrentItem();
        if (itemstack != null && itemstack.itemID == Items.BUCKET.id) {
            ItemBucketEmpty.useBucket(player, new ItemStack(Items.BUCKET_MILK));
            return true;
        } else {
            return super.interact(player);
        }
    }
}
