package luke.stardew.mixin;

import luke.stardew.StardewMod;
import luke.stardew.blocks.BlockLeavesAppleFlowering;
import net.minecraft.client.render.RenderBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.world.WorldSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RenderBlocks.class, remap = false)
public abstract class RenderBlocksMixin {

	@Shadow
	private WorldSource blockAccess;

	@Shadow
	private int overrideBlockTexture;

	@Shadow
	public abstract boolean renderStandardBlock(Block block, int x, int y, int z);

	@Shadow
	public abstract boolean renderStandardBlock(Block block, int x, int y, int z, float r, float g, float b);

	@Unique
	private boolean renderLeavesAppleFlowering(BlockLeavesAppleFlowering block, int x, int y, int z) {
		int growthRate = (this.blockAccess.getBlockMetadata(x, y, z) & 240) >> 4;
		int[] overlay = StardewMod.floweringOverlayApple;
		int[] overlay1 = StardewMod.floweringOverlayFlower;
		this.renderStandardBlock(block, x, y, z);
		if (growthRate > 0) {
			this.overrideBlockTexture = (Block.texCoordToIndex(overlay[0], overlay[1]));
		} else {
			this.overrideBlockTexture = (Block.texCoordToIndex(overlay1[0], overlay1[1]));
		}

		this.renderStandardBlock(block, x, y, z, 1.0F, 1.0F, 1.0F);
		this.overrideBlockTexture = -1;
		return true;
	}

		@Inject(method = "renderBlockByRenderType", at = @At("HEAD"), cancellable = true)
		private void stardewRenderLeaves (Block block,int renderType, int x, int y, int z, CallbackInfoReturnable<Boolean > cir){
			if (renderType == 36)
				cir.setReturnValue(renderLeavesAppleFlowering((BlockLeavesAppleFlowering) block, x, y, z));
		}

	@Inject(method = "renderItemIn3d", at = @At("TAIL"), cancellable = true)
	private static void renderItemIn3d(int i, CallbackInfoReturnable<Boolean> cir) {
		if (i == 36) {
			cir.setReturnValue(true);
		}
		else cir.setReturnValue(true);
    }

}
