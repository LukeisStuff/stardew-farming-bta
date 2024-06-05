package luke.stardew.blocks;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;
import turniplabs.halplibe.helper.TextureHelper;

import java.util.Random;

import static luke.stardew.StardewMod.MOD_ID;

public class BlockLeavesAppleGoldenFlowering extends BlockLeavesAppleGolden {
	public final int[] growthStageTextures = new int[]{
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleGoldenFlowering.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleGoldenFloweringFast.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleGoldenGrown.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleGoldenGrownFast.png"),
	};

	public BlockLeavesAppleGoldenFlowering(String key, int id) {
		super(key, id);
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		int growthRate = (meta & 240) >> 4;
		if (dropCause != EnumDropCause.PICK_BLOCK && dropCause != EnumDropCause.SILK_TOUCH) {
			return growthRate == 0 ? null : new ItemStack[]{new ItemStack(Item.foodAppleGold, world.rand.nextInt(1) + 1)};
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
				return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleGoldenFloweringFast.png");
			}
			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleGoldenFlowering.png");
		}
		else if (!fancyGraphics) {
			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleGoldenGrownFast.png");
		}
		return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "leavesAppleGoldenGrown.png");
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
			world.scheduleBlockUpdate(x, y, z, StardewBlocks.leavesAppleGoldenFlowering.id, this.tickRate());
			return true;
		} else {
			return super.blockActivated(world, x, y, z, player);
		}
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		int meta = world.getBlockMetadata(x, y, z);
		int growthRate = (meta & 240) >> 4;
		if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_WINTER) {
			if (rand.nextInt(80) == 0 && growthRate == 0) {
				world.setBlockMetadataWithNotify(x, y, z, 16 | meta);
				world.scheduleBlockUpdate(x, y, z, StardewBlocks.leavesAppleGoldenFlowering.id, this.tickRate());
			}
		} else if (growthRate > 0) {
			world.setBlockMetadataWithNotify(x, y, z, meta & 15);
			world.scheduleBlockUpdate(x, y, z, StardewBlocks.leavesAppleGoldenFlowering.id, this.tickRate());
		}

	}

}
