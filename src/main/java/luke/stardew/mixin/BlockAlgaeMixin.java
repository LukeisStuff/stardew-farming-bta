package luke.stardew.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicAlgae;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.IBonemealable;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Random;

@Mixin(value = BlockLogicAlgae.class, remap = false)
public abstract class BlockAlgaeMixin extends BlockLogic implements IBonemealable {
	@Unique
	public boolean canBeBonemealed = true;

	protected BlockAlgaeMixin(Block<?> block, Material material) {
		super(block, material);
	}

	@Override
	public boolean onBonemealUsed(ItemStack itemstack, Player player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		if (world.isClientSide || !this.canBeBonemealed) {
			return this.canBeBonemealed;
		}

		Random rand = world.rand;
		if (player.getGamemode().consumeBlocks()) {
			--itemstack.stackSize;
		}

		for (int i = 0; i < 128; ++i) {
			int x = blockX;
			int y = blockY;
			int z = blockZ;

			for (int step = 0; step < i / 16; ++step) {
				x += rand.nextInt(3) - 1;
				y += (rand.nextInt(3) - 1) * rand.nextInt(3) / 2;
				z += rand.nextInt(3) - 1;

				int belowId = world.getBlockId(x, y - 1, z);
				if (Blocks.blocksList[belowId] == null || !Blocks.blocksList[belowId].hasTag(BlockTags.IS_WATER)) {
					break;
				}
			}

			if (world.getBlockId(x, y, z) == 0 && rand.nextFloat() > 0.90f) {
				world.setBlockWithNotify(x, y, z, Blocks.ALGAE.id());
			}
		}

		return true;
	}
}
