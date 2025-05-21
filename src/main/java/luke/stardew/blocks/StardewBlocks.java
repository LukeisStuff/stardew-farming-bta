package luke.stardew.blocks;

import luke.stardew.StardewConfig;
import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicLog;
import net.minecraft.core.block.BlockLogicMushroom;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSound;
import net.minecraft.core.sound.BlockSounds;
import net.minecraft.core.world.World;
import turniplabs.halplibe.helper.BlockBuilder;

import static luke.stardew.StardewMod.MOD_ID;

public class StardewBlocks {
	public int blockID(String blockName) {
		return StardewConfig.cfg.getInt("Block IDs." + blockName);
	}

	//Spring Crops
	public static Block<?> cropsCarrot;

	public static Block<?> cropsBlueberry;
	public static Block<?> cropsPineapple;

	//Summer Crops
	public static Block<?> cropsTomato;
	public static Block<?> cropsPotato;

	public static Block<?> cropsStrawberry;
	public static Block<?> cropsWatermelon;
	public static Block<?> watermelon;

	//Fall Crops
	public static Block<?> logApple;
	public static Block<?> leavesApple;
	public static Block<?> leavesAppleFlowering;
	public static Block<?> saplingApple;

	public static Block<?> logAppleGolden;
	public static Block<?> leavesAppleGolden;
	public static Block<?> leavesAppleGoldenFlowering;
	public static Block<?> saplingAppleGolden;

	public static Block<?> cropsCornBottom;
	public static Block<?> cropsCornTop;

	public static Block<?> cropsGrapeBottom;
	public static Block<?> cropsGrapeTop;

	//Winter Crops

	public static Block<?> cropsCauliflower;
	public static Block<?> cauliflower;

	public static Block<?> cropsCranberries;


	public static Block<?> bush;

	public static Block<?> beehiveIdle;
	public static Block<?> beehiveHoney;

	public static Block<?> blockHoney;

	public static Block<?> cropsBeansBottom;
	public static Block<?> cropsBeansTop;

	public static Block<?> cakeChocolate;
	public static Block<?> pie;

	public static Block<?> beehive;

	public static Block<?> pizza;

	public static Block<?> candle;
	public static Block<?> candleActive;

	public static Block<?> plantStake;

	public static Block<?> mushroomTruffle;

	public static Block<?> thatch;

	public void initializeBlockDetails() {
		//FIXME INSTANCE IS NULL
		//LookupFuelFurnace.instance.addFuelEntry(thatch.id(), 400);
	}

	public void initializeBlocks() {

		BlockBuilder crops = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GRASS)
			.setHardness(0.0f)
			.setResistance(0.0f)
			//.setBlockModel(BlockModelCrossedSquares::new)
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU);

		BlockBuilder cropsBlock = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GRASS)
			.setHardness(0.0f)
			.setResistance(0.0f)
			//.setBlockModel(BlockModelCropsPumpkin::new)
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.OVERRIDE_STEPSOUND);

		BlockBuilder blocks = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.WOOD)
			.setHardness(1.0F)
			.setResistance(1.0F)
			.setTags(BlockTags.MINEABLE_BY_AXE);

		BlockBuilder leaves = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f))
			.setHardness(0.2F)
			.setResistance(0.2F)
			.setFlammability(30, 60)
			.setTickOnLoad()
			.setVisualUpdateOnMetadata()
			//.setItemBlock(ItemBlockLeaves::new)
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS, BlockTags.SHEARS_DO_SILK_TOUCH);

		BlockBuilder sapling = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f))
			.setHardness(0.0f)
			.setResistance(0.0f)
			//.setBlockModel(BlockModelCrossedSquares::new)
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR);

		BlockBuilder log = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.wood", "step.wood", 1.0f, 1.0f))
			.setHardness(2.0F)
			.setResistance(1.0f)
			.setFlammability(5, 5)
			//.setBlockModel(BlockModelAxisAligned::new)
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT);

		BlockBuilder wood = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.wood", "step.wood", 1.0f, 1.0f))
			.setHardness(2.5f)
			.setResistance(1.0f)
			.setFlammability(5, 5)
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT);

		BlockBuilder metal = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.5f))
			.setHardness(5.0f)
			.setResistance(10.0f)
			.setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.CHAINLINK_FENCES_CONNECT);



		//Spring Crops

		// Spring Vegetables
		cropsCarrot = crops.build("crops_carrot", blockID("cropsCarrot"), (b) -> new BlockLogicCropBase(b)
			.withCrop(StardewItems.carrot)
			.withSeed(StardewItems.seedsCarrot, 2, 0)
			.withGrowth(2)
			.notFertilized()
			.noHarvest());
		/*cropsCarrot = crops
			.setBlockModel(BlockModelCropsCarrot::new)
			.build(new BlockCropsCarrot("crops.carrot", blockID("cropsCarrot")));*/


		// Spring Fruits
		cropsBlueberry = crops.build("crops_blueberry", blockID("cropsBlueberry"), (b) -> new BlockLogicCropBase(b)
			.withCrop(StardewItems.blueberry)
			.withSeed(StardewItems.seedsBlueberry, 1, 2)
			.withGrowth(4)
			.withResetMeta(2)
			.notFertilized());
		/*cropsBlueberry = crops
			.setBlockModel(BlockModelCropsBlueberry::new)
			.build(new BlockCropsBlueberry("crops.blueberry", blockID("cropsBlueberry")));*/

		cropsPineapple = crops.build("crops_pineapple", blockID("cropsPineapple"), (b) -> new BlockLogicCropBase(b)
			.withCrop(StardewItems.pineapple)
			.withSeed(StardewItems.seedsPineapple, 1, 2)
			.withGrowth(4)
			.withResetMeta(2)
			.notFertilized());
		/*cropsPineapple = crops
			.setBlockModel(BlockModelCropsPineapple::new)
			.build(new BlockCropsPineapple("crops.pineapple", blockID("cropsPineapple")));*/


		//Summer Crops

		// Summer Vegetables
		cropsTomato = crops.build("crops_tomato", blockID("cropsTomato"), (b) -> new BlockLogicCropBase(b)
			.withCrop(StardewItems.tomato)
			.withSeed(StardewItems.seedsPineapple, 1, 0)
			.withGrowth(5)
			.withResetMeta(3)
			.notFertilized());
		/*cropsTomato = crops
			.setBlockModel(BlockModelCropsTomato::new)
			.build(new BlockCropsTomato("crops.tomato", blockID("cropsTomato")));*/

		cropsPotato = crops.build("crops_potato", blockID("cropsPotato"), (b) -> new BlockLogicCropBase(b)
			.withCrop(StardewItems.potato)
			.withSeed(StardewItems.seedsPotato, 2, 0)
			.withGrowth(4)
			.notFertilized()
			.noHarvest());
		/*cropsPotato = crops
			.setBlockModel(BlockModelCropsPotato::new)
			.build(new BlockCropsPotato("crops.potato", blockID("cropsPotato")));*/

		// Summer Fruits

		cropsStrawberry = crops.build("crops_strawberry", blockID("cropsStrawberry"), (b) -> new BlockLogicCropBase(b)
			.withCrop(StardewItems.strawberry)
			.withSeed(StardewItems.seedsStrawberry, 1, 1)
			.withResetMeta(1)
			.withGrowth(3)
			.notFertilized());
		/*cropsStrawberry = crops
			.setBlockModel(BlockModelCropsStrawberry::new)
			.build(new BlockCropsStrawberry("crops.strawberry", blockID("cropsStrawberry")));*/

		cropsWatermelon = crops.build("crops_watermelon", blockID("cropsWatermelon"), (b) -> new BlockLogicCropBase(b)
			.withSeed(StardewItems.seedsWatermelon, 0, 0)
			.withGrowth(4)
			.growsInto(watermelon));
		/*cropsWatermelon = cropsBlock
			.setBlockModel(BlockModelCropsWatermelon::new)
			.build(new BlockCropsWatermelon("crops.watermelon", blockID("cropsWatermelon")));*/
		watermelon = blocks
			//.setBlockModel(block -> new BlockModelAxisAligned<>(block).withTextures(MOD_ID + ":block/watermelon_top", MOD_ID + ":block/watermelon_side"))
			.build("watermelon", blockID("watermelon"), b -> new BlockFruit(b));

		//Fall Crops
		cropsCornBottom = crops
			//.setBlockModel(BlockModelCropsCornBottom::new)
			.build("crops_corn_bottom", blockID("cropsCornBottom"), b -> new BlockLogicCropTall(b)
				.growsTop(cropsCornTop, 3)
				.withGrowth(6)
				.withSeed(StardewItems.seedsCorn, 2, 0)
				.withCrop(StardewItems.corn)
				.notFertilized()
				.noHarvest());

		cropsCornTop = crops
			//.setBlockModel(BlockModelCropsCornTop::new)
			.build("crops_corn_top", blockID("cropsCornTop"), b -> new BlockLogicCropTall(b)
				.asTop(StardewBlocks.cropsCornBottom)
				.withGrowth(3)
				.withSeed(StardewItems.seedsCorn, 2, 0)
				.withCrop(StardewItems.corn)
				.notFertilized()
				.noHarvest());


		/* FIXME
		cropsGrapeBottom = crops
			//.setBlockModel(BlockModelCropsGrapesBottom::new)
			.build("crops_grape_bottom", blockID("cropsGrapeBottom"), b -> new BlockCropsGrapeBottom(b));

		cropsGrapeTop = crops
			//.setBlockModel(BlockModelCropsGrapesTop::new)
			.build("crops_grape_top", blockID("cropsGrapeTop"), b -> new BlockCropsGrapeTop(b));*/


		// Fall Tree
		logApple = log
			//.setBlockModel(block -> new BlockModelAxisAligned<>(block).withTextures(MOD_ID + ":block/log_apple_top", MOD_ID + ":block/log_apple_side"))
			.build("log_apple", blockID("logApple"), b -> new BlockLogicLog(b));
		leavesApple = leaves
			//.setBlockModel(block -> new BlockModelLeaves<>(block, MOD_ID + ":block/leaves_apple"))
			.build("leaves_apple", blockID("leavesApple"), b -> new BlockLeavesApple(b));
		leavesAppleFlowering = leaves
			//.setBlockModel(BlockModelAppleLeavesBloom::new)
			.build("leaves_apple_flowering", blockID("leavesAppleFlowering"), b -> new BlockLeavesAppleFlowering(b));
		saplingApple = sapling
			//.setBlockModel(block -> new BlockModelCrossedSquares<>(block).withTextures(MOD_ID + ":block/sapling_apple"))
			.build("sapling_apple", blockID("saplingApple"), b -> new BlockSaplingApple(b));

		logAppleGolden = log
			//.setBlockModel(block -> new BlockModelAxisAligned<>(block).withTextures(MOD_ID + ":block/log_apple_golden_top", MOD_ID + ":block/log_apple_golden_side"))
			.build("log_apple_golden", blockID("logAppleGolden"), b -> new BlockLogicLog(b));
		leavesAppleGolden = leaves
			//.setBlockModel(block -> new BlockModelLeaves<>(block, MOD_ID + ":block/leaves_apple_golden"))
			.build("leaves_apple_golden", blockID("leavesAppleGolden"), b -> new BlockLeavesAppleGolden(b));
		leavesAppleGoldenFlowering = leaves
			//.setBlockModel(BlockModelGoldenAppleLeavesBloom::new)
			.build("leaves_apple_golden_flowering", blockID("leavesAppleGoldenFlowering"), b -> new BlockLeavesAppleGoldenFlowering(b));
		saplingAppleGolden = sapling
			//.setBlockModel(block -> new BlockModelCrossedSquares<>(block).withTextures(MOD_ID + ":block/sapling_apple_golden"))
			.build("sapling_apple_golden", blockID("saplingAppleGolden"), b -> new BlockSaplingAppleGolden(b));


		//Winter Crops
		cropsCauliflower = crops
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.OVERRIDE_STEPSOUND, BlockTags.PLANTABLE_IN_JAR)
			.build("crops_cauliflower", blockID("cropsCauliflower"), (b) -> new BlockLogicCropGrowing(b)
			.withSeed(StardewItems.seedsCauliflower, 0, 0)
			.withGrowth(4)
			.growsInto(cauliflower)
			.notFertilized());
		/*cropsCauliflower = cropsBlock
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.OVERRIDE_STEPSOUND, BlockTags.PLANTABLE_IN_JAR)
			.setBlockModel(BlockModelCropsCauliflower::new)
			.build(new BlockCropsCauliflower("crops.cauliflower", blockID("cropsCauliflower")));*/
		cauliflower = blocks
			//.setBlockModel(block -> new BlockModelAxisAligned<>(block).withTextures(MOD_ID + ":block/cauliflower_bottom", MOD_ID + ":block/cauliflower_top", MOD_ID + ":block/cauliflower_side"))
			.build("cauliflower", blockID("cauliflower"), b -> new BlockLogic(b, Material.vegetable));


		cropsCranberries = crops.build("crops_cranberries", blockID("cropsCranberries"), (b) -> new BlockLogicCropBase(b)
			.withCrop(StardewItems.cranberries)
			.withSeed(StardewItems.seedsCranberries, 1, 2)
			.withGrowth(3)
			.withResetMeta(2)
			.notFertilized());
		/*cropsCranberries = crops
			.setBlockModel(BlockModelCropsCranberry::new)
			.build(new BlockCropsCranberries("crops.cranberries", blockID("cropsCranberries")));*/

		bush = crops
			.setTags(BlockTags.PLANTABLE_IN_JAR, BlockTags.SHEARS_DO_SILK_TOUCH, BlockTags.MINEABLE_BY_SHEARS)
			.setTicking(true)
			.setTickOnLoad()
			//.setBlockModel(BlockModelBush::new)
			.build("bush", blockID("bush"), BlockBush::new);


		beehiveIdle = wood
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT, BlockTags.NOT_IN_CREATIVE_MENU)
			//.setTopBottomTextures(MOD_ID + ":block/beehive_top")
			//.setSideTextures(MOD_ID + ":block/beehive_side")
			//.setNorthTexture(MOD_ID + ":block/beehive_idle")
			//.setBlockModel((block) -> {
			//	TextureRegistry.getTexture(MOD_ID + ":item/bee");
			//	return new BlockModelHorizontalRotation<>(block);
			//})
			.build("beehive_idle", blockID("beehiveIdle"), b -> new BlockBeehiveActive(b, false));

		beehiveHoney = wood
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT, BlockTags.NOT_IN_CREATIVE_MENU)
			//.setTopBottomTextures(MOD_ID + ":block/beehive_top")
			//.setSideTextures(MOD_ID + ":block/beehive_side")
			//.setNorthTexture(MOD_ID + ":block/beehive_active")
			//.setBlockModel((block) -> {
			//	TextureRegistry.getTexture(MOD_ID + ":item/bee");
			//	return new BlockModelHorizontalRotation<>(block);
			//})
			.build("beehive_honey", blockID("beehiveHoney"), b -> new BlockBeehiveActive(b, true));

		blockHoney = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.5f))
			.setHardness(0.2f)
			.setResistance(0.2f)
			.setLightOpacity(6)
			.setTags(BlockTags.MINEABLE_BY_AXE)
			//.setBlockModel(block -> new BlockModelStandard<>(block).withTextures(MOD_ID + ":block/block_honey")) //Ice model needed
			.build("block_honey", blockID("blockHoney"), BlockHoney::new);


		cropsBeansBottom = crops
			//.setBlockModel(BlockModelCropsBeansBottom::new)
			.build("crops_beans_bottom", blockID("cropsBeansBottom"), b -> new BlockLogicCropTall(b)
				.growsTop(cropsBeansTop, 4)
				.withGrowth(6)
				.withSeed(StardewItems.beansCoffee, 2, 0)
				.notFertilized()
				.noHarvest());

		cropsBeansTop = crops
			//.setBlockModel(BlockModelCropsBeansTop::new)
			.build("crops_beans_top", blockID("cropsBeansTop"), b -> new BlockLogicCropTall(b)
				.asTop(cropsBeansBottom)
				.withGrowth(2)
				.withSeed(StardewItems.beansCoffee, 2, 0)
				.notFertilized()
				.noHarvest());



		cakeChocolate = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.cloth", "step.cloth", 1.0f, 1.0f))
			.setHardness(0.5f)
			.setResistance(0.5f)
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
			//.setBlockModel(block -> new BlockModelCakeChocolate<>(block).withTextures(MOD_ID + ":block/chokoCake_top", MOD_ID + ":block/chokoCake_bottom", MOD_ID + ":block/chokoCake_side"))
			.build("cake_chocolate", blockID("cakeChocolate"), b -> new BlockCakeChocolate(b, 0.5f, StardewItems.foodCakeChocolate));

		beehive = wood.build("beehive", blockID("beehive"), BlockBeehive::new);
			//.setBlockModel(block -> new BlockModelHorizontalRotation<>(block).withTextures(MOD_ID + ":block/beehive_top", MOD_ID + ":block/beehive_top", MOD_ID + ":block/beehive_idle", MOD_ID + ":block/beehive_side", MOD_ID + ":block/beehive_side", MOD_ID + ":block/beehive_side"))


		pizza = new BlockBuilder(MOD_ID)
			//.setBlockModel(block -> new BlockModelPizza<>(block).withTextures(MOD_ID + ":block/pizza_top", MOD_ID + ":block/pizza_bottom", MOD_ID + ":block/pizza_side"))
			.setBlockSound(new BlockSound("step.cloth", "step.cloth", 1.0f, 1.0f))
			.setHardness(0.5f)
			.setResistance(0.5f)
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
			.build("pizza", blockID("pizza"), b -> new BlockCakeChocolate(b, 0.25F, StardewItems.foodPizza));

		candle = new BlockBuilder(MOD_ID)
			//.setBlockModel(BlockModelWaxCandle::new)
			//.setTextures(MOD_ID + ":block/candle")
			//.setIcon(MOD_ID + ":block/candle_item")
			.setBlockSound(new BlockSound("step.wood", "step.wood", 1.0f, 1.2f))
			.setHardness(0.0f)
			.setResistance(0.0f)
			.setVisualUpdateOnMetadata()
			.setTags(BlockTags.MINEABLE_BY_SWORD, BlockTags.BROKEN_BY_FLUIDS)
			.build("candle", blockID("candle"), b -> new BlockWaxCandle(b, false));

		candleActive = new BlockBuilder(MOD_ID)
			//.setBlockModel(BlockModelWaxCandle::new)
			//.setTextures(MOD_ID + ":block/candle")
			//.setIcon(MOD_ID + ":block/candle_item")
			.setBlockSound(new BlockSound("step.wood", "step.wood", 1.0f, 1.2f))
			.setHardness(0.0f)
			.setResistance(0.0f)
			.setLuminance(14)
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setTags(BlockTags.MINEABLE_BY_SWORD, BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
			.build("candle_active", blockID("candleActive"), b -> new BlockWaxCandle(b, true));

		plantStake = new BlockBuilder(MOD_ID)
			//.setBlockModel(BlockModelPlantStake::new)
			//.setTextures(MOD_ID + ":block/plantStake")
			.setHardness(0.0f)
			.setResistance(0.0f)
			.setBlockSound(new BlockSound("step.gravel", "step.wood", 1.0f, 1.0f))
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
			.build("plantstake", blockID("plantStake"), b -> new BlockPlantStake(b, Material.plant));


		mushroomTruffle = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f))
			.setHardness(0.0f)
			.setResistance(0.0f)
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR)
			//.setBlockModel(BlockModelCrossedSquares::new)
			//.setTextures(MOD_ID + ":block/truffle")
			.build("mushroom_truffle", blockID("mushroomTruffle"), b -> new BlockLogicMushroom(b));

		thatch = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.grass", "step.grass", 0.6f, 1.2f))
			.setHardness(0.6f)
			.setResistance(0.6f)
			//.setBlockModel(BlockModelAxisAligned::new)
			//.setTopBottomTextures(MOD_ID + ":block/thatch_top")
			//.setSideTextures(MOD_ID + ":block/thatch_side")
			.setFlammability(60, 120)
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS)
			.build("thatch", blockID("thatch"), b -> new BlockThatch(b, Material.grass));

		initializeBlockDetails();
	}

	public static boolean isBlockLogic(World world, int x, int y, int z, Class<? extends BlockLogic> logic) {
		Block<?> block = world.getBlock(x, y, z);
		return block != null && logic.isAssignableFrom(block.getLogic().getClass());
	}
}
