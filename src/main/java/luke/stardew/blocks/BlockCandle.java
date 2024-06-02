package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import useless.dragonfly.model.block.processed.BlockModel;

import java.util.Random;

public class BlockCandle extends Block {
	protected final boolean isActive;

	public BlockCandle(String key, int id, boolean flag, BlockModel orCreateBlockModel, boolean render3d) {
		super(key, id, Material.decoration);
		this.setTicking(true);
		this.isActive = flag;
		this.setBlockBounds(0.40625F, 0.0F, 0.40625F, 0.59375F, 0.5F, 0.59375F);
	}

	public AABB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	public boolean isSolidRender() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public void onBlockAdded(World world, int x, int y, int z) {
		world.isBlockNormalCube(x, y - 1, z);
	}

	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.isBlockNormalCube(x, y - 1, z) || world.canPlaceOnSurfaceOfBlock(x, y - 1, z);
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
		if (!this.canBlockStay(world, x, y, z)) {
			this.dropBlockWithCause(world, EnumDropCause.WORLD, x, y, z, world.getBlockMetadata(x, y, z), null);
			world.setBlockWithNotify(x, y, z, 0);
		}

	}

	public boolean canBlockStay(World world, int x, int y, int z) {
		return world.canPlaceOnSurfaceOfBlock(x, y - 1, z);
	}

	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if (this.isActive) {
			if (rand.nextInt(2) == 0) {
				world.spawnParticle("smoke", x + 0.5, y + 0.8, z + 0.5, 0.0, 0.0, 0.0);
				world.spawnParticle("flame", x + 0.5, y + 0.8, z + 0.5, 0.0, 0.0, 0.0);
				}
			}
		}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(StardewBlocks.candle)};
	}
}
