package luke.stardew.blocks;

import luke.stardew.StardewConfig;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;

import static luke.stardew.StardewMod.MOD_ID;

public class StardewBlocks {
	private int blockID(String blockName) {
		return StardewConfig.cfg.getInt("Block IDs." + blockName);
	}

	public static Block cropsTomato;
	public static Block cropsStrawberry;

	private void initializeBlockDetails() {

	}

	public void initializeBlocks() {

		BlockBuilder crops = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GRASS)
			.setHardness(0.0f)
			.setResistance(0.0f)
			.setBlockModel(new BlockModelRenderBlocks(1))
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU);

		cropsTomato = crops
			.build(new BlockCropsTomato("crops.tomato", blockID("cropsTomato")));

		cropsStrawberry = crops
			.build(new BlockCropsStrawberry("crops.strawberry", blockID("cropsStrawberru")));












		initializeBlockDetails();
	}


}
