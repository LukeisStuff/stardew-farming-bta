package luke.stardew.items;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.item.*;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tag.ItemTags;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;

import static luke.stardew.StardewConfig.itemID;
import static luke.stardew.StardewMod.MOD_ID;

public class StardewItems {

    //Other Natural
    public static Item BUSH;

    //Spring Crops
    public static Item SEEDS_CARROT;
    public static Item CARROT;

    public static Item SEEDS_BLUEBERRY;
    public static Item BLUEBERRY;

    public static Item SEEDS_PINEAPPLE;
    public static Item PINEAPPLE;

    //Summer Crops

    public static Item SEEDS_TOMATO;
    public static Item TOMATO;
    public static Item SEEDS_POTATO;
    public static Item POTATO;

    public static Item SEEDS_STRAWBERRY;
    public static Item STRAWBERRY;
    public static Item SEEDS_WATERMELON;

    //Fall Crops
    public static Item SEEDS_CORN;
    public static Item CORN;

    public static Item SEEDS_GRAPES;
    public static Item GRAPES;

    //Winter Crops

    public static Item SEEDS_CAULIFLOWER;

    public static Item SEEDS_CRANBERRIES;
    public static Item CRANBERRIES;

    //Processed Foods
    public static Item EGG_COOKED;
    public static Item HONEY;
    public static Item JAR_JAM;
    public static Item CHEESE;

    public static Item FOOD_STEW_VEGETABLE;
    public static Item FOOD_STEW_CHEESE;
    public static Item FOOD_STEW_FRUIT;

    public static Item FOOD_CAKE_CHOCOLATE;


    //Watering Cans
    public static Item WATERING_CAN;
    public static Item WATERING_CAN_STEEL;

    public static Item WAX;
    public static Item CANDLE;


    public static Item FOOD_PIZZA;

    //Fishes
    public static Item FOOD_SALMON_RAW;
    public static Item FOOD_SALMON_COOKED;
    public static Item FOOD_BASS_RAW;
    public static Item FOOD_BASS_COOKED;
    public static Item FOOD_SNAPPER_RAW;
    public static Item FOOD_SNAPPER_COOKED;

    public static Item FISH_EEL_LAVA;
    public static Item FISH_SWORD;
    public static Item FISH_GHOST;
    public static Item FISH_PIG;
    public static Item FISH_STONE;

    //Fishing Rods
    public static Item TOOL_FISHINGROD_STONE;
    public static Item TOOL_FISHINGROD_IRON;
    public static Item TOOL_FISHINGROD_GOLD;
    public static Item TOOL_FISHINGROD_DIAMOND;
    public static Item TOOL_FISHINGROD_STEEL;

    public static Item WORM;
    public static Item ARMOR_CAN_OF_WORMS;
    public static Item ARMOR_CAN_OF_WORMS_GOLDEN;

    //Treasures
    public static Item RECORD_PINK;

    // Beans/Coffee
    public static Item BEANS_COFFEE;
    public static Item FOOD_COFFEE;

    public static Item EGG_DUCK;

    public static Item FIBER;

    public static Item FOOD_STEW_TRUFFLE;
    public static Item FOOD_SEEDS_ROASTED;

    public static Item FOOD_APPLE_PIE;
    public static Item FOOD_APPLE_PIE_SLICE;

    private static boolean hasInit = false;

    public static void init() {
        if (!hasInit) {
            hasInit = true;
            initializeItems();
        }
    }

    public static String itemNSID(String string) {
        return MOD_ID + ":item/" + string;
    }

    public static void initializeItems() {

        ItemBuilder seeds = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.SEEDS_WHEAT))
            .setTags(ItemTags.CHICKENS_FAVOURITE_ITEM);

        BUSH = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Blocks.SPINIFEX))
            .build(new ItemPlaceable(MOD_ID + ".bush", itemNSID("bush"), itemID("BUSH"), StardewBlocks.BUSH));

        //Spring Crops
        SEEDS_CARROT = seeds
            .build(new ItemSeeds(MOD_ID + ".seeds.carrot", itemNSID("seeds_carrot"), itemID("SEEDS_CARROT"), StardewBlocks.CROPS_CARROT));

        CARROT = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemFood(MOD_ID + ".food.carrot", itemNSID("food_carrot"), itemID("CARROT"), 2, 8, false, 8));

        SEEDS_BLUEBERRY = seeds
            .build(new ItemSeeds(MOD_ID + ".seeds.blueberry", itemNSID("seeds_blueberry"), itemID("SEEDS_BLUEBERRY"), StardewBlocks.CROPS_BLUEBERRY));

        BLUEBERRY = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemFruit(MOD_ID + ".food.blueberry", itemNSID("food_blueberry"), itemID("BLUEBERRY"), 1, 8, 16));

        SEEDS_PINEAPPLE = seeds
            .build(new ItemSeeds(MOD_ID + ".seeds.pineapple", itemNSID("seeds_pineapple"), itemID("SEEDS_PINEAPPLE"), StardewBlocks.CROPS_PINEAPPLE));

        PINEAPPLE = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemFruit(MOD_ID + ".food.pineapple", itemNSID("food_pineapple"), itemID("PINEAPPLE"), 4, 8, 4));


        //Summer Crops
        SEEDS_TOMATO = seeds
            .build(new ItemSeeds(MOD_ID + ".seeds.tomato", itemNSID("seeds_tomato"), itemID("SEEDS_TOMATO"), StardewBlocks.CROPS_TOMATO));

        TOMATO = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemTomato(MOD_ID + ".food.tomato", itemNSID("food_tomato"), itemID("TOMATO"), 2, 8, false, 8));

        SEEDS_POTATO = seeds
            .build(new ItemSeeds(MOD_ID + ".seeds.potato", itemNSID("seeds_potato"), itemID("SEEDS_POTATO"), StardewBlocks.CROPS_POTATO));

        POTATO = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemFood(MOD_ID + ".food.potato", itemNSID("food_potato"), itemID("POTATO"), 1, 8, false, 8));

        SEEDS_STRAWBERRY = seeds
            .build(new ItemSeeds(MOD_ID + ".seeds.strawberry", itemNSID("seeds_strawberry"), itemID("SEEDS_STRAWBERRY"), StardewBlocks.CROPS_STRAWBERRY));

        STRAWBERRY = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemFruit(MOD_ID + ".food.strawberry", itemNSID("food_strawberry"), itemID("STRAWBERRY"), 2, 8, 8));

        SEEDS_WATERMELON = seeds
            .build(new ItemSeeds(MOD_ID + ".seeds.watermelon", itemNSID("seeds_watermelon"), itemID("SEEDS_WATERMELON"), StardewBlocks.CROPS_WATERMELON));


        //Fall Crops
        SEEDS_CORN = seeds
            .build(new ItemSeeds(MOD_ID + ".seeds.corn", itemNSID("seeds_corn"), itemID("SEEDS_CORN"), StardewBlocks.CROPS_CORN_BOTTOM));

        CORN = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemFood(MOD_ID + ".food.corn", itemNSID("food_corn"), itemID("CORN"), 2, 8, false, 8));

        SEEDS_GRAPES = seeds
            .build(new ItemSeedsStake(MOD_ID + ".seeds.grapes", itemNSID("seeds_grapes"), itemID("SEEDS_GRAPES"), StardewBlocks.CROPS_GRAPE_BOTTOM));

        GRAPES = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemFruit(MOD_ID + ".food.grapes", itemNSID("food_grapes"), itemID("GRAPES"), 1, 8, 16));


        //Winter Crops
        SEEDS_CAULIFLOWER = seeds
            .build(new ItemSeeds(MOD_ID + ".seeds.cauliflower", itemNSID("seeds_cauliflower"), itemID("SEEDS_CAULIFLOWER"), StardewBlocks.CROPS_CAULIFLOWER));


        SEEDS_CRANBERRIES = seeds
            .build(new ItemSeeds(MOD_ID + ".seeds.cranberries", itemNSID("seeds_cranberries"), itemID("SEEDS_CRANBERRIES"), StardewBlocks.CROPS_CRANBERRIES));

        CRANBERRIES = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemFruit(MOD_ID + ".food.cranberries", itemNSID("food_cranberries"), itemID("CRANBERRIES"), 1, 8, 16));


        //Fishes
        FOOD_SALMON_RAW = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_FISH_COOKED))
            .build(new ItemFood(MOD_ID + ".food.salmon.raw", itemNSID("food_salmon_raw"), itemID("FOOD_SALMON_RAW"), 2, 12, false, 8));

        FOOD_SALMON_COOKED = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_FISH_COOKED))
            .build(new ItemFood(MOD_ID + ".food.salmon.cooked", itemNSID("food_salmon_cooked"), itemID("FOOD_SALMON_COOKED"), 5, 12, false, 8));

        FOOD_BASS_RAW = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_FISH_COOKED))
            .build(new ItemFood(MOD_ID + ".food.bass.raw", itemNSID("food_bass_raw"), itemID("FOOD_BASS_RAW"), 2, 12, false, 8));

        FOOD_BASS_COOKED = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_FISH_COOKED))
            .build(new ItemFood(MOD_ID + ".food.bass.cooked", itemNSID("food_bass_cooked"), itemID("FOOD_BASS_COOKED"), 5, 12, false, 8));

        FOOD_SNAPPER_RAW = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_FISH_COOKED))
            .build(new ItemFood(MOD_ID + ".food.snapper.raw", itemNSID("food_snapper_raw"), itemID("FOOD_SNAPPER_RAW"), 2, 12, false, 8));

        FOOD_SNAPPER_COOKED = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_FISH_COOKED))
            .build(new ItemFood(MOD_ID + ".food.snapper.cooked", itemNSID("food_snapper_cooked"), itemID("FOOD_SNAPPER_COOKED"), 5, 12, false, 8));

        FISH_EEL_LAVA = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_FISH_COOKED))
            .setStackSize(1)
            .build(new Item(MOD_ID + ".fish.lavaeel", itemNSID("fish_lavaeel"), itemID("FISH_EEL_LAVA")));

        FISH_SWORD = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_FISH_COOKED))
            .setStackSize(1)
            .build(new Item(MOD_ID + ".fish.sword", itemNSID("fish_sword"), itemID("FISH_SWORD")));

        FISH_GHOST = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_FISH_COOKED))
            .setStackSize(1)
            .build(new Item(MOD_ID + ".fish.ghost", itemNSID("fish_ghost"), itemID("FISH_GHOST")));

        FISH_PIG = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_FISH_COOKED))
            .setStackSize(1)
            .build(new Item(MOD_ID + ".fish.pig", itemNSID("fish_pig"), itemID("FISH_PIG")));

        FISH_STONE = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_FISH_COOKED))
            .setStackSize(1)
            .build(new Item(MOD_ID + ".fish.stone", itemNSID("fish_stone"), itemID("FISH_STONE")));

        EGG_COOKED = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemFood(MOD_ID + ".food.egg.cooked", itemNSID("food_egg_cooked"), itemID("EGG_COOKED"), 10, 8, false, 16));

        HONEY = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemFood(MOD_ID + ".food.honey", itemNSID("honey"), itemID("HONEY"), 1, 16, false, 64));

        JAR_JAM = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemJam(MOD_ID + ".food.jam", itemNSID("food_jam"), itemID("JAR_JAM"), 8, 16, false, 1));

        CHEESE = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemFood(MOD_ID + ".food.cheese", itemNSID("food_cheese"), itemID("CHEESE"), 4, 8, false, 4));

        FOOD_COFFEE = new ItemBuilder(MOD_ID)
            .setContainerItem(() -> Items.BUCKET_IRON)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemCoffee(MOD_ID + ".food.coffee", itemNSID("food_coffee"), itemID("FOOD_COFFEE"), 1, 4, () -> Items.BUCKET_IRON));

        FOOD_STEW_VEGETABLE = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_STEW_MUSHROOM))
            .build(new ItemSoup(MOD_ID + ".food.stew.vegetable", itemNSID("food_stew_vegetable"), itemID("FOOD_STEW_VEGETABLE"), 12, 17));

        FOOD_STEW_CHEESE = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_STEW_MUSHROOM))
            .build(new ItemSoup(MOD_ID + ".food.stew.cheese", itemNSID("food_stew_cheese"), itemID("FOOD_STEW_CHEESE"), 14, 18));

        FOOD_STEW_FRUIT = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_STEW_MUSHROOM))
            .build(new ItemSoup(MOD_ID + ".food.stew.fruit", itemNSID("food_stew_fruit"), itemID("FOOD_STEW_FRUIT"), 16, 19));

        FOOD_STEW_TRUFFLE = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.FOOD_STEW_MUSHROOM))
            .build(new ItemSoup(MOD_ID + ".food.stew.truffle", itemNSID("food_stew_truffle"), itemID("FOOD_STEW_TRUFFLE"), 20, 15));

        FOOD_CAKE_CHOCOLATE = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemPlaceable(MOD_ID + ".food.cake.chocolate", itemNSID("food_cake_chocolate"), itemID("FOOD_CAKE_CHOCOLATE"), StardewBlocks.CAKE_CHOCOLATE).setMaxStackSize(1));

        FOOD_PIZZA = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemPlaceable(MOD_ID + ".food.pizza", itemNSID("food_pizza"), itemID("FOOD_PIZZA"), StardewBlocks.PIZZA).setMaxStackSize(1));

        FOOD_SEEDS_ROASTED = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemFood(MOD_ID + ".food.seeds.roasted", itemNSID("food_seeds_roasted"), itemID("FOOD_SEEDS_ROASTED"), 1, 20, false, 64));


        //Tools
        WATERING_CAN = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISC_TOOLS))
            .build(new ItemToolWateringCan(MOD_ID + ".tool.wateringcan", itemNSID("wateringcan"), itemID("WATERING_CAN"), ToolMaterial.iron));

        WATERING_CAN_STEEL = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISC_TOOLS))
            .build(new ItemToolWateringCan(MOD_ID + ".tool.wateringcan.steel", itemNSID("wateringcan_steel"), itemID("WATERING_CAN_STEEL"), ToolMaterial.steel));

        WAX = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.BASICS))
            .build(new Item(MOD_ID + ".wax", itemNSID("wax"), itemID("WAX")));

        CANDLE = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.PLACEABLES))
            .build(new ItemPlaceable(MOD_ID + ".candle", itemNSID("candle"), itemID("CANDLE"), StardewBlocks.CANDLE));


        //Fishing
        var fishingRod = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.TOOL_FISHINGROD));

        TOOL_FISHINGROD_STONE   = fishingRod.build(new ItemToolFishingRodTiered(MOD_ID + ".tool.fishingrod.stone", itemNSID("tool_fishingrod_stone"), itemID("TOOL_FISHINGROD_STONE"), ToolMaterial.stone));
        TOOL_FISHINGROD_IRON    = fishingRod.build(new ItemToolFishingRodTiered(MOD_ID + ".tool.fishingrod.iron", itemNSID("tool_fishingrod_iron"), itemID("TOOL_FISHINGROD_IRON"), ToolMaterial.iron));
        TOOL_FISHINGROD_GOLD    = fishingRod.build(new ItemToolFishingRodTiered(MOD_ID + ".tool.fishingrod.gold", itemNSID("tool_fishingrod_gold"), itemID("TOOL_FISHINGROD_GOLD"), ToolMaterial.gold));
        TOOL_FISHINGROD_DIAMOND = fishingRod.build(new ItemToolFishingRodTiered(MOD_ID + ".tool.fishingrod.diamond", itemNSID("tool_fishingrod_diamond"), itemID("TOOL_FISHINGROD_DIAMOND"), ToolMaterial.diamond));
        TOOL_FISHINGROD_STEEL   = fishingRod.build(new ItemToolFishingRodTiered(MOD_ID + ".tool.fishingrod.steel", itemNSID("tool_fishingrod_steel"), itemID("TOOL_FISHINGROD_STEEL"), ToolMaterial.steel));

        WORM = fishingRod.build(new Item(MOD_ID + ".worm", itemNSID("worm"), itemID("WORM")));

        ARMOR_CAN_OF_WORMS = fishingRod.build(new ItemCanOfWorms(MOD_ID + ".armor.canofworms", itemNSID("armor_canofworms"), itemID("ARMOR_CAN_OF_WORMS")));
        ARMOR_CAN_OF_WORMS_GOLDEN = fishingRod.build(new ItemCanOfWormsEndless(MOD_ID + ".armor.canofworms.golden", itemNSID("armor_canofworms_golden"), itemID("ARMOR_CAN_OF_WORMS_GOLDEN")));


        //Treasures
        RECORD_PINK = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.RECORDS))
            .setStackSize(1)
            .build(new ItemDiscMusic(MOD_ID + ".record.pink", itemNSID("record_pink"), itemID("RECORD_PINK"), "stardew:axolotl", "C418"));

        BEANS_COFFEE = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemSeedsStake(MOD_ID + ".bean.coffee", itemNSID("beans_coffee"), itemID("BEANS_COFFEE"), StardewBlocks.CROPS_BEANS_BOTTOM));

        EGG_DUCK = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.EGG_CHICKEN))
            .build(new ItemEggDuck(MOD_ID + ".egg.duck", itemNSID("egg_duck"), itemID("EGG_DUCK")));


        FIBER = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.BASICS))
            .build(new Item(MOD_ID + ".fiber", itemNSID("fiber"), itemID("FIBER")));

        FOOD_APPLE_PIE = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemPlaceable(MOD_ID + ".food.apple.pie", itemNSID("food_apple_pie"), itemID("FOOD_APPLE_PIE"), StardewBlocks.APPLE_PIE).setMaxStackSize(1));

        FOOD_APPLE_PIE_SLICE = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.FOOD))
            .build(new ItemFood(MOD_ID + ".food.apple.pie.slice", itemNSID("food_apple_pie_slice"), itemID("FOOD_APPLE_PIE_SLICE"), 3, 4, false, 4));
    }


}
