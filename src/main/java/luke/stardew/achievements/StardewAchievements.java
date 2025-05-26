package luke.stardew.achievements;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.client.gui.achievements.data.AchievementPage;
import net.minecraft.client.gui.achievements.data.AchievementPages;
import net.minecraft.core.achievement.Achievement;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.collection.NamespaceID;

import static luke.stardew.StardewMod.MOD_ID;

public final class StardewAchievements {

	public static NamespaceID key(String string) {
		return NamespaceID.getPermanent(MOD_ID, string);
	}

	public static final Achievement STARDEW = new Achievement(key("stardew"), "stardew.stardew", StardewItems.WATERING_CAN, null);

	public static final Achievement FRUIT = new Achievement(key("get_fruit"), "stardew.fruit", StardewItems.STRAWBERRY, STARDEW);
	public static final Achievement VEGETABLE = new Achievement(key("get_vegetable"), "stardew.vegetable", StardewItems.CARROT, STARDEW);

	public static final Achievement APPLE = new Achievement(key("get_apple"), "stardew.apple", Items.FOOD_APPLE, STARDEW);
	public static final Achievement GAPPLE = new Achievement(key("get_apple_gold"), "stardew.gapple", Items.FOOD_APPLE_GOLD, APPLE);

	public static final Achievement BEEHIVE = new Achievement(key("get_hive"), "stardew.beehive", StardewItems.HONEY, STARDEW);
	public static final Achievement CANDLE = new Achievement(key("get_candle"), "stardew.candle", StardewBlocks.CANDLE, BEEHIVE);

	public static final Achievement AMATEUR_FISHER = new Achievement(key("fishing_amateur"), "stardew.amateur.fisher", StardewItems.FOOD_SNAPPER_RAW, STARDEW);
	public static final Achievement MASTER_FISHER = new Achievement(key("fishing_master"), "stardew.master.fisher", StardewItems.FISH_SWORD, AMATEUR_FISHER);

	public static void init() {
		AchievementPageStardew page = new AchievementPageStardew(MOD_ID, StardewItems.WATERING_CAN.getDefaultStack());
		page.addAchievement(StardewAchievements.STARDEW, 0, 0);
		page.addAchievement(StardewAchievements.FRUIT, 2, -2);
		page.addAchievement(StardewAchievements.VEGETABLE, -2, -2);
		page.addAchievement(StardewAchievements.APPLE, -2, 0);
		page.addAchievement(StardewAchievements.GAPPLE, -4, 1);
		page.addAchievement(StardewAchievements.BEEHIVE, 2, 0);
		page.addAchievement(StardewAchievements.CANDLE, 4, -1);
		page.addAchievement(StardewAchievements.AMATEUR_FISHER, 0, 2);
		page.addAchievement(StardewAchievements.MASTER_FISHER, 1, 4);
		AchievementPages.register(page);

		for (AchievementPage.AchievementEntry e : page.getAchievementEntries()) {
			e.achievement.registerStat();
		}
	}
}
