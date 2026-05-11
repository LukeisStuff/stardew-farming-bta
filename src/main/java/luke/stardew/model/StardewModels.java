package luke.stardew.model;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.*;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.core.util.helper.Side;
import turniplabs.halplibe.util.ModelEntrypoint;

import static luke.stardew.StardewMod.MOD_ID;

@Environment(EnvType.CLIENT)
public class StardewModels implements ModelEntrypoint {

    @Override
    public void initBlockModels(BlockModelDispatcher dispatcher) {

        dispatcher.addDispatch(new BlockModelAxisAligned<>(StardewBlocks.LOG_APPLE)
            .setTex("stardew:block/log/apple_side", Side.sides)
            .setTex("stardew:block/log/apple_top", Side.TOP, Side.BOTTOM));

        dispatcher.addDispatch(new BlockModelAxisAligned<>(StardewBlocks.LOG_APPLE_GOLDEN)
            .setTex("stardew:block/log/apple_gold_side", Side.sides)
            .setTex("stardew:block/log/apple_gold_top", Side.TOP, Side.BOTTOM));

        dispatcher.addDispatch(StardewBlocks.LEAVES_APPLE, new BlockModelLeaves<>(StardewBlocks.LEAVES_APPLE,
            "stardew:block/leaves/apple"));
        dispatcher.addDispatch(StardewBlocks.LEAVES_APPLE_GOLDEN, new BlockModelLeaves<>(StardewBlocks.LEAVES_APPLE_GOLDEN,
            "stardew:block/leaves/apple_gold"));

//        dispatcher.addDispatch(StardewBlocks.LEAVES_APPLE_FLOWERING, new BlockModelLeavesSeasonalFlowering<>(StardewBlocks.LEAVES_APPLE,
//            "stardew:block/leaves/apple", "apple"));
//        dispatcher.addDispatch(StardewBlocks.LEAVES_APPLE_GOLDEN_FLOWERING, new BlockModelLeavesSeasonalFlowering<>(StardewBlocks.LEAVES_APPLE_GOLDEN,
//            "stardew:block/leaves/apple_gold", "apple_gold"));

        dispatcher.addDispatch(StardewBlocks.SAPLING_APPLE, new BlockModelCrossedSquares<>(StardewBlocks.SAPLING_APPLE).setAllTextures("stardew:block/sapling/apple"));
        dispatcher.addDispatch(StardewBlocks.SAPLING_APPLE_GOLDEN, new BlockModelCrossedSquares<>(StardewBlocks.SAPLING_APPLE_GOLDEN).setAllTextures("stardew:block/sapling/apple_gold"));

        dispatcher.addDispatch(StardewBlocks.MUSHROOM_TRUFFLE, new BlockModelCrossedSquares<>(StardewBlocks.MUSHROOM_TRUFFLE).setAllTextures("stardew:block/mushroom_truffle"));

        dispatcher.addDispatch(StardewBlocks.THATCH, new BlockModelFullyRotatable<>(StardewBlocks.THATCH)
            .setTex("stardew:block/thatch/side", Side.sides)
            .setTex("stardew:block/thatch/top", Side.TOP, Side.BOTTOM));

//        dispatcher.addDispatch(StardewBlocks.CROPS_STRAWBERRY, new BlockModelCrops<>(StardewBlocks.CROPS_STRAWBERRY, "strawberry", 4));
//        dispatcher.addDispatch(StardewBlocks.CROPS_BLUEBERRY, new BlockModelCrops<>(StardewBlocks.CROPS_BLUEBERRY, "blueberry", 5));
//        dispatcher.addDispatch(StardewBlocks.CROPS_CARROT, new BlockModelCrops<>(StardewBlocks.CROPS_CARROT, "carrot", 3));
//        dispatcher.addDispatch(StardewBlocks.CROPS_TOMATO, new BlockModelCrops<>(StardewBlocks.CROPS_TOMATO, "tomato", 6));
//        dispatcher.addDispatch(StardewBlocks.CROPS_POTATO, new BlockModelCrops<>(StardewBlocks.CROPS_POTATO, "potato", 5));
//        dispatcher.addDispatch(StardewBlocks.CROPS_CRANBERRIES, new BlockModelCrops<>(StardewBlocks.CROPS_CRANBERRIES, "cranberry", 4));
//        dispatcher.addDispatch(StardewBlocks.CROPS_PINEAPPLE, new BlockModelCrops<>(StardewBlocks.CROPS_PINEAPPLE, "pineapple", 5));
//
//        dispatcher.addDispatch(StardewBlocks.CROPS_GRAPE_BOTTOM, new BlockModelCrops<>(StardewBlocks.CROPS_GRAPE_BOTTOM, "grape", 6, "bottom"));
//        dispatcher.addDispatch(StardewBlocks.CROPS_GRAPE_TOP, new BlockModelCrops<>(StardewBlocks.CROPS_GRAPE_TOP, "grape", 3, "top"));
//
//        dispatcher.addDispatch(StardewBlocks.CROPS_CORN_BOTTOM, new BlockModelCrops<>(StardewBlocks.CROPS_CORN_BOTTOM, "corn", 7, "bottom"));
//        dispatcher.addDispatch(StardewBlocks.CROPS_CORN_TOP, new BlockModelCrops<>(StardewBlocks.CROPS_CORN_TOP, "corn", 4, "top"));
//
//        dispatcher.addDispatch(StardewBlocks.CROPS_BEANS_BOTTOM, new BlockModelCrops<>(StardewBlocks.CROPS_BEANS_BOTTOM, "beans", 7, "bottom"));
//        dispatcher.addDispatch(StardewBlocks.CROPS_BEANS_TOP, new BlockModelCrops<>(StardewBlocks.CROPS_BEANS_TOP, "beans", 3, "top"));
//
//        dispatcher.addDispatch(StardewBlocks.CROPS_WATERMELON, new BlockModelCropsGrowing<>(StardewBlocks.CROPS_WATERMELON, "watermelon"));
//
//        dispatcher.addDispatch(StardewBlocks.CROPS_CAULIFLOWER, new BlockModelCropsGrowing<>(StardewBlocks.CROPS_CAULIFLOWER, "cauliflower"));
//
//        dispatcher.addDispatch(StardewBlocks.BUSH, new BlockModelBush<>(StardewBlocks.BUSH));
//
//        dispatcher.addDispatch(StardewBlocks.CAKE_CHOCOLATE, new BlockModelEdible<>(StardewBlocks.CAKE_CHOCOLATE, 0.5f, "cake_chocolate")
//            .setTex("stardew:block/cake_chocolate/side", Side.sides)
//            .setTex("stardew:block/cake_chocolate/top", Side.TOP)
//            .setTex("stardew:block/cake_chocolate/bottom", Side.BOTTOM));
//        dispatcher.addDispatch(StardewBlocks.PIZZA, new BlockModelEdible<>(StardewBlocks.PIZZA, 0.25f, "pizza")
//            .setTex("stardew:block/pizza/side", Side.sides)
//            .setTex("stardew:block/pizza/top", Side.TOP)
//            .setTex("stardew:block/pizza/bottom", Side.BOTTOM));
//
//        dispatcher.addDispatch(StardewBlocks.APPLE_PIE, new BlockModelPieApple<>(StardewBlocks.APPLE_PIE)
//            .setTex("stardew:block/apple_pie/side", Side.sides)
//            .setTex("stardew:block/apple_pie/top", Side.TOP)
//            .setTex("stardew:block/apple_pie/bottom", Side.BOTTOM));

        dispatcher.addDispatch(StardewBlocks.BLOCK_HONEY, new BlockModelGlass<>(StardewBlocks.BLOCK_HONEY, "stardew:block/block_honey")
            .setAllTextures("stardew:block/block_honey").onRenderLayer(1));

        dispatcher.addDispatch(StardewBlocks.BEEHIVE, new BlockModelHorizontalRotation<>(StardewBlocks.BEEHIVE)
            .setTex("stardew:block/beehive/side", Side.sides)
            .setTex("stardew:block/beehive/idle_front", Side.NORTH)
            .setTex("stardew:block/beehive/top", Side.TOP, Side.BOTTOM));

        dispatcher.addDispatch(StardewBlocks.BEEHIVE_IDLE, new BlockModelHorizontalRotation<>(StardewBlocks.BEEHIVE_IDLE)
            .setTex("stardew:block/beehive/side", Side.sides)
            .setTex("stardew:block/beehive/idle_front", Side.NORTH)
            .setTex("stardew:block/beehive/top", Side.TOP, Side.BOTTOM));

        dispatcher.addDispatch(StardewBlocks.BEEHIVE_HONEY, new BlockModelHorizontalRotation<>(StardewBlocks.BEEHIVE_HONEY)
            .setTex("stardew:block/beehive/side", Side.sides)
            .setTex("stardew:block/beehive/active_front", Side.NORTH)
            .setTex("stardew:block/beehive/top", Side.TOP, Side.BOTTOM));

//        dispatcher.addDispatch(StardewBlocks.CANDLE, new BlockModelWaxCandle<>(StardewBlocks.CANDLE)
//            .setAllTextures("stardew:block/candle"));
//        dispatcher.addDispatch(StardewBlocks.CANDLE_ACTIVE, new BlockModelWaxCandle<>(StardewBlocks.CANDLE_ACTIVE)
//            .setAllTextures("stardew:block/candle"));
//
//        dispatcher.addDispatch(StardewBlocks.PLANT_STAKE, new BlockModelStake<>(StardewBlocks.PLANT_STAKE)
//            .setAllTextures("stardew:block/plant_stake"));

        dispatcher.addDispatch(new BlockModelFullyRotatable<>(StardewBlocks.WATERMELON)
            .setTex("stardew:block/watermelon/side", Side.sides)
            .setTex("stardew:block/watermelon/top", Side.TOP)
            .setTex("stardew:block/watermelon/bottom", Side.BOTTOM));
        dispatcher.addDispatch(new BlockModelFullyRotatable<>(StardewBlocks.CAULIFLOWER)
            .setTex("stardew:block/cauliflower/side", Side.sides)
            .setTex("stardew:block/cauliflower/top", Side.TOP)
            .setTex("stardew:block/cauliflower/bottom", Side.BOTTOM));

    }

    @Override
    public void initItemModels(ItemModelDispatcher dispatcher) {
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_BLUEBERRY, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_CARROT, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_CAULIFLOWER, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_CORN, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_GRAPES, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_CRANBERRIES, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_PINEAPPLE, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_TOMATO, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_STRAWBERRY, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.BEANS_COFFEE, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_POTATO, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_WATERMELON, MOD_ID));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.BLUEBERRY, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.CARROT, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.CORN, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.GRAPES, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.CRANBERRIES, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.PINEAPPLE, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.TOMATO, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.STRAWBERRY, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.POTATO, MOD_ID));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.HONEY, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.WAX, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.RECORD_PINK, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.WORM, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.EGG_DUCK, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FIBER, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.DOUGH, MOD_ID));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.WATERING_CAN, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.WATERING_CAN_STEEL, MOD_ID));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_PIZZA, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_CAKE_CHOCOLATE, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_BASS_COOKED, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_BASS_RAW, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_COFFEE, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SALMON_COOKED, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SALMON_RAW, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SNAPPER_COOKED, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SNAPPER_RAW, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_STEW_FRUIT, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_STEW_CHEESE, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_STEW_VEGETABLE, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_STEW_TRUFFLE, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.EGG_COOKED, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.CHEESE, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.JAR_JAM, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SEEDS_ROASTED, MOD_ID));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FISH_EEL_LAVA, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FISH_GHOST, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FISH_STONE, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FISH_SWORD, MOD_ID));

//        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_STONE, MODID, "stone");
//        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_IRON, MODID, "iron");
//        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_GOLD, MODID, "gold");
//        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_DIAMOND, MODID, "diamond");
//        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_STEEL, MODID, "steel");
//
        dispatcher.addDispatch(new ItemModelBait(StardewItems.ARMOR_CAN_OF_WORMS, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.ARMOR_CAN_OF_WORMS_GOLDEN, MOD_ID));

        dispatcher.addDispatch(new ItemModelStandard(StardewBlocks.CANDLE.asItem(), MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewBlocks.CANDLE_ACTIVE.asItem(), MOD_ID));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_APPLE_PIE, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_APPLE_PIE_SLICE, MOD_ID));
    }

    @Override
    public void initEntityModels(EntityRendererDispatcher dispatcher) {
//        ModelHelper.setEntityModel(MobDuck.class, () -> new MobRendererDuck(new ModelDuck(), 0.4F));
//        ModelHelper.setEntityModel(MobGoat.class, () -> new MobRendererGoat(new ModelGoat(), 0.7F));
//        ModelHelper.setEntityModel(ProjectileEggDuck.class, () -> new EntityRendererSprite<>(StardewItems.EGG_DUCK));
//        ModelHelper.setEntityModel(ProjectileTomato.class, () -> new EntityRendererSprite<>(StardewItems.TOMATO));
    }

    @Override
    public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

    }

    @Override
    public void initBlockColors(BlockColorDispatcher dispatcher) {

    }

}
