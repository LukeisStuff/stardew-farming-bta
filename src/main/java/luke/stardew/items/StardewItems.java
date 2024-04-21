package luke.stardew.items;

import luke.stardew.StardewConfig;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.item.*;
import turniplabs.halplibe.helper.ItemHelper;

import static luke.stardew.StardewMod.MOD_ID;

public class StardewItems {

	private int itemID(String itemName) {
		return StardewConfig.cfg.getInt("Item IDs." + itemName);
	}



	//Spring Crops
	public static Item seedsCarrot;
	public static Item carrot;
	public static Item seedsOnion;
	public static Item onion;

	public static Item seedsBlueberry;
	public static Item blueberry;
	public static Item jamBlueberry;

	public static Item seedsPineapple;
	public static Item pineapple;

	//Summer Crops
	public static Item orange;

	public static Item seedsTomato;
	public static Item tomato;
	public static Item seedsPotato;
	public static Item potato;

	public static Item seedsStrawberry;
	public static Item strawberry;
	public static Item jamStrawberry;
	public static Item seedsWatermelon;
	public static Item watermelon;

	//Fall Crops
	public static Item corn;

	public static Item seedsBlackberries;
	public static Item blackberries;
	public static Item seedsGrapes;
	public static Item grapes;

	//Winter Crops
	public static Item pomegranate;

	public static Item seedsCauliflower;
	public static Item seedsBroccoli;
	public static Item broccoli;

	public static Item seedsCranberries;
	public static Item cranberries;
	public static Item seedsPlum;
	public static Item plum;



	public static Item dough;
	public static Item eggCooked;
	public static Item jarHoney;
	public static Item cheese;

	public static Item foodStewVegetable;



	public static final Item devStick = ItemHelper.createItem(MOD_ID, new ItemDevStick("dev.stick", 18000), "stick_skyroot.png").setMaxStackSize(1);


	public void initilizeItems() {
		seedsTomato = ItemHelper.createItem(MOD_ID,
			new ItemSeeds("seeds.tomato", itemID("seedsTomato"), StardewBlocks.cropsTomato), "seedsTomato.png");

		tomato = ItemHelper.createItem(MOD_ID,
			new ItemFood("food.tomato", itemID("tomato"), 2, false), "tomato.png");


		seedsPotato = ItemHelper.createItem(MOD_ID,
			new ItemSeeds("seeds.potato", itemID("seedsPotato"), StardewBlocks.cropsPotato), "seedsPotato.png");

		potato = ItemHelper.createItem(MOD_ID,
			new ItemFood("food.potato", itemID("potato"), 2, false), "potato.png");


		seedsCarrot = ItemHelper.createItem(MOD_ID,
			new ItemSeeds("seeds.carrot", itemID("seedsCarrot"), StardewBlocks.cropsCarrot), "seedsCarrot.png");

		carrot = ItemHelper.createItem(MOD_ID,
			new ItemFood("food.carrot", itemID("carrot"), 2, false), "carrot.png");



		seedsStrawberry = ItemHelper.createItem(MOD_ID,
			new ItemSeeds("seeds.strawberry", itemID("seedsStrawberry"), StardewBlocks.cropsStrawberry), "seedsStrawberry.png");

		strawberry = ItemHelper.createItem(MOD_ID,
			new ItemFood("food.strawberry", itemID("strawberry"), 2, false), "strawberry.png");

		jamStrawberry = ItemHelper.createItem(MOD_ID,
			new ItemJam("food.jam.strawberry", itemID("jamStrawberry"), 10, 2), "jamStrawberry.png");


		seedsWatermelon = ItemHelper.createItem(MOD_ID,
			new ItemSeeds("seeds.watermelon", itemID("seedsWatermelon"), StardewBlocks.cropsWatermelon), "seedsWatermelon.png");

		watermelon = ItemHelper.createItem(MOD_ID,
			new ItemFood("food.watermelon", itemID("watermelon"), 2, false), "watermelon.png");



		seedsBlueberry = ItemHelper.createItem(MOD_ID,
			new ItemSeeds("seeds.blueberry", itemID("seedsBlueberry"), StardewBlocks.cropsBlueberry), "seedsBlueberry.png");

		blueberry = ItemHelper.createItem(MOD_ID,
			new ItemFoodStackable("food.blueberry", itemID("blueberry"), 1, false, 4), "blueberry.png");

		jamBlueberry = ItemHelper.createItem(MOD_ID,
			new ItemJam("food.jam.blueberry", itemID("jamBlueberry"), 10, 2), "jamBlueberry.png");




		seedsCauliflower = ItemHelper.createItem(MOD_ID,
			new ItemSeeds("seeds.cauliflower", itemID("seedsCauliflower"), StardewBlocks.cropsCauliflower), "seedsCauliflower.png");


		seedsCranberries = ItemHelper.createItem(MOD_ID,
			new ItemPlaceable("seeds.cranberries", itemID("seedsCranberries"), StardewBlocks.bushCranberries), "seedsCranberries.png");

		cranberries = ItemHelper.createItem(MOD_ID,
			new ItemFood("food.cranberries", itemID("cranberries"), 2, false), "cranberries.png");



		dough = ItemHelper.createItem(MOD_ID,
			new ItemFood("food.dough", itemID("dough"), -1, false), "dough.png");

		eggCooked = ItemHelper.createItem(MOD_ID,
			new ItemFood("egg.cooked", itemID("eggCooked"), 10, true), "eggCooked.png");

		jarHoney = ItemHelper.createItem(MOD_ID,
			new ItemJam("jar.honey", itemID("jarHoney"), 6, 64), "jarHoney.png");

		cheese = ItemHelper.createItem(MOD_ID,
			new ItemFood("cheese", itemID("cheese"), 4, false), "cheese.png");

		foodStewVegetable = ItemHelper.createItem(MOD_ID,
			new ItemSoup("food.stew.vegetable", itemID("foodStewVegetable"), 16), "vegetableSoup.png");

	}


}
