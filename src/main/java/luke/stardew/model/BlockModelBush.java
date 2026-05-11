package luke.stardew.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelCrossedSquares;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class BlockModelBush<T extends BlockLogic> extends BlockModelCrossedSquares<T> {
    public final IconCoordinate[] seasonalTextures = new IconCoordinate[]{
        TextureRegistry.getTexture("stardew:block/bush/spring"),
        TextureRegistry.getTexture("stardew:block/bush/summer"),
        TextureRegistry.getTexture("stardew:block/bush/fall"),
        TextureRegistry.getTexture("stardew:block/bush/winter"),
        TextureRegistry.getTexture("stardew:block/bush/dead")
    };

    public BlockModelBush(Block block) {
        super(block);
    }

    @Override
    public @Nullable IconCoordinate getBlockTexture(@NotNull WorldSource source, @NotNull TilePosc tilePos, @NotNull Side side) {
        return seasonalTextures[source.getBlockData(tilePos) % seasonalTextures.length];
    }
}
