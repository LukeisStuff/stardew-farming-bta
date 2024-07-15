package luke.stardew.blocks;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;

import java.util.Random;

import static luke.stardew.blocks.StardewBlocks.leavesAppleFlowering;
import static luke.stardew.blocks.StardewBlocks.leavesAppleGoldenFlowering;

public class BlockLeavesAppleGoldenFlowering extends BlockLeavesAppleGolden {

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

	public void onBlockLeftClicked(World world, int x, int y, int z, EntityPlayer player, Side side, double xHit, double yHit) {
		this.onBlockRightClicked(world, x, y, z, player, null, 0.0, 0.0);
	}

	public boolean onBlockRightClicked(World world, int x, int y, int z, EntityPlayer player, Side side, double xPlaced, double yPlaced) {
		int meta = world.getBlockMetadata(x, y, z);
		int decayData = meta & 15;
		int growthRate = (meta & 240) >> 4;
		if (growthRate > 0) {
			world.playSoundAtEntity(player, player, "random.pop", 0.2F, 0.5F);
			if (!world.isClientSide) {
				this.dropBlockWithCause(world, EnumDropCause.WORLD, x, y, z, meta, null);
			}

			world.setBlockMetadataWithNotify(x, y, z, decayData);
			world.scheduleBlockUpdate(x, y, z, leavesAppleGoldenFlowering.id, this.tickRate());
			return true;
		} else {
			return super.onBlockRightClicked(world, x, y, z, player, side, xPlaced, yPlaced);
		}
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		int meta = world.getBlockMetadata(x, y, z);
		int growthRate = (meta & 240) >> 4;
		if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_WINTER) {
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
