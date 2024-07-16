package luke.stardew.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockAlgae;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.IBonemealable;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Random;

@Mixin(value= BlockAlgae.class,remap=false)
public class BlockAlgaeMixin extends Block implements IBonemealable {
	@Unique
	public boolean canBeBonemealed = true;

	public BlockAlgaeMixin(String key, int id, Material material) {
		super(key, id, material);
	}

	@Override
	public boolean onBonemealUsed(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		Random rand = world.rand;
		if (!world.isClientSide && this.canBeBonemealed) {
			if (entityplayer.getGamemode().consumeBlocks()) {
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
					if (Block.blocksList[id1] == null || !Block.blocksList[id1].hasTag(BlockTags.IS_WATER)) {
						continue label39;
					}
				}

				if (world.getBlockId(k1, l1, i2) == 0 && (double) rand.nextFloat() > 0.75) {
					world.setBlockWithNotify(k1, l1, i2, Block.algae.id);
				}
			}

			return true;
		} else {
			return this.canBeBonemealed;
		}
	}
}
