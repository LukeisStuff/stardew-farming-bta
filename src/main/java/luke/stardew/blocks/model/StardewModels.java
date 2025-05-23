package luke.stardew.blocks.model;

import luke.stardew.StardewMod;
import luke.stardew.blocks.BlockLogicWaxCandle;
import luke.stardew.blocks.StardewBlocks;
import luke.stardew.entities.duck.DuckRenderer;
import luke.stardew.entities.duck.EntityDuck;
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
import net.minecraft.client.render.entity.MobRendererCow;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;

import static luke.stardew.StardewMod.MOD_ID;

public class StardewModels implements ModelEntrypoint {

	/*public static void createItemModel(Path path) {
		String name = path.getFileName().toString().replace(".png", "");
		Item item = Item.itemsMap.get(NamespaceID.getPermanent(StardewMod.MOD_ID, "item/" + name));
		if (itemModelDispatcher.hasDispatch(item)) return;

		if (item != null) {
			setStandardItemModel(item, (i, s) -> {
				ItemModelStandard model = new ItemModelStandard(i, s);
				return model;
			});
		}else {
			StardewMod.LOGGER.error("Missing for " + name);
		}
	}

	public static void loadItemModelsFromTexture() {
		//Despite the warning, ModContainer should always be present when this is called
		ModContainer modContainer = FabricLoader.getInstance().getModContainer(StardewMod.MOD_ID).get();
		Optional<Path> optionalPath = modContainer.findPath("assets/stardew/textures/item/");

		if (optionalPath.isPresent()) {
			Path path = optionalPath.get();
			try (Stream<Path> pathStream = Files.list(path)) {
				pathStream.forEach(StardewModels::createItemModel);
			}catch (IOException e) {
                StardewMod.LOGGER.error("Failed to initialize models");
            }
        }
	}

	public static void createBlockModel(Path path) {
		if (Files.isDirectory(path)) return;

		String name = path.getFileName().toString().replace(".png", "");
		int lastIndex = name.lastIndexOf("_");
		if (lastIndex < 0) return;
		String blockName = name.substring(0, lastIndex);

		Block<?> block = Blocks.blockMap.get(NamespaceID.getPermanent(StardewMod.MOD_ID, "block/" + blockName));

		if (block != null) {
			BlockModel<?> model;
			if ((model = BlockModelDispatcher.getInstance().getDispatch(block)) != null) {
				if (model instanceof BlockModelCrops && block.getLogic() instanceof BlockLogicCropBase) {
					((BlockModelCrops<?>)model).addIcon(StardewMod.MOD_ID + ":block/" + name);
				}

			}else {
				if (block.getLogic() instanceof BlockLogicCropBase) {
					BlockModelCrops<?> newModel = new BlockModelCrops<>(block);
					ModelHelper.setBlockModel(block, () -> newModel);
					newModel.addIcon(StardewMod.MOD_ID + ":block/" + name);
				}
			}
		}else {
			StardewMod.LOGGER.error("Missing for " + name);
		}
	}*/

	/*public static void loadBlockModelsFromTexture() {
		//Despite the warning, ModContainer should always be present when this is called
		ModContainer modContainer = FabricLoader.getInstance().getModContainer(StardewMod.MOD_ID).get();
		Optional<Path> optionalPath = modContainer.findPath("assets/stardew/textures/block/");

		if (optionalPath.isPresent()) {
			Path path = optionalPath.get();
			try (Stream<Path> pathStream = Files.list(path)) {
				pathStream.forEach(StardewModels::createBlockModel);
			}catch (IOException e) {
				StardewMod.LOGGER.error("Failed to initialize models");
			}
		}
	}*/

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
			.buildsModel(b ->new BlockModelWaxCandle<>((Block<BlockLogicWaxCandle>) b));

		edibleModelBuilder.build(StardewBlocks.cakeChocolate, "choko_cake");
		edibleModelBuilder.build(StardewBlocks.pizza);

		leavesModelBuilder.build(StardewBlocks.leavesAppleFlowering, "leaves_apple");
		leavesModelBuilder.build(StardewBlocks.leavesAppleGoldenFlowering, "leaves_apple_golden");

		//Need to figure out how to pass arbitrary parameters to the builder (or just nuke ModelLeaves)
		dispatcher.addDispatch(StardewBlocks.leavesApple, new BlockModelLeaves<>(StardewBlocks.leavesApple, MOD_ID + ":block/leaves_apple"));
		dispatcher.addDispatch(StardewBlocks.leavesAppleGolden, new BlockModelLeaves<>(StardewBlocks.leavesAppleGolden, MOD_ID + ":block/leaves_apple_golden"));

		simpleCrossBuilder.build(StardewBlocks.saplingApple);
		simpleCrossBuilder.build(StardewBlocks.saplingAppleGolden);
		simpleCrossBuilder.build(StardewBlocks.mushroomTruffle, "truffle");

		logModelBuilder.build(StardewBlocks.logApple);
		logModelBuilder.build(StardewBlocks.logAppleGolden);

		fullyRotatableBuilder.build(StardewBlocks.thatch);

		bushModelBuilder.build(StardewBlocks.bush);

		iceModelBuilder.build(StardewBlocks.blockHoney);

		cropsModelBuilder.count(3).build(StardewBlocks.cropsCarrot);
		cropsModelBuilder.count(6).build(StardewBlocks.cropsTomato);
		cropsModelBuilder.count(5).build(StardewBlocks.cropsPotato);
		cropsModelBuilder.count(5).build(StardewBlocks.cropsBlueberry);
		cropsModelBuilder.count(4).build(StardewBlocks.cropsCranberries);
		cropsModelBuilder.count(4).build(StardewBlocks.cropsStrawberry);
		cropsModelBuilder.count(5).build(StardewBlocks.cropsPineapple);

		cropsModelBuilder.count(6).build(StardewBlocks.cropsGrapeBottom, "crops_grape_bottom");
		cropsModelBuilder.count(3).build(StardewBlocks.cropsGrapeTop, "crops_grape_top");

		cropsModelBuilder.count(7).build(StardewBlocks.cropsCornBottom, "crops_corn_bottom");
		cropsModelBuilder.count(4).build(StardewBlocks.cropsCornTop, "crops_corn_top");

		cropsModelBuilder.count(7).build(StardewBlocks.cropsBeansBottom, "crops_beans_bottom");
		cropsModelBuilder.count(3).build(StardewBlocks.cropsBeansTop, "crops_beans_top");

		//This is kinda bad, might need to add proper params
		beeHiveModelBuilder.withMapping("beehive_idle", Side.NORTH).build(StardewBlocks.beehive, "beehive");
		beeHiveModelBuilder.build(StardewBlocks.beehiveIdle, "beehive");
		beeHiveModelBuilder.withMapping("beehive_active", Side.NORTH).build(StardewBlocks.beehiveHoney, "beehive");

		candleModelBuilder.build(StardewBlocks.candleActive, "candle");
		candleModelBuilder.build(StardewBlocks.candle, "candle");

		stakeModelBuilder.build(StardewBlocks.plantStake);

		ModelHelper.setBlockModel(StardewBlocks.cauliflower, () -> {
			BlockModelAxisAligned<?> model = new BlockModelAxisAligned<>(StardewBlocks.cauliflower);
			model.setAllTextures(0, StardewMod.MOD_ID + ":block/cauliflower_side");
			model.setTex(0, StardewMod.MOD_ID + ":block/cauliflower_top", Side.BOTTOM); //why the hell is this mirrored
			model.setTex(0, StardewMod.MOD_ID + ":block/cauliflower_bottom", Side.TOP);
			return model;
		});

		ModelHelper.setBlockModel(StardewBlocks.watermelon, () -> {
			BlockModelAxisAligned<?> model = new BlockModelAxisAligned<>(StardewBlocks.watermelon);
			model.setAllTextures(0, StardewMod.MOD_ID + ":block/watermelon_side");
			model.setTex(0, StardewMod.MOD_ID + ":block/watermelon_top", Side.BOTTOM, Side.TOP);
			return model;
		});

		ModelHelper.setBlockModel(StardewBlocks.cropsCauliflower, () -> {
			BlockModelCropsGrowing<?> model = new BlockModelCropsGrowing<>(StardewBlocks.cropsCauliflower);
			model.setLeafTexture(StardewMod.MOD_ID + ":block/cauliflower_crop_leaf");
			model.setTopTextures(
				MOD_ID + ":block/cauliflower_crop_1",
				MOD_ID + ":block/cauliflower_crop_2",
				MOD_ID + ":block/cauliflower_crop_3",
				MOD_ID + ":block/cauliflower_crop_4"
			);
			model.setSideTextures(
				MOD_ID + ":block/cauliflower_crop_side_1",
				MOD_ID + ":block/cauliflower_crop_side_2",
				MOD_ID + ":block/cauliflower_crop_side_3",
				MOD_ID + ":block/cauliflower_crop_side_4"
			);
			return model;
		});

		ModelHelper.setBlockModel(StardewBlocks.cropsWatermelon, () -> {
			BlockModelCropsGrowing<?> model = new BlockModelCropsGrowing<>(StardewBlocks.cropsWatermelon);
			model.setLeafTexture(StardewMod.MOD_ID + ":block/watermelon_crop_leaf");
			model.setTopTextures(
				MOD_ID + ":block/watermelon_crop_1",
				MOD_ID + ":block/watermelon_crop_2",
				MOD_ID + ":block/watermelon_crop_3",
				MOD_ID + ":block/watermelon_crop_4"
			);
			model.setSideTextures(
				MOD_ID + ":block/watermelon_crop_side_1",
				MOD_ID + ":block/watermelon_crop_side_2",
				MOD_ID + ":block/watermelon_crop_side_3",
				MOD_ID + ":block/watermelon_crop_side_4"
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

		fishingRodModelBuilder.build(StardewItems.toolFishingrodStone);
		fishingRodModelBuilder.build(StardewItems.toolFishingrodIron);
		fishingRodModelBuilder.build(StardewItems.toolFishingrodGold);
		fishingRodModelBuilder.build(StardewItems.toolFishingrodDiamond);
		fishingRodModelBuilder.build(StardewItems.toolFishingrodSteel);

		canModelBuilder.build(StardewItems.armorCanOfWorms);

		standardBuilder.build(StardewItems.armorCanOfWormsGolden);

		standardBuilder.build(StardewItems.seedsBlueberry);
		standardBuilder.build(StardewItems.seedsCarrot);
		standardBuilder.build(StardewItems.seedsCauliflower);
		standardBuilder.build(StardewItems.seedsCorn);
		standardBuilder.build(StardewItems.seedsGrapes);
		standardBuilder.build(StardewItems.seedsCranberries);
		standardBuilder.build(StardewItems.seedsPineapple);
		standardBuilder.build(StardewItems.seedsTomato);
		standardBuilder.build(StardewItems.seedsStrawberry);
		standardBuilder.build(StardewItems.beansCoffee);
		standardBuilder.build(StardewItems.seedsPotato);
		standardBuilder.build(StardewItems.seedsWatermelon);

		standardBuilder.build(StardewItems.blueberry);
		standardBuilder.build(StardewItems.carrot);
		standardBuilder.build(StardewItems.corn);
		standardBuilder.build(StardewItems.grapes);
		standardBuilder.build(StardewItems.cranberries);
		standardBuilder.build(StardewItems.pineapple);
		standardBuilder.build(StardewItems.tomato);
		standardBuilder.build(StardewItems.strawberry);
		standardBuilder.build(StardewItems.potato);

		standardBuilder.build(StardewItems.honey);
		standardBuilder.build(StardewItems.wax);
		standardBuilder.build(StardewItems.recordPink);
		standardBuilder.build(StardewItems.worm);
		standardBuilder.build(StardewItems.eggDuck);
		standardBuilder.build(StardewItems.fiber);
		standardBuilder.build(StardewItems.dough);

		standardBuilder.build(StardewItems.wateringCan);
		standardBuilder.build(StardewItems.wateringCanSteel);

		standardBuilder.build(StardewItems.foodPie);
		standardBuilder.build(StardewItems.foodPizza);
		standardBuilder.build(StardewItems.foodCakeChocolate);
		standardBuilder.build(StardewItems.foodBassCooked);
		standardBuilder.build(StardewItems.foodBassRaw);
		standardBuilder.build(StardewItems.foodCoffee);
		standardBuilder.build(StardewItems.foodSalmonCooked);
		standardBuilder.build(StardewItems.foodSalmonRaw);
		standardBuilder.build(StardewItems.foodSnapperCooked);
		standardBuilder.build(StardewItems.foodSnapperRaw);
		standardBuilder.build(StardewItems.foodStewFruit);
		standardBuilder.build(StardewItems.foodStewCheese);
		standardBuilder.build(StardewItems.foodStewVegetable);
		standardBuilder.build(StardewItems.eggCooked);
		standardBuilder.build(StardewItems.cheese);
		standardBuilder.build(StardewItems.jarJam);

		standardBuilder.build(StardewItems.fishEelLava);
		standardBuilder.build(StardewItems.fishGhost);
		standardBuilder.build(StardewItems.fishStone);
		standardBuilder.build(StardewItems.fishSword);

		blockItemModelBuilder.build(StardewBlocks.candleActive.asItem(), "candle");
		blockItemModelBuilder.build(StardewBlocks.candle.asItem(), "candle");
	}

	@Override
	public void initEntityModels(EntityRenderDispatcher dispatcher) {
		ModelHelper.setEntityModel(EntityDuck.class, () -> new DuckRenderer(new ModelDuck(), 0.4F));
		ModelHelper.setEntityModel(MobGoat.class, () -> new MobRendererGoat(new ModelGoat(), 0.7F));
		ModelHelper.setEntityModel(EntityEggDuck.class, () -> new EntityRendererSprite<>(StardewItems.eggDuck));
	}

	@Override
	public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

	}

	@Override
	public void initBlockColors(BlockColorDispatcher dispatcher) {

	}

	/*public static void setStandardItemModel(Item item, BiFunction<Item, String, ItemModel> modelSupplier) {
		ModelHelper.setItemModel(item, () -> {
			ItemModel model = modelSupplier.apply(item, null); //Nulled because spams warnings
			if (model instanceof ItemModelStandard) {
				((ItemModelStandard) model).icon = TextureRegistry.getTexture(item.namespaceID.toString());
				if (model instanceof ItemModelTieredFishingRod) {
					((ItemModelTieredFishingRod)model).initCastTexture();
				}
			}
			return model;
		});
	}

	public static void setBlockModel(Block<?> block, Function<Block<?>, BlockModel<?>> modelSupplier) {
		ModelHelper.setBlockModel(block, () -> {
			BlockModel<?> model = modelSupplier.apply(block);
			return model;
		});
	}*/
}
