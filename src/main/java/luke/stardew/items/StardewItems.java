package luke.stardew.items;

import luke.stardew.StardewConfig;
import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.models.ItemModelCanOfWorms;
import luke.stardew.items.models.ItemModelTieredFishingRod;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.*;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tag.ItemTags;
import turniplabs.halplibe.helper.ItemBuilder;
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

	public static Item seedsPineapple;
	public static Item pineapple;

	//Summer Crops

	public static Item seedsTomato;
	public static Item tomato;
	public static Item seedsPotato;
	public static Item potato;

	public static Item seedsStrawberry;
	public static Item strawberry;
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
	public static Item honey;
	public static Item jarJam;
	public static Item cheese;

	public static Item foodStewVegetable;
	public static Item foodStewCheese;
	public static Item foodStewFruit;

	public static Item foodCakeChocolate;
	public static Item foodPie;

	//Watering Cans
	public static Item wateringCan;
	public static Item wateringCanSteel;

	public static Item wax;

	public static Item foodPizza;

	//Fishes
	public static Item foodSalmonRaw;
	public static Item foodSalmonCooked;
	public static Item foodBassRaw;
	public static Item foodBassCooked;
	public static Item foodSnapperRaw;
	public static Item foodSnapperCooked;

	public static Item fishEelLava;
	public static Item fishSword;
	public static Item fishGhost;
	public static Item fishStone;

	//Fishing Rods
	public static Item toolFishingrodStone;
	public static Item toolFishingrodIron;
	public static Item toolFishingrodGold;
	public static Item toolFishingrodDiamond;
	public static Item toolFishingrodSteel;

	public static Item worm;
	public static Item armorCanOfWorms;
	public static Item armorCanOfWormsGolden;

	//Treasures
	public static Item recordPink;



	public void initilizeItems() {


		//Spring Crops
		seedsCarrot = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/seedsCarrot")
			.setStackSize(64)
			.build(new ItemSeeds("seeds.carrot", itemID("seedsCarrot"), StardewBlocks.cropsCarrot));
		carrot = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/carrot")
			.build(new ItemFood("food.carrot", itemID("carrot"), 2, 8,false, 8));

		seedsBlueberry = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/seedsBlueberry")
			.setStackSize(64)
			.build(new ItemSeeds("seeds.blueberry", itemID("seedsBlueberry"), StardewBlocks.cropsBlueberry));
		blueberry = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/blueberry")
			.build(new ItemFood("food.blueberry", itemID("blueberry"), 1, 8,false, 16));

		seedsPineapple = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/seedsPineapple")
			.setStackSize(64)
			.build(new ItemSeeds("seeds.pineapple", itemID("seedsPineapple"), StardewBlocks.cropsPineapple));
		pineapple = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/pineapple")
			.build(new ItemFood("food.pineapple", itemID("pineapple"), 4, 8,false, 4));


		//Summer Crops
		seedsTomato = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/seedsTomato")
			.setStackSize(64)
			.build(new ItemSeeds("seeds.tomato", itemID("seedsTomato"), StardewBlocks.cropsTomato));
		tomato = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/tomato")
			.build(new ItemFood("food.tomato", itemID("tomato"), 2, 8,false, 8));

		seedsPotato = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/seedsPotato")
			.setStackSize(64)
			.build(new ItemSeeds("seeds.potato", itemID("seedsPotato"), StardewBlocks.cropsPotato));
		potato = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/potato")
			.build(new ItemFood("food.potato", itemID("potato"), 1, 8,false, 8));

		seedsStrawberry = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/seedsStrawberry")
			.setStackSize(64)
			.build(new ItemSeeds("seeds.strawberry", itemID("seedsStrawberry"), StardewBlocks.cropsStrawberry));
		strawberry = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/strawberry")
			.build(new ItemFood("food.strawberry", itemID("strawberry"), 2, 8,false, 8));

		seedsWatermelon = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/seedsWatermelon")
			.setStackSize(64)
			.build(new ItemSeeds("seeds.watermelon", itemID("seedsWatermelon"), StardewBlocks.cropsWatermelon));


		//Fall Crops
		seedsCorn = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/seedsCorn")
			.setStackSize(64)
			.build(new ItemSeeds("seeds.corn", itemID("seedsCorn"), StardewBlocks.cropsCornBottom));
		corn = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/corn")
			.build(new ItemFood("food.corn", itemID("corn"), 2, 8,false, 8));

		seedsGrapes = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/seedsGrapes")
			.setStackSize(64)
			.build(new Item("seeds.grapes", itemID("seedsGrapes")));
		grapes = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/grapes")
			.build(new ItemFood("food.grapes", itemID("grapes"), 1, 8,false, 16));


		//Winter Crops
		seedsCauliflower = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/seedsCauliflower")
			.setStackSize(64)
			.build(new ItemSeeds("seeds.cauliflower", itemID("seedsCauliflower"), StardewBlocks.cropsCauliflower));

		seedsCranberries = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/seedsCranberries")
			.setStackSize(64)
			.build(new ItemSeeds("seeds.cranberries", itemID("seedsCranberries"), StardewBlocks.cropsCranberries));
		cranberries = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/cranberries")
			.build(new ItemFood("food.cranberries", itemID("cranberries"), 1, 8,false, 16));


		//Fishes
		foodSalmonRaw = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/salmon")
			.build(new ItemFood("food.salmon.raw", itemID("foodSalmonRaw"), 2, 12,false, 8));
		foodSalmonCooked = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/salmon_cooked")
			.build(new ItemFood("food.salmon.cooked", itemID("foodSalmonCooked"), 5, 12,false, 8));

		foodBassRaw = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/bass")
			.build(new ItemFood("food.bass.raw", itemID("foodBassRaw"), 2, 12,false, 8));
		foodBassCooked = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/bass_cooked")
			.build(new ItemFood("food.bass.cooked", itemID("foodBassCooked"), 5, 12,false, 8));

		foodSnapperRaw = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/snapper")
			.build(new ItemFood("food.snapper.raw", itemID("foodSnapperRaw"), 2, 12,false, 8));
		foodSnapperCooked = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/snapper_cooked")
			.build(new ItemFood("food.snapper.cooked", itemID("foodSnapperCooked"), 5, 12,false, 8));

		fishEelLava = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/lavaeel")
			.setStackSize(1)
			.build(new Item("fish.lavaeel", itemID("fishEelLava")));
		fishSword = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/swordfish")
			.setStackSize(1)
			.build(new Item("fish.sword", itemID("fishSword")));
		fishGhost = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/ghostfish")
			.setStackSize(1)
			.build(new Item("fish.ghost", itemID("fishGhost")));
		fishStone = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/stonefish")
			.setStackSize(1)
			.build(new Item("fish.stone", itemID("fishStone")));


		//Processed Foods
		dough = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/dough")
			.build(new ItemFood("food.dough", itemID("dough"), -1, 0,false, 64));

		eggCooked = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/eggCooked")
			.build(new ItemFood("egg.cooked", itemID("eggCooked"), 10, 8,false, 16));

		honey = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/honey")
			.build(new ItemFood("food.honey", itemID("honey"), 1, 16,false, 64));

		jarJam = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/jam")
			.build(new ItemJam("food.jam", itemID("jarJam"), 8, 16,false, 2));

		cheese = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/cheese")
			.build(new ItemFood("food.cheese", itemID("cheese"), 4, 8,false, 4));

		foodStewVegetable = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/vegetableSoup")
			.build(new ItemSoup("food.stew.vegetable", itemID("foodStewVegetable"), 12, 16));

		foodStewCheese = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/cheeseSoup")
			.build(new ItemSoup("food.stew.cheese", itemID("foodStewCheese"), 14, 16));

		foodStewFruit = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/fruitSoup")
			.build(new ItemSoup("food.stew.fruit", itemID("foodStewFruit"), 16, 16));

		foodCakeChocolate = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/foodCakeChocolate")
			.build(new ItemPlaceable("food.cake.chocolate", itemID("foodCakeChocolate"), StardewBlocks.cakeChocolate).setMaxStackSize(1));

		foodPie = new ItemBuilder(MOD_ID)
			.setTags(ItemTags.NOT_IN_CREATIVE_MENU)
			.build(new ItemPlaceable("food.pie", itemID("foodPie"), Block.pumpkinPie).setMaxStackSize(1));

		foodPizza = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/pizza")
			.build(new ItemPlaceable("food.pizza", itemID("foodPizza"), StardewBlocks.pizza).setMaxStackSize(1));


		//Tools
		wateringCan = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/wateringcan")
			.build(new ItemToolWateringCan("tool.wateringcan", itemID("wateringCan"), ToolMaterial.iron));

		wateringCanSteel = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/wateringcanSteel")
			.build(new ItemToolWateringCan("tool.wateringcan.steel", itemID("wateringCanSteel"), ToolMaterial.steel));

		wax = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/wax")
			.setStackSize(16)
			.build(new Item("wax", itemID("wax")));


		//Fishing
		toolFishingrodStone = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/fishingrod_stone")
			.setItemModel((item) -> new ItemModelTieredFishingRod(item, MOD_ID, "stone").setFull3D().setRotateWhenRendering())
			.build(new ItemToolFishingRodTiered("tool.fishingrod.stone", itemID("toolFishingrodStone"), ToolMaterial.stone));
		toolFishingrodIron = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/fishingrod_iron")
			.setItemModel((item) -> new ItemModelTieredFishingRod(item, MOD_ID, "iron").setFull3D().setRotateWhenRendering())
			.build(new ItemToolFishingRodTiered("tool.fishingrod.iron", itemID("toolFishingrodIron"), ToolMaterial.iron));
		toolFishingrodGold = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/fishingrod_gold")
			.setItemModel((item) -> new ItemModelTieredFishingRod(item, MOD_ID, "gold").setFull3D().setRotateWhenRendering())
			.build(new ItemToolFishingRodTiered("tool.fishingrod.gold", itemID("toolFishingrodGold"), ToolMaterial.gold));
		toolFishingrodDiamond = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/fishingrod_diamond")
			.setItemModel((item) -> new ItemModelTieredFishingRod(item, MOD_ID, "diamond").setFull3D().setRotateWhenRendering())
			.build(new ItemToolFishingRodTiered("tool.fishingrod.diamond", itemID("toolFishingrodDiamond"), ToolMaterial.diamond));
		toolFishingrodSteel = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/fishingrod_steel")
			.setItemModel((item) -> new ItemModelTieredFishingRod(item, MOD_ID, "steel").setFull3D().setRotateWhenRendering())
			.build(new ItemToolFishingRodTiered("tool.fishingrod.steel", itemID("toolFishingrodSteel"), ToolMaterial.steel));

		worm = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/worm")
			.setStackSize(16)
			.build(new Item("worm", itemID("worm")));

		armorCanOfWorms = new ItemBuilder(MOD_ID)
			.setItemModel((item) -> new ItemModelCanOfWorms(item, MOD_ID))
			.setIcon(MOD_ID + ":item/canOfWorms_full")
			.build(new ItemCanOfWorms("armor.canofworms", itemID("armorCanOfWorms")));

		armorCanOfWormsGolden = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/canOfWorms_golden")
			.build(new ItemCanOfWormsEndless("armor.canofworms.gold", itemID("armorCanOfWormsGolden")));


		//Treasures
		recordPink = new ItemBuilder(MOD_ID)
			.setIcon(MOD_ID + ":item/axolotl")
			.setStackSize(1)
			.build(new ItemRecord("record.pink", itemID("recordPink"), "axolotl", "C418"));
	}


}
