package luke.stardew.blocks;

import luke.stardew.StardewConfig;
import luke.stardew.StardewMod;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.block.ItemBlockLeaves;
import net.minecraft.core.sound.BlockSound;
import net.minecraft.core.sound.BlockSounds;
import net.minecraft.core.util.helper.Side;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.TextureHelper;

import static luke.stardew.StardewMod.MOD_ID;

public class StardewBlocks {
	private int blockID(String blockName) {
		return StardewConfig.cfg.getInt("Block IDs." + blockName);
	}

	//Spring Crops
	public static Block cropsCarrot;

	public static Block cropsBlueberry;
	public static Block cropsPineapple;

	//Summer Crops
	public static Block cropsTomato;
	public static Block cropsPotato;

	public static Block cropsStrawberry;
	public static Block cropsWatermelon;
	public static Block watermelon;

	//Fall Crops
	public static Block logApple;
	public static Block leavesApple;
	public static Block leavesAppleFlowering;
	public static Block saplingApple;

	public static Block logAppleGolden;
	public static Block leavesAppleGolden;
	public static Block leavesAppleGoldenFlowering;
	public static Block saplingAppleGolden;

	public static Block cropsCornBottom;
	public static Block cropsCornTop;

	public static Block cropsGrapeBottom;
	public static Block cropsGrapeTop;

	//Winter Crops

	public static Block cropsCauliflower;
	public static Block cauliflower;

	public static Block cropsCranberries;


	public static Block bush;



	public static Block beehiveIdle;
	public static Block beehiveHoney;

	public static Block blockHoney;

	public static Block crabTrapIdle;
	public static Block crabTrapFilled;

	public static Block cakeChocolate;
	public static Block pie;

	private void initializeBlockDetails() {

	}

	public void initializeBlocks() {

		BlockBuilder crops = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GRASS)
			.setHardness(0.0f)
			.setResistance(0.0f)
			.setBlockModel(new BlockModelRenderBlocks(1))
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU);

		BlockBuilder cropsBlock = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GRASS)
			.setHardness(0.0f)
			.setResistance(0.0f)
			.setBlockModel(new BlockModelRenderBlocks(32))
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.OVERRIDE_STEPSOUND);

		BlockBuilder block = new BlockBuilder(MOD_ID)
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
			.setItemBlock(ItemBlockLeaves::new)
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS, BlockTags.SHEARS_DO_SILK_TOUCH);

		BlockBuilder sapling = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f))
			.setHardness(0.0f)
			.setResistance(0.0f)
			.setBlockModel(new BlockModelRenderBlocks(1))
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR);

		BlockBuilder log = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.wood", "step.wood", 1.0f, 1.0f))
			.setHardness(2.0F)
			.setResistance(1.0f)
			.setFlammability(5, 5)
			.setBlockModel(new BlockModelRenderBlocks(27))
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

		// Spring Tree
		// Cherry Tree

		// Spring Vegetables
		cropsCarrot = crops
			.build(new BlockCropsCarrot("crops.carrot", blockID("cropsCarrot")));


		// Spring Fruits
		cropsBlueberry = crops
			.build(new BlockCropsBlueberry("crops.blueberry", blockID("cropsBlueberry")));

		cropsPineapple = crops
			.build(new BlockCropsPineapple("crops.pineapple", blockID("cropsPineapple")));


		//Summer Crops

		// Summer Tree


		// Summer Vegetables
		cropsTomato = crops
			.build(new BlockCropsTomato("crops.tomato", blockID("cropsTomato")));

		cropsPotato = crops
			.build(new BlockCropsPotato("crops.potato", blockID("cropsPotato")));

		// Summer Fruits

		cropsStrawberry = crops
			.build(new BlockCropsStrawberry("crops.strawberry", blockID("cropsStrawberry")));

		cropsWatermelon = cropsBlock
			.build(new BlockCropsWatermelon("crops.watermelon", blockID("cropsWatermelon")));
		watermelon = block
			.setSideTextures("melonSide.png")
			.setTopBottomTexture("melonTop.png")
			.build(new Block("watermelon", blockID("watermelon"), Material.vegetable));


		//Fall Crops

		// Fall Tree
		logApple = log
			.setTopBottomTexture("logAppleTop.png")
			.setSideTextures("logAppleSide.png")
			.build(new BlockLog("log.apple", blockID("logApple")));
		leavesApple = leaves
			.setSideTextures("leavesApple.png")
			.setBottomTexture("leavesAppleFast.png")
			.setTopBottomTexture("leavesApple.png")
			.build(new BlockLeavesApple("leaves.apple", blockID("leavesApple")));
		leavesAppleFlowering = leaves
			.setSideTextures("leavesAppleFlowering.png")
			.setBottomTexture("leavesAppleFloweringFast.png")
			.setTopBottomTexture("leavesAppleFlowering.png")
			.build(new BlockLeavesAppleFlowering("leaves.apple.flowering", blockID("leavesAppleFlowering")));
		saplingApple = sapling
			.setTextures("saplingApple.png")
			.build(new BlockSaplingApple("sapling.apple", blockID("saplingApple")));

		logAppleGolden = log
			.setTopBottomTexture("logAppleGoldenTop.png")
			.setSideTextures("logAppleGoldenSide.png")
			.build(new BlockLog("log.apple.golden", blockID("logAppleGolden")));
		leavesAppleGolden = leaves
			.setSideTextures("leavesAppleGolden.png")
			.setBottomTexture("leavesAppleGoldenFast.png")
			.setTopBottomTexture("leavesAppleGolden.png")
			.build(new BlockLeavesAppleGolden("leaves.apple.golden", blockID("leavesAppleGolden")));
		leavesAppleGoldenFlowering = leaves
			.setSideTextures("leavesAppleGoldenFlowering.png")
			.setBottomTexture("leavesAppleGoldenFloweringFast.png")
			.setTopBottomTexture("leavesAppleGoldenFlowering.png")
			.build(new BlockLeavesAppleGoldenFlowering("leaves.apple.flowering", blockID("leavesAppleGoldenFlowering")));
		saplingAppleGolden = sapling
			.setTextures("saplingAppleGolden.png")
			.build(new BlockSaplingAppleGolden("sapling.apple.golden", blockID("saplingAppleGolden")));


		cropsCornBottom = crops
			.build(new BlockCropsCornBottom("crops.corn.bottom", blockID("cropsCornBottom")));

		cropsCornTop = crops
			.build(new BlockCropsCornTop("crops.corn.top", blockID("cropsCornTop")));


		cropsGrapeBottom = crops
			.build(new BlockCropsGrapeBottom("crops.grape.bottom", blockID("cropsGrapeBottom")));

		cropsGrapeTop = crops
			.build(new BlockCropsGrapeTop("crops.grape.top", blockID("cropsGrapeTop")));



		//Winter Crops


		cropsCauliflower = cropsBlock
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.OVERRIDE_STEPSOUND, BlockTags.PLANTABLE_IN_JAR)
			.build(new BlockCropsCauliflower("crops.cauliflower", blockID("cropsCauliflower")));
		cauliflower = block
			.setSideTextures("cauliflowerSide.png")
			.setTopTexture("cauliflowerTop.png")
			.setBottomTexture("cauliflowerBottom.png")
			.build(new Block("cauliflower", blockID("cauliflower"), Material.vegetable));


		cropsCranberries = crops
			.build(new BlockCropsCranberries("crops.cranberries", blockID("cropsCranberries")));





		bush = crops
			.setTextures("bushSpring.png")
			.setTextures("bushSummer.png")
			.setTextures("bushFall.png")
			.setTextures("bushWinter.png")
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR)
			.setTicking(true)
			.setTickOnLoad()
			.build(new BlockBush("bush", blockID("bush")));


		beehiveIdle = wood
			.setTopBottomTexture("beehiveTop.png")
			.setSideTextures("beehiveSide.png")
			.setNorthTexture("beehiveFront.png")
			.build(new BlockBeehive("beehive.idle", blockID("beehiveIdle"), false));

		beehiveHoney = wood
			.setTopBottomTexture("beehiveTop.png")
			.setSideTextures("beehiveSide.png")
			.setNorthTexture("beehiveFrontHoney.png")
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT, BlockTags.NOT_IN_CREATIVE_MENU)
			.build(new BlockBeehive("beehive.honey", blockID("beehiveHoney"), true));

		blockHoney = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.5f))
			.setHardness(0.2f)
			.setResistance(0.2f)
			.setLightOpacity(6)
			.setTextures("blockHoney.png")
			.setTags(BlockTags.MINEABLE_BY_AXE)
			.build(new BlockHoney("block.honey", blockID("blockHoney"), false));


//		crabTrapIdle = metal
//			.setTopTexture("crabTrapTop.png")
//			.setSideTextures("crabTrapSide.png")
//			.setBottomTexture("crabTrapBottom.png")
//			.build(new BlockCrabTrap("crabtrap.idle", blockID("crabTrapIdle"), false));
//
//		crabTrapFilled = metal
//			.setTopTexture("crabTrapTop.png")
//			.setSideTextures("crabTrapSideFilled.png")
//			.setBottomTexture("crabTrapBottom.png")
//			.setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.CHAINLINK_FENCES_CONNECT, BlockTags.NOT_IN_CREATIVE_MENU)
//			.build(new BlockCrabTrap("crabtrap.Filled", blockID("crabTrapFilled"), true));


		cakeChocolate = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.cloth", "step.cloth", 1.0f, 1.0f))
			.setHardness(0.5f)
			.setResistance(0.5f)
			.setTopTexture("chocoCakeTop.png")
			.setBottomTexture("chocoCakeBottom.png")
			.setSideTextures("chocoCakeSideBit.png")
			.setSideTextures("chocoCakeSide.png")
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
			.build(new BlockCake("cake.chocolate", blockID("cakeChocolate")) {
				public int getBlockTextureFromSideAndMetadata(Side side, int j) {
					int[] texture = TextureHelper.getOrCreateBlockTexture(StardewMod.MOD_ID, "chocoCakeSideBit.png");
					return j > 0 && side == Side.WEST ? texCoordToIndex(texture[0], texture[1]) : this.atlasIndices[side.getId()];
				}
			});

		pie = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.cloth", "step.cloth", 1.0f, 1.0f))
			.setHardness(0.5f)
			.setResistance(0.5f)
			.setTopTexture("pumpkinPieTop.png")
			.setBottomTexture("pumpkinPieBottom.png")
			.setSideTextures("pumpkinPieSide.png")
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
			.build(new BlockPie("pie", blockID("pie")));

		initializeBlockDetails();
	}


}
