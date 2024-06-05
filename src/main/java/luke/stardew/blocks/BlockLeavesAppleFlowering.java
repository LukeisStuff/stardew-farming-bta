package luke.stardew.blocks;

import java.util.Random;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.IBonemealable;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;
import turniplabs.halplibe.helper.TextureHelper;

import static luke.stardew.StardewMod.MOD_ID;

public class BlockLeavesAppleFlowering extends BlockLeavesApple implements IBonemealable {
	public final int[] growthStageTextures = new int[]{
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleFlowering.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleFloweringFast.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleGrown.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleGrownFast.png"),
	};

	public BlockLeavesAppleFlowering(String key, int id) {
		super(key, id);
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		int growthRate = (meta & 240) >> 4;
		if (dropCause != EnumDropCause.PICK_BLOCK && dropCause != EnumDropCause.SILK_TOUCH) {
			return growthRate == 0 ? null : new ItemStack[]{new ItemStack(Item.foodApple, world.rand.nextInt(1) + 1)};
		} else {
			return new ItemStack[]{new ItemStack(this)};
		}
	}

	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		this.blockActivated(world, x, y, z, player);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(Side side, int meta) {
		if (meta < 16) {
			if (!fancyGraphics) {
				return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleFloweringFast.png");
			}
			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleFlowering.png");
		}
		else if (!fancyGraphics) {
			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleGrownFast.png");
		}
        return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleGrown.png");
    }

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		int meta = world.getBlockMetadata(x, y, z);
		int decayData = meta & 15;
		int growthRate = (meta & 240) >> 4;
		if (growthRate > 0) {
			world.playSoundAtEntity(player, player, "random.pop", 0.2F, 0.5F);
			if (!world.isClientSide) {
				this.dropBlockWithCause(world, EnumDropCause.WORLD, x, y, z, meta, null);
			}

			world.setBlockMetadataWithNotify(x, y, z, decayData);
			world.scheduleBlockUpdate(x, y, z, StardewBlocks.leavesAppleFlowering.id, this.tickRate());
			return true;
		} else {
			return super.blockActivated(world, x, y, z, player);
		}
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		int meta = world.getBlockMetadata(x, y, z);
		int growthRate = (meta & 240) >> 4;
		if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_FALL) {
			if (rand.nextInt(20) == 0 && growthRate == 0) {
				world.setBlockMetadataWithNotify(x, y, z, 16 | meta);
				world.scheduleBlockUpdate(x, y, z, StardewBlocks.leavesAppleFlowering.id, this.tickRate());
			}
		} else if (growthRate > 0) {
			world.setBlockMetadataWithNotify(x, y, z, meta & 15);
			world.scheduleBlockUpdate(x, y, z, StardewBlocks.leavesAppleFlowering.id, this.tickRate());
		}

	}

	public boolean onBonemealUsed(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		int meta = world.getBlockMetadata(blockX, blockY, blockZ);
		if ((meta & 240) >> 4 == 0) {
			if (!world.isClientSide) {
				if (world.seasonManager.getCurrentSeason() != Seasons.OVERWORLD_FALL) {
					return true;
				}

				world.setBlockMetadataWithNotify(blockX, blockY, blockZ, 16 | meta);
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
