package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.material.Material;

public class BlockFruit extends BlockLogic {
	public BlockFruit(Block<?> block) {
		super(block, Material.vegetable);
	}
}
