package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.world.World;

public class BlockLogicCropTall extends BlockLogicCropBase {
    protected int growTopMeta = -1; //Block is considered top if -1
    protected Block<? extends BlockLogicCropTall> otherBlock;

    public BlockLogicCropTall(Block<?> block) {
        super(block);
    }

    @SuppressWarnings("unchecked")
    public BlockLogicCropTall asTop(Block<?> block) {
        block.setTicking(false);
        this.otherBlock = (Block<? extends BlockLogicCropTall>) block;
        this.setBlockBounds(0.25F, -1.0F, 0.25F, 0.75F, 0.5F, 0.75F);
        return this;
    }

    @SuppressWarnings("unchecked")
    public BlockLogicCropTall growsTop(Block<?> block, int atMeta) {
        this.otherBlock = (Block<? extends BlockLogicCropTall>) block;
        this.growTopMeta = atMeta;
        this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.5F, 0.75F);
        return this;
    }

//    @Override
//    public boolean mayPlaceOn(int blockId) {
//        return this.growTopMeta < 0 || super.mayPlaceOn(blockId);
//    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) { //This isn't very good but it works
        int meta = world.getBlockMetadata(x, y, z);
        if (growTopMeta > -1 && meta >= this.growTopMeta) {
            if (world.getBlock(x, y + 1, z) != otherBlock) world.setBlockWithNotify(x, y, z, 0);
        } else if (growTopMeta < 0) {
            if (world.getBlock(x, y - 1, z) != otherBlock) world.setBlockWithNotify(x, y, z, 0);
        } else if (!this.canBlockStay(world, x, y, z)) {
            world.setBlockWithNotify(x, y, z, 0);
            this.dropBlockWithCause(world, EnumDropCause.WORLD, x, y, z, meta, null, null);
        }
    }

//    @Override
//    public AABB getBlockBoundsFromState(WorldSource world, int x, int y, int z) {
//        int meta = world.getBlockMetadata(x, y, z);
//        return meta < this.growTopMeta ? AABB.getTemporaryBB(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F) : this.bounds.copy();
//    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        if (this.growTopMeta < 0) {
            return super.canBlockStay(world, x, y, z) && world.getBlockId(x, y - 1, z) == this.otherBlock.id();
        } else {
            int meta = world.getBlockMetadata(x, y, z);
            if (meta >= this.growTopMeta) {
                return super.canBlockStay(world, x, y, z) && world.getBlockId(x, y + 1, z) == this.otherBlock.id();
            } else {
                return super.canBlockStay(world, x, y, z);
            }
        }
    }
}
