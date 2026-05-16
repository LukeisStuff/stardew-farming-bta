package luke.stardew.model;

import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockModelCropPumplike<T extends BlockLogic> extends BlockModelGenericProgressive<T>{

    public BlockModelCropPumplike(@NotNull Block block, @NotNull String dataModelPath, int amount) {
        super(block, dataModelPath, amount);
    }

    @Override
    public boolean renderAttached(@NotNull TessellatorGeneral tessellator, @NotNull WorldSource worldSource, @NotNull TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
        if (worldSource.getBlockData(tilePos) > 0) {
            this.models[0].renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
        }

        return super.renderAttached(tessellator, worldSource, tilePos, cullFaces, overrideTexture);
    }
}
