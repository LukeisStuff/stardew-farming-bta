package luke.stardew.particles;

import luke.stardew.StardewMod;
import luke.stardew.blocks.BlockLogicLeavesSeasonal;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.client.entity.particle.ParticleLeaf;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicLeavesBase;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;

public class ParticleLeafStardew extends ParticleLeaf {
	public ParticleLeafStardew(World world, double x, double y, double z, double xa, double ya, double za) {
		super(world, x, y, z, xa, ya, za);

		init(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
	}

	@Override
	public ParticleLeaf init(int x, int y, int z) {
		if (world == null) return this;

		Block<?> block = world.getBlock(x, y, z);
		if (!Block.hasLogicClass(block, BlockLogicLeavesBase.class)) {
			this.remove();
		}

		if (block != null && block.getLogic() instanceof BlockLogicLeavesSeasonal) { // Very hacky awful mess I hate particles
			if (((BlockLogicLeavesSeasonal) block.getLogic()).getSapling() == StardewBlocks.SAPLING_APPLE) {
				tex = TextureRegistry.getTexture(StardewMod.MOD_ID + ":block/leaves_apple_fancy");
			}else {
				tex = TextureRegistry.getTexture(StardewMod.MOD_ID + ":block/leaves_apple_golden_fancy");
			}

		}

		return this; //Skip color fetching
	}
}
