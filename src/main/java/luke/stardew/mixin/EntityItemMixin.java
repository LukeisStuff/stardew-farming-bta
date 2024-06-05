package luke.stardew.mixin;

import luke.stardew.StardewAchievements;
import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.core.achievement.stat.StatList;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityItem.class, remap = false)
public abstract class EntityItemMixin {
	@Unique
	public ItemStack item;

	@Inject(method = "playerTouch(Lnet/minecraft/core/entity/player/EntityPlayer;)V", at = @At("TAIL"), cancellable = true)
	public void playerTouch(EntityPlayer player, CallbackInfo ci) {
		if (this.item.itemID == StardewBlocks.saplingApple.id) {
			player.triggerAchievement(StardewAchievements.APPLE);
		}

		if ((this.item.itemID == Item.foodFishRaw.id || this.item.itemID == StardewItems.foodBassRaw.id || this.item.itemID == StardewItems.foodSnapperRaw.id || this.item.itemID == StardewItems.foodSalmonRaw.id) && player.getStat(StatList.pickUpItemStats[Item.foodFishRaw.id]) > 0 && player.getStat(StatList.pickUpItemStats[StardewItems.foodSalmonRaw.id]) > 0 && player.getStat(StatList.pickUpItemStats[StardewItems.foodSnapperRaw.id]) > 0 && player.getStat(StatList.pickUpItemStats[StardewItems.foodBassRaw.id]) > 0) {
			player.triggerAchievement(StardewAchievements.AMATEUR_FISHER);
		}
		if ((this.item.itemID == StardewItems.fishGhost.id || this.item.itemID == StardewItems.fishSword.id || this.item.itemID == StardewItems.fishEelLava.id || this.item.itemID == StardewItems.fishStone.id) && player.getStat(StatList.pickUpItemStats[StardewItems.fishSword.id]) > 0 && player.getStat(StatList.pickUpItemStats[StardewItems.fishGhost.id]) > 0 && player.getStat(StatList.pickUpItemStats[StardewItems.fishEelLava.id]) > 0 && player.getStat(StatList.pickUpItemStats[StardewItems.fishStone.id]) > 0) {
			player.triggerAchievement(StardewAchievements.MASTER_FISHER);
		}
		if ((this.item.itemID == StardewItems.cranberries.id
			|| this.item.itemID == StardewItems.grapes.id
			|| this.item.itemID == StardewItems.strawberry.id
			|| this.item.itemID == StardewItems.pineapple.id
			|| this.item.itemID == StardewItems.blueberry.id
			|| this.item.itemID == StardewBlocks.watermelon.id
			|| this.item.itemID == Item.foodApple.id
			|| this.item.itemID == Item.cherry.id)
			&& player.getStat(StatList.pickUpItemStats[StardewItems.cranberries.id]) > 0
			&& player.getStat(StatList.pickUpItemStats[StardewItems.grapes.id]) > 0
			&& player.getStat(StatList.pickUpItemStats[StardewItems.strawberry.id]) > 0
			&& player.getStat(StatList.pickUpItemStats[StardewItems.pineapple.id]) > 0
			&& player.getStat(StatList.pickUpItemStats[StardewItems.blueberry.id]) > 0
			&& player.getStat(StatList.pickUpItemStats[StardewBlocks.watermelon.id]) > 0
			&& player.getStat(StatList.pickUpItemStats[Item.foodApple.id]) > 0
			&& player.getStat(StatList.pickUpItemStats[Item.cherry.id]) > 0) {
			player.triggerAchievement(StardewAchievements.FRUIT);
		}
		if ((this.item.itemID == StardewItems.potato.id
			|| this.item.itemID == StardewItems.carrot.id
			|| this.item.itemID == StardewItems.tomato.id
			|| this.item.itemID == StardewItems.corn.id
			|| this.item.itemID == Block.pumpkin.id
			|| this.item.itemID == StardewBlocks.cauliflower.id
			|| this.item.itemID == Item.wheat.id)
			&& player.getStat(StatList.pickUpItemStats[StardewItems.potato.id]) > 0
			&& player.getStat(StatList.pickUpItemStats[StardewItems.carrot.id]) > 0
			&& player.getStat(StatList.pickUpItemStats[StardewItems.tomato.id]) > 0
			&& player.getStat(StatList.pickUpItemStats[StardewItems.corn.id]) > 0
			&& player.getStat(StatList.pickUpItemStats[Block.pumpkin.id]) > 0
			&& player.getStat(StatList.pickUpItemStats[StardewBlocks.cauliflower.id]) > 0
			&& player.getStat(StatList.pickUpItemStats[Item.wheat.id]) > 0) {
			player.triggerAchievement(StardewAchievements.VEGETABLE);
		}
	}
}
