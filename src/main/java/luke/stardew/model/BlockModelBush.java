package luke.stardew.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.helper.Side;

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
    public boolean render(Tessellator tessellator, int x, int y, int z) {
        float brightness = 1.0F;
        if (!LightmapHelper.isLightmapEnabled()) {
            brightness = this.getBlockBrightness(renderBlocks.blockAccess, x, y, z);
        } else {
            tessellator.setLightmapCoord(this.block.getLightmapCoord(renderBlocks.blockAccess, x, y, z));
        }

        int color = BlockColorDispatcher.getInstance().getDispatch(this.block).getWorldColor(renderBlocks.blockAccess, x, y, z);
        float r = (color >> 16 & 255) / 255.0F;
        float g = (color >> 8 & 255) / 255.0F;
        float b = (color & 255) / 255.0F;
        tessellator.setColorOpaque_F(brightness * r, brightness * g, brightness * b);

        int metadata = renderBlocks.blockAccess.getBlockMetadata(x, y, z);
        IconCoordinate texIndex = this.getBlockTextureFromSideAndMetadata(Side.BOTTOM, metadata);
        if (renderBlocks.overrideBlockTexture != null) {
            texIndex = renderBlocks.overrideBlockTexture;
        }

        double minU = texIndex.getIconUMin();
        double maxU = texIndex.getIconUMax();
        double minV = texIndex.getIconVMin();
        double maxV = texIndex.getIconVMax();
        double minX = x + 0.5 - 0.45;
        double maxX = x + 0.5 + 0.45;
        double minZ = z + 0.5 - 0.45;
        double maxZ = z + 0.5 + 0.45;
        tessellator.addVertexWithUV(minX, y + 1.0 + 0.0, minZ, minU, minV);
        tessellator.addVertexWithUV(minX, y + 0.0, minZ, minU, maxV);
        tessellator.addVertexWithUV(maxX, y + 0.0, maxZ, maxU, maxV);
        tessellator.addVertexWithUV(maxX, y + 1.0 + 0.0, maxZ, maxU, minV);
        tessellator.addVertexWithUV(maxX, y + 1.0 + 0.0, maxZ, minU, minV);
        tessellator.addVertexWithUV(maxX, y + 0.0, maxZ, minU, maxV);
        tessellator.addVertexWithUV(minX, y + 0.0, minZ, maxU, maxV);
        tessellator.addVertexWithUV(minX, y + 1.0 + 0.0, minZ, maxU, minV);
        tessellator.addVertexWithUV(minX, y + 1.0 + 0.0, maxZ, minU, minV);
        tessellator.addVertexWithUV(minX, y + 0.0, maxZ, minU, maxV);
        tessellator.addVertexWithUV(maxX, y + 0.0, minZ, maxU, maxV);
        tessellator.addVertexWithUV(maxX, y + 1.0 + 0.0, minZ, maxU, minV);
        tessellator.addVertexWithUV(maxX, y + 1.0 + 0.0, minZ, minU, minV);
        tessellator.addVertexWithUV(maxX, y + 0.0, minZ, minU, maxV);
        tessellator.addVertexWithUV(minX, y + 0.0, maxZ, maxU, maxV);
        tessellator.addVertexWithUV(minX, y + 1.0 + 0.0, maxZ, maxU, minV);
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
