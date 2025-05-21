package luke.stardew;

import net.minecraft.client.gui.achievements.ScreenAchievements;
import net.minecraft.client.gui.achievements.data.AchievementPage;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.achievement.Achievement;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
//import turniplabs.halplibe.util.achievements.AchievementPage;
//import turniplabs.halplibe.util.achievements.GuiAchievements;

import java.util.Random;

public class StardewAchievements extends AchievementPage {
	//FIXME
	@Override
	public @NotNull String getName() {
		return null;
	}

	@Override
	public @NotNull String getDescription() {
		return null;
	}

	@Override
	public @NotNull AchievementEntry onOpenAchievement() {
		return null;
	}

	@Override
	public @Nullable IconCoordinate getBackgroundTile(ScreenAchievements screenAchievements, int i, Random random, int j, int k) {
		return null;
	}

	@Override
	public void postProcessBackground(ScreenAchievements screenAchievements, Random random, ScreenAchievements.BGLayer bGLayer, int i, int j) {

	}

	@Override
	public @NotNull ItemStack getIcon() {
		return null;
	}

	@Override
	public int backgroundLayers() {
		return 0;
	}

	@Override
	public int backgroundColor() {
		return 0;
	}

	@Override
	public IconCoordinate getAchievementIcon(Achievement achievement) {
		return null;
	}

	@Override
	public int lineColorLocked(boolean bl) {
		return 0;
	}

	@Override
	public int lineColorUnlocked(boolean bl) {
		return 0;
	}

	@Override
	public int lineColorCanUnlock(boolean bl) {
		return 0;
	}
	/*
	public StardewAchievements() {
		super("Stardew Farming", "achievements.page.stardew");
		Field[] achievements = StardewAchievements.class.getDeclaredFields();
		Arrays.stream(achievements).filter((F)->F.getType().equals(Achievement.class)).forEach((F)->{
			try {
				achievementList.add((Achievement) ((Stat) F.get(null)).registerStat());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		});

		((Stat) STARDEW).registerStat();
		achievementList.add(STARDEW);

		((Stat) FRUIT).registerStat();
		achievementList.add(FRUIT);
		((Stat) VEGETABLE).registerStat();
		achievementList.add(VEGETABLE);

		((Stat) APPLE).registerStat();
		achievementList.add(APPLE);
		((Stat) GAPPLE).registerStat();
		achievementList.add(GAPPLE);

		((Stat) BEEHIVE).registerStat();
		achievementList.add(BEEHIVE);
		((Stat) CANDLE).registerStat();
		achievementList.add(CANDLE);

		((Stat) AMATEUR_FISHER).registerStat();
		achievementList.add(AMATEUR_FISHER);
		((Stat) MASTER_FISHER).registerStat();
		achievementList.add(MASTER_FISHER);
	}

	public static final int StardewAchievementsID = 524300;
	public static final Achievement STARDEW = new Achievement(StardewAchievementsID + 1, "stardew.stardew", 0, 0, StardewItems.wateringCan, null);

	public static final Achievement FRUIT = new Achievement(StardewAchievementsID + 2, "stardew.fruit", 2, -2, StardewItems.strawberry, STARDEW);
	public static final Achievement VEGETABLE = new Achievement(StardewAchievementsID + 3, "stardew.vegetable", -2, -2, StardewItems.carrot, STARDEW);

	public static final Achievement APPLE = new Achievement(StardewAchievementsID + 4, "stardew.apple", -2, 0, Item.foodApple, STARDEW);
	public static final Achievement GAPPLE = new Achievement(StardewAchievementsID + 5, "stardew.gapple", -4, 1, Item.foodAppleGold, APPLE);

	public static final Achievement BEEHIVE = new Achievement(StardewAchievementsID + 6, "stardew.beehive", 2, 0, StardewItems.honey, STARDEW);
	public static final Achievement CANDLE = new Achievement(StardewAchievementsID + 7, "stardew.candle", 4, -1, StardewBlocks.candle, BEEHIVE);

	public static final Achievement AMATEUR_FISHER = new Achievement(StardewAchievementsID + 8, "stardew.amateur.fisher", 0, 2, StardewItems.foodSnapperRaw, STARDEW);
	public static final Achievement MASTER_FISHER = new Achievement(StardewAchievementsID + 9, "stardew.master.fisher", 1, 4, StardewItems.fishSword, AMATEUR_FISHER);

	@Override
	public void getBackground(GuiAchievements guiAchievements, Random random, int iOffset, int jOffset, int blockX1, int blockY1, int blockX2, int blockY2) {
		int row = 0;
		while (row * 16 - blockY2 < 155) {
			float f5 = 0.6f - (float)(blockY1 + row) / 25.0f * 0.3f;
			GL11.glColor4f(f5, f5, f5, 1.0f);
			int column = 0;
			while (column * 16 - blockX2 < 224) {
				IconCoordinate texture = getTextureFromBlock(StardewBlocks.blockHoney);
				guiAchievements.drawTexturedIcon(
					iOffset + column * 16 - blockX2,
					jOffset + row * 16 - blockY2,
					texture.width,
					texture.height,
					texture
				);
				++column;
			}
			++row;
		}
	}

	public IconCoordinate getTextureFromBlock(Block block) {
		return BlockModelDispatcher.getInstance().getDispatch(block).getBlockTextureFromSideAndMetadata(Side.BOTTOM, 0);
	}
*/
}
