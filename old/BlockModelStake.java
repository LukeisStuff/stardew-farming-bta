package luke.stardew.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.Side;

@Environment(EnvType.CLIENT)
public class BlockModelStake<T extends BlockLogic> extends BlockModelStandard<T> {

    public BlockModelStake(Block<T> block) {
        super(block);
    }

    @Override
    public boolean render(Tessellator tessellator, int x, int y, int z) {
        float brightness = 1.0F;
        if (LightmapHelper.isLightmapEnabled()) {
            tessellator.setLightmapCoord(this.block.getLightmapCoord(renderBlocks.blockAccess, x, y, z));
        } else {
            brightness = this.getBlockBrightness(renderBlocks.blockAccess, x, y, z);
        }

        tessellator.setColorOpaque_F(brightness, brightness, brightness);
        IconCoordinate texture = this.getBlockTextureFromSideAndMetadata(Side.BOTTOM, renderBlocks.blockAccess.getBlockMetadata(x, y, z));
        if (renderBlocks.overrideBlockTexture != null) {
            texture = renderBlocks.overrideBlockTexture;
        }

        double minU = texture.getIconUMin();
        double maxU = texture.getIconUMax();
        double minV = texture.getIconVMin();
        double maxV = texture.getIconVMax();
        double minX = x + 0.5 - 0.45;
        double maxX = x + 0.5 + 0.45;
        double minZ = z + 0.5 - 0.45;
        double maxZ = z + 0.5 + 0.45;
        double yd = y - 0.0625F;

        tessellator.addVertexWithUV(minX, yd + 1.0, minZ, minU, minV);
        tessellator.addVertexWithUV(minX, yd + 0.0, minZ, minU, maxV);
        tessellator.addVertexWithUV(maxX, yd + 0.0, maxZ, maxU, maxV);
        tessellator.addVertexWithUV(maxX, yd + 1.0, maxZ, maxU, minV);

        tessellator.addVertexWithUV(maxX, yd + 1.0, maxZ, minU, minV);
        tessellator.addVertexWithUV(maxX, yd + 0.0, maxZ, minU, maxV);
        tessellator.addVertexWithUV(minX, yd + 0.0, minZ, maxU, maxV);
        tessellator.addVertexWithUV(minX, yd + 1.0, minZ, maxU, minV);

        tessellator.addVertexWithUV(minX, yd + 1.0, maxZ, minU, minV);
        tessellator.addVertexWithUV(minX, yd + 0.0, maxZ, minU, maxV);
        tessellator.addVertexWithUV(maxX, yd + 0.0, minZ, maxU, maxV);
        tessellator.addVertexWithUV(maxX, yd + 1.0, minZ, maxU, minV);

        tessellator.addVertexWithUV(maxX, yd + 1.0, minZ, minU, minV);
        tessellator.addVertexWithUV(maxX, yd + 0.0, minZ, minU, maxV);
        tessellator.addVertexWithUV(minX, yd + 0.0, maxZ, maxU, maxV);
        tessellator.addVertexWithUV(minX, yd + 1.0, maxZ, maxU, minV);

        return true;
    }

    @Override
    public boolean shouldItemRender3d() {
        return false;
    }

}
