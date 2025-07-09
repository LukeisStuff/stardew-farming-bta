package luke.stardew.items;

import luke.stardew.StardewConfig;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.item.*;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tag.ItemTags;
import turniplabs.halplibe.helper.ItemBuilder;

import static luke.stardew.StardewMod.MOD_ID;

public class StardewItems {

	public static int itemID = 22000;

	public static int itemID(String itemName) {
		try {
			return StardewConfig.cfg.getInt("Item IDs." + itemName);
		}catch (NullPointerException e) {
			StardewConfig.properties.addEntry("Item IDs." + itemName, itemID);
			return itemID++;
		}
	}

	public static String itemKey(String string) {
		return MOD_ID + ":item/" + string;
	}


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



	public static Item DOUGH;
	public static Item EGG_COOKED;
	public static Item HONEY;
	public static Item JAR_JAM;
	public static Item CHEESE;

	public static Item FOOD_STEW_VEGETABLE;
	public static Item FOOD_STEW_CHEESE;
	public static Item FOOD_STEW_FRUIT;
	public static Item FOOD_STEW_TRUFFLE;

	public static Item FOOD_CAKE_CHOCOLATE;

	//Watering Cans
	public static Item WATERING_CAN;
	public static Item WATERING_CAN_STEEL;

	public static Item WAX;

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
	public static Item BEANS_COFFE;
	public static Item FOOD_COFFEE;

	public static Item EGG_DUCK;

	public static Item FIBER;



	public static void initializeItems() {

		ItemBuilder seeds = new ItemBuilder(MOD_ID)
			.setStackSize(64)
			.setTags(ItemTags.CHICKENS_FAVOURITE_ITEM);

		//Spring Crops
		SEEDS_CARROT = seeds
			.build(new ItemSeeds("seeds.carrot", itemKey("seeds_carrot"), itemID("SEEDS_CARROT"), StardewBlocks.CROPS_CARROT));
		CARROT = new ItemBuilder(MOD_ID)
			.build(new ItemFood("food.carrot", itemKey("carrot"), itemID("CARROT"), 2, 8,false, 8));

		SEEDS_BLUEBERRY = seeds
			.build(new ItemSeeds("seeds.blueberry", itemKey("seeds_blueberry"), itemID("SEEDS_BLUEBERRY"), StardewBlocks.CROPS_BLUEBERRY));
		BLUEBERRY = new ItemBuilder(MOD_ID)
			.build(new ItemFruit("food.blueberry", itemKey("blueberry"), itemID("BLUEBERRY"), 1, 8, 16));

		SEEDS_PINEAPPLE = seeds
			.build(new ItemSeeds("seeds.pineapple", itemKey("seeds_pineapple"), itemID("SEEDS_PINEAPPLE"), StardewBlocks.CROPS_PINEAPPLE));
		PINEAPPLE = new ItemBuilder(MOD_ID)
			.build(new ItemFruit("food.pineapple", itemKey("pineapple"), itemID("PINEAPPLE"), 4, 8, 4));


		//Summer Crops
		SEEDS_TOMATO = seeds
			.build(new ItemSeeds("seeds.tomato", itemKey("seeds_tomato"), itemID("SEEDS_TOMATO"), StardewBlocks.CROPS_TOMATO));
		TOMATO = new ItemBuilder(MOD_ID)
			.build(new ItemFood("food.tomato", itemKey("tomato"), itemID("TOMATO"), 2, 8,false, 8));

		SEEDS_POTATO = seeds
			.build(new ItemSeeds("seeds.potato", itemKey("seeds_potato"), itemID("SEEDS_POTATO"), StardewBlocks.CROPS_POTATO));
		POTATO = new ItemBuilder(MOD_ID)
			.build(new ItemFood("food.potato", itemKey("food_potato"), itemID("POTATO"), 1, 8,false, 8));

		SEEDS_STRAWBERRY = seeds
			.build(new ItemSeeds("seeds.strawberry", itemKey("seeds_strawberry"), itemID("SEEDS_STRAWBERRY"), StardewBlocks.CROPS_STRAWBERRY));
		STRAWBERRY = new ItemBuilder(MOD_ID)
			.build(new ItemFruit("food.strawberry", itemKey("food_strawberry"), itemID("STRAWBERRY"), 2, 8, 8));

		SEEDS_WATERMELON = seeds
			.build(new ItemSeeds("seeds.watermelon", itemKey("seeds_watermelon"), itemID("SEEDS_WATERMELON"), StardewBlocks.CROPS_WATERMELON));


		//Fall Crops
		SEEDS_CORN = seeds
			.build(new ItemSeeds("seeds.corn", itemKey("seeds_corn"), itemID("SEEDS_CORN"), StardewBlocks.CROPS_CORN_BOTTOM));
		CORN = new ItemBuilder(MOD_ID)
			.build(new ItemFood("food.corn", itemKey("corn"), itemID("CORN"), 2, 8,false, 8));

		SEEDS_GRAPES = seeds
			.build(new ItemSeedsStake("seeds.grapes", itemKey("seeds_grapes"), itemID("SEEDS_GRAPES"),  StardewBlocks.CROPS_GRAPE_BOTTOM));
		GRAPES = new ItemBuilder(MOD_ID)
			.build(new ItemFruit("food.grapes", itemKey("grapes"), itemID("GRAPES"), 1, 8, 16));


		//Winter Crops
		SEEDS_CAULIFLOWER = seeds
			.build(new ItemSeeds("seeds.cauliflower", itemKey("seeds_cauliflower"), itemID("SEEDS_CAULIFLOWER"), StardewBlocks.CROPS_CAULIFLOWER));

		SEEDS_CRANBERRIES = seeds
			.build(new ItemSeeds("seeds.cranberries", itemKey("seeds_cranberries"), itemID("SEEDS_CRANBERRIES"), StardewBlocks.CROPS_CRANBERRIES));
		CRANBERRIES = new ItemBuilder(MOD_ID)
			.build(new ItemFruit("food.cranberries", itemKey("cranberries"), itemID("CRANBERRIES"), 1, 8, 16));


		//Fishes
		FOOD_SALMON_RAW = new ItemBuilder(MOD_ID)
			.build(new ItemFood("food.salmon.raw", itemKey("food_salmon_raw"), itemID("FOOD_SALMON_RAW"), 2, 12,false, 8));
		FOOD_SALMON_COOKED = new ItemBuilder(MOD_ID)
			.build(new ItemFood("food.salmon.cooked", itemKey("food_salmon_cooked"), itemID("FOOD_SALMON_COOKED"), 5, 12,false, 8));

		FOOD_BASS_RAW = new ItemBuilder(MOD_ID)
			.build(new ItemFood("food.bass.raw", itemKey("food_bass_raw"), itemID("FOOD_BASS_RAW"), 2, 12,false, 8));
		FOOD_BASS_COOKED = new ItemBuilder(MOD_ID)
			.build(new ItemFood("food.bass.cooked", itemKey("food_bass_cooked"), itemID("FOOD_BASS_COOKED"), 5, 12,false, 8));

		FOOD_SNAPPER_RAW = new ItemBuilder(MOD_ID)
			.build(new ItemFood("food.snapper.raw", itemKey("food_snapper_raw"), itemID("FOOD_SNAPPER_RAW"), 2, 12,false, 8));
		FOOD_SNAPPER_COOKED = new ItemBuilder(MOD_ID)
			.build(new ItemFood("food.snapper.cooked", itemKey("food_snapper_cooked"), itemID("FOOD_SNAPPER_COOKED"), 5, 12,false, 8));

		FISH_EEL_LAVA = new ItemBuilder(MOD_ID)
			.setStackSize(1)
			.build(new Item("fish.lavaeel", itemKey("fish_lavaeel"), itemID("FISH_EEL_LAVA")));
		FISH_SWORD = new ItemBuilder(MOD_ID)
			.setStackSize(1)
			.build(new Item("fish.sword", itemKey("fish_sword"), itemID("FISH_SWORD")));
		FISH_GHOST = new ItemBuilder(MOD_ID)
			.setStackSize(1)
			.build(new Item("fish.ghost", itemKey("fish_ghost"), itemID("FISH_GHOST")));
		FISH_STONE = new ItemBuilder(MOD_ID)
			.setStackSize(1)
			.build(new Item("fish.stone", itemKey("fish_stone"), itemID("FISH_STONE")));


		//Processed Foods
		DOUGH = new ItemBuilder(MOD_ID)
			.build(new ItemFood("food.dough", itemKey("dough"), itemID("DOUGH"), -1, 0,false, 64));

		EGG_COOKED = new ItemBuilder(MOD_ID)
			.build(new ItemFood("food.egg.cooked", itemKey("food_egg_cooked"), itemID("EGG_COOKED"), 10, 8,false, 16));

		HONEY = new ItemBuilder(MOD_ID)
			.build(new ItemFood("food.honey", itemKey("honey"), itemID("HONEY"), 1, 16,false, 64));

		JAR_JAM = new ItemBuilder(MOD_ID)
			.build(new ItemJam("food.jam", itemKey("food_jam"), itemID("JAR_JAM"), 8, 16,false, 1));

		CHEESE = new ItemBuilder(MOD_ID)
			.build(new ItemFood("food.cheese", itemKey("cheese"), itemID("CHEESE"), 4, 8,false, 4));

		FOOD_COFFEE = new ItemBuilder(MOD_ID)
			.build(new ItemCoffee("food.coffee", itemKey("food_coffee"), itemID("FOOD_COFFEE"), 1, 4));

		FOOD_STEW_VEGETABLE = new ItemBuilder(MOD_ID)
			.build(new ItemSoup("food.stew.vegetable", itemKey("food_stew_vegetable"), itemID("FOOD_STEW_VEGETABLE"), 12, 17));

		FOOD_STEW_CHEESE = new ItemBuilder(MOD_ID)
			.build(new ItemSoup("food.stew.cheese", itemKey("food_stew_cheese"), itemID("FOOD_STEW_CHEESE"), 14, 18));

		FOOD_STEW_FRUIT = new ItemBuilder(MOD_ID)
			.build(new ItemSoup("food.stew.fruit", itemKey("food_stew_fruit"), itemID("FOOD_STEW_FRUIT"), 16, 19));

		FOOD_STEW_TRUFFLE = new ItemBuilder(MOD_ID)
			.build(new ItemSoup("food.stew.truffle", itemKey("food_stew_truffle"), itemID("FOOD_STEW_TRUFFLE"), 20, 15));

		FOOD_CAKE_CHOCOLATE = new ItemBuilder(MOD_ID)
			.build(new ItemPlaceable("food.cake.chocolate", itemKey("food_cake_chocolate"), itemID("FOOD_CAKE_CHOCOLATE"), StardewBlocks.CAKE_CHOCOLATE).setMaxStackSize(1));

		FOOD_PIZZA = new ItemBuilder(MOD_ID)
			.build(new ItemPlaceable("food.pizza", itemKey("food_pizza"), itemID("FOOD_PIZZA"), StardewBlocks.PIZZA).setMaxStackSize(1));


		//Tools
		WATERING_CAN = new ItemBuilder(MOD_ID)
			.build(new ItemToolWateringCan("tool.wateringcan", itemKey("wateringcan"), itemID("WATERING_CAN"), ToolMaterial.iron));

		WATERING_CAN_STEEL = new ItemBuilder(MOD_ID)
			.build(new ItemToolWateringCan("tool.wateringcan.steel", itemKey("wateringcan_steel"), itemID("WATERING_CAN_STEEL"), ToolMaterial.steel));

		WAX = new ItemBuilder(MOD_ID)
			.setStackSize(16)
			.build(new Item("wax", itemKey("wax"), itemID("WAX")));


		//Fishing
		TOOL_FISHINGROD_STONE = new ItemBuilder(MOD_ID)
			.build(new ItemToolFishingRodTiered("tool.fishingrod.stone", itemKey("tool_fishingrod_stone"), itemID("TOOL_FISHINGROD_STONE"), ToolMaterial.stone));
		TOOL_FISHINGROD_IRON = new ItemBuilder(MOD_ID)
			.build(new ItemToolFishingRodTiered("tool.fishingrod.iron", itemKey("tool_fishingrod_iron"), itemID("TOOL_FISHINGROD_IRON"), ToolMaterial.iron));
		TOOL_FISHINGROD_GOLD = new ItemBuilder(MOD_ID)
			.build(new ItemToolFishingRodTiered("tool.fishingrod.gold", itemKey("tool_fishingrod_gold"), itemID("TOOL_FISHINGROD_GOLD"), ToolMaterial.gold));
		TOOL_FISHINGROD_DIAMOND = new ItemBuilder(MOD_ID)
			.build(new ItemToolFishingRodTiered("tool.fishingrod.diamond", itemKey("tool_fishingrod_diamond"), itemID("TOOL_FISHINGROD_DIAMOND"), ToolMaterial.diamond));
		TOOL_FISHINGROD_STEEL = new ItemBuilder(MOD_ID)
			.build(new ItemToolFishingRodTiered("tool.fishingrod.steel", itemKey("tool_fishingrod_steel"), itemID("TOOL_FISHINGROD_STEEL"), ToolMaterial.steel));

		WORM = new ItemBuilder(MOD_ID)
			.setStackSize(16)
			.build(new Item("worm", itemKey("worm"), itemID("WORM")));

		ARMOR_CAN_OF_WORMS = new ItemBuilder(MOD_ID)
			.build(new ItemCanOfWorms("armor.canofworms", itemKey("armor_canofworms"), itemID("ARMOR_CAN_OF_WORMS")));

		ARMOR_CAN_OF_WORMS_GOLDEN = new ItemBuilder(MOD_ID)
			.build(new ItemCanOfWormsEndless("armor.canofworms.gold", itemKey("armor_canofworms_golden"), itemID("ARMOR_CAN_OF_WORMS_GOLDEN")));


		//Treasures
		RECORD_PINK = new ItemBuilder(MOD_ID)
			.setStackSize(1)
			.build(new ItemDiscMusic("record.pink", itemKey("record_pink"), itemID("RECORD_PINK"), "stardew:axolotl", "C418"));

		BEANS_COFFE = new ItemBuilder(MOD_ID)
			.setStackSize(64)
			.build(new ItemSeedsStake("bean.coffee", itemKey("beans_coffee"), itemID("BEANS_COFFE"), StardewBlocks.CROPS_BEANS_BOTTOM));

		EGG_DUCK = new ItemBuilder(MOD_ID)
			.setStackSize(64)
			.build(new ItemEggDuck("egg.duck", itemKey("egg_duck"), itemID("EGG_DUCK")));


		FIBER = new ItemBuilder(MOD_ID)
			.setStackSize(64)
			.build(new Item("fiber", itemKey("fiber"), itemID("FIBER")));
	}


}
