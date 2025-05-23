package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicFarmland;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import org.spongepowered.asm.mixin.Unique;

import java.util.Random;

public class BlockLogicPlantStake extends BlockLogic {
	public BlockLogicPlantStake(Block<?> block, Material material) {
		super(block, material);
		this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		this.checkSupport(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
		this.checkSupport(world, x, y, z);
	}

	@Unique
	public void checkSupport(World world, int x, int y, int z) {
		if (!this.canBlockStay(world, x, y, z)) {
			world.setBlockAndMetadataWithNotify(x, y, z, 0, 0);
			this.dropBlockWithCause(world, EnumDropCause.WORLD, x, y, z, world.getBlockMetadata(x, y, z), null, null);
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return world.getBlockLogic(x, y - 1, z, BlockLogicFarmland.class) != null;
	}

	@Override
	public boolean canPlaceOnSurfaceOfBlock(World world, int x, int y, int z) {
		return world.getBlockLogic(x, y, z, BlockLogicFarmland.class) != null;
	}

	@Override
	public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xHit, double yHit) {
		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == StardewItems.seedsGrapes.id && world.getBlockId(x, y - 1, z) == Blocks.FARMLAND_DIRT.id()) {
			player.getCurrentEquippedItem().consumeItem(player);
			world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.cropsGrapeBottom.id(), 0);
			player.swingItem();
			world.playBlockSoundEffect(player, (float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F, StardewBlocks.cropsGrapeBottom, EnumBlockSoundEffectType.PLACE);
		}
		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == StardewItems.beansCoffee.id && world.getBlockId(x, y - 1, z) == Blocks.FARMLAND_DIRT.id()) {
			player.getCurrentEquippedItem().consumeItem(player);
			world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.cropsBeansBottom.id(), 0);
			player.swingItem();
			world.playBlockSoundEffect(player, (float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F, StardewBlocks.cropsBeansBottom, EnumBlockSoundEffectType.PLACE);
		}
		return super.onBlockRightClicked(world, x, y, z, player, side, xHit, yHit);
	}

	@Override
	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(Items.STICK)};
	}

	@Override
	public AABB getCollisionBoundingBoxFromPool(WorldSource world, int x, int y, int z) {
		return null;
	}

	@Override
	public boolean isSolidRender() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlockOnCondition(WorldSource world, int x, int y, int z) {
		return false;
	}
}
