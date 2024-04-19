package luke.stardew.mixin;

import luke.stardew.StardewMod;
import luke.stardew.blocks.BlockLeavesAppleFlowering;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.client.Minecraft;
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
public class RenderBlocksMixin {

	@Shadow private WorldSource blockAccess;

	@Shadow private int overrideBlockTexture;

	@Unique
	private final Minecraft mc = Minecraft.getMinecraft(this);

	@Unique
	private boolean renderLeavesAppleFlowering(BlockLeavesAppleFlowering block, int x, int y, int z) {
		int meta = this.blockAccess.getBlockMetadata(x, y, z);
		RenderBlocks renderBlocks = (RenderBlocks)(Object)this;
		renderBlocks.renderStandardBlock(block, x, y, z);
		if (meta == 1) {
			int[] overlay = StardewMod.floweringOverlay;
			this.overrideBlockTexture = (Block.texCoordToIndex(overlay[0], overlay[1]));
			if (mc.isAmbientOcclusionEnabled()) {
				renderBlocks.renderStandardBlock(block, x, y, z, 1.0F, 1.0F, 1.0F);
			} else {
				renderBlocks.renderStandardBlock(block, x, y, z, 1.0F, 1.0F, 1.0F);
			}
			this.overrideBlockTexture = -1;
		}
		return true;
	}

	@Inject(method = "renderBlockByRenderType", at = @At("HEAD"), cancellable = true)
	private void stardewRenderLeaves(Block block, int renderType, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
		if (renderType == 36)
			cir.setReturnValue(renderLeavesAppleFlowering((BlockLeavesAppleFlowering) block, x, y, z));
	}


}
