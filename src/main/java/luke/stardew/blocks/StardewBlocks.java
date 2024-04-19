package luke.stardew.blocks;

import luke.stardew.StardewConfig;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLeavesBase;
import net.minecraft.core.block.BlockLog;
import net.minecraft.core.block.BlockPumpkin;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.block.ItemBlockLeaves;
import net.minecraft.core.sound.BlockSound;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;

import static luke.stardew.StardewMod.MOD_ID;

public class StardewBlocks {
	private int blockID(String blockName) {
		return StardewConfig.cfg.getInt("Block IDs." + blockName);
	}

	public static Block cropsCarrot;


	public static Block cropsBlueberry;

	public static Block cropsTomato;
	public static Block cropsPotato;
	public static Block cropsStrawberry;
	public static Block cropsWatermelon;
	public static Block watermelon;


	public static Block logApple;
	public static Block leavesApple;
	public static Block leavesAppleFlowering;
	public static Block saplingApple;

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

		//Spring Crops

		// Spring Tree
		// Cherry Tree

		// Spring Vegetables
		cropsCarrot = crops
			.build(new BlockCropsCarrot("crops.carrot", blockID("cropsCarrot")));


		// Spring Fruits
		cropsBlueberry = crops
			.build(new BlockCropsBlueberry("crops.blueberry", blockID("cropsBlueberry")));


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
			.setSideTextures("leavesApple.png")
			.setBottomTexture("leavesAppleFast.png")
			.setTopBottomTexture("leavesApple.png")
			.setBlockModel(new BlockModelRenderBlocks(36))
			.build(new BlockLeavesAppleFlowering("leaves.apple.flowering", blockID("leavesAppleFlowering")));

		saplingApple = sapling
			.setTextures("saplingApple.png")
			.build(new BlockSaplingApple("sapling.apple", blockID("saplingApple")));



		//Winter Crops












		initializeBlockDetails();
	}


}
