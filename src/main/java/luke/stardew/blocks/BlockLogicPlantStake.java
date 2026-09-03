package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicFarmland;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.primitives.AABBdc;
import org.spongepowered.asm.mixin.Unique;

import java.util.Random;

public class BlockLogicPlantStake extends BlockLogic {

    public BlockLogicPlantStake(Block<?> block, Material material) {
        super(block, material);
        this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
    }

    @Override
    public void updateTick(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Random rand, boolean isRandomTick) {
        this.checkSupport(world, tilePos);
    }

    @Override
    public void onNeighborChanged(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Block<?> block) {
        this.checkSupport(world, tilePos);
    }

    @Unique
    public void checkSupport(World world, TilePosc tilePos) {
        if (!this.canStay(world, tilePos)) {
            world.setBlockTypeDataNotify(tilePos, Blocks.AIR, 0);
            this.dropWithCause(world, EnumDropCause.WORLD, tilePos, world.getBlockData(tilePos), null, null);
        }
    }

    @Override
    public boolean canStay(@NotNull World world, @NotNull TilePosc tilePos) {
        return world.getBlockLogic(new TilePos(tilePos.x(), tilePos.y() - 1, tilePos.z()), BlockLogicFarmland.class) != null;
    }

    @Override
    public boolean canPlaceAt(@NotNull World world, @NotNull TilePosc tilePos) {
        return world.getBlockLogic(tilePos, BlockLogicFarmland.class) != null;
    }

    @Override
    public @NotNull ItemStack @Nullable [] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, @NotNull TilePosc tilePos, int data, @Nullable TileEntity tileEntity) {
        return new ItemStack[]{new ItemStack(Items.STICK)};
    }

    @Override
    public @Nullable AABBdc getCollisionAABB(@NotNull WorldSource source, @NotNull TilePosc tilePos) {
        return null;
    }

    @Override
    public boolean isSolidRender() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlockOnCondition(@NotNull WorldSource world, @NotNull TilePosc tilePosc) {
        return false;
    }
}
