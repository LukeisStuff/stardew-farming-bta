package luke.stardew.blocks;

import luke.stardew.misc.Range;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFarmland;
import net.minecraft.core.block.BlockLogicFlower;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.IBonemealable;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockLogicCropBase extends BlockLogicFlower implements IBonemealable {
	protected float fertilizedRate;
	protected int maxGrowth;
	protected boolean canFertilize;
	protected Item seedItem = Items.SEEDS_WHEAT;
	protected Item cropItem;
	protected Range seedRange = Range.EMPTY;
	protected Range cropRange = Range.ONE;
	protected int resetMeta = -1;
	protected Block<?> growsInto;
	protected boolean canHarvest;

	public BlockLogicCropBase(Block<?> block) {
		super(block);
		block.setTicking(true);
		this.fertilizedRate = 1.0F;
		this.maxGrowth = 5;
		this.canFertilize = true;
		this.canHarvest = true;
		this.setBlockBounds(0, 0.0F, 0, 1, 0.25F, 1);
	}
	public BlockLogicCropBase withGrowth(int maxGrowth) {
		this.maxGrowth = maxGrowth;
		return this;
	}
	public BlockLogicCropBase withSeed(Item seedItem, int seedMin, int seedMax) {
		this.seedItem = seedItem;
		this.seedRange = new Range(seedMin, seedMax);
		return this;
	}

	public BlockLogicCropBase withCrop(Item cropItem, int seedMin, int seedMax) {
		this.cropItem = cropItem;
		this.cropRange = new Range(seedMin, seedMax);
		return this;
	}

	public BlockLogicCropBase withCrop(Item cropItem) {
		this.cropItem = cropItem;
		this.cropRange = Range.ONE;
		return this;
	}

	public BlockLogicCropBase withFertilizedRate(float fertilizedRate) {
		this.fertilizedRate = fertilizedRate;
		return this;
	}

	public BlockLogicCropBase withResetMeta(int resetMeta) {
		this.resetMeta = resetMeta;
		return this;
	}

	public BlockLogicCropBase notFertilized() {
		this.canFertilize = false;
		return this;
	}

	public BlockLogicCropBase growsInto(Block<?> block) {
		this.growsInto = block;
		return this;
	}

	public BlockLogicCropBase noHarvest() {
		this.canHarvest = false;
		return this;
	}

	@Override
	protected boolean mayPlaceOn(int blockId) {
		return Blocks.FARMLAND_DIRT.id() == blockId;
	}

	public void fertilize(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, this.maxGrowth);
	}

	public void onGrowth(World world, int x, int y, int z, int meta) {
		if (this.growsInto != null && meta >= this.maxGrowth) {
			world.setBlockAndMetadataWithNotify(x, y, z, this.growsInto.id(), 0);
		}else {
			world.setBlockMetadataWithNotify(x, y, z, meta);
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_SUMMER) {
			if (world.getBlockLightValue(x, y + 1, z) >= 9) {
				int meta = world.getBlockMetadata(x, y, z);
				if (meta++ < this.maxGrowth) {
					float growthRate = this.getGrowthRate(world, x, y, z);
					if (rand.nextInt((int) (100.0F / growthRate)) == 0) {
						this.onGrowth(world, x, y, z, meta);
					}
				}
			}
		}
	}

	public float getGrowthRate(World world, int x, int y, int z) {
		float growthRate = 1.0F;
		int idNegZ = world.getBlockId(x, y, z - 1);
		int idPosZ = world.getBlockId(x, y, z + 1);
		int idNegX = world.getBlockId(x - 1, y, z);
		int idPosX = world.getBlockId(x + 1, y, z);
		int idNegXNegZ = world.getBlockId(x - 1, y, z - 1);
		int idPosXNegZ = world.getBlockId(x + 1, y, z - 1);
		int idPosXPosZ = world.getBlockId(x + 1, y, z + 1);
		int idNegXPosZ = world.getBlockId(x - 1, y, z + 1);
		boolean xNeighbor = idNegX == this.id() || idPosX == this.id();
		boolean zNeighbor = idNegZ == this.id() || idPosZ == this.id();
		boolean diagNeighbor = idNegXNegZ == this.id() || idPosXNegZ == this.id() || idPosXPosZ == this.id() || idNegXPosZ == this.id();

		for(int dx = x - 1; dx <= x + 1; ++dx) {
			for(int dz = z - 1; dz <= z + 1; ++dz) {
				int id = world.getBlockId(dx, y - 1, dz);
				float growthRateMod = 0.0F;
				if (id == Blocks.FARMLAND_DIRT.id()) {
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

		if (this.canFertilize) {
			boolean isFertilized = BlockLogicFarmland.isFertilized(world.getBlockMetadata(x, y - 1, z));
			if (isFertilized) growthRate *= this.fertilizedRate;
		} else {
			if (world.getSeasonManager().getCurrentSeason() != null) {
				growthRate *= world.getSeasonManager().getCurrentSeason().cropGrowthFactor;
			}
		}

		return growthRate;
	}

	public List<ItemStack> getDrops(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		List<ItemStack> drops = new ArrayList<>();
		if (dropCause == EnumDropCause.PICK_BLOCK) {
			if (this.seedItem != null) {
				drops.add(new ItemStack(this.seedItem, 1));
            }else {
				ItemStack stack = new ItemStack(Items.SEEDS_WHEAT, 1);
				drops.add(stack);
				stack.setCustomName("If you somehow got this item, this is a bug [" + this.namespaceId() + "]");
            }
            return drops;
        }

		if (meta < maxGrowth) {
			if (this.seedItem != null) drops.add(new ItemStack(this.seedItem));
		}else {
			if (this.seedItem != null) drops.add(new ItemStack(this.seedItem, this.seedRange.get(world.rand)));
			if (this.cropItem != null) drops.add(new ItemStack(this.cropItem, this.cropRange.get(world.rand)));
		}
		return drops;
	}

	@Override
	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		return getDrops(world, dropCause, x, y, z, meta, tileEntity).toArray(new ItemStack[]{});
	}

	public boolean onBonemealUsed(ItemStack stack, Player player, World world, int x, int y, int z, Side side, double xPlaced, double yPlaced) {
		if (world.getBlockMetadata(x, y, z) < this.maxGrowth) {
			if (!world.isClientSide) {
				this.onGrowth(world, x, y, z, this.maxGrowth);
				if (player.getGamemode().consumeBlocks()) {
					stack.stackSize--;
				}
			}
			return true;
		}

		return false;
	}

	public void onHarvest(World world, int x, int y, int z, int meta) {
		world.setBlockAndMetadataWithNotify(x, y, z, 0, 0);
	}

	@Override
	public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xHit, double yHit) {
		if (!this.canHarvest) return false;
		int meta = world.getBlockMetadata(x, y, z);
		if (meta >= this.maxGrowth) {
			if (this.resetMeta < 0) {
				onHarvest(world, x, y, z, meta);
			}else {
				onGrowth(world, x, y, z, this.resetMeta);
			}

			world.playSoundEffect(player, SoundCategory.WORLD_SOUNDS, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.pop", 0.3F, 1.0f);
			if (!world.isClientSide) {
				if(this.cropItem != null) world.dropItem(x, y, z, new ItemStack(this.cropItem, this.cropRange.get(world.rand)));
				if(this.seedItem != null) world.dropItem(x, y, z, new ItemStack(this.seedItem, this.seedRange.get(world.rand)));
			}
			return true;
		}
		return false;
	}
}
