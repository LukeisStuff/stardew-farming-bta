package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockFlower;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.IBonemealable;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;
import turniplabs.halplibe.helper.TextureHelper;

import java.util.Random;

import static luke.stardew.StardewMod.MOD_ID;

public class BlockCropsWatermelon extends BlockFlower implements IBonemealable {
	public static final int[] GROWTH_STAGE_TEXTURES_TOP = new int[]{
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "watermelon_crop_4.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "watermelon_crop_3.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "watermelon_crop_2.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "watermelon_crop_1.png")
	};
	public static final int[] GROWTH_STAGE_TEXTURES_SIDE = new int[]{
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "watermelon_crop_side_4.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "watermelon_crop_side_3.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "watermelon_crop_side_2.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "watermelon_crop_side_1.png")
	};
	public static final int LEAF_TEXTURE = TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "watermelon_crop_leaf.png");

	public BlockCropsWatermelon(String key, int id) {
		super(key, id);
	}

	public void setBlockBoundsBasedOnState(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		float onePix = 0.0625F;
		float size = 0.0F;
		if (meta == 0) {
			size = 6.0F * onePix;
		} else if (meta == 1) {
			size = 8.0F * onePix;
		} else if (meta == 2) {
			size = 10.0F * onePix;
		} else if (meta == 3) {
			size = 12.0F * onePix;
		} else if (meta == 4) {
			size = 14.0F * onePix;
		}

		this.setBlockBounds(0.5F - size / 2.0F, 0.0F, 0.5F - size / 2.0F, 0.5F + size / 2.0F, size, 0.5F + size / 2.0F);
	}

	protected boolean canThisPlantGrowOnThisBlockID(int i) {
		return i == Block.farmlandDirt.id;
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_SUMMER) {
			if (world.getBlockLightValue(x, y + 1, z) >= 9) {
				int meta = world.getBlockMetadata(x, y, z);
				if (meta < 6) {
					float f = this.getGrowthRate(world, x, y, z);
					if (rand.nextInt((int) (100.0F / f)) == 0) {
						++meta;
						if (meta == 5) {
							world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.watermelon.id, 0);
						} else {
							world.setBlockMetadataWithNotify(x, y, z, meta);
						}
					}
				}
			}
		}
	}

	public void fertilize(World world, int x, int y, int z) {
		world.setBlockWithNotify(x, y, z, StardewBlocks.watermelon.id);
	}

	private float getGrowthRate(World world, int x, int y, int z) {
		float growthRate = 1.0F;
		int idNegZ = world.getBlockId(x, y, z - 1);
		int idPosZ = world.getBlockId(x, y, z + 1);
		int idNegX = world.getBlockId(x - 1, y, z);
		int idPosX = world.getBlockId(x + 1, y, z);
		int idNegXNegZ = world.getBlockId(x - 1, y, z - 1);
		int idPosXNegZ = world.getBlockId(x + 1, y, z - 1);
		int idPosXPosZ = world.getBlockId(x + 1, y, z + 1);
		int idNegXPosZ = world.getBlockId(x - 1, y, z + 1);
		boolean xNeighbor = idNegX == this.id || idPosX == this.id;
		boolean zNeighbor = idNegZ == this.id || idPosZ == this.id;
		boolean diagNeighbor = idNegXNegZ == this.id || idPosXNegZ == this.id || idPosXPosZ == this.id || idNegXPosZ == this.id;

		for(int dx = x - 1; dx <= x + 1; ++dx) {
			for(int dz = z - 1; dz <= z + 1; ++dz) {
				int id = world.getBlockId(dx, y - 1, dz);
				float growthRateMod = 0.0F;
				if (id == Block.farmlandDirt.id) {
					growthRateMod = 1.0F;
					if (world.getBlockMetadata(dx, y - 1, dz) > 0) {
						growthRateMod = 3.0F;
					}
				}

				if (dx != x || dz != z) {
					growthRateMod /= 4.0F;
				}

				growthRate += growthRateMod;
			}
		}

		if (diagNeighbor || xNeighbor && zNeighbor) {
			growthRate /= 2.0F;
		}

		if (world.seasonManager.getCurrentSeason() != null) {
			growthRate *= world.seasonManager.getCurrentSeason().cropGrowthFactor;
		}

		return growthRate;
	}

	public int getBlockTextureFromSideAndMetadata(Side side, int data) {
		if (data < 1 || data > 4) {
			data = 1;
		}

		return side != Side.TOP && side != Side.BOTTOM ? GROWTH_STAGE_TEXTURES_SIDE[data - 1] : GROWTH_STAGE_TEXTURES_TOP[data - 1];
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(StardewItems.seedsWatermelon, 1)};
	}

	public AABB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		this.setBlockBoundsBasedOnState(world, x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 0 ? null : AABB.getBoundingBoxFromPool((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
	}

	public boolean onBonemealUsed(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		if (world.getBlockMetadata(blockX, blockY, blockZ) < 5) {
			if (!world.isClientSide) {
				((BlockCropsWatermelon)StardewBlocks.cropsWatermelon).fertilize(world, blockX, blockY, blockZ);
				if (entityplayer.getGamemode().consumeBlocks()) {
					--itemstack.stackSize;
				}
			}

			return true;
		} else {
			return false;
		}
	}
}
