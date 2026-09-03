package luke.stardew.model;

import luke.stardew.blocks.BlockLogicLeavesSeasonalFlowering;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.client.render.block.model.generic.BlockModelGenericLeaves;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.models.block.StaticBlockModel;


public class BlockModelLeavesFlowering<T extends BlockLogic> extends BlockModelGenericLeaves<T> {
    public final @NotNull StaticBlockModel overlay;
    public final @NotNull StaticBlockModel overlayFlowering;

    public BlockModelLeavesFlowering(
        @NotNull Block<T> block,
        @NotNull String base,
        @NotNull String overlay,
        @NotNull String overlayFlowering
    ) {
        super(block, base);

        this.overlay = BlockModelDispatcher.loadDataModel(overlay).asModel();
        this.overlayFlowering = BlockModelDispatcher.loadDataModel(overlayFlowering).asModel();
    }

    public void renderStandalone(@NotNull TessellatorGeneral tessellator, int metadata, byte lightIndex) {
        super.renderStandalone(tessellator, metadata, lightIndex);
        this.overlay.renderStandalone(this, tessellator, 0.0F, 0.0F, 0.0F, metadata, lightIndex, BlockColorDispatcher.getInstance().getDispatch(this.block));
    }

    public boolean renderAttached(@NotNull TessellatorGeneral tessellator, @NotNull WorldSource worldSource, @NotNull TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
        boolean didRender = super.renderAttached(tessellator, worldSource, tilePos, cullFaces, overrideTexture);
        int growthRate = BlockLogicLeavesSeasonalFlowering.getGrowthRate(worldSource.getBlockData(tilePos));

        if (growthRate > 0) {
            didRender |= this.overlay.renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
        } else {
            didRender |= this.overlayFlowering.renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
        }

        return didRender;
    }
}
