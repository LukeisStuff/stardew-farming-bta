package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

import java.util.Random;

public class BlockPlantStake extends Block {
	public BlockPlantStake(String key, int id, Material material) {
		super(key, id, material);
		this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		this.func_268_h(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
		func_268_h(world, x, y, z);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return world.getBlockId(x, y - 1, z) == farmlandDirt.id;
	}

	protected final void func_268_h(World world, int i, int j, int k) {
		if (!this.canBlockStay(world, i, j, k)) {
			this.dropBlockWithCause(world, EnumDropCause.WORLD, i, j, k, world.getBlockMetadata(i, j, k), null);
			world.setBlockWithNotify(i, j, k, 0);
		}

	}

	@Override
	public boolean onBlockRightClicked(World world, int x, int y, int z, EntityPlayer player, Side side, double xHit, double yHit) {
		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == StardewItems.seedsGrapes.id && world.getBlockId(x, y - 1, z) == farmlandDirt.id) {
			player.getCurrentEquippedItem().consumeItem(player);
			world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.cropsGrapeBottom.id, 0);
			player.swingItem();
			world.playBlockSoundEffect(player, (float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F, StardewBlocks.cropsGrapeBottom, EnumBlockSoundEffectType.PLACE);
		}
		return super.onBlockRightClicked(world, x, y, z, player, side, xHit, yHit);
	}

	@Override
	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(Item.stick)};
	}

	public AABB getCollisionBoundingBoxFromPool(WorldSource world, int x, int y, int z) {
		return null;
	}

	public boolean isSolidRender() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
}
