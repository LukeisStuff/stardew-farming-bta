package luke.stardew.blocks.model;

import luke.stardew.StardewMod;
import luke.stardew.blocks.BlockLogicCropBase;
import luke.stardew.blocks.StardewBlocks;
import luke.stardew.entities.duck.DuckRenderer;
import luke.stardew.entities.duck.EntityDuck;
import luke.stardew.entities.duck.ModelDuck;
import luke.stardew.entities.goat.EntityGoat;
import luke.stardew.entities.goat.ModelGoat;
import luke.stardew.items.ItemToolFishingRodTiered;
import luke.stardew.items.models.ItemModelTieredFishingRod;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.*;
import net.minecraft.client.render.entity.MobRendererCow;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.util.helper.Side;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import static luke.stardew.StardewMod.MOD_ID;

public class StardewModels implements ModelEntrypoint {

	public static void createItemModel(Path path) {
		String name = path.getFileName().toString().replace(".png", "");
		Item item = Item.itemsMap.get(NamespaceID.getPermanent(StardewMod.MOD_ID, "item/" + name));

		if (item != null) {
			setStandardItemModel(item, (i, s) -> {
				ItemModelStandard model = new ItemModelStandard(i, s);
				if (i instanceof ItemToolFishingRodTiered) { //FIXME pretty bad
					model = new ItemModelTieredFishingRod(i, s);
				}
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
	}

	public static void loadBlockModelsFromTexture() {
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
	}

	@Override
	public void initBlockModels(BlockModelDispatcher dispatcher) {
		//ModelHelper.setBlockModel(StardewBlocks.cropsTomato, BlockModelCrossedSquares::new);
		//setBlockModel(StardewBlocks.cropsTomato, BlockModelCropsTomato::new);

		//setBlockModel(StardewBlocks.logApple, BlockModelVeryRotatable::new);
		//setBlockModel(StardewBlocks.leavesApple, BlockModelLeaves::new);

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
			BlockModelCropsCauliflower<?> model = new BlockModelCropsCauliflower<>(StardewBlocks.cropsCauliflower);
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
			BlockModelCropsCauliflower<?> model = new BlockModelCropsCauliflower<>(StardewBlocks.cropsWatermelon);
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

		setBlockModel(StardewBlocks.leavesApple, b -> new BlockModelLeaves<>(b, MOD_ID + ":block/leaves_apple"));

		ModelHelper.setBlockModel(StardewBlocks.leavesAppleFlowering, () -> {
			BlockModelAppleLeavesBloom<?> model = new BlockModelAppleLeavesBloom<>(StardewBlocks.leavesApple, MOD_ID + ":block/leaves_apple");
			model.setFlowingOverlay(MOD_ID + ":block/leaves_apple_flowering_overlay");
			model.setGrownOverlay(MOD_ID + ":block/leaves_apple_overlay");
			return model;
		});

		setBlockModel(StardewBlocks.leavesAppleGolden, b -> new BlockModelLeaves<>(b, MOD_ID + ":block/leaves_apple_golden"));

		ModelHelper.setBlockModel(StardewBlocks.leavesAppleGoldenFlowering, () -> {
			BlockModelAppleLeavesBloom<?> model = new BlockModelAppleLeavesBloom<>(StardewBlocks.leavesAppleGolden, MOD_ID + ":block/leaves_apple_golden");
			model.setFlowingOverlay(MOD_ID + ":block/leaves_apple_golden_flowering_overlay");
			model.setGrownOverlay(MOD_ID + ":block/leaves_apple_golden_overlay");
			return model;
		});

		ModelHelper.setBlockModel(StardewBlocks.logApple, () -> {
			BlockModelAxisAligned<?> model = new BlockModelAxisAligned<>(StardewBlocks.logApple);
			model.setAllTextures(0, StardewMod.MOD_ID + ":block/log_apple_side");
			model.setTex(0, StardewMod.MOD_ID + ":block/log_apple_top", Side.BOTTOM, Side.TOP);
			return model;
		});

		ModelHelper.setBlockModel(StardewBlocks.logAppleGolden, () -> {
			BlockModelAxisAligned<?> model = new BlockModelAxisAligned<>(StardewBlocks.logAppleGolden);
			model.setAllTextures(0, StardewMod.MOD_ID + ":block/log_apple_golden_side");
			model.setTex(0, StardewMod.MOD_ID + ":block/log_apple_golden_top", Side.BOTTOM, Side.TOP);
			return model;
		});

		setBlockModel(StardewBlocks.saplingApple, b -> {
			BlockModelCrossedSquares<?> model = new BlockModelCrossedSquares<>(b);
			model.setAllTextures(0, MOD_ID + ":block/sapling_apple");
			return model;
		});

		setBlockModel(StardewBlocks.saplingAppleGolden, b -> {
			BlockModelCrossedSquares<?> model = new BlockModelCrossedSquares<>(b);
			model.setAllTextures(0, MOD_ID + ":block/sapling_apple");
			return model;
		});

		setBlockModel(StardewBlocks.cakeChocolate, b -> {
			BlockModelCakeChocolate<?> model = new BlockModelCakeChocolate<>(b, 0.5F);
			model.setCakeInner(MOD_ID + ":block/choko_cake_inner");
			model.setAllTextures(0, MOD_ID + ":block/choko_cake_side");
			model.setTex(0, MOD_ID + ":block/choko_cake_top", Side.TOP);
			model.setTex(0, MOD_ID + ":block/choko_cake_bottom", Side.BOTTOM);
			return model;
		});

		setBlockModel(StardewBlocks.pizza, b -> {
			BlockModelCakeChocolate<?> model = new BlockModelCakeChocolate<>(b, 0.25F);
			model.setCakeInner(MOD_ID + ":block/pizza_inner");
			model.setAllTextures(0, MOD_ID + ":block/pizza_side");
			model.setTex(0, MOD_ID + ":block/pizza_top", Side.TOP);
			model.setTex(0, MOD_ID + ":block/pizza_bottom", Side.BOTTOM);
			return model;
		});

		loadBlockModelsFromTexture();
	}

	@Override
	public void initItemModels(ItemModelDispatcher dispatcher) {

		loadItemModelsFromTexture();
	}

	@Override
	public void initEntityModels(EntityRenderDispatcher dispatcher) {
		ModelHelper.setEntityModel(EntityDuck.class, () -> new DuckRenderer(new ModelDuck(), 0.4F));
		ModelHelper.setEntityModel(EntityGoat.class, () -> new MobRendererCow(new ModelGoat(), 0.7F));
	}

	@Override
	public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

	}

	@Override
	public void initBlockColors(BlockColorDispatcher dispatcher) {

	}

	public static void setStandardItemModel(Item item, BiFunction<Item, String, ItemModel> modelSupplier) {
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
	}
}
