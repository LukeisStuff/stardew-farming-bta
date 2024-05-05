package luke.stardew.items;

import luke.stardew.StardewConfig;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
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

	public static Item seedsBlueberry;
	public static Item blueberry;
	public static Item jamBlueberry;

	public static Item seedsPineapple;
	public static Item pineapple;

	//Summer Crops

	public static Item seedsTomato;
	public static Item tomato;
	public static Item seedsPotato;
	public static Item potato;

	public static Item seedsStrawberry;
	public static Item strawberry;
	public static Item jamStrawberry;
	public static Item seedsWatermelon;

	//Fall Crops
	public static Item seedsCorn;
	public static Item corn;

	public static Item seedsGrapes;
	public static Item grapes;

	//Winter Crops

	public static Item seedsCauliflower;

	public static Item seedsCranberries;
	public static Item cranberries;



	public static Item dough;
	public static Item eggCooked;
	public static Item jarHoney;
	public static Item cheese;

	public static Item foodStewVegetable;
	public static Item foodStewCheese;
	public static Item foodStewFruit;

	public static Item foodCakeChocolate;



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
			new ItemJam("food.jam.strawberry", itemID("jamStrawberry"), 8, 2), "jamStrawberry.png");


		seedsWatermelon = ItemHelper.createItem(MOD_ID,
			new ItemSeeds("seeds.watermelon", itemID("seedsWatermelon"), StardewBlocks.cropsWatermelon), "seedsWatermelon.png");


		seedsBlueberry = ItemHelper.createItem(MOD_ID,
			new ItemSeeds("seeds.blueberry", itemID("seedsBlueberry"), StardewBlocks.cropsBlueberry), "seedsBlueberry.png");

		blueberry = ItemHelper.createItem(MOD_ID,
			new ItemFoodStackable("food.blueberry", itemID("blueberry"), 1, false, 4), "blueberry.png");

		jamBlueberry = ItemHelper.createItem(MOD_ID,
			new ItemJam("food.jam.blueberry", itemID("jamBlueberry"), 8, 2), "jamBlueberry.png");


		seedsPineapple = ItemHelper.createItem(MOD_ID,
			new ItemSeeds("seeds.pineapple", itemID("seedsPineapple"), StardewBlocks.cropsPineapple), "seedsPineapple.png");

		pineapple = ItemHelper.createItem(MOD_ID,
			new ItemFood("food.pineapple", itemID("pineapple"), 6, false), "pineapple.png");




		seedsCorn = ItemHelper.createItem(MOD_ID,
			new ItemSeeds("seeds.corn", itemID("seedsCorn"), StardewBlocks.cropsCornBottom), "seedsCorn.png");

		corn = ItemHelper.createItem(MOD_ID,
			new ItemFood("food.corn", itemID("corn"), 2, false), "corn.png");


		seedsGrapes = ItemHelper.createItem(MOD_ID,
			new ItemSeeds("seeds.grape", itemID("seedsGrapes"), StardewBlocks.cropsGrapeBottom), "seedsGrapes.png");

		grapes = ItemHelper.createItem(MOD_ID,
			new ItemFood("food.grape", itemID("grapes"), 1, false), "grapes.png");




		seedsCauliflower = ItemHelper.createItem(MOD_ID,
			new ItemSeeds("seeds.cauliflower", itemID("seedsCauliflower"), StardewBlocks.cropsCauliflower), "seedsCauliflower.png");


		seedsCranberries = ItemHelper.createItem(MOD_ID,
			new ItemPlaceable("seeds.cranberries", itemID("seedsCranberries"), StardewBlocks.cropsCranberries), "seedsCranberries.png");

		cranberries = ItemHelper.createItem(MOD_ID,
			new ItemFood("food.cranberries", itemID("cranberries"), 1, false), "cranberries.png");



		dough = ItemHelper.createItem(MOD_ID,
			new ItemFood("food.dough", itemID("dough"), -1, false), "dough.png");

		eggCooked = ItemHelper.createItem(MOD_ID,
			new ItemFood("egg.cooked", itemID("eggCooked"), 10, true), "eggCooked.png");

		jarHoney = ItemHelper.createItem(MOD_ID,
			new ItemJam("jar.honey", itemID("jarHoney"), 3, 64), "jarHoney.png");

		cheese = ItemHelper.createItem(MOD_ID,
			new ItemFood("food.cheese", itemID("cheese"), 4, false), "cheese.png");

		foodStewVegetable = ItemHelper.createItem(MOD_ID,
			new ItemSoup("food.stew.vegetable", itemID("foodStewVegetable"), 12), "vegetableSoup.png");

		foodStewCheese = ItemHelper.createItem(MOD_ID,
			new ItemSoup("food.stew.cheese", itemID("foodStewCheese"), 14), "cheeseSoup.png");

		foodStewFruit = ItemHelper.createItem(MOD_ID,
			new ItemSoup("food.stew.fruit", itemID("foodStewFruit"), 16), "fruitSoup.png");

		foodCakeChocolate = ItemHelper.createItem(MOD_ID,
			new ItemPlaceable("food.cake.chocolate", itemID("foodCakeChocolate"), StardewBlocks.cakeChocolate).setMaxStackSize(1), "foodCakeChocolate.png");
	}


}
