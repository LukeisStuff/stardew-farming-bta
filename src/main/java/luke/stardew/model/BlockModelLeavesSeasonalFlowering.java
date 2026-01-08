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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.models.block.StaticBlockModel;

@Environment(EnvType.CLIENT)
public class BlockModelLeavesSeasonalFlowering<T extends BlockLogic> extends BlockModelGenericLeaves<T> {
    private final @NotNull StaticBlockModel overlayGrown;
    private final @NotNull StaticBlockModel overlayFlowering;

    public BlockModelLeavesSeasonalFlowering(@NotNull Block<T> block, String baseTexturePath, String overlayName) {
        super(block, baseTexturePath);
        this.overlayGrown = BlockModelDispatcher.loadDataModel("stardew:block/leaves/" + overlayName + "_overlay").asModel();
        this.overlayFlowering = BlockModelDispatcher.loadDataModel("stardew:block/leaves/" + overlayName + "_flowering_overlay").asModel();
    }

    @Override
    public boolean renderAttached(@NotNull TessellatorGeneral tessellator, @NotNull WorldSource worldSource, @NotNull TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
        boolean rendered = super.renderAttached(tessellator, worldSource, tilePos, cullFaces, overrideTexture);
        int meta = worldSource.getBlockData(tilePos);
        int growthRate = BlockLogicLeavesSeasonalFlowering.getGrowthRate(meta);
        StaticBlockModel overlay = growthRate > 0 ? overlayGrown : overlayFlowering;
        rendered |= overlay.renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, 0.0, 0.0, 0.0, false, cullFaces, overrideTexture);
        return rendered;
    }

    @Override
    public void renderStandalone(@NotNull TessellatorGeneral tessellator, int metadata, byte lightIndex) {
        super.renderStandalone(tessellator, metadata, lightIndex);
        overlayGrown.renderStandalone(this, tessellator, 0.0, 0.0, 0.0, metadata, lightIndex, BlockColorDispatcher.getInstance().getDispatch(this.block));
    }
}
