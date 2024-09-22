package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.BlockFlower;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.IBonemealable;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;

import java.util.Random;

public class BlockCropsGrapeTop extends BlockFlower implements IBonemealable {

	public BlockCropsGrapeTop(String key, int id) {
		super(key, id);
		this.setTicking(true);
		this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
	}
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_FALL) {
			if (world.getBlockLightValue(x, y + 1, z) >= 9) {
				int l = world.getBlockMetadata(x, y, z);
				if (l < 2) {
					float f = this.getGrowthRate(world, x, y, z);
					if (rand.nextInt((int) (100.0F / f)) == 0) {
						++l;
						world.setBlockMetadataWithNotify(x, y, z, l);
					}
				}
			}
		}
	}

	public float getGrowthRate(World world, int x, int y, int z) {
		float growthRate = 10.0F;
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
				if (id == StardewBlocks.cropsGrapeBottom.id) {
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

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return (world.getBlockId(x, y-1, z) == StardewBlocks.cropsGrapeBottom.id);
	}

	@Override
	public boolean canThisPlantGrowOnThisBlockID(int i) {
		return i == StardewBlocks.cropsGrapeBottom.id;
	}

	public boolean onBlockRightClicked(World world, int x, int y, int z, EntityPlayer player, Side side, double xHit, double yHit) {
		int l = world.getBlockMetadata(x, y, z);
		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 1);
			world.playSoundAtEntity(player, player, "random.pop", 0.2F, 0.5F);
			world.dropItem(x, y, z, new ItemStack(StardewItems.grapes, world.rand.nextInt(1) + 2));
		}
		return false;
	}

	@Override
	public boolean onBonemealUsed(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		if (world.getBlockMetadata(blockX, blockY, blockZ) < 2) {
			if (!world.isClientSide) {
				((BlockCropsGrapeBottom) StardewBlocks.cropsGrapeBottom).fertilize(world, blockX, blockY - 1, blockZ);
				if (entityplayer.getGamemode().consumeBlocks()) {
					--itemstack.stackSize;
				}
			}

			return true;
		} else {
			return false;
		}
	}


	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		return meta != 2 ? new ItemStack[]{new ItemStack(StardewItems.seedsGrapes)} : new ItemStack[]{new ItemStack(StardewItems.seedsGrapes, world.rand.nextInt(1) + 2), new ItemStack(StardewItems.grapes)};
	}
}
