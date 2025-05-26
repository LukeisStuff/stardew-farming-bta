package luke.stardew.achievements;

import luke.stardew.StardewMod;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.client.gui.achievements.ScreenAchievements;
import net.minecraft.client.gui.achievements.data.AchievementPage;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.achievement.Achievement;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.lang.I18n;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Random;

public class AchievementPageStardew extends AchievementPage {
	private final String name;
	private final ItemStack icon;

	public AchievementPageStardew(String name, ItemStack icon) {
		this.name = name;
		this.icon = icon;
	}

	@Override
	public @NotNull String getName() {
		return I18n.getInstance().translateNameKey(name);
	}

	@Override
	public @NotNull String getDescription() {
		return I18n.getInstance().translateNameKey(name);
	}

	@Override
	public @NotNull AchievementEntry onOpenAchievement() {
		return Objects.requireNonNull(this.getEntry(StardewAchievements.STARDEW));
	}

	@Override
	public @Nullable IconCoordinate getBackgroundTile(ScreenAchievements screen, int i, Random random, int j, int k) {
		return getTextureFromBlock(StardewBlocks.THATCH);
	}

	@Override
	public void postProcessBackground(ScreenAchievements screen, Random random, ScreenAchievements.BGLayer bGLayer, int i, int j) {
	}

	@Override
	public @NotNull ItemStack getIcon() {
		return this.icon;
	}

	@Override
	public int backgroundLayers() {
		return 1;
	}

	@Override
	public int backgroundColor() {
		return 0;
	}

	@Override
	public IconCoordinate getAchievementIcon(Achievement achievement) {
		return TextureRegistry.getTexture(achievement.getType().texture);
	}

	@Override
	public int lineColorLocked(boolean bl) {
		return 0;
	}

	@Override
	public int lineColorUnlocked(boolean bl) {
		return 7368816;
	}

	@Override
	public int lineColorCanUnlock(boolean bl) {
		return 65280;
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
