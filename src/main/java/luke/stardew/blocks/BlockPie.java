package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import java.util.Random;

public class BlockPie extends Block {
	public BlockPie(String key, int id, Material material) {
		super(key, id, material);
		setTicking(true);
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (rand.nextInt(2) == 0) {
			world.setBlockAndMetadataWithNotify(x, y, z, Block.pumpkinPie.id, world.getBlockMetadata(x, y, z));
		}
	}

	public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
		world.setBlockAndMetadataWithNotify(x, y, z, Block.pumpkinPie.id, world.getBlockMetadata(x, y, z));
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(Item.foodPumpkinPie)};
	}

}
