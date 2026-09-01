package luke.stardew.entities.goat;

import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.animal.Creature;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemBucket;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.item.tag.ItemTags;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;

import static luke.stardew.StardewMod.MOD_ID;

public class MobGoat extends MobAnimal implements Creature {
    public MobGoat(World world) {
        super(world);
        this.setTextureIdentifier(MOD_ID, "goat");
        this.setSize(1.0f, 1.4f);
        this.mobDrops.add(new WeightedRandomLootObject(Items.LEATHER.getDefaultStack(), 0, 2));
        this.mobDrops.add(new WeightedRandomLootObject(Blocks.WOOL.getDefaultStack(), 1, 2));
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
        if (itemStack != null && itemStack.getItem().hasTag(ItemTags.COWS_FAVOURITE_ITEM)) {
            return true;
        }
        if (itemStack == null || itemStack.itemID >= Blocks.blocksList.length) {
            return false;
        }
        Block<?> block = Blocks.blocksList[itemStack.itemID];
        if (block == null) return false;
        return block.hasTag(BlockTags.SHEEPS_FAVOURITE_BLOCK);
    }

    @Override
    public boolean interact(Player player) {
        ItemStack itemstack = player.inventory.getCurrentItem();
        if (itemstack != null && itemstack.getItem() instanceof ItemBucket item) {
            var state = ItemBucket.getState(itemstack);

            if (state != ItemBucket.STATE_MILK && state != ItemBucket.STATE_EMPTY)  {
                return false;
            }

            if (item.maxCharges >= ItemBucket.getCharges(itemstack)) {
                return false;
            }

            ItemBucket.useBucket(itemstack, player, player.world, ItemBucket.STATE_MILK);

            return true;
        }
        return super.interact(player);
    }
}
