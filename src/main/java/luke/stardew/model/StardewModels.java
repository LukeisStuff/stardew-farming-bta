package luke.stardew.model;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.entities.ProjectileTomato;
import luke.stardew.entities.duck.MobDuck;
import luke.stardew.entities.duck.ProjectileEggDuck;
import luke.stardew.entities.goat.MobGoat;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.*;
import net.minecraft.client.render.block.model.generic.BlockModelGenericFullyRotatable;
import net.minecraft.client.render.block.model.generic.BlockModelGenericRotatable;
import net.minecraft.client.render.entity.EntityRendererSprite;
import net.minecraft.client.render.entity.MobRendererQuadruped;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.core.util.helper.Side;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;

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

        dispatcher.addDispatch(StardewBlocks.LEAVES_APPLE_FLOWERING,
            new BlockModelLeavesFlowering<>(
                StardewBlocks.LEAVES_APPLE,
                "stardew:block/leaves/apple/base",
                "stardew:block/leaves/apple/overlay",
                "stardew:block/leaves/apple/overlay_flowering"
            )
        );

        dispatcher.addDispatch(StardewBlocks.LEAVES_APPLE_GOLDEN_FLOWERING,
            new BlockModelLeavesFlowering<>(
                StardewBlocks.LEAVES_APPLE_GOLDEN,
                "stardew:block/leaves/apple_golden/base",
                "stardew:block/leaves/apple_golden/overlay",
                "stardew:block/leaves/apple_golden/overlay_flowering"
            )
        );


        dispatcher.addDispatch(StardewBlocks.SAPLING_APPLE, new BlockModelCrossedSquares<>(StardewBlocks.SAPLING_APPLE).setAllTextures("stardew:block/sapling/apple"));
        dispatcher.addDispatch(StardewBlocks.SAPLING_APPLE_GOLDEN, new BlockModelCrossedSquares<>(StardewBlocks.SAPLING_APPLE_GOLDEN).setAllTextures("stardew:block/sapling/apple_gold"));

        dispatcher.addDispatch(StardewBlocks.MUSHROOM_TRUFFLE, new BlockModelCrossedSquares<>(StardewBlocks.MUSHROOM_TRUFFLE).setAllTextures("stardew:block/mushroom_truffle"));

        dispatcher.addDispatch(StardewBlocks.THATCH, new BlockModelFullyRotatable<>(StardewBlocks.THATCH)
            .setTex("stardew:block/thatch/side", Side.sides)
            .setTex("stardew:block/thatch/top", Side.TOP, Side.BOTTOM));

        // short crops

        dispatcher.addDispatch(
             StardewBlocks.CROPS_STRAWBERRY,
             new BlockModelGenericProgressive<>(StardewBlocks.CROPS_STRAWBERRY, "stardew:block/crops/strawberry", 4)
        );

        dispatcher.addDispatch(
             StardewBlocks.CROPS_BLUEBERRY,
             new BlockModelGenericProgressive<>(StardewBlocks.CROPS_BLUEBERRY, "stardew:block/crops/blueberry", 5)
        );

         dispatcher.addDispatch(
             StardewBlocks.CROPS_CARROT,
             new BlockModelGenericProgressive<>(StardewBlocks.CROPS_CARROT, "stardew:block/crops/carrot", 3)
         );

        dispatcher.addDispatch(
             StardewBlocks.CROPS_TOMATO,
             new BlockModelGenericProgressive<>(StardewBlocks.CROPS_TOMATO, "stardew:block/crops/tomato", 6)
        );

        dispatcher.addDispatch(
             StardewBlocks.CROPS_POTATO,
             new BlockModelGenericProgressive<>(StardewBlocks.CROPS_POTATO, "stardew:block/crops/potato", 5)
        );

        dispatcher.addDispatch(
             StardewBlocks.CROPS_CRANBERRIES,
             new BlockModelGenericProgressive<>(StardewBlocks.CROPS_CRANBERRIES, "stardew:block/crops/cranberry", 4)
        );

        dispatcher.addDispatch(
             StardewBlocks.CROPS_PINEAPPLE,
             new BlockModelGenericProgressive<>(StardewBlocks.CROPS_PINEAPPLE, "stardew:block/crops/pineapple", 5)
        );


        // tall crops

        dispatcher.addDispatch(
             StardewBlocks.CROPS_GRAPE_BOTTOM,
             new BlockModelGenericProgressive<>(StardewBlocks.CROPS_GRAPE_BOTTOM, "stardew:block/crops/grape_bottom", 6)
        );

        dispatcher.addDispatch(
             StardewBlocks.CROPS_GRAPE_TOP,
             new BlockModelGenericProgressive<>(StardewBlocks.CROPS_GRAPE_TOP, "stardew:block/crops/grape_top", 3)
        );

        dispatcher.addDispatch(
             StardewBlocks.CROPS_CORN_BOTTOM,
             new BlockModelGenericProgressive<>(StardewBlocks.CROPS_CORN_BOTTOM, "stardew:block/crops/corn_bottom", 7)
        );

        dispatcher.addDispatch(
             StardewBlocks.CROPS_CORN_TOP,
             new BlockModelGenericProgressive<>(StardewBlocks.CROPS_CORN_TOP, "stardew:block/crops/corn_top", 4)
        );

        dispatcher.addDispatch(
             StardewBlocks.CROPS_BEANS_BOTTOM,
             new BlockModelGenericProgressive<>(StardewBlocks.CROPS_BEANS_BOTTOM, "stardew:block/crops/beans_bottom", 7)
        );

        dispatcher.addDispatch(
             StardewBlocks.CROPS_BEANS_TOP,
             new BlockModelGenericProgressive<>(StardewBlocks.CROPS_BEANS_TOP, "stardew:block/crops/beans_top", 3)
        );

        dispatcher.addDispatch(
            StardewBlocks.CROPS_WATERMELON,
            new BlockModelCropPumplike<>(StardewBlocks.CROPS_WATERMELON, "stardew:block/crops/watermelon", 5)
        );

        dispatcher.addDispatch(
            StardewBlocks.CROPS_CAULIFLOWER,
            new BlockModelCropPumplike<>(StardewBlocks.CROPS_CAULIFLOWER, "stardew:block/crops/cauliflower", 5)
        );

        dispatcher.addDispatch(StardewBlocks.BUSH, new BlockModelBush<>(StardewBlocks.BUSH, "stardew:block/bush"));

        dispatcher.addDispatch(StardewBlocks.CAKE_CHOCOLATE, new BlockModelGenericProgressive<>(StardewBlocks.CAKE_CHOCOLATE, "stardew:block/cake_chocolate" , 6));
        dispatcher.addDispatch(StardewBlocks.PIZZA, new BlockModelGenericProgressive<>(StardewBlocks.PIZZA, "stardew:block/pizza" , 6));
        dispatcher.addDispatch(StardewBlocks.APPLE_PIE, new BlockModelGenericProgressive<>(StardewBlocks.APPLE_PIE, "stardew:block/apple_pie" , 3));

        dispatcher.addDispatch(StardewBlocks.BLOCK_HONEY, new BlockModelGlass<>(StardewBlocks.BLOCK_HONEY, "stardew:block/block_honey")
            .setAllTextures("stardew:block/block_honey").onRenderLayer(1));


        dispatcher.addDispatch(
            StardewBlocks.BEEHIVE,
            new BlockModelGenericRotatable<>(StardewBlocks.BEEHIVE, BlockModelDispatcher.loadDataModel("stardew:/block/beehive/inactive"))
        );

        dispatcher.addDispatch(
            StardewBlocks.BEEHIVE_IDLE,
            new BlockModelGenericRotatable<>(StardewBlocks.BEEHIVE_IDLE, BlockModelDispatcher.loadDataModel("stardew:/block/beehive/idle"))
        );

        dispatcher.addDispatch(
            StardewBlocks.BEEHIVE_HONEY,
            new BlockModelGenericRotatable<>(StardewBlocks.BEEHIVE_HONEY, BlockModelDispatcher.loadDataModel("stardew:/block/beehive/full"))
        );


        dispatcher.addDispatch(StardewBlocks.CANDLE,new BlockModelCandle(StardewBlocks.CANDLE));
        dispatcher.addDispatch(StardewBlocks.CANDLE_ACTIVE,new BlockModelCandle(StardewBlocks.CANDLE_ACTIVE));

        dispatcher.addDispatch(
            StardewBlocks.PLANT_STAKE,
            new BlockModelGenericFullyRotatable<>(StardewBlocks.PLANT_STAKE, BlockModelDispatcher.loadDataModel("stardew:/block/plant_stake").asModel())
        );

        dispatcher.addDispatch(
            StardewBlocks.WATERMELON,
            new BlockModelGenericFullyRotatable<>(StardewBlocks.WATERMELON, BlockModelDispatcher.loadDataModel("stardew:/block/watermelon").asModel())
        );

        dispatcher.addDispatch(
            StardewBlocks.CAULIFLOWER,
            new BlockModelGenericFullyRotatable<>(StardewBlocks.CAULIFLOWER, BlockModelDispatcher.loadDataModel("stardew:/block/cauliflower").asModel())
        );
    }

    @Override
    public void initItemModels(ItemModelDispatcher dispatcher) {
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.BUSH));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_BLUEBERRY));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_CARROT));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_CAULIFLOWER));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_CORN));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_GRAPES));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_CRANBERRIES));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_PINEAPPLE));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_TOMATO));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_STRAWBERRY));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.BEANS_COFFEE));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_POTATO));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_WATERMELON));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.BLUEBERRY));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.CARROT));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.CORN));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.GRAPES));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.CRANBERRIES));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.PINEAPPLE));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.TOMATO));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.STRAWBERRY));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.POTATO));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.HONEY));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.WAX));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.CANDLE));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.RECORD_PINK));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.WORM));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.EGG_DUCK));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FIBER));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.WATERING_CAN));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.WATERING_CAN_STEEL));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_PIZZA));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_CAKE_CHOCOLATE));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_BASS_COOKED));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_BASS_RAW));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_COFFEE));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SALMON_COOKED));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SALMON_RAW));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SNAPPER_COOKED));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SNAPPER_RAW));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_STEW_FRUIT));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_STEW_CHEESE));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_STEW_VEGETABLE));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_STEW_TRUFFLE));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.EGG_COOKED));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.CHEESE));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.JAR_JAM));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SEEDS_ROASTED));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FISH_EEL_LAVA));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FISH_GHOST));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FISH_PIG));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FISH_STONE));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FISH_SWORD));

        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_STONE, "stone"));
        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_IRON, "iron"));
        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_GOLD, "gold"));
        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_DIAMOND, "diamond"));
        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_STEEL, "steel"));

        dispatcher.addDispatch(new ItemModelBait(StardewItems.ARMOR_CAN_OF_WORMS));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.ARMOR_CAN_OF_WORMS_GOLDEN));

        dispatcher.addDispatch(new ItemModelStandard(StardewBlocks.CANDLE.asItem()));
        dispatcher.addDispatch(new ItemModelStandard(StardewBlocks.CANDLE_ACTIVE.asItem()));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_APPLE_PIE));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_APPLE_PIE_SLICE));
    }

    @Override
    public void initEntityModels(EntityRendererDispatcher dispatcher) {
        ModelHelper.setEntityModel(MobDuck.class, new MobRendererDuck<>(0.4F));
        ModelHelper.setEntityModel(MobGoat.class, new MobRendererQuadruped<>(0.7F));

        ModelHelper.setEntityModel(ProjectileEggDuck.class, new EntityRendererSprite<>(StardewItems.EGG_DUCK));
        ModelHelper.setEntityModel(ProjectileTomato.class,  new EntityRendererSprite<>(StardewItems.TOMATO));
    }

    @Override
    public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

    }

    @Override
    public void initBlockColors(BlockColorDispatcher dispatcher) {

    }

}
