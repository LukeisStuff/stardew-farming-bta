package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicFarmland;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.Nullable;
import org.joml.primitives.AABBdc;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Unique;

import java.util.Random;

public class BlockLogicPlantStake extends BlockLogic {

    public BlockLogicPlantStake(Block<?> block, Material material) {
        super(block, material);
        this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
    }

    @Override
    public void updateTick(@NonNull World world, @NonNull TilePosc tilePos, @NonNull Random rand, boolean isRandomTick) {
        this.checkSupport(world, tilePos.x(), tilePos.y(), tilePos.z());
    }

    @Override
    public void onNeighborBlockChange(@NonNull World world, int x, int y, int z, int blockId) {
        this.checkSupport(world, x, y, z);
    }

    @Unique
    public void checkSupport(World world, int x, int y, int z) {
        if (!this.canBlockStay(world, x, y, z)) {
            world.setBlockAndMetadataWithNotify(x, y, z, 0, 0);
            this.dropBlockWithCause(world, EnumDropCause.WORLD, x, y, z, world.getBlockMetadata(x, y, z), null, null);
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return world.getBlockLogic(x, y - 1, z, BlockLogicFarmland.class) != null;
    }

    @Override
    public boolean canPlaceOnSurfaceOfBlock(World world, int x, int y, int z) {
        return world.getBlockLogic(x, y, z, BlockLogicFarmland.class) != null;
    }

    @Override
    public ItemStack[] getBreakResult(@NonNull World world, @NonNull EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        return new ItemStack[]{new ItemStack(Items.STICK)};
    }

    @Override
    public @Nullable AABBdc getCollisionAABB(@NonNull WorldSource source, @NonNull TilePosc tilePos) {
        return null;
    }

    @Override
    public boolean isSolidRender() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlockOnCondition(@NonNull WorldSource world, int x, int y, int z) {
        return false;
    }
}
