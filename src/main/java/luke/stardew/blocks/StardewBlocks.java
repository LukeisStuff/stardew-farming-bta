package luke.stardew.blocks;

import luke.stardew.StardewTags;
import luke.stardew.blocks.crops.*;
import luke.stardew.items.StardewItems;
import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.Items;
import net.minecraft.core.sound.BlockSound;
import net.minecraft.core.sound.BlockSounds;
import net.minecraft.core.world.season.Seasons;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import turniplabs.halplibe.util.BlockInitEntrypoint;

import static luke.stardew.StardewConfig.blockID;
import static luke.stardew.StardewMod.MOD_ID;

public final class StardewBlocks implements BlockInitEntrypoint {

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

    public static Block<?> BEEHIVE;

    public static Block<?> PIZZA;

    public static Block<?> CANDLE;
    public static Block<?> CANDLE_ACTIVE;

    public static Block<?> PLANT_STAKE;

    public static Block<?> MUSHROOM_TRUFFLE;

    public static Block<?> THATCH;

    public static Block<BlockLogicPieApple> APPLE_PIE;

    private static boolean hasInit = false;

    public static void init() {
        if (!hasInit) {
            hasInit = true;
            initializeBlocks();
        }
    }

    public static void initializeBlocks() {

        BlockBuilder crops = new BlockBuilder(MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0f)
            .setResistance(0.0f)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, StardewTags.NEARBY_CROP);

        BlockBuilder cropsBlock = new BlockBuilder(MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0f)
            .setResistance(0.0f)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.OVERRIDE_STEPSOUND, StardewTags.NEARBY_CROP);

        BlockBuilder blocks = new BlockBuilder(MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(1.0F)
            .setResistance(1.0F)
            .setTags(BlockTags.MINEABLE_BY_AXE);

        BlockBuilder leaves = new BlockBuilder(MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.2F)
            .setResistance(0.2F)
            .setFlammability(30, 60)
            .setTicking(true)
            .setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS, BlockTags.SHEARS_DO_SILK_TOUCH);

        BlockBuilder sapling = new BlockBuilder(MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0f)
            .setResistance(0.0f)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR);

        BlockBuilder log = new BlockBuilder(MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.0F)
            .setResistance(1.0f)
            .setFlammability(5, 5)
            .setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT);

        BlockBuilder wood = new BlockBuilder(MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(2.5f)
            .setResistance(1.0f)
            .setFlammability(5, 5)
            .setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT);


        //Spring Crops

        // Spring Vegetables
        CROPS_CARROT = crops
            .build("crops.carrot", "crops_carrot", blockID("CROPS_CARROT"), BlockLogicCropBase::new);


        // Spring Fruits
        CROPS_BLUEBERRY = crops
            .build("crops.blueberry", "crops_blueberry", blockID("CROPS_BLUEBERRY"), BlockLogicCropBase::new);

        CROPS_PINEAPPLE = crops
            .build("crops.pineapple", "crops_pineapple", blockID("CROPS_PINEAPPLE"), BlockLogicCropBase::new);


        //Summer Crops

        // Summer Vegetables
        CROPS_TOMATO = crops
            .build("crops.tomato", "crops_tomato", blockID("CROPS_TOMATO"), BlockLogicCropBase::new);

        CROPS_POTATO = crops
            .build("crops.potato", "crops_potato", blockID("CROPS_POTATO"), BlockLogicCropBase::new);


        // Summer Fruits

        CROPS_STRAWBERRY = crops
            .build("crops.strawberry", "crops_strawberry", blockID("CROPS_STRAWBERRY"), BlockLogicCropBase::new);

        WATERMELON = blocks
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
            .build("watermelon", "watermelon", blockID("WATERMELON"), b -> new BlockLogicFullyRotatable(b, Materials.VEGETABLE));

        CROPS_WATERMELON = cropsBlock.build("crops.watermelon", "crops_watermelon", blockID("CROPS_WATERMELON"), BlockLogicCropsWatermelon::new); //These need to be assigned in order because awful things happen if watermelon is null when assigning here

        //Fall Crops
        CROPS_CORN_BOTTOM = crops
            .build("crops.corn.bottom", "crops_corn_bottom", blockID("CROPS_CORN_BOTTOM"), BlockLogicCropTall::new);
        CROPS_CORN_TOP = crops
            .build("crops.corn.top", "crops_corn_top", blockID("CROPS_CORN_TOP"), BlockLogicCropTall::new);

        CROPS_GRAPE_TOP = crops
            .build("crops.grape.top", "crops_grape_top", blockID("CROPS_GRAPE_TOP"), BlockLogicCropTallStake::new);
        CROPS_GRAPE_BOTTOM = crops
            .build("crops.grape.bottom", "crops_grape_bottom", blockID("CROPS_GRAPE_BOTTOM"), BlockLogicCropTallStake::new);


        // Fall Tree
        LOG_APPLE = log
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.LOGS))
            .build("log.apple", "log_apple", blockID("LOG_APPLE"), BlockLogicLog::new);

        LEAVES_APPLE = leaves
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Blocks.LEAVES_CACAO))
            .build("leaves.apple", "leaves_apple", blockID("LEAVES_APPLE"), b -> new BlockLogicLeavesSeasonal(b, () -> SAPLING_APPLE, Seasons.OVERWORLD_FALL));

        LEAVES_APPLE_FLOWERING = leaves
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Blocks.LEAVES_CACAO))
            .build("leaves.apple.flowering", "leaves_apple_flowering", blockID("LEAVES_APPLE_FLOWERING"),
                b -> new BlockLogicLeavesSeasonalFlowering(b, () -> SAPLING_APPLE, Seasons.OVERWORLD_FALL, () -> Items.FOOD_APPLE, LEAVES_APPLE_FLOWERING));

        SAPLING_APPLE = sapling
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Blocks.SAPLING_CACAO))
            .build("sapling.apple", "sapling_apple", blockID("SAPLING_APPLE"), b -> new BlockLogicSaplingSeasonal(b, LOG_APPLE, LEAVES_APPLE, LEAVES_APPLE_FLOWERING, 5));

        LOG_APPLE_GOLDEN = log
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.LOGS))
            .build("log.apple.golden", "log_apple_golden", blockID("LOG_APPLE_GOLDEN"), BlockLogicLog::new);

        LEAVES_APPLE_GOLDEN = leaves
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Blocks.LEAVES_CACAO))
            .build("leaves.apple.golden", "leaves_apple_golden", blockID("LEAVES_APPLE_GOLDEN"), b -> new BlockLogicLeavesSeasonal(b, () -> SAPLING_APPLE_GOLDEN, Seasons.OVERWORLD_WINTER));

        LEAVES_APPLE_GOLDEN_FLOWERING = leaves
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Blocks.LEAVES_CACAO))
            .build("leaves.apple.golden.flowering", "leaves_apple_golden_flowering", blockID("LEAVES_APPLE_GOLDEN_FLOWERING"),
                b -> new BlockLogicLeavesSeasonalFlowering(b, () -> SAPLING_APPLE_GOLDEN, Seasons.OVERWORLD_WINTER, () -> Items.FOOD_APPLE_GOLD, LEAVES_APPLE_GOLDEN_FLOWERING));

        SAPLING_APPLE_GOLDEN = sapling
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Blocks.SAPLING_CACAO))
            .build("sapling.apple.golden", "sapling_apple_golden", blockID("SAPLING_APPLE_GOLDEN"), b -> new BlockLogicSaplingSeasonal(b, LOG_APPLE_GOLDEN, LEAVES_APPLE_GOLDEN, LEAVES_APPLE_GOLDEN_FLOWERING, 20));


        //Winter Crops
        CAULIFLOWER = blocks
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
            .build("cauliflower", "cauliflower", blockID("CAULIFLOWER"), b -> new BlockLogicFullyRotatable(b, Materials.VEGETABLE));

        CROPS_CAULIFLOWER = cropsBlock
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.OVERRIDE_STEPSOUND, BlockTags.PLANTABLE_IN_JAR)
            .build("crops.cauliflower", "crops_cauliflower", blockID("CROPS_CAULIFLOWER"), BlockLogicCropsCauliflower::new);

        CROPS_CRANBERRIES = crops.build("crops.cranberries", "crops_cranberries", blockID("CROPS_CRANBERRIES"), BlockLogicCropBase::new);

        BUSH = crops
            .setTags(BlockTags.PLANTABLE_IN_JAR, BlockTags.SHEARS_DO_SILK_TOUCH, BlockTags.MINEABLE_BY_SHEARS)
            .setTicking(true)
            .build("bush", "bush", blockID("BUSH"), BlockLogicBush::new);

        BEEHIVE_IDLE = wood
            .setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT)
            .setTicking(true)
            .build("beehive.idle", "beehive_idle", blockID("BEEHIVE_IDLE"), b -> new BlockLogicBeehiveActive(b, false));

        BEEHIVE_HONEY = wood
            .setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT)
            .build("beehive.honey", "beehive_honey", blockID("BEEHIVE_HONEY"), b -> new BlockLogicBeehiveActive(b, true));

        BLOCK_HONEY = new BlockBuilder(MOD_ID)
            .setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.5f))
            .setHardness(0.2f)
            .setResistance(0.2f)
            .setLightOpacity(6)
            .setTags(BlockTags.MINEABLE_BY_AXE)
            .build("block.honey", "block_honey", blockID("BLOCK_HONEY"), BlockLogicHoney::new);

        CROPS_BEANS_BOTTOM = crops.build("crops.beans.bottom", "crops_beans_bottom", blockID("CROPS_BEANS_BOTTOM"), BlockLogicCropTallStake::new);
        CROPS_BEANS_TOP = crops.build("crops.beans.top", "crops_beans_top", blockID("CROPS_BEANS_TOP"), BlockLogicCropTallStake::new);

        CAKE_CHOCOLATE = new BlockBuilder(MOD_ID)
            .setBlockSound(BlockSounds.CLOTH)
            .setHardness(0.5f)
            .setResistance(0.5f)
            .setTags(BlockTags.BROKEN_BY_FLUIDS)
            .build("cake.chocolate", "cake_chocolate", blockID("CAKE_CHOCOLATE"), b -> new BlockLogicEdibleCustom(b, 0.5f, () -> StardewItems.FOOD_CAKE_CHOCOLATE));

        BEEHIVE = wood
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.WORKBENCHES))
            .build("beehive", "beehive", blockID("BEEHIVE"), BlockLogicBeehive::new);

        PIZZA = new BlockBuilder(MOD_ID)
            .setBlockSound(BlockSounds.CLOTH)
            .setHardness(0.5f)
            .setResistance(0.5f)
            .setTags(BlockTags.BROKEN_BY_FLUIDS)
            .build("pizza", "pizza", blockID("PIZZA"), b -> new BlockLogicEdibleCustom(b, 2F/16F, () -> StardewItems.FOOD_PIZZA));

        CANDLE = new BlockBuilder(MOD_ID)
            .setBlockSound(new BlockSound("step.wood", "step.wood", 1.0f, 1.2f))
            .setHardness(0.0f)
            .setResistance(0.0f)
            .setTags(BlockTags.MINEABLE_BY_SWORD, BlockTags.BROKEN_BY_FLUIDS)
            .build("candle", "candle", blockID("CANDLE"), b -> new BlockLogicWaxCandle(b, false));

        CANDLE_ACTIVE = new BlockBuilder(MOD_ID)
            .setBlockSound(new BlockSound("step.wood", "step.wood", 1.0f, 1.2f))
            .setHardness(0.0f)
            .setResistance(0.0f)
            .setLuminance(14)
            .setUseInternalLight()
            .setTags(BlockTags.MINEABLE_BY_SWORD, BlockTags.BROKEN_BY_FLUIDS)
            .build("candle.active", "candle_active", blockID("CANDLE_ACTIVE"), b -> new BlockLogicWaxCandle(b, true));

        PLANT_STAKE = new BlockBuilder(MOD_ID)
            .setHardness(0.0f)
            .setResistance(0.0f)
            .setBlockSound(BlockSounds.GRAVEL)
            .setTags(BlockTags.BROKEN_BY_FLUIDS)
            .build("plant.stake", "plant_stake", blockID("PLANT_STAKE"), b -> new BlockLogicPlantStake(b, Materials.PLANT));

        MUSHROOM_TRUFFLE = new BlockBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Blocks.MUSHROOM_RED))
            .setBlockSound(BlockSounds.GRASS)
            .setHardness(0.0f)
            .setResistance(0.0f)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR, BlockTags.PIGS_FAVOURITE_BLOCK)
            .build("mushroom.truffle", "mushroom_truffle", blockID("MUSHROOM_TRUFFLE"), BlockLogicMushroom::new);

        THATCH = new BlockBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
            .setBlockSound(new BlockSound("step.grass", "step.grass", 0.6f, 1.2f))
            .setHardness(0.6f)
            .setResistance(0.6f)
            .setFlammability(60, 120)
            .setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS)
            .build("thatch", "thatch", blockID("THATCH"), b -> new BlockLogicThatch(b, Materials.GRANITE));

        APPLE_PIE = new BlockBuilder(MOD_ID)
            .setBlockSound(BlockSounds.CLOTH)
            .setHardness(0.5F)
            .setTags(BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.BROKEN_BY_FLUIDS)
            .build("apple.pie", "apple_pie", blockID("APPLE_PIE"), BlockLogicPieApple::new).setStatParent(() -> StardewItems.FOOD_APPLE_PIE);

    }

    public static void initializeCrops() {
        //Spring Crops
        StardewBlocks.<BlockLogicCropBase>getLogicAs(CROPS_CARROT)
            .withCrop(StardewItems.CARROT)
            .withSeed(StardewItems.SEEDS_CARROT, 2, 2)
            .withGrowth(2)
            .withProperSeason(Seasons.OVERWORLD_SPRING)
            .notFertilized()
            .noHarvest();

        StardewBlocks.<BlockLogicCropBase>getLogicAs(CROPS_BLUEBERRY)
            .withCrop(StardewItems.BLUEBERRY, 1, 4)
            .withSeed(StardewItems.SEEDS_BLUEBERRY, 1, 2)
            .withGrowth(4)
            .withProperSeason(Seasons.OVERWORLD_SPRING)
            .withResetMeta(2)
            .notFertilized();

        StardewBlocks.<BlockLogicCropBase>getLogicAs(CROPS_PINEAPPLE)
            .withCrop(StardewItems.PINEAPPLE)
            .withSeed(StardewItems.SEEDS_PINEAPPLE, 1, 2)
            .withGrowth(4)
            .withProperSeason(Seasons.OVERWORLD_SPRING)
            .withResetMeta(2)
            .notFertilized();

        //Summer Crops
        StardewBlocks.<BlockLogicCropBase>getLogicAs(CROPS_TOMATO)
            .withCrop(StardewItems.TOMATO, 1, 2)
            .withSeed(StardewItems.SEEDS_TOMATO, 1, 1)
            .withGrowth(5)
            .withProperSeason(Seasons.OVERWORLD_SUMMER)
            .withResetMeta(3)
            .notFertilized();

        StardewBlocks.<BlockLogicCropBase>getLogicAs(CROPS_POTATO)
            .withCrop(StardewItems.POTATO)
            .withSeed(StardewItems.SEEDS_POTATO, 2, 2)
            .withGrowth(4)
            .withProperSeason(Seasons.OVERWORLD_SUMMER)
            .notFertilized()
            .noHarvest();

        StardewBlocks.<BlockLogicCropBase>getLogicAs(CROPS_STRAWBERRY)
            .withCrop(StardewItems.STRAWBERRY, 1, 2)
            .withSeed(StardewItems.SEEDS_STRAWBERRY, 1, 1)
            .withResetMeta(1)
            .withGrowth(3)
            .withProperSeason(Seasons.OVERWORLD_SUMMER)
            .notFertilized();

        StardewBlocks.<BlockLogicCropsWatermelon>getLogicAs(CROPS_WATERMELON)
            .withSeed(StardewItems.SEEDS_WATERMELON, 0, 0)
            .withGrowth(4)
            .withProperSeason(Seasons.OVERWORLD_SUMMER)
            .growsInto(WATERMELON)
            .noHarvest();

        //Fall Crops
        StardewBlocks.<BlockLogicCropTall>getLogicAs(CROPS_CORN_BOTTOM)
            .growsTop(CROPS_CORN_TOP, 3)
            .withGrowth(6)
            .withProperSeason(Seasons.OVERWORLD_FALL)
            .withSeed(StardewItems.SEEDS_CORN, 2, 2)
            .withCrop(StardewItems.CORN)
            .notFertilized()
            .noHarvest();

        StardewBlocks.<BlockLogicCropTall>getLogicAs(CROPS_CORN_TOP)
            .asTop(CROPS_CORN_BOTTOM)
            .withGrowth(3)
            .withProperSeason(Seasons.OVERWORLD_FALL)
            .withSeed(StardewItems.SEEDS_CORN, 2, 2)
            .withCrop(StardewItems.CORN)
            .notFertilized()
            .noHarvest();

        StardewBlocks.<BlockLogicCropTall>getLogicAs(CROPS_GRAPE_TOP)
            .asTop(CROPS_GRAPE_BOTTOM)
            .withGrowth(2)
            .withProperSeason(Seasons.OVERWORLD_FALL)
            .withSeed(StardewItems.SEEDS_GRAPES, 2, 2)
            .withResetMeta(0)
            .withCrop(StardewItems.GRAPES, 1, 3)
            .notFertilized();

        StardewBlocks.<BlockLogicCropTall>getLogicAs(CROPS_GRAPE_BOTTOM)
            .growsTop(CROPS_GRAPE_TOP, 3)
            .withGrowth(5)
            .withProperSeason(Seasons.OVERWORLD_FALL)
            .withSeed(StardewItems.SEEDS_GRAPES, 2, 2)
            .withResetMeta(3)
            .withCrop(StardewItems.GRAPES, 1, 3)
            .notFertilized();

        //Winter Crops

        StardewBlocks.<BlockLogicCropBase>getLogicAs(CROPS_CRANBERRIES)
            .withCrop(StardewItems.CRANBERRIES, 1, 3)
            .withSeed(StardewItems.SEEDS_CRANBERRIES, 1, 2)
            .withGrowth(3)
            .withProperSeason(Seasons.OVERWORLD_WINTER, Seasons.OVERWORLD_WINTER_ENDLESS)
            .withResetMeta(2)
            .notFertilized();

        StardewBlocks.<BlockLogicCropsCauliflower>getLogicAs(CROPS_CAULIFLOWER)
            .withSeed(StardewItems.SEEDS_CAULIFLOWER, 0, 0)
            .withGrowth(4)
            .withProperSeason(Seasons.OVERWORLD_WINTER, Seasons.OVERWORLD_WINTER_ENDLESS)
            .growsInto(CAULIFLOWER)
            .notFertilized()
            .noHarvest();

        StardewBlocks.<BlockLogicCropTall>getLogicAs(CROPS_BEANS_BOTTOM)
            .growsTop(CROPS_BEANS_TOP, 4)
            .withGrowth(6)
            .withProperSeason(Seasons.OVERWORLD_WINTER, Seasons.OVERWORLD_WINTER_ENDLESS)
            .withResetMeta(4)
            .withCrop(StardewItems.BEANS_COFFEE, 1, 3)
            .notFertilized()
            .seedItem = (StardewItems.BEANS_COFFEE);

        StardewBlocks.<BlockLogicCropTall>getLogicAs(CROPS_BEANS_TOP)
            .asTop(CROPS_BEANS_BOTTOM)
            .withGrowth(2)
            .withProperSeason(Seasons.OVERWORLD_WINTER, Seasons.OVERWORLD_WINTER_ENDLESS)
            .withResetMeta(0)
            .withCrop(StardewItems.BEANS_COFFEE, 1, 3)
            .notFertilized()
            .seedItem = (StardewItems.BEANS_COFFEE);
    }

    @SuppressWarnings("unchecked")
    public static <A> A getLogicAs(Block<?> block) {
        return (A) block.getLogic();
    }

    @Override
    public void afterBlockInit() {
        init();
    }
}
