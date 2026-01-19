package luke.stardew.compat.aether;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import teamport.aether.item.AetherItems;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.RecipeEntrypoint;

import static luke.stardew.StardewMod.MOD_ID;

public class StardewAetherRecipes implements RecipeEntrypoint {

    @Override
    public void onRecipesReady() {
        RecipeBuilder.ModifyWorkbench("aether").removeRecipe("cake");
        RecipeBuilder.ModifyWorkbench("aether").removeRecipe("pumpkin_pie");

        RecipeBuilder.ModifyWorkbench("stardew").removeRecipe("cake");
        RecipeBuilder.ModifyWorkbench("stardew").removeRecipe("cake_chocolate");
        RecipeBuilder.ModifyWorkbench("stardew").removeRecipe("apple_pie");
        RecipeBuilder.ModifyWorkbench("stardew").removeRecipe("pumpkin_pie");

        RecipeBuilder.ModifyFurnace("stardew").removeRecipe("cheese");

        RecipeBuilder.Shaped("stardew", "BMB", "SES", "WWW")
            .addInput('W', StardewItems.DOUGH)
            .addInput('B', StardewItems.STRAWBERRY)
            .addInput('S', Items.DUST_SUGAR)
            .addInput('E', "stardew:item/eggs")
            .addInput('M', "aether:milk_buckets")
            .setConsumeContainer(false)
            .create("cake", new ItemStack(Items.FOOD_CAKE, 1));

        RecipeBuilder.Shaped("stardew", "BMB", "SES", "WWW")
            .addInput('W', StardewItems.DOUGH)
            .addInput('B', Items.DYE, 3)
            .addInput('S', Items.DUST_SUGAR)
            .addInput('E', "stardew:item/eggs")
            .addInput('M', "aether:milk_buckets")
            .setConsumeContainer(false)
            .create("cake_chocolate", new ItemStack(StardewItems.FOOD_CAKE_CHOCOLATE, 1));

        RecipeBuilder.Shaped(MOD_ID, " M ", "SES", "WPW")
            .addInput('M', "aether:milk_buckets")
            .addInput('W', StardewItems.DOUGH)
            .addInput('S', Items.DUST_SUGAR)
            .addInput('E', MOD_ID + ":item/eggs")
            .addInput('P', Blocks.PUMPKIN)
            .setConsumeContainer(false)
            .create("pumpkin_pie", new ItemStack(Items.FOOD_PUMPKIN_PIE, 1));

        RecipeBuilder.Shaped(MOD_ID, " M ", "SES", "WPW")
            .addInput('M', "aether:milk_buckets")
            .addInput('W', StardewItems.DOUGH)
            .addInput('S', Items.DUST_SUGAR)
            .addInput('E', MOD_ID + ":item/eggs")
            .addInput('P', Items.FOOD_APPLE)
            .setConsumeContainer(false)
            .create("apple_pie", new ItemStack(StardewItems.FOOD_APPLE_PIE, 1));

        RecipeBuilder.Shaped(MOD_ID, "S", "B", "M")
            .addInput('B', StardewItems.BEANS_COFFEE)
            .addInput('M', AetherItems.BUCKET_SKYROOT_MILK)
            .addInput('S', Items.DUST_SUGAR)
            .setConsumeContainer(true)
            .create("food_coffee_skyroot", new ItemStack(StardewAetherItems.FOOD_COFFEE_SKYROOT, 1));

        RecipeBuilder.Furnace(MOD_ID)
            .setInput("aether:milk_buckets")
            .create("cheese", StardewItems.CHEESE.getDefaultStack());

    }

    @Override
    public void initNamespaces() {
        RecipeBuilder.initNameSpace(MOD_ID);

        RecipeBuilder.ModifyWorkbench("aether").removeRecipe("cake");
        RecipeBuilder.ModifyWorkbench("aether").removeRecipe("pumpkin_pie");

        Registries.ITEM_GROUPS.getItem("stardew:item/eggs").add(AetherItems.EGG_MOA_BLUE.getDefaultStack());
        Registries.ITEM_GROUPS.getItem("stardew:item/eggs").add(AetherItems.EGG_MOA_WHITE.getDefaultStack());
        Registries.ITEM_GROUPS.getItem("stardew:item/eggs").add(AetherItems.EGG_MOA_BLACK.getDefaultStack());

    }
}
