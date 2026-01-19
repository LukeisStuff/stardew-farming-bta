package luke.stardew.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicEdible;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;

@Environment(EnvType.CLIENT)
public class BlockModelPieApple<T extends BlockLogicEdible> extends BlockModelStandard<T> {
    protected IconCoordinate sideTexture = TextureRegistry.getTexture("stardew:block/apple_pie/side");
    protected IconCoordinate insideTexture = TextureRegistry.getTexture("stardew:block/apple_pie/inner");
    protected IconCoordinate topTexture = TextureRegistry.getTexture("stardew:block/apple_pie/top");
    public int maxSlices;

    public BlockModelPieApple(Block<T> block) {
        super(block);
        float f = 0.0625F;
        float f1 = 0.375F;
        this.withCustomItemBounds(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
    }

    public void renderSliceSide(Tessellator tessellator, AABB bounds, int x, int y, int z, Side side, boolean overrideTex) {
        if (overrideTex) {
            renderBlocks.overrideBlockTexture = this.insideTexture;
        }

        this.renderSide(tessellator, bounds, x, y, z, side, 0);

        if (overrideTex) {
            renderBlocks.overrideBlockTexture = null;
        }
    }

    private void renderSlice(Tessellator tessellator, AABB bounds, int x, int y, int z, int sliceX, int sliceZ) {
        double onePix = 0.0625D;
        double sliceWidthX = 1.0D / 3.0D;
        double sliceWidthZ = 1.0D / 2.0D;
        double xMin = sliceWidthX * sliceX;
        double xMax = xMin + sliceWidthX;
        double zMin = sliceWidthZ * sliceZ;
        double zMax = zMin + sliceWidthZ;
        double offsetXMin = sliceX == 0 ? onePix : 0.0D;
        double offsetXMax = sliceX == 2 ? onePix : 0.0D;
        double offsetZMin = sliceZ == 0 ? onePix : 0.0D;
        double offsetZMax = sliceZ == 1 ? onePix : 0.0D;
        boolean insideSouth = sliceZ == 0;
        boolean insideNorth = sliceZ == 1;
        boolean insideEast = sliceX == 0;
        boolean insideWest = sliceX == 2;

        this.maxSlices = this.block.getLogic().maxBites;
        bounds.set(xMin + offsetXMin, 0.0D, zMin + offsetZMin, xMax - offsetXMax, 0.375D, zMax - offsetZMax);
        this.renderSide(tessellator, bounds, x, y, z, Side.TOP, 0);
        this.renderSide(tessellator, bounds, x, y, z, Side.BOTTOM, 0);
        this.renderSliceSide(tessellator, bounds, x, y, z, Side.SOUTH, insideSouth);
        this.renderSliceSide(tessellator, bounds, x, y, z, Side.WEST, insideWest);
        renderBlocks.flipTexture = true;
        this.renderSliceSide(tessellator, bounds, x, y, z, Side.NORTH, insideNorth);
        this.renderSliceSide(tessellator, bounds, x, y, z, Side.EAST, insideEast);
        renderBlocks.flipTexture = false;
    }

    @Override
    public boolean render(Tessellator tessellator, int x, int y, int z) {
        AABB bounds = this.block.getBlockBoundsFromState(renderBlocks.blockAccess, x, y, z);
        int meta = renderBlocks.blockAccess.getBlockMetadata(x, y, z);
        renderBlocks.enableAO = true;
        renderBlocks.cache.setupCache(this.block, renderBlocks.blockAccess, x, y, z);

        int slices = 0;
        for (int xSlice = 0; xSlice < 3; ++xSlice) {
            for (int zSlice = 0; zSlice < 2; ++zSlice) {
                ++slices;
                if (meta < slices) {
                    this.renderSlice(tessellator, bounds, x, y, z, xSlice, zSlice);
                }
            }
        }

        renderBlocks.enableAO = false;
        return true;
    }
}
