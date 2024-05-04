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
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;
import turniplabs.halplibe.helper.TextureHelper;

import java.util.Random;

import static luke.stardew.StardewMod.MOD_ID;

public class BlockCropsGrapeBottom extends BlockFlower implements IBonemealable {
	public final int[] growthStageTextures = new int[]{
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "grape_crop_bottom_1.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "grape_crop_bottom_2.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "grape_crop_bottom_3.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "grape_crop_bottom_4.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "grape_crop_bottom_5.png"),
	};

	public BlockCropsGrapeBottom(String key, int id) {
		super(key, id);
		this.setTicking(true);
		this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
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

	public boolean canThisPlantGrowOnThisBlockID(int i) {
		return i == Block.farmlandDirt.id;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		if(world.getBlockMetadata(x, y, z) == 0){
			return super.canBlockStay(world, x ,y, z);
		}
		if(world.getBlockId(x, y+1, z) == StardewBlocks.cropsGrapeTop.id && world.getBlockMetadata(x,y,z) >= 0){
			return super.canBlockStay(world, x ,y, z);
		}
		return true;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_FALL) {
			if (world.getBlockLightValue(x, y + 1, z) >= 9) {
				int l = world.getBlockMetadata(x, y, z);
				if (l < 4) {
					float f = this.getGrowthRate(world, x, y, z);
					if (rand.nextInt((int) (100.0F / f)) == 0) {
						++l;
						world.setBlockMetadataWithNotify(x, y, z, l);
					}
				}
			}
			if (world.getBlockMetadata(x, y, z) == 3) {
				world.setBlockAndMetadataWithNotify(x, y + 1, z, StardewBlocks.cropsGrapeTop.id, 0);
			}
		}
	}


	public void fertilize(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, 4);
		int blockAbove = world.getBlockId(x, y + 1, z);
		if (blockAbove == 0 || blockAbove == StardewBlocks.cropsGrapeTop.id) {
			world.setBlockAndMetadataWithNotify(x, y + 1, z, StardewBlocks.cropsGrapeTop.id, 3);
		}
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(Side side, int meta) {
		if (meta < 0 || meta > 4) {
			meta = 4;
		}
		return this.growthStageTextures[meta];
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		return meta != 4 ? new ItemStack[]{new ItemStack(StardewItems.seedsGrapes)} : new ItemStack[]{new ItemStack(StardewItems.seedsGrapes, world.rand.nextInt(1) + 2), new ItemStack(StardewItems.grapes, world.rand.nextInt(1) + 3)};
	}

	@Override
	public boolean onBonemealUsed(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		if (world.getBlockMetadata(blockX, blockY, blockZ) < 4) {
			if (!world.isClientSide) {
				((BlockCropsGrapeBottom) StardewBlocks.cropsGrapeBottom).fertilize(world, blockX, blockY, blockZ);
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
