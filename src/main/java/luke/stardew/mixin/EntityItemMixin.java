package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.core.achievement.stat.StatList;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityItem.class, remap = false)
public abstract class EntityItemMixin {
	@Unique
	public ItemStack item;

	@Inject(method = "playerTouch", at = @At("TAIL"), cancellable = true)
	public void playerTouch(Player player, CallbackInfo ci) {
		if (this.item.itemID == StardewBlocks.saplingApple.id()) {
			//player.triggerAchievement(StardewAchievements.APPLE);
		}

		String pickUpKey = StatList.STAT_PICKED_UP;

		if ((this.item.itemID == Items.FOOD_FISH_RAW.id
			|| this.item.itemID == StardewItems.foodBassRaw.id
			|| this.item.itemID == StardewItems.foodSnapperRaw.id
			|| this.item.itemID == StardewItems.foodSalmonRaw.id)
			&& player.getStat(Items.FOOD_FISH_RAW.getStat(pickUpKey)) > 0
			&& player.getStat(StardewItems.foodSalmonRaw.getStat(pickUpKey)) > 0
			&& player.getStat(StardewItems.foodSnapperRaw.getStat(pickUpKey)) > 0
			&& player.getStat(StardewItems.foodBassRaw.getStat(pickUpKey)) > 0) {
			//player.triggerAchievement(StardewAchievements.AMATEUR_FISHER);
		}
		if ((this.item.itemID == StardewItems.fishGhost.id
			|| this.item.itemID == StardewItems.fishSword.id
			|| this.item.itemID == StardewItems.fishEelLava.id
			|| this.item.itemID == StardewItems.fishStone.id)
			&& player.getStat(StardewItems.fishSword.getStat(pickUpKey)) > 0
			&& player.getStat(StardewItems.fishGhost.getStat(pickUpKey)) > 0
			&& player.getStat(StardewItems.fishEelLava.getStat(pickUpKey)) > 0
			&& player.getStat(StardewItems.fishStone.getStat(pickUpKey)) > 0) {
			//player.triggerAchievement(StardewAchievements.MASTER_FISHER);
		}
		if ((this.item.itemID == StardewItems.cranberries.id
			|| this.item.itemID == StardewItems.grapes.id
			|| this.item.itemID == StardewItems.strawberry.id
			|| this.item.itemID == StardewItems.pineapple.id
			|| this.item.itemID == StardewItems.blueberry.id
			|| this.item.itemID == StardewBlocks.watermelon.id()
			|| this.item.itemID == Items.FOOD_APPLE.id)
			&& player.getStat(StardewItems.cranberries.getStat(pickUpKey)) > 0
			&& player.getStat(StardewItems.grapes.getStat(pickUpKey)) > 0
			&& player.getStat(StardewItems.strawberry.getStat(pickUpKey)) > 0
			&& player.getStat(StardewItems.pineapple.getStat(pickUpKey)) > 0
			&& player.getStat(StardewItems.blueberry.getStat(pickUpKey)) > 0
			&& player.getStat(StardewBlocks.watermelon.getStat(pickUpKey)) > 0
			&& player.getStat(Items.FOOD_APPLE.getStat(pickUpKey)) > 0) {
			//player.triggerAchievement(StardewAchievements.FRUIT);
		}
		if ((this.item.itemID == StardewItems.potato.id
			|| this.item.itemID == StardewItems.carrot.id
			|| this.item.itemID == StardewItems.tomato.id
			|| this.item.itemID == StardewItems.corn.id
			|| this.item.itemID == Blocks.PUMPKIN.id()
			|| this.item.itemID == StardewBlocks.cauliflower.id()
			|| this.item.itemID == Items.WHEAT.id)
			&& player.getStat(StardewItems.potato.getStat(pickUpKey)) > 0
			&& player.getStat(StardewItems.carrot.getStat(pickUpKey)) > 0
			&& player.getStat(StardewItems.tomato.getStat(pickUpKey)) > 0
			&& player.getStat(StardewItems.corn.getStat(pickUpKey)) > 0
			&& player.getStat(Blocks.PUMPKIN.getStat(pickUpKey)) > 0
			&& player.getStat(StardewBlocks.cauliflower.getStat(pickUpKey)) > 0
			&& player.getStat(Items.WHEAT.getStat(pickUpKey)) > 0) {
			//player.triggerAchievement(StardewAchievements.VEGETABLE);
			ci.cancel();
		}
	}
}
