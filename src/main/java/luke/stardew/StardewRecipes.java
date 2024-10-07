package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped;
import turniplabs.halplibe.util.RecipeEntrypoint;

import static luke.stardew.StardewMod.MOD_ID;

public class StardewRecipes implements RecipeEntrypoint {

	public void initializeRecipes() {
		RecipeBuilderShaped templateLogtoPlank = new RecipeBuilderShaped(MOD_ID, "X", "X", "X");

		RecipeBuilderShaped template9ItemtoBlock = new RecipeBuilderShaped(MOD_ID, "XXX", "XXX", "XXX");

		RecipeBuilderShaped template4ItemtoBlock = new RecipeBuilderShaped(MOD_ID, "XX", "XX");

		RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("cookie");
		RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("bread");
		RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("cake");
		RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("golden_apple");
		RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("pumpkin_pie");

		RecipeBuilder.Shaped(MOD_ID, "DCD")
			.addInput('D', StardewItems.dough)
			.addInput('C', Item.dye, 3)
			.create("cookie", new ItemStack(Item.foodCookie, 1));

		RecipeBuilder.Shaped(MOD_ID, "WWW")
			.addInput('W', Item.wheat)
			.create("dough", new ItemStack(StardewItems.dough, 3));

		RecipeBuilder.Shaped(MOD_ID, "BMB", "SES", "DDD")
			.addInput('D', StardewItems.dough)
			.addInput('B', StardewItems.strawberry)
			.addInput('M', Item.bucketMilk)
			.addInput('S', Item.dustSugar)
			.addInput('E', Item.eggChicken)
			.create("cake", new ItemStack(Item.foodCake, 1));

		RecipeBuilder.Shaped(MOD_ID, "GGG", "GAG", "GGG")
			.addInput('G', Item.ingotGold)
			.addInput('A', Item.foodApple)
			.create("golden_apple", new ItemStack(Item.foodAppleGold, 1));

		RecipeBuilder.Shaped(MOD_ID, "GGG", "GAG", "GGG")
			.addInput('G', Block.blockGold)
			.addInput('A', StardewBlocks.saplingApple)
			.create("golden_apple_sapling", new ItemStack(StardewBlocks.saplingAppleGolden, 1));

		RecipeBuilder.Shaped(MOD_ID, "PPP", "SSS", "PPP")
			.addInput('P', "minecraft:planks")
			.addInput('S', MOD_ID + ":block/flower")
			.create("beehive", new ItemStack(StardewBlocks.beehive, 1));

		template4ItemtoBlock
			.addInput('X', StardewItems.honey)
			.create("block_of_honey", new ItemStack(StardewBlocks.blockHoney, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewBlocks.blockHoney, 1))
			.create("block_of_honey_to_honey", new ItemStack(StardewItems.honey, 4));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewBlocks.watermelon, 1))
			.create("melon_to_melon_seeds", new ItemStack(StardewItems.seedsWatermelon, 4));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewBlocks.cauliflower, 1))
			.create("cauliflower_to_cauliflower_seeds", new ItemStack(StardewItems.seedsCauliflower, 4));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.strawberry, 1))
			.create("strawberry_to_strawberry_seeds", new ItemStack(StardewItems.seedsStrawberry, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.grapes, 1))
			.create("grapes_to_grapes_seeds", new ItemStack(StardewItems.seedsGrapes, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.blueberry, 1))
			.create("blueberry_to_blueberry_seeds", new ItemStack(StardewItems.seedsBlueberry, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.corn, 1))
			.create("corn_to_corn_seeds", new ItemStack(StardewItems.seedsCorn, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.cranberries, 1))
			.create("cranberries_to_cranberries_seeds", new ItemStack(StardewItems.seedsCranberries, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.potato, 1))
			.create("potato_to_potato_seeds", new ItemStack(StardewItems.seedsPotato, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.tomato, 1))
			.create("tomato_to_tomato_seeds", new ItemStack(StardewItems.seedsTomato, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.pineapple, 1))
			.create("pineapple_to_pineapple_seeds", new ItemStack(StardewItems.seedsPineapple, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.carrot, 1))
			.create("carrot_to_carrot_seeds", new ItemStack(StardewItems.seedsCarrot, 1));



		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(Item.jar, 1))
			.addInput(new ItemStack(Item.dustSugar, 1))
			.addInput(MOD_ID + ":item/small_fruits")
			.addInput(MOD_ID + ":item/small_fruits")
			.addInput(MOD_ID + ":item/small_fruits")
			.create("small_jam", new ItemStack(StardewItems.jarJam, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(Item.jar, 1))
			.addInput(new ItemStack(Item.dustSugar, 1))
			.addInput(MOD_ID + ":item/fruits")
			.addInput(MOD_ID + ":item/fruits")
			.create("jam", new ItemStack(StardewItems.jarJam, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(Item.jar, 1))
			.addInput(new ItemStack(Item.dustSugar, 1))
			.addInput(MOD_ID + ":item/large_fruits")
			.create("large_jam", new ItemStack(StardewItems.jarJam, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.carrot, 1))
			.addInput(new ItemStack(StardewItems.potato, 1))
			.addInput(new ItemStack(Item.bowl, 1))
			.create("vegetable_soup", new ItemStack(StardewItems.foodStewVegetable, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.cheese, 1))
			.addInput(new ItemStack(StardewBlocks.cauliflower, 1))
			.addInput(new ItemStack(Item.bowl, 1))
			.create("cheese_soup", new ItemStack(StardewItems.foodStewCheese, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.blueberry, 1))
			.addInput(new ItemStack(StardewItems.pineapple, 1))
			.addInput(new ItemStack(StardewItems.strawberry, 1))
			.addInput(new ItemStack(StardewItems.grapes, 1))
			.addInput(new ItemStack(StardewBlocks.watermelon, 1))
			.addInput(new ItemStack(Item.bowl, 1))
			.create("fruit_soup", new ItemStack(StardewItems.foodStewFruit, 1));

		RecipeBuilder.Shaped(MOD_ID, "CMC", "SES", "DDD")
			.addInput('D', StardewItems.dough)
			.addInput('C', Item.dye, 3)
			.addInput('M', Item.bucketMilk)
			.addInput('S', Item.dustSugar)
			.addInput('E', Item.eggChicken)
			.setConsumeContainer(false)
			.create("cake_chocolate", new ItemStack(StardewItems.foodCakeChocolate, 1));

		RecipeBuilder.Shaped(MOD_ID, " M ", "SES", "WPW")
			.addInput('M', (Item.bucketMilk))
			.addInput('W', (StardewItems.dough))
			.addInput('S', (Item.dustSugar))
			.addInput('E', (Item.eggChicken))
			.addInput('P', (Block.pumpkin))
			.setConsumeContainer(false)
			.create("pumpkin_pie", new ItemStack(Item.foodPumpkinPie, 1));

		RecipeBuilder.Shaped(MOD_ID, " W ", "SES", "DDD")
			.addInput('W', (Item.bucketWater))
			.addInput('D', (StardewItems.dough))
			.addInput('S', (StardewItems.tomato))
			.addInput('E', (StardewItems.cheese))
			.setConsumeContainer(false)
			.create("pizza", new ItemStack(StardewItems.foodPizza, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewBlocks.logApple, 1))
			.create("apple_log_to_red_wooden_planks", new ItemStack(Block.planksOakPainted, 4, 14));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewBlocks.logAppleGolden, 1))
			.create("golden_apple_log_to_yellow_wooden_planks", new ItemStack(Block.planksOakPainted, 4, 4));

		RecipeBuilder.Shaped(MOD_ID, "I  ", "IBI", " I ")
			.addInput('I', Item.ingotIron)
			.addInput('B', Item.bucketWater)
			.create("watering_can", new ItemStack(StardewItems.wateringCan, 1));

		RecipeBuilder.Shaped(MOD_ID, "I  ", "IBI", " I ")
			.addInput('I', Item.ingotSteel)
			.addInput('B', Item.bucketWater)
			.create("watering_can_steel", new ItemStack(StardewItems.wateringCanSteel, 1));


		RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
			.addInput('I', "minecraft:stones")
			.addInput('S', Item.string)
			.create("fishingrod_stone", new ItemStack(StardewItems.toolFishingrodStone, 1));

		RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
			.addInput('I', Item.ingotIron)
			.addInput('S', Item.string)
			.create("fishingrod_iron", new ItemStack(StardewItems.toolFishingrodIron, 1));

		RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
			.addInput('I', Item.ingotGold)
			.addInput('S', Item.string)
			.create("fishingrod_gold", new ItemStack(StardewItems.toolFishingrodGold, 1));

		RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
			.addInput('I', Item.diamond)
			.addInput('S', Item.string)
			.create("fishingrod_diamond", new ItemStack(StardewItems.toolFishingrodDiamond, 1));

		RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
			.addInput('I', Item.ingotSteel)
			.addInput('S', Item.string)
			.create("fishingrod_steel", new ItemStack(StardewItems.toolFishingrodSteel, 1));


		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.honey, 1))
			.addInput(new ItemStack(Item.dustSugar, 1))
			.addInput(new ItemStack(StardewItems.corn, 1))
			.create("wax", new ItemStack(StardewItems.wax, 1));

		RecipeBuilder.Shaped(MOD_ID, "I", "S")
			.addInput('I', Item.string)
			.addInput('S', StardewItems.wax)
			.create("wax", new ItemStack(StardewBlocks.candle, 4));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.beansCoffee, 1))
			.addInput(new ItemStack(Item.bucketMilk, 1))
			.addInput(new ItemStack(Item.dustSugar, 1))
			.create("food_coffee", new ItemStack(StardewItems.foodCoffee, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.fishStone, 1))
			.addInput(new ItemStack(StardewItems.fishEelLava, 1))
			.addInput(new ItemStack(StardewItems.fishSword, 1))
			.addInput(new ItemStack(StardewItems.fishGhost, 1))
			.create("secret_disc", new ItemStack(StardewItems.recordPink, 1));


		ItemStack itemStack = new ItemStack(StardewItems.armorCanOfWorms);
		itemStack.damageItem(itemStack.getItem().getMaxDamage(), null);
		RecipeBuilder.Shaped(MOD_ID, " I ", " I ")
			.addInput('I', Item.ingotIron)
			.create("can_of_worms", itemStack);











		RecipeBuilder.Furnace(MOD_ID)
			.setInput(Item.eggChicken)
			.create("egg_cooked", StardewItems.eggCooked.getDefaultStack());

		RecipeBuilder.Furnace(MOD_ID)
			.setInput(StardewItems.dough)
			.create("bread", Item.foodBread.getDefaultStack());

		RecipeBuilder.Furnace(MOD_ID)
			.setInput(Item.bucketMilk)
			.create("cheese", StardewItems.cheese.getDefaultStack());

		RecipeBuilder.Furnace(MOD_ID)
			.setInput(StardewItems.foodSalmonRaw)
			.create("fish_salmon_cooked", new ItemStack(StardewItems.foodSalmonCooked, 1));

		RecipeBuilder.Furnace(MOD_ID)
			.setInput(StardewItems.foodBassRaw)
			.create("fish_bass_cooked", new ItemStack(StardewItems.foodBassCooked, 1));

		RecipeBuilder.Furnace(MOD_ID)
			.setInput(StardewItems.foodSnapperRaw)
			.create("fish_snapper_cooked", new ItemStack(StardewItems.foodSnapperCooked, 1));



	}

	@Override
	public void onRecipesReady() {
		initializeRecipes();
	}

	@Override
	public void initNamespaces() {
		RecipeBuilder.initNameSpace(MOD_ID);
		RecipeBuilder.getRecipeNamespace(MOD_ID);
		Registries.ITEM_GROUPS.register(MOD_ID + ":item/small_fruits", Registries.stackListOf(StardewItems.blueberry, StardewItems.cranberries));
		Registries.ITEM_GROUPS.register(MOD_ID + ":item/fruits", Registries.stackListOf(StardewItems.strawberry, StardewItems.grapes, Item.foodApple));
		Registries.ITEM_GROUPS.register(MOD_ID + ":item/large_fruits", Registries.stackListOf(StardewItems.pineapple, StardewBlocks.watermelon));

		Registries.ITEM_GROUPS.register(MOD_ID + ":block/flower", Registries.stackListOf(Block.flowerRed, Block.flowerYellow, Block.flowerOrange, Block.flowerPink, Block.flowerPurple, Block.flowerLightBlue));

	}
}
