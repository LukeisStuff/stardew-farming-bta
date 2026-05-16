package luke.stardew.model;

import luke.stardew.blocks.BlockLogicWaxCandle;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BlockModelCandle extends BlockModelGenericProgressive{

    public BlockModelCandle(@NotNull Block block) {
        super(block, "stardew:block/candle", 4);
    }

    @Override
    public boolean renderAttached(@NotNull TessellatorGeneral tessellator, @NotNull WorldSource worldSource, @NotNull TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
        return this.getModel(worldSource, tilePos)
            .renderAttached(this, tessellator, worldSource, tilePos, 0, BlockLogicWaxCandle.getRotX(tilePos), 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);

    }
}
