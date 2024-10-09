package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.animal.EntityAnimal;
import net.minecraft.core.entity.animal.EntityPig;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = EntityPig.class, remap = false)
public class PigDigTruffleMixin extends EntityAnimal {
	@Unique
	public int timeUntilNextTruffle = this.random.nextInt(3000) + 3000;
	public PigDigTruffleMixin(World world) {
		super(world);
	}


	public void onLivingUpdate() {
		Block blockBelow;
		int blockZ;
		int blockY;
		int blockX;
		super.onLivingUpdate();
		blockX = MathHelper.floor_double(this.x);
		blockY = MathHelper.floor_double(this.y);
		blockZ = MathHelper.floor_double(this.z);
		blockBelow = this.world.getBlock(blockX, blockY - 1, blockZ);
		if (!(blockBelow != Block.grass && blockBelow != Block.grassRetro && blockBelow != Block.dirt && blockBelow != Block.mud && blockBelow != Block.farmlandDirt || this.world.isClientSide)) {
			if (--this.timeUntilNextTruffle <= 0) {
				this.world.playSoundEffect(null, 2001, (int)this.x, (int)this.y - 1, (int)this.z, this.world.getBlockId((int)this.x, (int)this.y - 1, (int)this.z));
				this.spawnAtLocation(StardewBlocks.mushroomTruffle.id, world.rand.nextInt(2) + 1);
				this.isMovementBlocked();
				this.world.setBlockWithNotify(blockX, blockY - 1, blockZ, Block.dirt.id);
				this.timeUntilNextTruffle = this.random.nextInt(3000) + 3000;
			}
		}
	}

	@Override
	protected boolean isMovementBlocked() {
		return super.isMovementBlocked();
	}

}
