package luke.stardew.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jspecify.annotations.NonNull;

@Environment(EnvType.CLIENT)
public class BlockModelBush<T extends BlockLogic> extends BlockModelStandard<T> {
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
    public boolean render(@NonNull TessellatorGeneral tessellator, @NonNull WorldSource worldSource, @NonNull TilePosc tilePos) {
        tessellator.setLightmapCoord1i(this.block.getLightIndex(worldSource, tilePos));
        int color = BlockColorDispatcher.getInstance().getDispatch(this.block).getWorldColor(worldSource, tilePos, 0);
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        tessellator.setColorOpaque3f(r, g, b);
        double xd = tilePos.x();
        double yd = tilePos.y();
        double zd = tilePos.z();
        int metadata = worldSource.getBlockData(tilePos);
        IconCoordinate texIndex = this.getBlockTextureFromSideAndMetadata(Side.BOTTOM, metadata);
        if (renderBlocks.overrideBlockTexture != null) {
            texIndex = renderBlocks.overrideBlockTexture;
        }

        double minU = texIndex.getIconUMin();
        double maxU = texIndex.getIconUMax();
        double minV = texIndex.getIconVMin();
        double maxV = texIndex.getIconVMax();
        double minX = xd + (double) 0.5F - 0.45;
        double maxX = xd + (double) 0.5F + 0.45;
        double minZ = zd + (double) 0.5F - 0.45;
        double maxZ = zd + (double) 0.5F + 0.45;
        tessellator.addVertexWithUV(minX, yd + (double) 1.0F + (double) 0.0F, minZ, minU, minV);
        tessellator.addVertexWithUV(minX, yd + (double) 0.0F, minZ, minU, maxV);
        tessellator.addVertexWithUV(maxX, yd + (double) 0.0F, maxZ, maxU, maxV);
        tessellator.addVertexWithUV(maxX, yd + (double) 1.0F + (double) 0.0F, maxZ, maxU, minV);
        tessellator.addVertexWithUV(maxX, yd + (double) 1.0F + (double) 0.0F, maxZ, minU, minV);
        tessellator.addVertexWithUV(maxX, yd + (double) 0.0F, maxZ, minU, maxV);
        tessellator.addVertexWithUV(minX, yd + (double) 0.0F, minZ, maxU, maxV);
        tessellator.addVertexWithUV(minX, yd + (double) 1.0F + (double) 0.0F, minZ, maxU, minV);
        tessellator.addVertexWithUV(minX, yd + (double) 1.0F + (double) 0.0F, maxZ, minU, minV);
        tessellator.addVertexWithUV(minX, yd + (double) 0.0F, maxZ, minU, maxV);
        tessellator.addVertexWithUV(maxX, yd + (double) 0.0F, minZ, maxU, maxV);
        tessellator.addVertexWithUV(maxX, yd + (double) 1.0F + (double) 0.0F, minZ, maxU, minV);
        tessellator.addVertexWithUV(maxX, yd + (double) 1.0F + (double) 0.0F, minZ, minU, minV);
        tessellator.addVertexWithUV(maxX, yd + (double) 0.0F, minZ, minU, maxV);
        tessellator.addVertexWithUV(minX, yd + (double) 0.0F, maxZ, maxU, maxV);
        tessellator.addVertexWithUV(minX, yd + (double) 1.0F + (double) 0.0F, maxZ, maxU, minV);
        return true;
    }

    @Override
    public boolean shouldItemRender3d() {
        return false;
    }

    @Override
    public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
        return this.seasonalTextures[MathHelper.clamp(data, 0, 4)];
    }
}
