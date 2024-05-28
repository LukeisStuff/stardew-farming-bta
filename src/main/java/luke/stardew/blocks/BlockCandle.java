package luke.stardew.blocks;

import luke.stardew.EntityBeeFX;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.fx.EntityFlameFX;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import turniplabs.halplibe.helper.TextureHelper;

import java.util.Random;

import static luke.stardew.StardewMod.MOD_ID;

public class BlockCandle extends Block {
	protected final boolean isActive;

	public BlockCandle(String key, int id, boolean flag) {
		super(key, id, Material.decoration);
		this.setTicking(true);
		this.isActive = flag;
		this.setBlockBounds(0.40625F, 0.0F, 0.40625F, 0.59375F, 0.5F, 0.59375F);
	}

	private boolean canPlaceOnTop(World world, int x, int y, int z) {
		int id = world.getBlockId(x, y, z);
		return world.canPlaceOnSurfaceOfBlock(x, y, z) || id == Block.fencePlanksOak.id || id == Block.fencePlanksOakPainted.id;
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
		if (this.dropTorchIfCantStay(world, x, y, z)) {
			int i1 = world.getBlockMetadata(x, y, z);
			this.dropBlockWithCause(world, EnumDropCause.WORLD, x, y, z, i1, null);
			world.setBlockWithNotify(x, y, z, 0);
		}
	}

	private boolean dropTorchIfCantStay(World world, int i, int j, int k) {
		if (!this.canPlaceBlockAt(world, i, j, k)) {
			this.dropBlockWithCause(world, EnumDropCause.WORLD, i, j, k, world.getBlockMetadata(i, j, k), null);
			world.setBlockWithNotify(i, j, k, 0);
			return false;
		} else {
			return true;
		}
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (world.getBlockMetadata(x, y, z) == 0) {
			this.onBlockAdded(world, x, y, z);
		}

	}
@Override
	public int getBlockTexture(WorldSource blockAccess, int x, int y, int z, Side side) {
		return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "candle.png");
	}

	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		double random = (world.rand.nextInt(1) - Math.random());
		if (this.isActive) {
			if (rand.nextInt(2) == 0) {
				world.spawnParticle("smoke", x, y + random, z, 0.0, 0.0, 0.0);
				world.spawnParticle("flame", x, y + random, z, 0.0, 0.0, 0.0);
				if (rand.nextInt(2) == 0) {
					world.playSoundEffect(null, SoundCategory.ENTITY_SOUNDS, (double) x + 0.5, (double) y + 0.5, (double) z + 0.5, "stardew.bee", 0.2F, rand.nextFloat() * 0.4F + 0.8F);
				}
			}
		}
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(StardewBlocks.candle)};
	}
}
