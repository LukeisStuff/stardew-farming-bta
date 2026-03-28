package luke.stardew.model;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.entities.duck.MobDuck;
import luke.stardew.entities.duck.MobRendererDuck;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.*;
import net.minecraft.client.render.item.model.ItemModelBlock;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.core.item.block.ItemBlock;
import org.useless.dragonfly.DisplayPos;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;

import static luke.stardew.StardewMod.MOD_ID;
import static net.minecraft.client.render.block.model.BlockModelDispatcher.loadDataModel;

@Environment(EnvType.CLIENT)
public class StardewModels implements ModelEntrypoint {

    @Override
    public void initBlockModels(BlockModelDispatcher dispatcher) {
        dispatcher.addDispatch(new BlockModelGenericAxis<>(StardewBlocks.LOG_APPLE, loadDataModel("stardew:block/log/apple")));
        dispatcher.addDispatch(new BlockModelGenericAxis<>(StardewBlocks.LOG_APPLE_GOLDEN, loadDataModel("stardew:block/log/apple_gold")));

        dispatcher.addDispatch(new BlockModelGenericLeaves<>(StardewBlocks.LEAVES_APPLE, "stardew:block/leaves/apple"));
        dispatcher.addDispatch(new BlockModelGenericLeaves<>(StardewBlocks.LEAVES_APPLE_GOLDEN, "stardew:block/leaves/apple_gold"));

        dispatcher.addDispatch(new BlockModelLeavesSeasonalFlowering<>(StardewBlocks.LEAVES_APPLE_FLOWERING, "stardew:block/leaves/apple", "apple"));
        dispatcher.addDispatch(new BlockModelLeavesSeasonalFlowering<>(StardewBlocks.LEAVES_APPLE_GOLDEN_FLOWERING, "stardew:block/leaves/apple_gold", "apple_gold"));

        dispatcher.addDispatch(new BlockModelGeneric<>(StardewBlocks.SAPLING_APPLE, loadDataModel("stardew:block/sapling/apple")).render3D(false));
        dispatcher.addDispatch(new BlockModelGeneric<>(StardewBlocks.SAPLING_APPLE_GOLDEN, loadDataModel("stardew:block/sapling/apple_gold")).render3D(false));

        dispatcher.addDispatch((new BlockModelGeneric<>(StardewBlocks.MUSHROOM_TRUFFLE, loadDataModel("stardew:block/mushroom_truffle"))).render3D(false));

        dispatcher.addDispatch(new BlockModelGenericFullyRotatable<>(StardewBlocks.THATCH, loadDataModel("stardew:block/thatch")));

        dispatcher.addDispatch(new BlockModelGenericCrops<>(StardewBlocks.CROPS_STRAWBERRY, "strawberry", 4));
        dispatcher.addDispatch(new BlockModelGenericCrops<>(StardewBlocks.CROPS_BLUEBERRY, "blueberry", 5));
        dispatcher.addDispatch(new BlockModelGenericCrops<>(StardewBlocks.CROPS_CARROT, "carrot", 3));
        dispatcher.addDispatch(new BlockModelGenericCrops<>(StardewBlocks.CROPS_TOMATO, "tomato", 6));
        dispatcher.addDispatch(new BlockModelGenericCrops<>(StardewBlocks.CROPS_POTATO, "potato", 5));
        dispatcher.addDispatch(new BlockModelGenericCrops<>(StardewBlocks.CROPS_CRANBERRIES, "cranberry", 4));
        dispatcher.addDispatch(new BlockModelGenericCrops<>(StardewBlocks.CROPS_PINEAPPLE, "pineapple", 5));

        dispatcher.addDispatch(new BlockModelGenericCropsDouble<>(StardewBlocks.CROPS_GRAPE_BOTTOM, "grape", 6, false));
        dispatcher.addDispatch(new BlockModelGenericCropsDouble<>(StardewBlocks.CROPS_GRAPE_TOP, "grape", 3, true));
        dispatcher.addDispatch(new BlockModelGenericCropsDouble<>(StardewBlocks.CROPS_CORN_BOTTOM, "corn", 7, false));
        dispatcher.addDispatch(new BlockModelGenericCropsDouble<>(StardewBlocks.CROPS_CORN_TOP, "corn", 4, true));
        dispatcher.addDispatch(new BlockModelGenericCropsDouble<>(StardewBlocks.CROPS_BEANS_BOTTOM, "beans", 7, false));
        dispatcher.addDispatch(new BlockModelGenericCropsDouble<>(StardewBlocks.CROPS_BEANS_TOP, "beans", 3, true));

        dispatcher.addDispatch(new BlockModelGenericCropsCube<>(StardewBlocks.CROPS_WATERMELON, "watermelon"));
        dispatcher.addDispatch(new BlockModelGenericCropsCube<>(StardewBlocks.CROPS_CAULIFLOWER, "cauliflower"));

        dispatcher.addDispatch(new BlockModelBush<>(StardewBlocks.BUSH));

        dispatcher.addDispatch(new BlockModelGenericCakeChocolate<>(StardewBlocks.CAKE_CHOCOLATE));
        dispatcher.addDispatch(new BlockModelGenericPizza<>(StardewBlocks.PIZZA));

        dispatcher.addDispatch(new BlockModelGeneric<>(StardewBlocks.BLOCK_HONEY, loadDataModel("stardew:block/block_honey")).forceCullSelf(true));

        dispatcher.addDispatch(new BlockModelGenericFurnace<>(StardewBlocks.BEEHIVE_IDLE, "stardew:block/beehive/idle"));
        dispatcher.addDispatch(new BlockModelGenericFurnace<>(StardewBlocks.BEEHIVE_ACTIVE, "stardew:block/beehive/active"));

        dispatcher.addDispatch(new BlockModelGeneric<>(StardewBlocks.CANDLE, loadDataModel("stardew:block/candle")));
        dispatcher.addDispatch(new BlockModelGeneric<>(StardewBlocks.CANDLE_ACTIVE, loadDataModel("stardew:block/candle")));

        dispatcher.addDispatch(new BlockModelGeneric<>(StardewBlocks.PLANT_STAKE, loadDataModel("stardew:block/plant_stake")).render3D(false));

        dispatcher.addDispatch(new BlockModelGenericFullyRotatable<>(StardewBlocks.WATERMELON, loadDataModel("stardew:block/watermelon")));
        dispatcher.addDispatch(new BlockModelGenericFullyRotatable<>(StardewBlocks.CAULIFLOWER, loadDataModel("stardew:block/cauliflower")));
    }

    protected static final DisplayPos HANDHELD_ROD_FIRST_PERSON_RIGHT_HAND = new DisplayPos(0.0F, 0.1F, 0.05F, 0.0F, 90.0F, 25.0F, 0.68F, 0.68F, 0.68F);
    protected static final DisplayPos HANDHELD_ROD_FIRST_PERSON_LEFT_HAND = new DisplayPos(0.0F, 0.1F, 0.05F, 0.0F, -90.0F, -25.0F, 0.68F, 0.68F, 0.68F);
    protected static final DisplayPos HANDHELD_ROD_THIRD_PERSON_RIGHT_HAND = new DisplayPos(0.0F, 0.25F, 0.15625F, 0.0F, 90.0F, 55.0F, 0.85F, 0.85F, 0.85F);
    protected static final DisplayPos HANDHELD_ROD_THIRD_PERSON_LEFT_HAND = new DisplayPos(0.0F, 0.25F, 0.15625F, 0.0F, -90.0F, -55.0F, 0.85F, 0.85F, 0.85F);

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

        dispatcher.addDispatch(new ItemModelStandard(StardewItems.TOOL_WATERING_CAN, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.TOOL_WATERING_CAN_STEEL, MOD_ID));

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

        dispatcher.addDispatch((new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_STONE, MOD_ID, "stone"))
            .setDisplayPos("firstperson_righthand", HANDHELD_ROD_FIRST_PERSON_RIGHT_HAND)
            .setDisplayPos("firstperson_lefthand", HANDHELD_ROD_FIRST_PERSON_LEFT_HAND)
            .setDisplayPos("thirdperson_righthand", HANDHELD_ROD_THIRD_PERSON_RIGHT_HAND)
            .setDisplayPos("thirdperson_lefthand", HANDHELD_ROD_THIRD_PERSON_LEFT_HAND));

        dispatcher.addDispatch((new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_IRON, MOD_ID, "iron"))
            .setDisplayPos("firstperson_righthand", HANDHELD_ROD_FIRST_PERSON_RIGHT_HAND)
            .setDisplayPos("firstperson_lefthand", HANDHELD_ROD_FIRST_PERSON_LEFT_HAND)
            .setDisplayPos("thirdperson_righthand", HANDHELD_ROD_THIRD_PERSON_RIGHT_HAND)
            .setDisplayPos("thirdperson_lefthand", HANDHELD_ROD_THIRD_PERSON_LEFT_HAND));

        dispatcher.addDispatch((new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_GOLD, MOD_ID, "gold"))
            .setDisplayPos("firstperson_righthand", HANDHELD_ROD_FIRST_PERSON_RIGHT_HAND)
            .setDisplayPos("firstperson_lefthand", HANDHELD_ROD_FIRST_PERSON_LEFT_HAND)
            .setDisplayPos("thirdperson_righthand", HANDHELD_ROD_THIRD_PERSON_RIGHT_HAND)
            .setDisplayPos("thirdperson_lefthand", HANDHELD_ROD_THIRD_PERSON_LEFT_HAND));

        dispatcher.addDispatch((new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_DIAMOND, MOD_ID, "diamond"))
            .setDisplayPos("firstperson_righthand", HANDHELD_ROD_FIRST_PERSON_RIGHT_HAND)
            .setDisplayPos("firstperson_lefthand", HANDHELD_ROD_FIRST_PERSON_LEFT_HAND)
            .setDisplayPos("thirdperson_righthand", HANDHELD_ROD_THIRD_PERSON_RIGHT_HAND)
            .setDisplayPos("thirdperson_lefthand", HANDHELD_ROD_THIRD_PERSON_LEFT_HAND));

        dispatcher.addDispatch((new ItemModelTieredFishingRod(StardewItems.TOOL_FISHINGROD_STEEL, MOD_ID, "steel"))
            .setDisplayPos("firstperson_righthand", HANDHELD_ROD_FIRST_PERSON_RIGHT_HAND)
            .setDisplayPos("firstperson_lefthand", HANDHELD_ROD_FIRST_PERSON_LEFT_HAND)
            .setDisplayPos("thirdperson_righthand", HANDHELD_ROD_THIRD_PERSON_RIGHT_HAND)
            .setDisplayPos("thirdperson_lefthand", HANDHELD_ROD_THIRD_PERSON_LEFT_HAND));

        dispatcher.addDispatch(new ItemModelBait(StardewItems.ARMOR_CAN_WORMS, MOD_ID));
        dispatcher.addDispatch(new ItemModelStandard(StardewItems.ARMOR_CAN_WORMS_GOLD, MOD_ID));

        dispatcher.addDispatch(new ItemModelBlock((ItemBlock<?>) StardewBlocks.CANDLE.asItem()));
        dispatcher.addDispatch(new ItemModelBlock((ItemBlock<?>) StardewBlocks.CANDLE_ACTIVE.asItem()).setFullBright());
    }

    @Override
    public void initEntityModels(EntityRendererDispatcher dispatcher) {
        ModelHelper.setEntityModel(MobDuck.class, () -> new MobRendererDuck(0.4F));
//        ModelHelper.setEntityModel(MobGoat.class, () -> new MobRendererQuadruped<>(0.7F));
//        ModelHelper.setEntityModel(ProjectileEggDuck.class, () -> new EntityRendererSprite<>(StardewItems.EGG_DUCK));
    }

    @Override
    public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

    }

    @Override
    public void initBlockColors(BlockColorDispatcher dispatcher) {

    }

}
