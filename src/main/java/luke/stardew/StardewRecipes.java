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
        RecipeBuilderShaped template4ItemtoBlock = new RecipeBuilderShaped(MOD_ID, "XX", "XX");

        RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("cookie");
        RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("bread");
        RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("cake");
        RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("golden_apple");
        RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("pumpkin_pie");
        RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("basket");

        RecipeBuilder.Shaped(MOD_ID, "DCD")
            .addInput('D', StardewItems.DOUGH)
            .addInput('C', Items.DYE, 3)
            .create("cookie", new ItemStack(Items.FOOD_COOKIE, 1));

        RecipeBuilder.Shaped(MOD_ID, "LLL", "F F", "FFF")
            .addInput('L', Items.LEATHER)
            .addInput('F', StardewItems.FIBER)
            .create("basket", new ItemStack(Items.BASKET, 1));

        RecipeBuilder.Shaped(MOD_ID, "WS", "SW")
            .addInput('W', StardewItems.FIBER)
            .addInput('S', Items.STICK)
            .create("block_of_thatch", new ItemStack(StardewBlocks.THATCH, 4));

        RecipeBuilder.Shaped(MOD_ID, "WWW")
            .addInput('W', Items.WHEAT)
            .create("dough", new ItemStack(StardewItems.DOUGH, 3));

        RecipeBuilder.Shaped(MOD_ID, "BMB", "SES", "DDD")
            .addInput('D', StardewItems.DOUGH)
            .addInput('B', StardewItems.STRAWBERRY)
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
            .addInput('A', StardewBlocks.SAPLING_APPLE)
            .create("golden_apple_sapling", new ItemStack(StardewBlocks.SAPLING_APPLE_GOLDEN, 1));

        RecipeBuilder.Shaped(MOD_ID, "PPP", "SSS", "PPP")
            .addInput('P', "minecraft:planks")
            .addInput('S', MOD_ID + ":block/flower")
            .create("beehive", new ItemStack(StardewBlocks.BEEHIVE, 1));

        template4ItemtoBlock
            .addInput('X', StardewItems.HONEY)
            .create("block_of_honey", new ItemStack(StardewBlocks.BLOCK_HONEY, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewBlocks.BLOCK_HONEY, 1))
            .create("block_of_honey_to_honey", new ItemStack(StardewItems.HONEY, 4));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewBlocks.WATERMELON, 1))
            .create("melon_to_melon_seeds", new ItemStack(StardewItems.SEEDS_WATERMELON, 4));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewBlocks.CAULIFLOWER, 1))
            .create("cauliflower_to_cauliflower_seeds", new ItemStack(StardewItems.SEEDS_CAULIFLOWER, 4));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.STRAWBERRY, 1))
            .create("strawberry_to_strawberry_seeds", new ItemStack(StardewItems.SEEDS_STRAWBERRY, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.GRAPES, 1))
            .create("grapes_to_grapes_seeds", new ItemStack(StardewItems.SEEDS_GRAPES, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.BLUEBERRY, 1))
            .create("blueberry_to_blueberry_seeds", new ItemStack(StardewItems.SEEDS_BLUEBERRY, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.CORN, 1))
            .create("corn_to_corn_seeds", new ItemStack(StardewItems.SEEDS_CORN, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.CRANBERRIES, 1))
            .create("cranberries_to_cranberries_seeds", new ItemStack(StardewItems.SEEDS_CRANBERRIES, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.POTATO, 1))
            .create("potato_to_potato_seeds", new ItemStack(StardewItems.SEEDS_POTATO, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.TOMATO, 1))
            .create("tomato_to_tomato_seeds", new ItemStack(StardewItems.SEEDS_TOMATO, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.PINEAPPLE, 1))
            .create("pineapple_to_pineapple_seeds", new ItemStack(StardewItems.SEEDS_PINEAPPLE, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.CARROT, 1))
            .create("carrot_to_carrot_seeds", new ItemStack(StardewItems.SEEDS_CARROT, 1));


        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(Items.JAR, 1))
            .addInput(new ItemStack(Items.DUST_SUGAR, 1))
            .addInput(MOD_ID + ":item/small_fruits")
            .addInput(MOD_ID + ":item/small_fruits")
            .addInput(MOD_ID + ":item/small_fruits")
            .addInput(MOD_ID + ":item/small_fruits")
            .create("small_jam", new ItemStack(StardewItems.JAR_JAM, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(Items.JAR, 1))
            .addInput(new ItemStack(Items.DUST_SUGAR, 1))
            .addInput(MOD_ID + ":item/fruits")
            .addInput(MOD_ID + ":item/fruits")
            .create("jam", new ItemStack(StardewItems.JAR_JAM, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(Items.JAR, 1))
            .addInput(new ItemStack(Items.DUST_SUGAR, 1))
            .addInput(MOD_ID + ":item/large_fruits")
            .create("large_jam", new ItemStack(StardewItems.JAR_JAM, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.CARROT, 1))
            .addInput(new ItemStack(StardewItems.POTATO, 1))
            .addInput(new ItemStack(Items.BOWL, 1))
            .create("vegetable_soup", new ItemStack(StardewItems.FOOD_STEW_VEGETABLE, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.CHEESE, 1))
            .addInput(new ItemStack(StardewBlocks.CAULIFLOWER, 1))
            .addInput(new ItemStack(Items.BOWL, 1))
            .create("cheese_soup", new ItemStack(StardewItems.FOOD_STEW_CHEESE, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.BLUEBERRY, 1))
            .addInput(new ItemStack(StardewItems.PINEAPPLE, 1))
            .addInput(new ItemStack(StardewItems.STRAWBERRY, 1))
            .addInput(new ItemStack(StardewItems.GRAPES, 1))
            .addInput(new ItemStack(StardewBlocks.WATERMELON, 1))
            .addInput(new ItemStack(Items.BOWL, 1))
            .create("fruit_soup", new ItemStack(StardewItems.FOOD_STEW_FRUIT, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(Blocks.MUSHROOM_BROWN, 1))
            .addInput(new ItemStack(Blocks.MUSHROOM_RED, 1))
            .addInput(new ItemStack(StardewBlocks.MUSHROOM_TRUFFLE, 1))
            .addInput(new ItemStack(Items.BOWL, 1))
            .create("truffle_soup", new ItemStack(StardewItems.FOOD_STEW_TRUFFLE, 1));

        RecipeBuilder.Shaped(MOD_ID, "CMC", "SES", "DDD")
            .addInput('D', StardewItems.DOUGH)
            .addInput('C', Items.DYE, 3)
            .addInput('M', Items.BUCKET_MILK)
            .addInput('S', Items.DUST_SUGAR)
            .addInput('E', MOD_ID + ":item/eggs")
            .setConsumeContainer(false)
            .create("cake_chocolate", new ItemStack(StardewItems.FOOD_CAKE_CHOCOLATE, 1));

        RecipeBuilder.Shaped(MOD_ID, " M ", "SES", "WPW")
            .addInput('M', (Items.BUCKET_MILK))
            .addInput('W', (StardewItems.DOUGH))
            .addInput('S', (Items.DUST_SUGAR))
            .addInput('E', (MOD_ID + ":item/eggs"))
            .addInput('P', (Blocks.PUMPKIN))
            .setConsumeContainer(false)
            .create("pumpkin_pie", new ItemStack(Items.FOOD_PUMPKIN_PIE, 1));

        RecipeBuilder.Shaped(MOD_ID, " W ", "SES", "DDD")
            .addInput('W', (Items.BUCKET_WATER))
            .addInput('D', (StardewItems.DOUGH))
            .addInput('S', (StardewItems.TOMATO))
            .addInput('E', (StardewItems.CHEESE))
            .setConsumeContainer(false)
            .create("pizza", new ItemStack(StardewItems.FOOD_PIZZA, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewBlocks.LOG_APPLE, 1))
            .create("apple_log_to_red_wooden_planks", new ItemStack(Blocks.PLANKS_OAK_PAINTED, 4, 14));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewBlocks.LOG_APPLE_GOLDEN, 1))
            .create("golden_apple_log_to_yellow_wooden_planks", new ItemStack(Blocks.PLANKS_OAK_PAINTED, 4, 4));

        RecipeBuilder.Shaped(MOD_ID, "I  ", "IBI", " I ")
            .addInput('I', Items.INGOT_IRON)
            .addInput('B', Items.BUCKET_WATER)
            .create("watering_can", new ItemStack(StardewItems.WATERING_CAN, 1));

        RecipeBuilder.Shaped(MOD_ID, "I  ", "IBI", " I ")
            .addInput('I', Items.INGOT_STEEL)
            .addInput('B', Items.BUCKET_WATER)
            .create("watering_can_steel", new ItemStack(StardewItems.WATERING_CAN_STEEL, 1));


        RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
            .addInput('I', "minecraft:cobblestones")
            .addInput('S', Items.STRING)
            .create("fishingrod_stone", new ItemStack(StardewItems.TOOL_FISHINGROD_STONE, 1));

        RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
            .addInput('I', Items.INGOT_IRON)
            .addInput('S', Items.STRING)
            .create("fishingrod_iron", new ItemStack(StardewItems.TOOL_FISHINGROD_IRON, 1));

        RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
            .addInput('I', Items.INGOT_GOLD)
            .addInput('S', Items.STRING)
            .create("fishingrod_gold", new ItemStack(StardewItems.TOOL_FISHINGROD_GOLD, 1));

        RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
            .addInput('I', Items.DIAMOND)
            .addInput('S', Items.STRING)
            .create("fishingrod_diamond", new ItemStack(StardewItems.TOOL_FISHINGROD_DIAMOND, 1));

        RecipeBuilder.Shaped(MOD_ID, "  I", " IS", "I S")
            .addInput('I', Items.INGOT_STEEL)
            .addInput('S', Items.STRING)
            .create("fishingrod_steel", new ItemStack(StardewItems.TOOL_FISHINGROD_STEEL, 1));


        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.HONEY, 1))
            .addInput(new ItemStack(Items.DUST_SUGAR, 1))
            .addInput(new ItemStack(StardewItems.CORN, 1))
            .create("wax", new ItemStack(StardewItems.WAX, 1));

        RecipeBuilder.Shaped(MOD_ID, "I", "S")
            .addInput('I', Items.STRING)
            .addInput('S', StardewItems.WAX)
            .create("wax", new ItemStack(StardewBlocks.CANDLE, 4));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.BEANS_COFFEE, 1))
            .addInput(new ItemStack(Items.BUCKET_MILK, 1))
            .addInput(new ItemStack(Items.DUST_SUGAR, 1))
            .create("food_coffee", new ItemStack(StardewItems.FOOD_COFFEE, 1));

        RecipeBuilder.Shapeless(MOD_ID)
            .addInput(new ItemStack(StardewItems.FISH_STONE, 1))
            .addInput(new ItemStack(StardewItems.FISH_EEL_LAVA, 1))
            .addInput(new ItemStack(StardewItems.FISH_SWORD, 1))
            .addInput(new ItemStack(StardewItems.FISH_GHOST, 1))
            .create("secret_disc", new ItemStack(StardewItems.RECORD_PINK, 1));


        ItemStack itemStack = new ItemStack(StardewItems.ARMOR_CAN_OF_WORMS);
        itemStack.damageItem(itemStack.getItem().getMaxDamage(), null);
        RecipeBuilder.Shaped(MOD_ID, " I ", " I ")
            .addInput('I', Items.INGOT_IRON)
            .create("can_of_worms", itemStack);


        RecipeBuilder.Furnace(MOD_ID)
            .setInput(MOD_ID + ":item/eggs")
            .create("egg_cooked", StardewItems.EGG_COOKED.getDefaultStack());

        RecipeBuilder.Furnace(MOD_ID)
            .setInput(StardewItems.DOUGH)
            .create("bread", Items.FOOD_BREAD.getDefaultStack());

        RecipeBuilder.Furnace(MOD_ID)
            .setInput(Items.BUCKET_MILK)
            .create("cheese", StardewItems.CHEESE.getDefaultStack());

        RecipeBuilder.Furnace(MOD_ID)
            .setInput(StardewItems.FOOD_SALMON_RAW)
            .create("fish_salmon_cooked", new ItemStack(StardewItems.FOOD_SALMON_COOKED, 1));

        RecipeBuilder.Furnace(MOD_ID)
            .setInput(StardewItems.FOOD_BASS_RAW)
            .create("fish_bass_cooked", new ItemStack(StardewItems.FOOD_BASS_COOKED, 1));

        RecipeBuilder.Furnace(MOD_ID)
            .setInput(StardewItems.FOOD_SNAPPER_RAW)
            .create("fish_snapper_cooked", new ItemStack(StardewItems.FOOD_SNAPPER_COOKED, 1));

        RecipeBuilder.Furnace(MOD_ID)
            .setInput(MOD_ID + ":block/grass")
            .create("fiber", new ItemStack(StardewItems.FIBER, 1));

        RecipeBuilder.Furnace(MOD_ID)
            .setInput(MOD_ID + ":item/seeds")
            .create("roasted_seeds", new ItemStack(StardewItems.FOOD_SEEDS_ROASTED, 1));


    }

    @Override
    public void onRecipesReady() {
        initializeRecipes();
    }

    @Override
    public void initNamespaces() {
        RecipeBuilder.initNameSpace(MOD_ID);
        RecipeBuilder.getRecipeNamespace(MOD_ID);
        Registries.ITEM_GROUPS.register(MOD_ID + ":item/small_fruits", Registries.stackListOf(StardewItems.BLUEBERRY, StardewItems.CRANBERRIES));
        Registries.ITEM_GROUPS.register(MOD_ID + ":item/fruits", Registries.stackListOf(StardewItems.STRAWBERRY, StardewItems.GRAPES, Items.FOOD_APPLE));
        Registries.ITEM_GROUPS.register(MOD_ID + ":item/large_fruits", Registries.stackListOf(StardewItems.PINEAPPLE, StardewBlocks.WATERMELON));

        Registries.ITEM_GROUPS.register(MOD_ID + ":item/eggs", Registries.stackListOf(StardewItems.EGG_DUCK, Items.EGG_CHICKEN));

        Registries.ITEM_GROUPS.register(MOD_ID + ":item/seeds", Registries.stackListOf
            (Items.SEEDS_WHEAT, Items.SEEDS_PUMPKIN,
                StardewItems.SEEDS_BLUEBERRY, StardewItems.SEEDS_CARROT,
                StardewItems.SEEDS_CAULIFLOWER, StardewItems.SEEDS_CORN,
                StardewItems.SEEDS_CRANBERRIES, StardewItems.SEEDS_GRAPES,
                StardewItems.SEEDS_PINEAPPLE, StardewItems.SEEDS_TOMATO,
                StardewItems.SEEDS_STRAWBERRY, StardewItems.SEEDS_WATERMELON,
                StardewItems.SEEDS_POTATO));

        Registries.ITEM_GROUPS.register(MOD_ID + ":block/flower", Registries.stackListOf
            (Blocks.FLOWER_RED, Blocks.FLOWER_YELLOW, Blocks.FLOWER_ORANGE, Blocks.FLOWER_PINK, Blocks.FLOWER_PURPLE, Blocks.FLOWER_LIGHT_BLUE));

        Registries.ITEM_GROUPS.register(MOD_ID + ":block/grass", Registries.stackListOf(Blocks.TALLGRASS, Blocks.TALLGRASS_FERN, Blocks.SPINIFEX));
    }
}
