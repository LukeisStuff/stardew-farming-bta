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














	}

}
