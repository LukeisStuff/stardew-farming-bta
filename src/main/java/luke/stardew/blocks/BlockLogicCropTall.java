package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

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

    @Override
    protected boolean mayPlaceOn(@NotNull Block<?> block) {
        return this.growTopMeta < 0 || super.mayPlaceOn(block);
    }

    @Override
    public void onNeighborChanged(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Block<?> block) {
        int meta = world.getBlockData(tilePos);
        if (growTopMeta < 0) {
            if (world.getBlock(tilePos.x(), tilePos.y() - 1, tilePos.z()) != otherBlock) {
                world.setBlockWithNotify(tilePos.x(), tilePos.y(), tilePos.z(), 0);
            }
            return;
        }

        if (meta >= growTopMeta && world.getBlock(tilePos.x(), tilePos.y() + 1, tilePos.z()) != otherBlock) {
            world.setBlockWithNotify(tilePos.x(), tilePos.y(), tilePos.z(), 0);
            return;
        }

        if (!super.canBlockStay(world, tilePos.x(), tilePos.y(), tilePos.z())) {
            world.setBlockWithNotify(tilePos.x(), tilePos.y(), tilePos.z(), 0);
            this.dropBlockWithCause(world, EnumDropCause.WORLD, tilePos.x(), tilePos.y(), tilePos.z(), meta, null, null);
        }
    }


    @Override
    public AABBdc getBoundsFromState(@NotNull WorldSource source, @NotNull TilePosc tilePos) {
        int meta = source.getBlockData(tilePos);
        return meta < this.growTopMeta ? new AABBd(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F) : this.bounds;
    }

    @Override
    public void onGrowth(World world, int x, int y, int z, int newMeta) {
        // this.growTopMeta > -1 == BOTTOM Block of a tall crop
        if (this.growTopMeta > -1 && newMeta >= this.growTopMeta) {
            Block<?> blockAbove = world.getBlock(x, y + 1, z);
            if (blockAbove == otherBlock || blockAbove == null) {
                world.setBlockMetadataWithNotify(x, y, z, newMeta);
                int max = otherBlock.getLogic().maxGrowth;
                int topMeta = MathHelper.clamp(newMeta - growTopMeta, 0, max);
                world.setBlockAndMetadataWithNotify(x, y + 1, z, otherBlock.id(), topMeta);
            }
        } else if (this.growTopMeta < 0) {
            // NOTE: You generally don't want the top block to be
            // ticking since it would result in tall blocks growing
            // more frequently, but this is handled regardless
            world.setBlockMetadataWithNotify(x, y, z, newMeta);
            Block<?> blockBelow = world.getBlock(x, y - 1, z);
            if (blockBelow == otherBlock) {
                int max = otherBlock.getLogic().maxGrowth;
                int diff = max - this.maxGrowth;
                int bottomMeta = MathHelper.clamp(newMeta + diff, 0, max);
                world.setBlockMetadataWithNotify(x, y - 1, z, bottomMeta);
            }
        } else {
            world.setBlockMetadataWithNotify(x, y, z, newMeta);
        }
    }

    @Override
    public boolean canStay(@NotNull World world, @NotNull TilePosc tilePos) {
        if (this.growTopMeta < 0) {
            return super.canBlockStay(world, tilePos.x(), tilePos.y(), tilePos.z()) && world.getBlockId(tilePos.x(), tilePos.y() - 1, tilePos.z()) == this.otherBlock.id();
        } else {
            int meta = world.getBlockData(tilePos);
            if (meta >= this.growTopMeta) {
                return super.canBlockStay(world, tilePos.x(), tilePos.y(), tilePos.z()) && world.getBlockId(tilePos.x(), tilePos.y() + 1, tilePos.z()) == this.otherBlock.id();
            } else {
                return super.canBlockStay(world, tilePos.x(), tilePos.y(), tilePos.z());
            }
        }
    }
}
