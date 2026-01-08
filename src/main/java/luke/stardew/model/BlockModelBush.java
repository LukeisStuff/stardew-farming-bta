package luke.stardew.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelCrossedSquares;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.helper.Side;
import org.jspecify.annotations.NonNull;

@Environment(EnvType.CLIENT)
public class BlockModelBush<T extends BlockLogic> extends BlockModelCrossedSquares<T> {
    public final IconCoordinate[] seasonalTextures = new IconCoordinate[]{
        TextureRegistry.getTexture("stardew:block/bush/spring"),
        TextureRegistry.getTexture("stardew:block/bush/summer"),
        TextureRegistry.getTexture("stardew:block/bush/fall"),
        TextureRegistry.getTexture("stardew:block/bush/winter"),
        TextureRegistry.getTexture("stardew:block/bush/dead")};

    public BlockModelBush(Block<T> block) {
        super(block);
    }

    @Override
    public IconCoordinate getBlockTextureFromSideAndMetadata(@NonNull Side side, int data) {
        return this.seasonalTextures[MathHelper.clamp(data, 0, 4)];
    }
}
