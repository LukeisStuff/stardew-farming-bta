package luke.stardew.achievements;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.core.achievement.Achievement;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.collection.NamespaceID;

import static luke.stardew.StardewMod.MOD_ID;

public final class StardewAchievements {

    public static NamespaceID key(String string) {
        return NamespaceID.getPermanent(MOD_ID, string);
    }

    public static final Achievement STARDEW = new Achievement(key("stardew"), "stardew.stardew", StardewItems.WATERING_CAN, null).registerAchievement();

    public static final Achievement FRUIT = new Achievement(key("get_fruit"), "stardew.fruit", StardewItems.STRAWBERRY, STARDEW).registerAchievement();
    public static final Achievement VEGETABLE = new Achievement(key("get_vegetable"), "stardew.vegetable", StardewItems.CARROT, STARDEW).registerAchievement();
    public static final Achievement EGG = new Achievement(key("get_egg"), "stardew.egg", StardewItems.EGG_DUCK, STARDEW).registerAchievement();

    public static final Achievement APPLE = new Achievement(key("get_apple"), "stardew.apple", Items.FOOD_APPLE, STARDEW).registerAchievement();
    public static final Achievement GAPPLE = new Achievement(key("get_apple_gold"), "stardew.gapple", Items.FOOD_APPLE_GOLD, APPLE).registerAchievement();

    public static final Achievement BEEHIVE = new Achievement(key("get_hive"), "stardew.beehive", StardewItems.HONEY, STARDEW).registerAchievement();
    public static final Achievement CANDLE = new Achievement(key("get_candle"), "stardew.candle", StardewItems.CANDLE, BEEHIVE).registerAchievement();

    public static final Achievement AMATEUR_FISHER = new Achievement(key("fishing_amateur"), "stardew.amateur.fisher", StardewItems.FOOD_SNAPPER_RAW, STARDEW).registerAchievement();
    public static final Achievement MASTER_FISHER = new Achievement(key("fishing_master"), "stardew.master.fisher", StardewItems.FISH_SWORD, AMATEUR_FISHER).registerAchievement();
}
