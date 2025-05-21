package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
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
		RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("basket");

		RecipeBuilder.Shaped(MOD_ID, "DCD")
			.addInput('D', StardewItems.dough)
			.addInput('C', Items.DYE, 3)
			.create("cookie", new ItemStack(Items.FOOD_COOKIE, 1));

		RecipeBuilder.Shaped(MOD_ID, "LLL", "F F", "FFF")
			.addInput('L', Items.LEATHER)
			.addInput('F', StardewItems.fiber)
			.create("basket", new ItemStack(Items.BASKET, 1));

		RecipeBuilder.Shaped(MOD_ID, "WS", "SW")
			.addInput('W', StardewItems.fiber)
			.addInput('S', Items.STICK)
			.create("block_of_thatch", new ItemStack(StardewBlocks.thatch, 4));

		RecipeBuilder.Shaped(MOD_ID, "WWW")
			.addInput('W', Items.WHEAT)
			.create("dough", new ItemStack(StardewItems.dough, 3));

		RecipeBuilder.Shaped(MOD_ID, "BMB", "SES", "DDD")
			.addInput('D', StardewItems.dough)
			.addInput('B', StardewItems.strawberry)
			.addInput('M', Items.BUCKET_MILK)
			.addInput('S', Items.DUST_SUGAR)
			.addInput('E', MOD_ID + ":item/eggs")
			.create("cake", new ItemStack(Items.FOOD_CAKE, 1));

		RecipeBuilder.Shaped(MOD_ID, "GGG", "GAG", "GGG")
			.addInput('G', Items.INGOT_GOLD)
			.addInput('A', Items.FOOD_APPLE)
			.create("golden_apple", new ItemStack(Items.FOOD_APPLE_GOLD, 1));

		RecipeBuilder.Shaped(MOD_ID, "GGG", "GAG", "GGG")
			.addInput('G', Blocks.BLOCK_GOLD)
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
			.addInput(new ItemStack(Items.JAR, 1))
			.addInput(new ItemStack(Items.DUST_SUGAR, 1))
			.addInput(MOD_ID + ":item/small_fruits")
			.addInput(MOD_ID + ":item/small_fruits")
			.addInput(MOD_ID + ":item/small_fruits")
			.create("small_jam", new ItemStack(StardewItems.jarJam, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(Items.JAR, 1))
			.addInput(new ItemStack(Items.DUST_SUGAR, 1))
			.addInput(MOD_ID + ":item/fruits")
			.addInput(MOD_ID + ":item/fruits")
			.create("jam", new ItemStack(StardewItems.jarJam, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(Items.JAR, 1))
			.addInput(new ItemStack(Items.DUST_SUGAR, 1))
			.addInput(MOD_ID + ":item/large_fruits")
			.create("large_jam", new ItemStack(StardewItems.jarJam, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.carrot, 1))
			.addInput(new ItemStack(StardewItems.potato, 1))
			.addInput(new ItemStack(Items.BOWL, 1))
			.create("vegetable_soup", new ItemStack(StardewItems.foodStewVegetable, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.cheese, 1))
			.addInput(new ItemStack(StardewBlocks.cauliflower, 1))
			.addInput(new ItemStack(Items.BOWL, 1))
			.create("cheese_soup", new ItemStack(StardewItems.foodStewCheese, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.blueberry, 1))
			.addInput(new ItemStack(StardewItems.pineapple, 1))
			.addInput(new ItemStack(StardewItems.strawberry, 1))
			.addInput(new ItemStack(StardewItems.grapes, 1))
			.addInput(new ItemStack(StardewBlocks.watermelon, 1))
			.addInput(new ItemStack(Items.BOWL, 1))
			.create("fruit_soup", new ItemStack(StardewItems.foodStewFruit, 1));

		RecipeBuilder.Shaped(MOD_ID, "CMC", "SES", "DDD")
			.addInput('D', StardewItems.dough)
			.addInput('C', Items.DYE, 3)
			.addInput('M', Items.BUCKET_MILK)
			.addInput('S', Items.DUST_SUGAR)
			.addInput('E', MOD_ID + ":item/eggs")
			.setConsumeContainer(false)
			.create("cake_chocolate", new ItemStack(StardewItems.foodCakeChocolate, 1));

		RecipeBuilder.Shaped(MOD_ID, " M ", "SES", "WPW")
			.addInput('M', (Items.BUCKET_MILK))
			.addInput('W', (StardewItems.dough))
			.addInput('S', (Items.DUST_SUGAR))
			.addInput('E', (MOD_ID + ":item/eggs"))
			.addInput('P', (Blocks.PUMPKIN))
			.setConsumeContainer(false)
			.create("pumpkin_pie", new ItemStack(Items.FOOD_PUMPKIN_PIE, 1));

		RecipeBuilder.Shaped(MOD_ID, " W ", "SES", "DDD")
			.addInput('W', (Items.BUCKET_WATER))
			.addInput('D', (StardewItems.dough))
			.addInput('S', (StardewItems.tomato))
			.addInput('E', (StardewItems.cheese))
			.setConsumeContainer(false)
			.create("pizza", new ItemStack(StardewItems.foodPizza, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewBlocks.logApple, 1))
			.create("apple_log_to_red_wooden_planks", new ItemStack(Blocks.PLANKS_OAK_PAINTED, 4, 14));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewBlocks.logAppleGolden, 1))
			.create("golden_apple_log_to_yellow_wooden_planks", new ItemStack(Blocks.PLANKS_OAK_PAINTED, 4, 4));

		RecipeBuilder.Shaped(MOD_ID, "I  ", "IBI", " I ")
			.addInput('I', Items.INGOT_IRON)
			.addInput('B', Items.BUCKET_WATER)
			.create("watering_can", new ItemStack(StardewItems.wateringCan, 1));

		RecipeBuilder.Shaped(MOD_ID, "I  ", "IBI", " I ")
			.addInput('I', Items.INGOT_STEEL)
			.addInput('B', Items.BUCKET_WATER)
			.create("watering_can_steel", new ItemStack(StardewItems.wateringCanSteel, 1));


		RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
			.addInput('I', "minecraft:stones")
			.addInput('S', Items.STRING)
			.create("fishingrod_stone", new ItemStack(StardewItems.toolFishingrodStone, 1));

		RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
			.addInput('I', Items.INGOT_IRON)
			.addInput('S', Items.STRING)
			.create("fishingrod_iron", new ItemStack(StardewItems.toolFishingrodIron, 1));

		RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
			.addInput('I', Items.INGOT_GOLD)
			.addInput('S', Items.STRING)
			.create("fishingrod_gold", new ItemStack(StardewItems.toolFishingrodGold, 1));

		RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
			.addInput('I', Items.DIAMOND)
			.addInput('S', Items.STRING)
			.create("fishingrod_diamond", new ItemStack(StardewItems.toolFishingrodDiamond, 1));

		RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
			.addInput('I', Items.INGOT_STEEL)
			.addInput('S', Items.STRING)
			.create("fishingrod_steel", new ItemStack(StardewItems.toolFishingrodSteel, 1));


		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.honey, 1))
			.addInput(new ItemStack(Items.DUST_SUGAR, 1))
			.addInput(new ItemStack(StardewItems.corn, 1))
			.create("wax", new ItemStack(StardewItems.wax, 1));

		RecipeBuilder.Shaped(MOD_ID, "I", "S")
			.addInput('I', Items.STRING)
			.addInput('S', StardewItems.wax)
			.create("wax", new ItemStack(StardewBlocks.candle, 4));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewItems.beansCoffee, 1))
			.addInput(new ItemStack(Items.BUCKET_MILK, 1))
			.addInput(new ItemStack(Items.DUST_SUGAR, 1))
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
			.addInput('I', Items.INGOT_IRON)
			.create("can_of_worms", itemStack);











		RecipeBuilder.Furnace(MOD_ID)
			.setInput(MOD_ID + ":item/eggs")
			.create("egg_cooked", StardewItems.eggCooked.getDefaultStack());

		RecipeBuilder.Furnace(MOD_ID)
			.setInput(StardewItems.dough)
			.create("bread", Items.FOOD_BREAD.getDefaultStack());

		RecipeBuilder.Furnace(MOD_ID)
			.setInput(Items.BUCKET_MILK)
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

		RecipeBuilder.Furnace(MOD_ID)
			.setInput(MOD_ID + ":block/grass")
			.create("fiber", new ItemStack(StardewItems.fiber, 1));



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
		Registries.ITEM_GROUPS.register(MOD_ID + ":item/fruits", Registries.stackListOf(StardewItems.strawberry, StardewItems.grapes, Items.FOOD_APPLE));
		Registries.ITEM_GROUPS.register(MOD_ID + ":item/large_fruits", Registries.stackListOf(StardewItems.pineapple, StardewBlocks.watermelon));

		Registries.ITEM_GROUPS.register(MOD_ID + ":item/eggs", Registries.stackListOf(StardewItems.eggDuck, Items.EGG_CHICKEN));

		Registries.ITEM_GROUPS.register(MOD_ID + ":block/flower", Registries.stackListOf(Blocks.FLOWER_RED, Blocks.FLOWER_YELLOW, Blocks.FLOWER_ORANGE, Blocks.FLOWER_PINK, Blocks.FLOWER_PURPLE, Blocks.FLOWER_LIGHT_BLUE));

		Registries.ITEM_GROUPS.register(MOD_ID + ":block/grass", Registries.stackListOf(Blocks.TALLGRASS, Blocks.TALLGRASS_FERN, Blocks.SPINIFEX));


	}
}
