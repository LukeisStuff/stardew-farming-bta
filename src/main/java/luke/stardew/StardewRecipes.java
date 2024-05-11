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

		RecipeBuilder.Shaped(MOD_ID, "DCD")
			.addInput('D', StardewItems.dough)
			.addInput('C', Item.dye, 3)
			.create("cookie", new ItemStack(Item.foodCookie, 1));

		RecipeBuilder.Shaped(MOD_ID, "WWW")
			.addInput('W', Item.wheat)
			.create("dough", new ItemStack(StardewItems.dough, 3));

		RecipeBuilder.Shaped(MOD_ID, "CMC", "SES", "DDD")
			.addInput('D', StardewItems.dough)
			.addInput('C', Item.cherry)
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

		template4ItemtoBlock
			.addInput('X', StardewItems.jarHoney)
			.create("block_of_honey", new ItemStack(StardewBlocks.blockHoney, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewBlocks.watermelon, 1))
			.create("melon_to_melon_seeds", new ItemStack(StardewItems.seedsWatermelon, 4));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(StardewBlocks.cauliflower, 1))
			.create("cauliflower_to_cauliflower_seeds", new ItemStack(StardewItems.seedsCauliflower, 4));


		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(Item.jar, 1))
			.addInput(new ItemStack(Item.dustSugar, 1))
			.addInput("stardew:item/fruits")
			.create("jam", new ItemStack(StardewItems.jarJam, 1));

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
			.create("cake_chocolate", new ItemStack(StardewItems.foodCakeChocolate, 1));

		RecipeBuilder.Shaped(MOD_ID, " M ", "SES", "WPW")
			.addInput('M', (Item.bucketMilk))
			.addInput('W', (StardewItems.dough))
			.addInput('S', (Item.dustSugar))
			.addInput('E', (Item.eggChicken))
			.addInput('P', (Block.pumpkin))
			.create("pumpkin_pie", new ItemStack(StardewItems.foodPie, 1));

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














		RecipeBuilder.Furnace(MOD_ID)
			.setInput(Item.eggChicken)
			.create("egg_cooked", StardewItems.eggCooked.getDefaultStack());

		RecipeBuilder.Furnace(MOD_ID)
			.setInput(StardewItems.dough)
			.create("bread", Item.foodBread.getDefaultStack());

		RecipeBuilder.Furnace(MOD_ID)
			.setInput(Item.bucketMilk)
			.create("cheese", StardewItems.cheese.getDefaultStack());



	}

	@Override
	public void onRecipesReady() {
		initializeRecipes();
	}

	@Override
	public void initNamespaces() {
		RecipeBuilder.initNameSpace(MOD_ID);
		RecipeBuilder.getRecipeNamespace(MOD_ID);
		Registries.ITEM_GROUPS.register("stardew:item/fruits", Registries.stackListOf(StardewItems.strawberry, StardewItems.blueberry, StardewItems.pineapple, StardewItems.grapes, StardewItems.cranberries, StardewBlocks.watermelon, StardewItems.grapes));
	}
}
