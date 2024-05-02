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
import turniplabs.halplibe.helper.TextureHelper;

import java.util.Random;

import static luke.stardew.StardewMod.MOD_ID;

public class BlockCropsCornTop extends BlockFlower implements IBonemealable {
	public final int[] growthStageTextures = new int[]{
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "corn_crop_top_1.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "corn_crop_top_2.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "corn_crop_top_3.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "corn_crop_top_4.png"),
	};

	public BlockCropsCornTop(String key, int id) {
		super(key, id);
		this.setTicking(true);
		this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
	}
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
	}
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return (world.getBlockId(x, y-1, z) == StardewBlocks.cropsCornBottom.id);
	}

	@Override
	public boolean canThisPlantGrowOnThisBlockID(int i) {
		return i == StardewBlocks.cropsCornBottom.id;
	}
	public void fertilize(World world, int i, int j, int k) {
		world.setBlockAndMetadataWithNotify(i, j, k, this.id,3);
	}
	@Override
	public int getBlockTextureFromSideAndMetadata(Side side, int j) {
		if (j < 0 || j > 3) {
			j = 3;
		}
		return this.growthStageTextures[j];
	}

	@Override
	public boolean onBonemealUsed(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		if (world.getBlockMetadata(blockX, blockY, blockZ) < 3) {
			if (!world.isClientSide) {
				((BlockCropsCornTop) StardewBlocks.cropsCornTop).fertilize(world, blockX, blockY, blockZ);
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
		return meta != 3 ? new ItemStack[]{new ItemStack(StardewItems.seedsCorn)} : new ItemStack[]{new ItemStack(StardewItems.seedsCorn, world.rand.nextInt(1) + 2), new ItemStack(StardewItems.corn, world.rand.nextInt(1) + 1)};
	}
}
