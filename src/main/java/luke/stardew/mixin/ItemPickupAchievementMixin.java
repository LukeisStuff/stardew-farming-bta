package luke.stardew.mixin;

import luke.stardew.achievements.StardewAchievements;
import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.core.achievement.stat.StatList;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityItem.class, remap = false)
public abstract class ItemPickupAchievementMixin {

    @Shadow
    public ItemStack item;

    @Inject(method = "playerTouch", at = @At("TAIL"))
    public void playerTouch(Player player, CallbackInfo ci) {

        String pickUpKey = StatList.STAT_PICKED_UP;

        if (this.item.itemID == StardewBlocks.SAPLING_APPLE.id()) {
            player.triggerAchievement(StardewAchievements.APPLE);
        }

        if ((this.item.itemID == Items.FOOD_FISH_RAW.id
            || this.item.itemID == StardewItems.FOOD_BASS_RAW.id
            || this.item.itemID == StardewItems.FOOD_SNAPPER_RAW.id
            || this.item.itemID == StardewItems.FOOD_SALMON_RAW.id)
            && player.getStat(Items.FOOD_FISH_RAW.getStat(pickUpKey)) > 0
            && player.getStat(StardewItems.FOOD_SALMON_RAW.getStat(pickUpKey)) > 0
            && player.getStat(StardewItems.FOOD_SNAPPER_RAW.getStat(pickUpKey)) > 0
            && player.getStat(StardewItems.FOOD_BASS_RAW.getStat(pickUpKey)) > 0) {
            player.triggerAchievement(StardewAchievements.AMATEUR_FISHER);
        }
        if ((this.item.itemID == StardewItems.FISH_GHOST.id
            || this.item.itemID == StardewItems.FISH_SWORD.id
            || this.item.itemID == StardewItems.FISH_EEL_LAVA.id
            || this.item.itemID == StardewItems.FISH_STONE.id)
            && player.getStat(StardewItems.FISH_SWORD.getStat(pickUpKey)) > 0
            && player.getStat(StardewItems.FISH_GHOST.getStat(pickUpKey)) > 0
            && player.getStat(StardewItems.FISH_EEL_LAVA.getStat(pickUpKey)) > 0
            && player.getStat(StardewItems.FISH_STONE.getStat(pickUpKey)) > 0
            && player.getStat(StardewItems.FISH_PIG.getStat(pickUpKey)) > 0) {
                player.triggerAchievement(StardewAchievements.MASTER_FISHER);
        }
        if ((this.item.itemID == StardewItems.CRANBERRIES.id
            || this.item.itemID == StardewItems.GRAPES.id
            || this.item.itemID == StardewItems.STRAWBERRY.id
            || this.item.itemID == StardewItems.PINEAPPLE.id
            || this.item.itemID == StardewItems.BLUEBERRY.id
            || this.item.itemID == StardewBlocks.WATERMELON.id()
            || this.item.itemID == Items.FOOD_APPLE.id)
            && player.getStat(StardewItems.CRANBERRIES.getStat(pickUpKey)) > 0
            && player.getStat(StardewItems.GRAPES.getStat(pickUpKey)) > 0
            && player.getStat(StardewItems.STRAWBERRY.getStat(pickUpKey)) > 0
            && player.getStat(StardewItems.PINEAPPLE.getStat(pickUpKey)) > 0
            && player.getStat(StardewItems.BLUEBERRY.getStat(pickUpKey)) > 0
            && player.getStat(StardewBlocks.WATERMELON.getStat(pickUpKey)) > 0
            && player.getStat(Items.FOOD_APPLE.getStat(pickUpKey)) > 0) {
            player.triggerAchievement(StardewAchievements.FRUIT);
        }
        if ((this.item.itemID == StardewItems.POTATO.id
            || this.item.itemID == StardewItems.CARROT.id
            || this.item.itemID == StardewItems.TOMATO.id
            || this.item.itemID == StardewItems.CORN.id
            || this.item.itemID == Blocks.PUMPKIN.id()
            || this.item.itemID == StardewBlocks.CAULIFLOWER.id()
            || this.item.itemID == Items.WHEAT.id)
            && player.getStat(StardewItems.POTATO.getStat(pickUpKey)) > 0
            && player.getStat(StardewItems.CARROT.getStat(pickUpKey)) > 0
            && player.getStat(StardewItems.TOMATO.getStat(pickUpKey)) > 0
            && player.getStat(StardewItems.CORN.getStat(pickUpKey)) > 0
            && player.getStat(Blocks.PUMPKIN.getStat(pickUpKey)) > 0
            && player.getStat(StardewBlocks.CAULIFLOWER.getStat(pickUpKey)) > 0
            && player.getStat(Items.WHEAT.getStat(pickUpKey)) > 0) {
            player.triggerAchievement(StardewAchievements.VEGETABLE);
        }
        if ((this.item.itemID == StardewItems.EGG_DUCK.id
            || this.item.itemID == Items.EGG_CHICKEN.id)
            && player.getStat(StardewItems.EGG_DUCK.getStat(pickUpKey)) > 0
            && player.getStat(Items.EGG_CHICKEN.getStat(pickUpKey)) > 0) {
            player.triggerAchievement(StardewAchievements.EGG);
        }
    }
}
