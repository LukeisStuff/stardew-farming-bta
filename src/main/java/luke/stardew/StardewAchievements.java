package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.achievement.Achievement;
import net.minecraft.core.achievement.AchievementList;
import net.minecraft.core.achievement.stat.Stat;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.helper.Side;
import org.lwjgl.opengl.GL11;
import turniplabs.halplibe.util.achievements.AchievementPage;
import turniplabs.halplibe.util.achievements.GuiAchievements;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;

public class StardewAchievements extends AchievementPage {
	public StardewAchievements() {
		super("Stardew Farming", "achievements.page.stardew");
		Field[] achievements = StardewAchievements.class.getDeclaredFields();
		Arrays.stream(achievements).filter((F)->F.getType().equals(Achievement.class)).forEach((F)->{
			try {
				achievementList.add((Achievement) ((Stat) F.get(null)).registerStat());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
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

	public static final Achievement STARDEW = new Achievement(AchievementList.achievementList.size() + 1, "stardew.stardew", 0, 0, StardewItems.wateringCan, null);

	public static final Achievement FRUIT = new Achievement(AchievementList.achievementList.size() + 1, "stardew.fruit", 2, -2, StardewItems.strawberry, STARDEW);
	public static final Achievement VEGETABLE = new Achievement(AchievementList.achievementList.size() + 1, "stardew.vegetable", -2, -2, StardewItems.carrot, STARDEW);

	public static final Achievement APPLE = new Achievement(AchievementList.achievementList.size() + 1, "stardew.apple", -2, 0, Item.foodApple, STARDEW);
	public static final Achievement GAPPLE = new Achievement(AchievementList.achievementList.size() + 1, "stardew.gapple", -4, 1, Item.foodAppleGold, APPLE);

	public static final Achievement BEEHIVE = new Achievement(AchievementList.achievementList.size() + 1, "stardew.beehive", 2, 0, StardewItems.honey, STARDEW);
	public static final Achievement CANDLE = new Achievement(AchievementList.achievementList.size() + 1, "stardew.candle", 4, -1, StardewBlocks.candle, BEEHIVE);

	public static final Achievement AMATEUR_FISHER = new Achievement(AchievementList.achievementList.size() + 1, "stardew.amateur.fisher", 0, 2, StardewItems.foodSnapperRaw, STARDEW);
	public static final Achievement MASTER_FISHER = new Achievement(AchievementList.achievementList.size() + 1, "stardew.master.fisher", 1, 4, StardewItems.fishSword, AMATEUR_FISHER);

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

	protected IconCoordinate getTextureFromBlock(Block block) {
		return BlockModelDispatcher.getInstance().getDispatch(block).getBlockTextureFromSideAndMetadata(Side.BOTTOM, 0);
	}

	/*@Override
	public void getBackground(GuiAchievements guiAchievements, Random random, int iOffset, int jOffset, int blockX1, int blockY1, int blockX2, int blockY2) {
		int row = 0;
		while (row * 16 - blockY2 < 155) {
			float brightness = 0.6f - (float)(blockY1 + row) / 25.0f * 0.3f;
			GL11.glColor4f(brightness, brightness, brightness, 1.0f);
			int column = 0;
			while (column * 16 - blockX2 < 224) {
				random.setSeed(1234 + blockX1 + column);
				random.nextInt();
				int randomY = random.nextInt(1 + blockY1 + row) + (blockY1 + row) / 2;
				IconCoordinate texture = this.getTextureFromBlock(Block.sand);
				Block[] oreArray = stoneOres;
				if (randomY >= 28 || blockY1 + row > 24) {
					oreArray = basaltOres;
				}

				if (randomY > 37 || blockY1 + row == 35) {
					texture = this.getTextureFromBlock(Block.bedrock);
				} else if (randomY == 22) {
					if (random.nextInt(2) == 0) {
						texture = this.getTextureFromBlock(oreArray[3]);
					} else {
						texture = this.getTextureFromBlock(oreArray[4]);
					}
				} else if (randomY == 10) {
					texture = this.getTextureFromBlock(oreArray[1]);
				} else if (randomY == 8) {
					texture = this.getTextureFromBlock(oreArray[0]);
				} else if (randomY > 4) {
					texture = this.getTextureFromBlock(Block.stone);
					if (randomY >= 28 || blockY1 + row > 24) {
						texture = this.getTextureFromBlock(Block.basalt);
					}
				} else if (randomY > 0) {
					texture = this.getTextureFromBlock(Block.dirt);
				}

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
	}*/
}
