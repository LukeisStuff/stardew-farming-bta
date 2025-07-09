package luke.stardew.blocks.model;

import luke.stardew.StardewMod;
import luke.stardew.blocks.BlockLogicWaxCandle;
import luke.stardew.blocks.StardewBlocks;
import luke.stardew.entities.duck.MobRendererDuck;
import luke.stardew.entities.duck.MobDuck;
import luke.stardew.entities.duck.EntityEggDuck;
import luke.stardew.entities.duck.ModelDuck;
import luke.stardew.entities.goat.MobGoat;
import luke.stardew.entities.goat.MobRendererGoat;
import luke.stardew.entities.goat.ModelGoat;
import luke.stardew.helper.BlockModelBuilder;
import luke.stardew.helper.ItemModelBuilder;
import luke.stardew.helper.ModelBuilder;
import luke.stardew.items.StardewItems;
import luke.stardew.items.models.ItemModelCanOfWorms;
import luke.stardew.items.models.ItemModelTieredFishingRod;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.*;
import net.minecraft.client.render.entity.EntityRendererSprite;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;

import static luke.stardew.StardewMod.MOD_ID;

public class StardewModels implements ModelEntrypoint {

	@Override
	public void initBlockModels(BlockModelDispatcher dispatcher) {
		BlockModelBuilder edibleModelBuilder = ModelBuilder.block(MOD_ID, dispatcher)
			.withMapping("%s_side")
			.withMapping("%s_top", Side.TOP)
			.withMapping("%s_bottom", Side.BOTTOM)
			.extMapping("%s_inner")
			.buildsModel(b -> new BlockModelEdible<>(b, (float) b.getBoundsRaw().maxY)); //This might be a bad idea in some cases, but it works :-]

		BlockModelBuilder leavesModelBuilder = ModelBuilder.block(MOD_ID, dispatcher)
			.withMapping("%s")
			.withMapping("%s_flowering_overlay", 1)
			.withMapping("%s_overlay", 2)
			.withMapping("%s_fancy", 3)
			.buildsModel(BlockModelLeavesSeasonal::new);

		BlockModelBuilder simpleCrossBuilder = ModelBuilder.block(MOD_ID, dispatcher)
			.withMapping("%s") //Not sure why, but it's broken in inventory if only BOTTOM is set
			.buildsModel(BlockModelCrossedSquares::new);

		BlockModelBuilder logModelBuilder = ModelBuilder.block(MOD_ID, dispatcher)
			.withMapping("%s_side")
			.withMapping("%s_top", Side.TOP, Side.BOTTOM)
			.buildsModel(BlockModelAxisAligned::new);

		//Add copying
		BlockModelBuilder fullyRotatableBuilder = ModelBuilder.block(MOD_ID, dispatcher)
			.withMapping("%s_side")
			.withMapping("%s_top", Side.TOP, Side.BOTTOM)
			.buildsModel(BlockModelFullyRotatable::new);

		BlockModelBuilder bushModelBuilder = ModelBuilder.block(MOD_ID, dispatcher)
			.withMapping("%s_spring", 0)
			.withMapping("%s_summer", 1)
			.withMapping("%s_fall", 2)
			.withMapping("%s_winter", 3)
			.withMapping("%s_dead", 4)
			.buildsModel(BlockModelBush::new); //I hate this

		BlockModelBuilder iceModelBuilder = ModelBuilder.block(MOD_ID, dispatcher)
			.withMapping("%s")
			.onLayer(1)
			.buildsModel(BlockModelIce::new);

		BlockModelBuilder cropsModelBuilder = ModelBuilder.block(MOD_ID, dispatcher)
			.extMappingCounter("%s_%d")
			.buildsModel(BlockModelCrops::new);

		BlockModelBuilder stakeModelBuilder = ModelBuilder.block(MOD_ID, dispatcher)
			.extMapping("%s")
			.buildsModel(BlockModelCrops::new);

		BlockModelBuilder beeHiveModelBuilder = ModelBuilder.block(MOD_ID, dispatcher)
			.withMapping("%s_side")
			.withMapping("%s_top", Side.TOP, Side.BOTTOM)
			.buildsModel(BlockModelHorizontalRotation::new);

		BlockModelBuilder candleModelBuilder = ModelBuilder.block(MOD_ID, dispatcher)
			.withMapping("%s")
			.buildsModel(b -> new BlockModelWaxCandle<>((Block<BlockLogicWaxCandle>) b));

		edibleModelBuilder.build(StardewBlocks.CAKE_CHOCOLATE, "choko_cake");
		edibleModelBuilder.build(StardewBlocks.PIZZA);

		leavesModelBuilder.build(StardewBlocks.LEAVES_APPLE_FLOWERING, "leaves_apple");
		leavesModelBuilder.build(StardewBlocks.LEAVES_APPLE_GOLDEN_FLOWERING, "leaves_apple_golden");

		//Need to figure out how to pass arbitrary parameters to the builder (or just nuke ModelLeaves)
		dispatcher.addDispatch(StardewBlocks.LEAVES_APPLE, new BlockModelLeaves<>(StardewBlocks.LEAVES_APPLE, MOD_ID + ":block/leaves_apple"));
		dispatcher.addDispatch(StardewBlocks.LEAVES_APPLE_GOLDEN, new BlockModelLeaves<>(StardewBlocks.LEAVES_APPLE_GOLDEN, MOD_ID + ":block/leaves_apple_golden"));

		simpleCrossBuilder.build(StardewBlocks.SAPLING_APPLE);
		simpleCrossBuilder.build(StardewBlocks.SAPLING_APPLE_GOLDEN);
		simpleCrossBuilder.build(StardewBlocks.MUSHROOM_TRUFFLE, "truffle");

		logModelBuilder.build(StardewBlocks.LOG_APPLE);
		logModelBuilder.build(StardewBlocks.LOG_APPLE_GOLDEN);

		fullyRotatableBuilder.build(StardewBlocks.THATCH);

		bushModelBuilder.build(StardewBlocks.BUSH);

		iceModelBuilder.build(StardewBlocks.BLOCK_HONEY);

		cropsModelBuilder.count(3).build(StardewBlocks.CROPS_CARROT);
		cropsModelBuilder.count(6).build(StardewBlocks.CROPS_TOMATO);
		cropsModelBuilder.count(5).build(StardewBlocks.CROPS_POTATO);
		cropsModelBuilder.count(5).build(StardewBlocks.CROPS_BLUEBERRY);
		cropsModelBuilder.count(4).build(StardewBlocks.CROPS_CRANBERRIES);
		cropsModelBuilder.count(4).build(StardewBlocks.CROPS_STRAWBERRY);
		cropsModelBuilder.count(5).build(StardewBlocks.CROPS_PINEAPPLE);

		cropsModelBuilder.count(6).build(StardewBlocks.CROPS_GRAPE_BOTTOM, "crops_grape_bottom");
		cropsModelBuilder.count(3).build(StardewBlocks.CROPS_GRAPE_TOP, "crops_grape_top");

		cropsModelBuilder.count(7).build(StardewBlocks.CROPS_CORN_BOTTOM, "crops_corn_bottom");
		cropsModelBuilder.count(4).build(StardewBlocks.CROPS_CORN_TOP, "crops_corn_top");

		cropsModelBuilder.count(7).build(StardewBlocks.CROPS_BEANS_BOTTOM, "crops_beans_bottom");
		cropsModelBuilder.count(3).build(StardewBlocks.CROPS_BEANS_TOP, "crops_beans_top");

		//This is kinda bad, might need to add proper params
		beeHiveModelBuilder.withMapping("beehive_idle", Side.NORTH).build(StardewBlocks.BEEHIVE, "beehive");
		beeHiveModelBuilder.build(StardewBlocks.BEEHIVE_IDLE, "beehive");
		beeHiveModelBuilder.withMapping("beehive_active", Side.NORTH).build(StardewBlocks.BEEHIVE_HONEY, "beehive");

		candleModelBuilder.build(StardewBlocks.CANDLE_ACTIVE, "candle");
		candleModelBuilder.build(StardewBlocks.CANDLE, "candle");

		stakeModelBuilder.build(StardewBlocks.PLANT_STAKE);

		ModelHelper.setBlockModel(StardewBlocks.CAULIFLOWER, () -> {
			BlockModelAxisAligned<?> model = new BlockModelAxisAligned<>(StardewBlocks.CAULIFLOWER);
			model.setAllTextures(0, StardewMod.MOD_ID + ":block/cauliflower_side");
			model.setTex(0, StardewMod.MOD_ID + ":block/cauliflower_top", Side.BOTTOM); //why the hell is this mirrored
			model.setTex(0, StardewMod.MOD_ID + ":block/cauliflower_bottom", Side.TOP);
			return model;
		});

		ModelHelper.setBlockModel(StardewBlocks.WATERMELON, () -> {
			BlockModelAxisAligned<?> model = new BlockModelAxisAligned<>(StardewBlocks.WATERMELON);
			model.setAllTextures(0, StardewMod.MOD_ID + ":block/watermelon_side");
			model.setTex(0, StardewMod.MOD_ID + ":block/watermelon_top", Side.BOTTOM, Side.TOP);
			return model;
		});

		ModelHelper.setBlockModel(StardewBlocks.CROPS_CAULIFLOWER, () -> {
			BlockModelCropsGrowing<?> model = new BlockModelCropsGrowing<>(StardewBlocks.CROPS_CAULIFLOWER);
			model.setLeafTexture(StardewMod.MOD_ID + ":block/cauliflower_crop_leaf");
			model.setTopTextures(
				MOD_ID + ":block/cauliflower_crop_4",
				MOD_ID + ":block/cauliflower_crop_3",
				MOD_ID + ":block/cauliflower_crop_2",
				MOD_ID + ":block/cauliflower_crop_1"
			);
			model.setSideTextures(
				MOD_ID + ":block/cauliflower_crop_side_4",
				MOD_ID + ":block/cauliflower_crop_side_3",
				MOD_ID + ":block/cauliflower_crop_side_2",
				MOD_ID + ":block/cauliflower_crop_side_1"
			);
			return model;
		});

		ModelHelper.setBlockModel(StardewBlocks.CROPS_WATERMELON, () -> {
			BlockModelCropsGrowing<?> model = new BlockModelCropsGrowing<>(StardewBlocks.CROPS_WATERMELON);
			model.setLeafTexture(StardewMod.MOD_ID + ":block/watermelon_crop_leaf");
			model.setTopTextures(
				MOD_ID + ":block/watermelon_crop_4",
				MOD_ID + ":block/watermelon_crop_3",
				MOD_ID + ":block/watermelon_crop_2",
				MOD_ID + ":block/watermelon_crop_1"
			);
			model.setSideTextures(
				MOD_ID + ":block/watermelon_crop_side_4",
				MOD_ID + ":block/watermelon_crop_side_3",
				MOD_ID + ":block/watermelon_crop_side_2",
				MOD_ID + ":block/watermelon_crop_side_1"
			);
			return model;
		});

		//loadBlockModelsFromTexture();
	}

	@Override
	public void initItemModels(ItemModelDispatcher dispatcher) {
		ItemModelBuilder standardBuilder = ModelBuilder.item(MOD_ID, dispatcher)
			.buildsModel(item -> new ItemModelStandard(item, null));

		ItemModelBuilder fishingRodModelBuilder = ModelBuilder.item(MOD_ID, dispatcher)
			.withMapping("%s")
			.extMapping("%s_cast")
			.rotateWhenRendering()
			.withFull3D()
			.buildsModel(ItemModelTieredFishingRod::new);

		ItemModelBuilder canModelBuilder = ModelBuilder.item(MOD_ID, dispatcher)
			.withMapping("%s")
			.extMapping("%s_full")
			.buildsModel(ItemModelCanOfWorms::new);

		ItemModelBuilder blockItemModelBuilder = ModelBuilder.item(MOD_ID, dispatcher)
			.withMapping("%s_item") //Same as standard, but with an '_item' suffix for clarity
			.buildsModel(item -> new ItemModelStandard(item, null));

		fishingRodModelBuilder.build(StardewItems.TOOL_FISHINGROD_STONE);
		fishingRodModelBuilder.build(StardewItems.TOOL_FISHINGROD_IRON);
		fishingRodModelBuilder.build(StardewItems.TOOL_FISHINGROD_GOLD);
		fishingRodModelBuilder.build(StardewItems.TOOL_FISHINGROD_DIAMOND);
		fishingRodModelBuilder.build(StardewItems.TOOL_FISHINGROD_STEEL);

		canModelBuilder.build(StardewItems.ARMOR_CAN_OF_WORMS);

		standardBuilder.build(StardewItems.ARMOR_CAN_OF_WORMS_GOLDEN);

		standardBuilder.build(StardewItems.SEEDS_BLUEBERRY);
		standardBuilder.build(StardewItems.SEEDS_CARROT);
		standardBuilder.build(StardewItems.SEEDS_CAULIFLOWER);
		standardBuilder.build(StardewItems.SEEDS_CORN);
		standardBuilder.build(StardewItems.SEEDS_GRAPES);
		standardBuilder.build(StardewItems.SEEDS_CRANBERRIES);
		standardBuilder.build(StardewItems.SEEDS_PINEAPPLE);
		standardBuilder.build(StardewItems.SEEDS_TOMATO);
		standardBuilder.build(StardewItems.SEEDS_STRAWBERRY);
		standardBuilder.build(StardewItems.BEANS_COFFE);
		standardBuilder.build(StardewItems.SEEDS_POTATO);
		standardBuilder.build(StardewItems.SEEDS_WATERMELON);

		standardBuilder.build(StardewItems.BLUEBERRY);
		standardBuilder.build(StardewItems.CARROT);
		standardBuilder.build(StardewItems.CORN);
		standardBuilder.build(StardewItems.GRAPES);
		standardBuilder.build(StardewItems.CRANBERRIES);
		standardBuilder.build(StardewItems.PINEAPPLE);
		standardBuilder.build(StardewItems.TOMATO);
		standardBuilder.build(StardewItems.STRAWBERRY);
		standardBuilder.build(StardewItems.POTATO);

		standardBuilder.build(StardewItems.HONEY);
		standardBuilder.build(StardewItems.WAX);
		standardBuilder.build(StardewItems.RECORD_PINK);
		standardBuilder.build(StardewItems.WORM);
		standardBuilder.build(StardewItems.EGG_DUCK);
		standardBuilder.build(StardewItems.FIBER);
		standardBuilder.build(StardewItems.DOUGH);

		standardBuilder.build(StardewItems.WATERING_CAN);
		standardBuilder.build(StardewItems.WATERING_CAN_STEEL);

		standardBuilder.build(StardewItems.FOOD_PIZZA);
		standardBuilder.build(StardewItems.FOOD_CAKE_CHOCOLATE);
		standardBuilder.build(StardewItems.FOOD_BASS_COOKED);
		standardBuilder.build(StardewItems.FOOD_BASS_RAW);
		standardBuilder.build(StardewItems.FOOD_COFFEE);
		standardBuilder.build(StardewItems.FOOD_SALMON_COOKED);
		standardBuilder.build(StardewItems.FOOD_SALMON_RAW);
		standardBuilder.build(StardewItems.FOOD_SNAPPER_COOKED);
		standardBuilder.build(StardewItems.FOOD_SNAPPER_RAW);
		standardBuilder.build(StardewItems.FOOD_STEW_FRUIT);
		standardBuilder.build(StardewItems.FOOD_STEW_CHEESE);
		standardBuilder.build(StardewItems.FOOD_STEW_VEGETABLE);
		standardBuilder.build(StardewItems.FOOD_STEW_TRUFFLE);
		standardBuilder.build(StardewItems.EGG_COOKED);
		standardBuilder.build(StardewItems.CHEESE);
		standardBuilder.build(StardewItems.JAR_JAM);

		standardBuilder.build(StardewItems.FISH_EEL_LAVA);
		standardBuilder.build(StardewItems.FISH_GHOST);
		standardBuilder.build(StardewItems.FISH_STONE);
		standardBuilder.build(StardewItems.FISH_SWORD);

		blockItemModelBuilder.build(StardewBlocks.CANDLE_ACTIVE.asItem(), "candle");
		blockItemModelBuilder.build(StardewBlocks.CANDLE.asItem(), "candle");
	}

	@Override
	public void initEntityModels(EntityRenderDispatcher dispatcher) {
		ModelHelper.setEntityModel(MobDuck.class, () -> new MobRendererDuck(new ModelDuck(), 0.4F));
		ModelHelper.setEntityModel(MobGoat.class, () -> new MobRendererGoat(new ModelGoat(), 0.7F));
		ModelHelper.setEntityModel(EntityEggDuck.class, () -> new EntityRendererSprite<>(StardewItems.EGG_DUCK));
	}

	@Override
	public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

	}

	@Override
	public void initBlockColors(BlockColorDispatcher dispatcher) {

	}

}
