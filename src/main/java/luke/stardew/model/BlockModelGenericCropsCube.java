package luke.stardew.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
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
public class BlockModelGenericCropsCube<T extends BlockLogic> extends BlockModelGeneric<T> {
    public final StaticBlockModel[] stages = new StaticBlockModel[4];
    public final String cropName;

    public BlockModelGenericCropsCube(@NotNull Block<T> block, String cropName) {
        super(block, BlockModelDispatcher.loadDataModel("stardew:block/crops_" + cropName + "/leaf"));
        this.cropName = cropName;
        for (int i = 0; i < this.stages.length; ++i) {
            this.stages[i] = BlockModelDispatcher.loadDataModel("stardew:block/crops_" + cropName + "/stage" + (i + 1)).asModel();
        }

    }

    @Override
    public boolean renderAttached(@NotNull TessellatorGeneral tessellator, @NotNull WorldSource worldSource, @NotNull TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
        boolean didRender = this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
        int data = worldSource.getBlockData(tilePos);
        if (data >= 1) {
            this.stages[data - 1 & 3].renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
        }

        return didRender;
    }
}
