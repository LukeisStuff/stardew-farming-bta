package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockTransparent;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

import java.util.Random;

public class BlockCrabTrap extends BlockTransparent {
	protected final boolean isActive;

	public BlockCrabTrap(String key, int id, boolean flag) {
		super(key, id, Material.metal);
		this.setTicking(true);
		this.isActive = flag;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		switch (dropCause) {
			case PICK_BLOCK:
			case EXPLOSION:
			case PROPER_TOOL:
			case SILK_TOUCH:
				return new ItemStack[]{new ItemStack(StardewBlocks.crabTrapIdle)};
			default:
				return null;
		}
	}


	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (this.inWater(world, x, y, z)) {
			if (rand.nextInt(2) == 0) {
				world.setBlockWithNotify(x, y, z, StardewBlocks.crabTrapFilled.id);
			}
		}
	}

	public boolean inWater(World world, int x, int y, int z) {
		if (Block.hasTag(world.getBlockId(x + 1, y, z), BlockTags.IS_WATER)) {
			return true;
		} else if (Block.hasTag(world.getBlockId(x - 1, y, z), BlockTags.IS_WATER)) {
			return true;
		} else if (Block.hasTag(world.getBlockId(x, y + 1, z), BlockTags.IS_WATER)) {
			return true;
		} else if (Block.hasTag(world.getBlockId(x, y - 1, z), BlockTags.IS_WATER)) {
			return true;
		} else if (Block.hasTag(world.getBlockId(x, y, z + 1), BlockTags.IS_WATER)) {
			return true;
		} else {
			return Block.hasTag(world.getBlockId(x, y, z - 1), BlockTags.IS_WATER);
		}
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		int l = world.getBlockMetadata(x, y, z);
		if (this.isActive) {
				world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.crabTrapIdle.id, l);
				world.playSoundAtEntity(player, player, "random.pop", 0.2F, 0.5F);
				player.inventory.insertItem(new ItemStack(Item.foodFishRaw, 1), true);
				return true;
			}
		return false;
	}

}
