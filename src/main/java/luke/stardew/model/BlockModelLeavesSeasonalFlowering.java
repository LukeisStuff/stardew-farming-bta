package luke.stardew.model;

import luke.stardew.blocks.BlockLogicLeavesSeasonalFlowering;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGenericLeaves;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import org.useless.dragonfly.models.block.StaticBlockModel;

@Environment(EnvType.CLIENT)
public class BlockModelLeavesSeasonalFlowering<T extends BlockLogic> extends BlockModelGenericLeaves<T> {
    private final @NonNull StaticBlockModel overlay;
    private final @NonNull StaticBlockModel overlayFlowering;

    public BlockModelLeavesSeasonalFlowering(@NonNull Block<T> block, String baseTexturePath, String overlayName) {
        super(block, baseTexturePath);
        this.overlay = BlockModelDispatcher.loadDataModel("stardew:block/leaves/" + overlayName + "_overlay").asModel();
        this.overlayFlowering = BlockModelDispatcher.loadDataModel("stardew:block/leaves/" + overlayName + "_overlay_flowering").asModel();
    }

    @Override
    public void renderStandalone(@NonNull TessellatorGeneral tessellator, int metadata, byte lightIndex) {
        super.renderStandalone(tessellator, metadata, lightIndex);
        this.overlay.renderStandalone(this, tessellator, 0.0F, 0.0F, 0.0F, metadata, lightIndex, BlockColorDispatcher.getInstance().getDispatch(this.block));
    }

    @Override
    public boolean renderAttached(@NonNull TessellatorGeneral tessellator, @NonNull WorldSource worldSource, @NonNull TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
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
