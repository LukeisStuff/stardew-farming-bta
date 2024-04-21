package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.BlockRotatableHorizontal;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;

import java.util.Objects;
import java.util.Random;

public class BlockBeehive extends BlockRotatableHorizontal {
	protected final boolean isActive;

	public BlockBeehive(String key, int id, boolean flag) {
		super(key, id, Material.wood);
		this.setTicking(true);
		this.isActive = flag;
	}

	public void onBlockAdded(World world, int x, int y, int z) {
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		switch (dropCause) {
			case PICK_BLOCK:
			case EXPLOSION:
			case PROPER_TOOL:
			case SILK_TOUCH:
				return new ItemStack[]{new ItemStack(StardewBlocks.beehiveIdle)};
			default:
				return null;
		}
	}


	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (world.seasonManager.getCurrentSeason() != Seasons.OVERWORLD_WINTER) {
			int l = world.getBlockMetadata(x, y, z);
			if (rand.nextInt(100) == 0) {
				world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.beehiveHoney.id, l);
			}
		}
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		int l = world.getBlockMetadata(x, y, z);
		if (this.isActive) {
			if  (player.getHeldItem() != null && player.getHeldItem().getItem() == Item.jar) {
				player.getHeldItem().consumeItem(player);
				world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.beehiveIdle.id, l);
				world.playSoundAtEntity(player, player, "random.pop", 0.2F, 0.5F);
				player.inventory.insertItem(new ItemStack(StardewItems.jarHoney, 1), true);
				return true;
			}
		}
		return false;
	}

}
