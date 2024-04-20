package luke.stardew;

import luke.stardew.items.StardewItems;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped;

import static luke.stardew.StardewMod.MOD_ID;

public class StardewRecipes {

	public void initializeRecipes() {
		RecipeBuilderShaped templateLogtoPlank = new RecipeBuilderShaped(MOD_ID, "X", "X", "X");

		RecipeBuilderShaped templateItemtoBlock = new RecipeBuilderShaped(MOD_ID, "XXX", "XXX", "XXX");

		RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("cookie");
		RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("bread");
		RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("cake");

		RecipeBuilder.Shaped(MOD_ID, "DCD")
			.addInput('D', StardewItems.dough)
			.addInput('C', Item.dye, 3)
			.create("cookie", new ItemStack(Item.foodCookie, 1));

		RecipeBuilder.Shaped(MOD_ID, "CMC", "SES", "DDD")
			.addInput('D', StardewItems.dough)
			.addInput('C', Item.cherry)
			.addInput('M', Item.bucketMilk)
			.addInput('S', Item.dustSugar)
			.addInput('E', Item.eggChicken)
			.create("cake", new ItemStack(Item.foodCake, 1));


		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(Item.jar, 1))
			.addInput(new ItemStack(Item.dustSugar, 1))
			.addInput(new ItemStack(StardewItems.strawberry, 1))
			.create("jam_strawberry", new ItemStack(StardewItems.jamStrawberry, 1));

		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(Item.jar, 1))
			.addInput(new ItemStack(Item.dustSugar, 1))
			.addInput(new ItemStack(StardewItems.blueberry, 1))
			.create("jam_blueberry", new ItemStack(StardewItems.jamBlueberry, 1));














		RecipeBuilder.Furnace(MOD_ID)
			.setInput(Item.eggChicken)
			.create("egg_cooked", StardewItems.eggCooked.getDefaultStack());

		RecipeBuilder.Furnace(MOD_ID)
			.setInput(StardewItems.dough)
			.create("bread", Item.foodBread.getDefaultStack());














	}

}
