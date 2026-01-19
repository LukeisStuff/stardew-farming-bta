package luke.stardew.model;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.entities.ProjectileTomato;
import luke.stardew.entities.duck.ProjectileEggDuck;
import luke.stardew.entities.duck.MobDuck;
import luke.stardew.entities.duck.MobRendererDuck;
import luke.stardew.entities.duck.ModelDuck;
import luke.stardew.entities.goat.MobGoat;
import luke.stardew.entities.goat.MobRendererGoat;
import luke.stardew.entities.goat.ModelGoat;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.*;
import net.minecraft.client.render.entity.EntityRendererSprite;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.core.util.helper.Side;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;

import static net.minecraft.client.render.block.model.BlockModelStandard.BLOCK_TEXTURES;

@Environment(EnvType.CLIENT)
public class StardewModels implements ModelEntrypoint {

    @Override
    public void initBlockModels(BlockModelDispatcher dispatcher) {

        dispatcher.addDispatch(new BlockModelAxisAligned<>(StardewBlocks.LOG_APPLE)
            .setTex(BLOCK_TEXTURES, "stardew:block/log/apple_side", Side.sides)
            .setTex(BLOCK_TEXTURES, "stardew:block/log/apple_top", Side.TOP, Side.BOTTOM));

        dispatcher.addDispatch(new BlockModelAxisAligned<>(StardewBlocks.LOG_APPLE_GOLDEN)
            .setTex(BLOCK_TEXTURES, "stardew:block/log/apple_gold_side", Side.sides)
            .setTex(BLOCK_TEXTURES, "stardew:block/log/apple_gold_top", Side.TOP, Side.BOTTOM));

        dispatcher.addDispatch(StardewBlocks.LEAVES_APPLE, new BlockModelLeaves<>(StardewBlocks.LEAVES_APPLE,
            "stardew:block/leaves/apple"));
        dispatcher.addDispatch(StardewBlocks.LEAVES_APPLE_GOLDEN, new BlockModelLeaves<>(StardewBlocks.LEAVES_APPLE_GOLDEN,
            "stardew:block/leaves/apple_gold"));

        dispatcher.addDispatch(StardewBlocks.LEAVES_APPLE_FLOWERING, new BlockModelLeavesSeasonalFlowering<>(StardewBlocks.LEAVES_APPLE,
            "stardew:block/leaves/apple", "apple"));
        dispatcher.addDispatch(StardewBlocks.LEAVES_APPLE_GOLDEN_FLOWERING, new BlockModelLeavesSeasonalFlowering<>(StardewBlocks.LEAVES_APPLE_GOLDEN,
            "stardew:block/leaves/apple_gold", "apple_gold"));

        dispatcher.addDispatch(StardewBlocks.SAPLING_APPLE, new BlockModelCrossedSquares<>(StardewBlocks.SAPLING_APPLE).setAllTextures(0, "stardew:block/sapling/apple"));
        dispatcher.addDispatch(StardewBlocks.SAPLING_APPLE_GOLDEN, new BlockModelCrossedSquares<>(StardewBlocks.SAPLING_APPLE_GOLDEN).setAllTextures(0, "stardew:block/sapling/apple_gold"));

        dispatcher.addDispatch(StardewBlocks.MUSHROOM_TRUFFLE, new BlockModelCrossedSquares<>(StardewBlocks.MUSHROOM_TRUFFLE).setAllTextures(0, "stardew:block/mushroom_truffle"));

        dispatcher.addDispatch(StardewBlocks.THATCH, new BlockModelFullyRotatable<>(StardewBlocks.THATCH)
            .setTex(BLOCK_TEXTURES, "stardew:block/thatch/side", Side.sides)
            .setTex(BLOCK_TEXTURES, "stardew:block/thatch/top", Side.TOP, Side.BOTTOM));

        dispatcher.addDispatch(StardewBlocks.CROPS_STRAWBERRY, new BlockModelCrops<>(StardewBlocks.CROPS_STRAWBERRY, "strawberry", 4));
        dispatcher.addDispatch(StardewBlocks.CROPS_BLUEBERRY, new BlockModelCrops<>(StardewBlocks.CROPS_BLUEBERRY, "blueberry", 5));
        dispatcher.addDispatch(StardewBlocks.CROPS_CARROT, new BlockModelCrops<>(StardewBlocks.CROPS_CARROT, "carrot", 3));
        dispatcher.addDispatch(StardewBlocks.CROPS_TOMATO, new BlockModelCrops<>(StardewBlocks.CROPS_TOMATO, "tomato", 6));
        dispatcher.addDispatch(StardewBlocks.CROPS_POTATO, new BlockModelCrops<>(StardewBlocks.CROPS_POTATO, "potato", 5));
        dispatcher.addDispatch(StardewBlocks.CROPS_CRANBERRIES, new BlockModelCrops<>(StardewBlocks.CROPS_CRANBERRIES, "cranberry", 4));
        dispatcher.addDispatch(StardewBlocks.CROPS_PINEAPPLE, new BlockModelCrops<>(StardewBlocks.CROPS_PINEAPPLE, "pineapple", 5));

        dispatcher.addDispatch(StardewBlocks.CROPS_GRAPE_BOTTOM, new BlockModelCrops<>(StardewBlocks.CROPS_GRAPE_BOTTOM, "grape", 6, "bottom"));
        dispatcher.addDispatch(StardewBlocks.CROPS_GRAPE_TOP, new BlockModelCrops<>(StardewBlocks.CROPS_GRAPE_TOP, "grape", 3, "top"));

        dispatcher.addDispatch(StardewBlocks.CROPS_CORN_BOTTOM, new BlockModelCrops<>(StardewBlocks.CROPS_CORN_BOTTOM, "corn", 7, "bottom"));
        dispatcher.addDispatch(StardewBlocks.CROPS_CORN_TOP, new BlockModelCrops<>(StardewBlocks.CROPS_CORN_TOP, "corn", 4, "top"));

        dispatcher.addDispatch(StardewBlocks.CROPS_BEANS_BOTTOM, new BlockModelCrops<>(StardewBlocks.CROPS_BEANS_BOTTOM, "beans", 7, "bottom"));
        dispatcher.addDispatch(StardewBlocks.CROPS_BEANS_TOP, new BlockModelCrops<>(StardewBlocks.CROPS_BEANS_TOP, "beans", 3, "top"));

        dispatcher.addDispatch(StardewBlocks.CROPS_WATERMELON, new BlockModelCropsGrowing<>(StardewBlocks.CROPS_WATERMELON, "watermelon"));

        dispatcher.addDispatch(StardewBlocks.CROPS_CAULIFLOWER, new BlockModelCropsGrowing<>(StardewBlocks.CROPS_CAULIFLOWER, "cauliflower"));

        dispatcher.addDispatch(StardewBlocks.BUSH, new BlockModelBush<>(StardewBlocks.BUSH));

        dispatcher.addDispatch(StardewBlocks.CAKE_CHOCOLATE, new BlockModelEdible<>(StardewBlocks.CAKE_CHOCOLATE, 0.5f, "cake_chocolate")
            .setTex(BLOCK_TEXTURES, "stardew:block/cake_chocolate/side", Side.sides)
            .setTex(BLOCK_TEXTURES, "stardew:block/cake_chocolate/top", Side.TOP)
            .setTex(BLOCK_TEXTURES, "stardew:block/cake_chocolate/bottom", Side.BOTTOM));
        dispatcher.addDispatch(StardewBlocks.PIZZA, new BlockModelEdible<>(StardewBlocks.PIZZA, 0.25f, "pizza")
            .setTex(BLOCK_TEXTURES, "stardew:block/pizza/side", Side.sides)
            .setTex(BLOCK_TEXTURES, "stardew:block/pizza/top", Side.TOP)
            .setTex(BLOCK_TEXTURES, "stardew:block/pizza/bottom", Side.BOTTOM));

        dispatcher.addDispatch(StardewBlocks.BLOCK_HONEY, new BlockModelIce<>(StardewBlocks.BLOCK_HONEY)
            .setAllTextures(BLOCK_TEXTURES, "stardew:block/block_honey"));

        dispatcher.addDispatch(StardewBlocks.BEEHIVE, new BlockModelHorizontalRotation<>(StardewBlocks.BEEHIVE)
            .setTex(BLOCK_TEXTURES, "stardew:block/beehive/side", Side.sides)
            .setTex(BLOCK_TEXTURES, "stardew:block/beehive/idle_front", Side.NORTH)
            .setTex(BLOCK_TEXTURES, "stardew:block/beehive/top", Side.TOP, Side.BOTTOM));

        dispatcher.addDispatch(StardewBlocks.BEEHIVE_IDLE, new BlockModelHorizontalRotation<>(StardewBlocks.BEEHIVE_IDLE)
            .setTex(BLOCK_TEXTURES, "stardew:block/beehive/side", Side.sides)
            .setTex(BLOCK_TEXTURES, "stardew:block/beehive/idle_front", Side.NORTH)
            .setTex(BLOCK_TEXTURES, "stardew:block/beehive/top", Side.TOP, Side.BOTTOM));

        dispatcher.addDispatch(StardewBlocks.BEEHIVE_HONEY, new BlockModelHorizontalRotation<>(StardewBlocks.BEEHIVE_HONEY)
            .setTex(BLOCK_TEXTURES, "stardew:block/beehive/side", Side.sides)
            .setTex(BLOCK_TEXTURES, "stardew:block/beehive/active_front", Side.NORTH)
            .setTex(BLOCK_TEXTURES, "stardew:block/beehive/top", Side.TOP, Side.BOTTOM));

        dispatcher.addDispatch(StardewBlocks.CANDLE, new BlockModelWaxCandle<>(StardewBlocks.CANDLE)
            .setAllTextures(BLOCK_TEXTURES, "stardew:block/candle"));
        dispatcher.addDispatch(StardewBlocks.CANDLE_ACTIVE, new BlockModelWaxCandle<>(StardewBlocks.CANDLE_ACTIVE)
            .setAllTextures(BLOCK_TEXTURES, "stardew:block/candle"));

        dispatcher.addDispatch(StardewBlocks.PLANT_STAKE, new BlockModelStake<>(StardewBlocks.PLANT_STAKE)
            .setAllTextures(BLOCK_TEXTURES, "stardew:block/plant_stake"));

        dispatcher.addDispatch(new BlockModelFullyRotatable<>(StardewBlocks.WATERMELON)
            .setTex(BLOCK_TEXTURES, "stardew:block/watermelon/side", Side.sides)
            .setTex(BLOCK_TEXTURES, "stardew:block/watermelon/top", Side.TOP)
            .setTex(BLOCK_TEXTURES, "stardew:block/watermelon/bottom", Side.BOTTOM));
        dispatcher.addDispatch(new BlockModelFullyRotatable<>(StardewBlocks.CAULIFLOWER)
            .setTex(BLOCK_TEXTURES, "stardew:block/cauliflower/side", Side.sides)
            .setTex(BLOCK_TEXTURES, "stardew:block/cauliflower/top", Side.TOP)
            .setTex(BLOCK_TEXTURES, "stardew:block/cauliflower/bottom", Side.BOTTOM));

    }

    @Override
    public void initItemModels(ItemModelDispatcher dispatcher) {
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_BLUEBERRY, null).setIcon("stardew:item/seeds_blueberry"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_CARROT, null).setIcon("stardew:item/seeds_carrot"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_CAULIFLOWER, null).setIcon("stardew:item/seeds_cauliflower"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_CORN, null).setIcon("stardew:item/seeds_corn"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_GRAPES, null).setIcon("stardew:item/seeds_grapes"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_CRANBERRIES, null).setIcon("stardew:item/seeds_cranberries"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_PINEAPPLE, null).setIcon("stardew:item/seeds_pineapple"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_TOMATO, null).setIcon("stardew:item/seeds_tomato"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_STRAWBERRY, null).setIcon("stardew:item/seeds_strawberry"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.BEANS_COFFEE, null).setIcon("stardew:item/beans_coffee"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_POTATO, null).setIcon("stardew:item/seeds_potato"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.SEEDS_WATERMELON, null).setIcon("stardew:item/seeds_watermelon"));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.BLUEBERRY, null).setIcon("stardew:item/food_blueberry"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.CARROT, null).setIcon("stardew:item/food_carrot"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.CORN, null).setIcon("stardew:item/food_corn"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.GRAPES, null).setIcon("stardew:item/food_grapes"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.CRANBERRIES, null).setIcon("stardew:item/food_cranberries"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.PINEAPPLE, null).setIcon("stardew:item/food_pineapple"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.TOMATO, null).setIcon("stardew:item/food_tomato"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.STRAWBERRY, null).setIcon("stardew:item/food_strawberry"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.POTATO, null).setIcon("stardew:item/food_potato"));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.HONEY, null).setIcon("stardew:item/honey"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.WAX, null).setIcon("stardew:item/wax"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.RECORD_PINK, null).setIcon("stardew:item/record_pink"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.WORM, null).setIcon("stardew:item/worm"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.EGG_DUCK, null).setIcon("stardew:item/egg_duck"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FIBER, null).setIcon("stardew:item/fiber"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.DOUGH, null).setIcon("stardew:item/dough"));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.WATERING_CAN, null).setIcon("stardew:item/watering_can"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.WATERING_CAN_STEEL, null).setIcon("stardew:item/watering_can_steel"));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_PIZZA, null).setIcon("stardew:item/food_pizza"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_CAKE_CHOCOLATE, null).setIcon("stardew:item/food_cake_chocolate"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_BASS_COOKED, null).setIcon("stardew:item/food_bass_cooked"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_BASS_RAW, null).setIcon("stardew:item/food_bass_raw"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_COFFEE, null).setIcon("stardew:item/food_coffee"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SALMON_COOKED, null).setIcon("stardew:item/food_salmon_cooked"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SALMON_RAW, null).setIcon("stardew:item/food_salmon_raw"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SNAPPER_COOKED, null).setIcon("stardew:item/food_snapper_cooked"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SNAPPER_RAW, null).setIcon("stardew:item/food_snapper_raw"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_STEW_FRUIT, null).setIcon("stardew:item/food_stew_fruit"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_STEW_CHEESE, null).setIcon("stardew:item/food_stew_cheese"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_STEW_VEGETABLE, null).setIcon("stardew:item/food_stew_vegetable"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_STEW_TRUFFLE, null).setIcon("stardew:item/food_stew_truffle"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.EGG_COOKED, null).setIcon("stardew:item/food_egg_cooked"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.CHEESE, null).setIcon("stardew:item/food_cheese"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.JAR_JAM, null).setIcon("stardew:item/food_jam"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FOOD_SEEDS_ROASTED, null).setIcon("stardew:item/food_seeds_roasted"));

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FISH_EEL_LAVA, null).setIcon("stardew:item/fish_eel_lava"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FISH_GHOST, null).setIcon("stardew:item/fish_ghost"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FISH_STONE, null).setIcon("stardew:item/fish_stone"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.FISH_SWORD, null).setIcon("stardew:item/fish_sword"));

        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_STONE, null, "stone").setIcon("stardew:item/tool_fishingrod_stone").setFull3D().setRotateWhenRendering());
        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_IRON, null, "iron").setIcon("stardew:item/tool_fishingrod_iron").setFull3D().setRotateWhenRendering());
        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_GOLD, null, "gold").setIcon("stardew:item/tool_fishingrod_gold").setFull3D().setRotateWhenRendering());
        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_DIAMOND, null, "diamond").setIcon("stardew:item/tool_fishingrod_diamond").setFull3D().setRotateWhenRendering());
        dispatcher.addDispatch(new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_STEEL, null, "steel").setIcon("stardew:item/tool_fishingrod_steel").setFull3D().setRotateWhenRendering());

        dispatcher.addDispatch(new ItemModelBait(StardewItems.ARMOR_CAN_OF_WORMS, null).setIcon("stardew:item/armor_bait"));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.ARMOR_CAN_OF_WORMS_GOLDEN, null).setIcon("stardew:item/armor_bait_golden"));

        dispatcher.addDispatch(new ItemModelStandard(StardewBlocks.CANDLE.asItem(), null).setIcon("stardew:item/candle"));
        dispatcher.addDispatch(new ItemModelStandard(StardewBlocks.CANDLE_ACTIVE.asItem(), null).setIcon("stardew:item/candle").setFullBright());
    }

    @Override
    public void initEntityModels(EntityRenderDispatcher dispatcher) {
        ModelHelper.setEntityModel(MobDuck.class, () -> new MobRendererDuck(new ModelDuck(), 0.4F));
        ModelHelper.setEntityModel(MobGoat.class, () -> new MobRendererGoat(new ModelGoat(), 0.7F));
        ModelHelper.setEntityModel(ProjectileEggDuck.class, () -> new EntityRendererSprite<>(StardewItems.EGG_DUCK));
        ModelHelper.setEntityModel(ProjectileTomato.class, () -> new EntityRendererSprite<>(StardewItems.TOMATO));
    }

    @Override
    public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

    }

    @Override
    public void initBlockColors(BlockColorDispatcher dispatcher) {

    }

}
