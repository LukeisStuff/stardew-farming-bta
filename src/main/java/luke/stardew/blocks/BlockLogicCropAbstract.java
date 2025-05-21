package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFlower;
import net.minecraft.core.item.IBonemealable;

public abstract class BlockLogicCropAbstract  extends BlockLogicFlower implements IBonemealable {
	public BlockLogicCropAbstract(Block<?> block) {
		super(block);
	}
	/*
	private float fertilizedRate;
	private int maxGrowth;
	private boolean canFertilize;
	private Item seedItem;
	private Item cropItem;
	private int seedMin;
	private int seedCount;
	private int resetMeta = -1;
	private Block<?> growsInto;
	private boolean canHarvest;

	public BlockLogicCropAbstract(Block<?> block) {
		super(block);
		block.setTicking(true);
		this.fertilizedRate = 1.0F;
		this.maxGrowth = 5;
		this.canFertilize = true;
		this.canHarvest = true;
		this.seedMin = 1;
		this.seedCount = 1;
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
	}

	public BlockLogicCropAbstract withGrowth(int maxGrowth) {
		this.maxGrowth = maxGrowth;
		return this;
	}
	public BlockLogicCropAbstract withSeed(Item seedItem, int seedMin, int seedCount) {
		this.seedItem = seedItem;
		this.seedMin = seedMin;
		this.seedCount = seedCount + 1;
		return this;
	}

	public BlockLogicCropAbstract withCrop(Item cropItem) {
		this.cropItem = cropItem;
		return this;
	}

	public BlockLogicCropAbstract withFertilizedRate(float fertilizedRate) {
		this.fertilizedRate = fertilizedRate;
		return this;
	}

	public BlockLogicCropAbstract withResetMeta(int resetMeta) {
		this.resetMeta = resetMeta;
		return this;
	}

	public BlockLogicCropAbstract notFertilized() {
		this.canFertilize = false;
		return this;
	}

	public BlockLogicCropAbstract growsInto(Block<?> block) {
		this.growsInto = block;
		return this;
	}

	public BlockLogicCropAbstract noHarvest() {
		this.canHarvest = false;
		return this;
	}

	public abstract void onGrowth(World world, int x, int y, int z, int meta);

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
						if (this.growsInto != null && meta >= this.maxGrowth) {
							world.setBlockAndMetadataWithNotify(x, y, z, this.growsInto.id(), 0);
						}else {
							world.setBlockMetadataWithNotify(x, y, z, meta);
						}
					}
				}
			}
		}
	}

	@Override
	public boolean onBonemealUsed(ItemStack stack, Player player, World world, int x, int y, int z, Side side, double xPlaced, double yPlaced) {
		if (world.getBlockMetadata(x, y, z) < this.maxGrowth) {
			if (!world.isClientSide) {
				this.onGrowth(world, x, y, z, this.maxGrowth);
				if (player.getGamemode().consumeBlocks()) {
					stack.stackSize--;
				}
			}
			return true;
		} else {
			return false;
		}
	}*/
}
