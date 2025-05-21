package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.animal.MobPig;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = MobPig.class, remap = false)
public class PigDigTruffleMixin extends MobAnimal {
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
		blockX = MathHelper.floor(this.x);
		blockY = MathHelper.floor(this.y);
		blockZ = MathHelper.floor(this.z);
		blockBelow = this.world.getBlock(blockX, blockY - 1, blockZ);
		if (!(blockBelow != Blocks.GRASS && blockBelow != Blocks.GRASS_RETRO && blockBelow != Blocks.DIRT && blockBelow != Blocks.MUD && blockBelow != Blocks.FARMLAND_DIRT || this.world.isClientSide)) {
			if (--this.timeUntilNextTruffle <= 0) {
				this.world.playBlockSoundEffect(null, (int)this.x, (int)this.y - 1, (int)this.z, this.world.getBlock((int)this.x, (int)this.y - 1, (int)this.z), EnumBlockSoundEffectType.MINE);
				this.dropItem(StardewBlocks.mushroomTruffle.id(), world.rand.nextInt(2) + 1);
				this.isMovementBlocked();
				this.world.setBlockWithNotify(blockX, blockY - 1, blockZ, Blocks.DIRT.id());
				this.timeUntilNextTruffle = this.random.nextInt(3000) + 3000;
			}
		}
	}

	@Override
	protected boolean isMovementBlocked() {
		return super.isMovementBlocked();
	}

}
