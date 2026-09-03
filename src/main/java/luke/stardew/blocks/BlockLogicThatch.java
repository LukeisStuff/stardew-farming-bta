package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFullyRotatable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

public class BlockLogicThatch extends BlockLogicFullyRotatable {

    public BlockLogicThatch(Block<?> block, Material material) {
        super(block, material);
    }

    @Override
    public boolean renderAsNormalBlockOnCondition(@NotNull WorldSource world, @NotNull TilePosc tilePosc) {
        return false;
    }

    @Override
    public void onEntityCollision(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Entity entity) {
        if (entity.yd < 0.0D) {
            entity.fallDistance = entity.fallDistance / 4;
        }
    }

    @Override
    public @Nullable AABBdc getCollisionAABB(@NotNull WorldSource source, @NotNull TilePosc tilePos) {
        int x = tilePos.x();
        int y = tilePos.y();
        int z = tilePos.z();
        return new AABBd(x, y, z, (x + 1), y + .9f, (z + 1));
    }
}
