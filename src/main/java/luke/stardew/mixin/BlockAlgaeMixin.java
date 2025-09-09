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

	public BlockAlgaeMixin(Block<?> block, Material material) {
		super(block, material);
	}

	@Override
	public boolean onBonemealUsed(ItemStack itemstack, Player player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		Random rand = world.rand;
		if (!world.isClientSide && this.canBeBonemealed) {
			if (player.getGamemode().consumeBlocks()) {
				--itemstack.stackSize;
			}

			label39:
			for (int j1 = 0; j1 < 128; ++j1) {
				int k1 = blockX;
				int l1 = blockY;
				int i2 = blockZ;

				for (int j2 = 0; j2 < j1 / 16; ++j2) {
					k1 += rand.nextInt(3) - 1;
					l1 += (rand.nextInt(3) - 1) * rand.nextInt(3) / 2;
					i2 += rand.nextInt(3) - 1;
					int id1 = world.getBlockId(k1, l1 - 1, i2);
					if (Blocks.blocksList[id1] == null || !Blocks.blocksList[id1].hasTag(BlockTags.IS_WATER)) {
						continue label39;
					}
				}

				if (world.getBlockId(k1, l1, i2) == 0 && (double) rand.nextFloat() > 0.90) {
					world.setBlockWithNotify(k1, l1, i2, Blocks.ALGAE.id());
				}
			}

			return true;
		} else {
			return this.canBeBonemealed;
		}
	}
}
