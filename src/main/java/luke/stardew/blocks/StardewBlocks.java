package luke.stardew.blocks;

import luke.stardew.StardewConfig;
import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicLog;
import net.minecraft.core.block.BlockLogicMushroom;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.Items;
import net.minecraft.core.sound.BlockSound;
import net.minecraft.core.sound.BlockSounds;
import net.minecraft.core.world.World;
import turniplabs.halplibe.helper.BlockBuilder;

import java.util.function.Supplier;

import static luke.stardew.StardewMod.MOD_ID;

public class StardewBlocks {
	private static int blockID = 6000;
	public static int blockID(String blockName) {
		try {
			return StardewConfig.cfg.getInt("Block IDs." + blockName);
		}catch (NullPointerException e) {
			System.out.println(blockID);
			StardewConfig.properties.addEntry("Block IDs." + blockName, blockID);
			return blockID++;
		}
	}

	//Spring Crops
	public static Block<?> CROPS_CARROT;

	public static Block<?> CROPS_BLUEBERRY;
	public static Block<?> CROPS_PINEAPPLE;

	//Summer Crops
	public static Block<?> CROPS_TOMATO;
	public static Block<?> CROPS_POTATO;

	public static Block<?> CROPS_STRAWBERRY;
	public static Block<?> CROPS_WATERMELON;
	public static Block<?> WATERMELON;

	//Fall Crops
	public static Block<?> LOG_APPLE;
	public static Block<?> LEAVES_APPLE;
	public static Block<?> LEAVES_APPLE_FLOWERING;
	public static Block<?> SAPLING_APPLE;

	public static Block<?> LOG_APPLE_GOLDEN;
	public static Block<?> LEAVES_APPLE_GOLDEN;
	public static Block<?> LEAVES_APPLE_GOLDEN_FLOWERING;
	public static Block<?> SAPLING_APPLE_GOLDEN;

	public static Block<?> CROPS_CORN_BOTTOM;
	public static Block<?> CROPS_CORN_TOP;

	public static Block<?> CROPS_GRAPE_BOTTOM;
	public static Block<?> CROPS_GRAPE_TOP;

	//Winter Crops

	public static Block<?> CROPS_CAULIFLOWER;
	public static Block<?> CAULIFLOWER;

	public static Block<?> CROPS_CRANBERRIES;


	public static Block<?> BUSH;

	public static Block<?> BEEHIVE_IDLE;
	public static Block<?> BEEHIVE_HONEY;

	public static Block<?> BLOCK_HONEY;

	public static Block<?> CROPS_BEANS_BOTTOM;
	public static Block<?> CROPS_BEANS_TOP;

	public static Block<?> CAKE_CHOCOLATE;
	public static Block<?> PIE;

	public static Block<?> BEEHIVE;

	public static Block<?> PIZZA;

	public static Block<?> CANDLE;
	public static Block<?> CANDLE_ACTIVE;

	public static Block<?> PLANT_STAKE;

	public static Block<?> MUSHROOM_TRUFFLE;

	public static Block<?> THATCH;

	public static String key(String name) {
		return MOD_ID + ":block/" + name;
	}

	public static void initializeBlocks() {

		BlockBuilder crops = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GRASS)
			.setHardness(0.0f)
			.setResistance(0.0f)
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU);

		BlockBuilder cropsBlock = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GRASS)
			.setHardness(0.0f)
			.setResistance(0.0f)
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
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS, BlockTags.SHEARS_DO_SILK_TOUCH);

		BlockBuilder sapling = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f))
			.setHardness(0.0f)
			.setResistance(0.0f)
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR);

		BlockBuilder log = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.wood", "step.wood", 1.0f, 1.0f))
			.setHardness(2.0F)
			.setResistance(1.0f)
			.setFlammability(5, 5)
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
		CROPS_CARROT = crops.build("crops_carrot", blockID("CROPS_CARROT"), (b) -> new BlockLogicCropBase(b));


		// Spring Fruits
		CROPS_BLUEBERRY = crops.build("crops_blueberry", blockID("CROPS_BLUEBERRY"), (b) -> new BlockLogicCropBase(b));

		CROPS_PINEAPPLE = crops.build("crops_pineapple", blockID("CROPS_PINEAPPLE"), (b) -> new BlockLogicCropBase(b));


		//Summer Crops

		// Summer Vegetables
		CROPS_TOMATO = crops.build("crops_tomato", blockID("CROPS_TOMATO"), (b) -> new BlockLogicCropBase(b));

		CROPS_POTATO = crops.build("crops_potato", blockID("CROPS_POTATO"), (b) -> new BlockLogicCropBase(b));

		// Summer Fruits

		CROPS_STRAWBERRY = crops.build("crops_strawberry", blockID("CROPS_STRAWBERRY"), (b) -> new BlockLogicCropBase(b));

		WATERMELON = blocks
			.build("watermelon", blockID("WATERMELON"), b -> new BlockLogic(b, Material.vegetable));
		CROPS_WATERMELON = crops.build("crops_watermelon", blockID("CROPS_WATERMELON"), (b) -> new BlockLogicCropGrowing(b)); //These need to be assigned in order because awful things happen if watermelon is null when assigning here

		//Fall Crops
		CROPS_CORN_BOTTOM = crops
			.build("crops_corn_bottom", blockID("CROPS_CORN_BOTTOM"), b -> new BlockLogicCropTall(b));
		CROPS_CORN_TOP = crops
			.build("crops_corn_top", blockID("CROPS_CORN_TOP"), b -> new BlockLogicCropTall(b));

		CROPS_GRAPE_TOP = crops
			.build("crops_grape_top", blockID("CROPS_GRAPE_TOP"), b -> new BlockLogicCropTallStake(b));
		CROPS_GRAPE_BOTTOM = crops
			.build("crops_grape_bottom", blockID("CROPS_GRAPE_BOTTOM"), b -> new BlockLogicCropTallStake(b));


		// Fall Tree
		LOG_APPLE = log
			.build("log_apple", blockID("LOG_APPLE"), b -> new BlockLogicLog(b));
		LEAVES_APPLE = leaves
			.build("leaves_apple", blockID("LEAVES_APPLE"), b -> new BlockLogicLeavesSeasonal(b, StardewBlocks.SAPLING_APPLE));
		LEAVES_APPLE_FLOWERING = leaves
			.build("leaves_apple_flowering", blockID("LEAVES_APPLE_FLOWERING"), b -> new BlockLogicLeavesSeasonalFlowering(b, StardewBlocks.SAPLING_APPLE, Items.FOOD_APPLE));
		SAPLING_APPLE = sapling
			.build("sapling_apple", blockID("SAPLING_APPLE"), b -> new BlockLogicSaplingSeasonal(b, LOG_APPLE, LEAVES_APPLE, LEAVES_APPLE_FLOWERING, 5));

		LOG_APPLE_GOLDEN = log
			.build("log_apple_golden", blockID("LOG_APPLE_GOLDEN"), b -> new BlockLogicLog(b));
		LEAVES_APPLE_GOLDEN = leaves
			.build("leaves_apple_golden", blockID("LEAVES_APPLE_GOLDEN"), b -> new BlockLogicLeavesSeasonal(b, SAPLING_APPLE_GOLDEN));
		LEAVES_APPLE_GOLDEN_FLOWERING = leaves
			.build("leaves_apple_golden_flowering", blockID("LEAVES_APPLE_GOLDEN_FLOWERING"), b -> new BlockLogicLeavesSeasonalFlowering(b, SAPLING_APPLE_GOLDEN, Items.FOOD_APPLE_GOLD));
		SAPLING_APPLE_GOLDEN = sapling
			.build("sapling_apple_golden", blockID("SAPLING_APPLE_GOLDEN"), b -> new BlockLogicSaplingSeasonal(b, LOG_APPLE_GOLDEN, LEAVES_APPLE_GOLDEN, LEAVES_APPLE_GOLDEN_FLOWERING, 20));


		//Winter Crops
		CAULIFLOWER = blocks
			.build("cauliflower", blockID("CAULIFLOWER"), b -> new BlockLogic(b, Material.vegetable));
		CROPS_CAULIFLOWER = crops
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.OVERRIDE_STEPSOUND, BlockTags.PLANTABLE_IN_JAR)
			.build("crops_cauliflower", blockID("CROPS_CAULIFLOWER"), (b) -> new BlockLogicCropGrowing(b));

		CROPS_CRANBERRIES = crops.build("crops_cranberries", blockID("CROPS_CRANBERRIES"), (b) -> new BlockLogicCropBase(b));
		/*cropsCranberries = crops
			.setBlockModel(BlockModelCropsCranberry::new)
			.build(new BlockCropsCranberries("crops.cranberries", blockID("cropsCranberries")));*/

		BUSH = crops
			.setTags(BlockTags.PLANTABLE_IN_JAR, BlockTags.SHEARS_DO_SILK_TOUCH, BlockTags.MINEABLE_BY_SHEARS)
			.setTicking(true)
			.setTickOnLoad()
			.build("bush", blockID("BUSH"), BlockLogicBush::new);


		BEEHIVE_IDLE = wood
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT, BlockTags.NOT_IN_CREATIVE_MENU)
			.build("beehive_idle", blockID("BEEHIVE_IDLE"), b -> new BlockLogicBeehiveActive(b, false));

		BEEHIVE_HONEY = wood
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT, BlockTags.NOT_IN_CREATIVE_MENU)
			.build("beehive_honey", blockID("BEEHIVE_HONEY"), b -> new BlockLogicBeehiveActive(b, true));

		BLOCK_HONEY = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.5f))
			.setHardness(0.2f)
			.setResistance(0.2f)
			.setLightOpacity(6)
			.setTags(BlockTags.MINEABLE_BY_AXE)
			.build("block_honey", blockID("BLOCK_HONEY"), BlockLogicHoney::new);


		CROPS_BEANS_BOTTOM = crops.build("crops_beans_bottom", blockID("CROPS_BEANS_BOTTOM"), b -> new BlockLogicCropTallStake(b));
		CROPS_BEANS_TOP = crops.build("crops_beans_top", blockID("CROPS_BEANS_TOP"), b -> new BlockLogicCropTallStake(b));

		CAKE_CHOCOLATE = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.cloth", "step.cloth", 1.0f, 1.0f))
			.setHardness(0.5f)
			.setResistance(0.5f)
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
			.build("cake_chocolate", blockID("CAKE_CHOCOLATE"), b -> new BlockLogicEdibleCustom(b, 0.5f, StardewItems.FOOD_CAKE_CHOCOLATE));

		BEEHIVE = wood.build("beehive", blockID("BEEHIVE"), BlockLogicBeehive::new);

		PIZZA = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.cloth", "step.cloth", 1.0f, 1.0f))
			.setHardness(0.5f)
			.setResistance(0.5f)
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
			.build("pizza", blockID("PIZZA"), b -> new BlockLogicEdibleCustom(b, 0.25F, StardewItems.FOOD_PIZZA));

		CANDLE = new BlockBuilder(MOD_ID)
			//.setBlockModel(BlockModelWaxCandle::new)
			//.setTextures(MOD_ID + ":block/candle")
			//.setIcon(MOD_ID + ":block/candle_item")
			.setBlockSound(new BlockSound("step.wood", "step.wood", 1.0f, 1.2f))
			.setHardness(0.0f)
			.setResistance(0.0f)
			.setVisualUpdateOnMetadata()
			.setTags(BlockTags.MINEABLE_BY_SWORD, BlockTags.BROKEN_BY_FLUIDS)
			.build("candle", blockID("CANDLE"), b -> new BlockLogicWaxCandle(b, false));

		CANDLE_ACTIVE = new BlockBuilder(MOD_ID)
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
			.build("candle_active", blockID("CANDLE_ACTIVE"), b -> new BlockLogicWaxCandle(b, true));

		PLANT_STAKE = new BlockBuilder(MOD_ID)
			//.setBlockModel(BlockModelPlantStake::new)
			//.setTextures(MOD_ID + ":block/plantStake")
			.setHardness(0.0f)
			.setResistance(0.0f)
			.setBlockSound(new BlockSound("step.gravel", "step.wood", 1.0f, 1.0f))
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
			.build("plant_stake", blockID("PLANT_STAKE"), b -> new BlockLogicPlantStake(b, Material.plant));


		MUSHROOM_TRUFFLE = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f))
			.setHardness(0.0f)
			.setResistance(0.0f)
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR)
			.build("mushroom_truffle", blockID("MUSHROOM_TRUFFLE"), b -> new BlockLogicMushroom(b));

		THATCH = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.grass", "step.grass", 0.6f, 1.2f))
			.setHardness(0.6f)
			.setResistance(0.6f)
			.setFlammability(60, 120)
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS)
			.build("thatch", blockID("THATCH"), b -> new BlockLogicThatch(b, Material.grass));
	}

	public static void initializeCrops() {
		StardewBlocks.<BlockLogicCropBase>getLogicAs(CROPS_CARROT)
			.withCrop(StardewItems.CARROT)
			.withSeed(StardewItems.SEEDS_CARROT, 2, 2)
			.withGrowth(2)
			.notFertilized()
			.noHarvest();

		StardewBlocks.<BlockLogicCropBase>getLogicAs(CROPS_BLUEBERRY)
			.withCrop(StardewItems.BLUEBERRY)
			.withSeed(StardewItems.SEEDS_BLUEBERRY, 1, 2)
			.withGrowth(4)
			.withResetMeta(2)
			.notFertilized();

		StardewBlocks.<BlockLogicCropBase>getLogicAs(CROPS_PINEAPPLE)
			.withCrop(StardewItems.PINEAPPLE)
			.withSeed(StardewItems.SEEDS_PINEAPPLE, 1, 2)
			.withGrowth(4)
			.withResetMeta(2)
			.notFertilized();

		StardewBlocks.<BlockLogicCropBase>getLogicAs(CROPS_TOMATO)
			.withCrop(StardewItems.TOMATO)
			.withSeed(StardewItems.SEEDS_TOMATO, 1, 1)
			.withGrowth(5)
			.withResetMeta(3)
			.notFertilized();

		StardewBlocks.<BlockLogicCropBase>getLogicAs(CROPS_POTATO)
			.withCrop(StardewItems.POTATO)
			.withSeed(StardewItems.SEEDS_POTATO, 2, 2)
			.withGrowth(4)
			.notFertilized()
			.noHarvest();

		StardewBlocks.<BlockLogicCropGrowing>getLogicAs(CROPS_CAULIFLOWER)
			.withSeed(StardewItems.SEEDS_CAULIFLOWER, 0, 0)
			.withGrowth(4)
			.growsInto(CAULIFLOWER)
			.notFertilized()
			.noHarvest();

		StardewBlocks.<BlockLogicCropBase>getLogicAs(CROPS_STRAWBERRY)
			.withCrop(StardewItems.STRAWBERRY)
			.withSeed(StardewItems.SEEDS_STRAWBERRY, 1, 1)
			.withResetMeta(1)
			.withGrowth(3)
			.notFertilized();

		StardewBlocks.<BlockLogicCropBase>getLogicAs(CROPS_CRANBERRIES)
			.withCrop(StardewItems.CRANBERRIES)
			.withSeed(StardewItems.SEEDS_CRANBERRIES, 1, 2)
			.withGrowth(3)
			.withResetMeta(2)
			.notFertilized();

		StardewBlocks.<BlockLogicCropGrowing>getLogicAs(CROPS_WATERMELON)
			.withSeed(StardewItems.SEEDS_WATERMELON, 0, 0)
			.withGrowth(4)
			.growsInto(WATERMELON)
			.noHarvest();

		StardewBlocks.<BlockLogicCropTall>getLogicAs(CROPS_CORN_BOTTOM)
			.growsTop(CROPS_CORN_TOP, 3)
			.withGrowth(6)
			.withSeed(StardewItems.SEEDS_CORN, 2, 2)
			.withCrop(StardewItems.CORN)
			.notFertilized()
			.noHarvest();

		StardewBlocks.<BlockLogicCropTall>getLogicAs(CROPS_CORN_TOP)
			.asTop(CROPS_CORN_BOTTOM)
			.withGrowth(3)
			.withSeed(StardewItems.SEEDS_CORN, 2, 2)
			.withCrop(StardewItems.CORN)
			.notFertilized()
			.noHarvest();

		StardewBlocks.<BlockLogicCropTall>getLogicAs(CROPS_BEANS_BOTTOM)
			.growsTop(CROPS_BEANS_TOP, 4)
			.withGrowth(6)
			.withSeed(StardewItems.BEANS_COFFE, 2, 3)
			.notFertilized();

		StardewBlocks.<BlockLogicCropTall>getLogicAs(CROPS_BEANS_TOP)
			.asTop(CROPS_BEANS_BOTTOM)
			.withGrowth(2)
			.withSeed(StardewItems.BEANS_COFFE, 2, 3)
			.notFertilized();

		StardewBlocks.<BlockLogicCropTall>getLogicAs(CROPS_GRAPE_TOP)
			.asTop(CROPS_GRAPE_BOTTOM)
			.withGrowth(2)
			.withSeed(StardewItems.SEEDS_GRAPES, 2, 2)
			.withResetMeta(0)
			.withCrop(StardewItems.GRAPES)
			.notFertilized();

		StardewBlocks.<BlockLogicCropTall>getLogicAs(CROPS_GRAPE_BOTTOM)
			.growsTop(CROPS_GRAPE_TOP, 3)
			.withGrowth(5)
			.withSeed(StardewItems.SEEDS_GRAPES, 2, 2)
			.withResetMeta(3)
			.withCrop(StardewItems.GRAPES)
			.notFertilized();
	}

	@SuppressWarnings("unchecked")
	public static <A> A getLogicAs(Block<?> block) {
		return (A) block.getLogic();
	}

	public static boolean isBlockLogic(World world, int x, int y, int z, Class<? extends BlockLogic> logic) {
		Block<?> block = world.getBlock(x, y, z);
		return block != null && logic.isAssignableFrom(block.getLogic().getClass());
	}
}
