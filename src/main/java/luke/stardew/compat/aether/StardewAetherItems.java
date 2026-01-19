package luke.stardew.compat.aether;

import luke.stardew.items.ItemCoffee;
import net.minecraft.core.item.Item;
import teamport.aether.item.AetherItems;
import turniplabs.halplibe.helper.ItemBuilder;

import static luke.stardew.StardewConfig.itemID;
import static luke.stardew.StardewMod.MOD_ID;

public class StardewAetherItems {
    public static Item FOOD_COFFEE_SKYROOT;

    private static boolean hasInit = false;

    public static void init() {
        if (!hasInit) {
            hasInit = true;
            initializeItems();
        }
    }

    public static String itemKey(String string) {
        return MOD_ID + ":item/" + string;
    }

    public static void initializeItems() {
        FOOD_COFFEE_SKYROOT = new ItemBuilder(MOD_ID)
            .build(new ItemCoffee("food.coffee.skyroot", itemKey("food_coffee_skyroot"), itemID("FOOD_COFFEE_SKYROOT"), 1, 4, AetherItems.BUCKET_SKYROOT));
    }


}
