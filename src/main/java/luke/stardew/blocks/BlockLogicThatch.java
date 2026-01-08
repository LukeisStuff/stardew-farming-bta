package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFullyRotatable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class BlockLogicThatch extends BlockLogicFullyRotatable {

    public BlockLogicThatch(Block<?> block, Material material) {
        super(block, material);
    }

    @Override
    public boolean renderAsNormalBlockOnCondition(@NonNull WorldSource world, int x, int y, int z) {
        return false;
    }

    @Override
    public void onEntityCollidedWithBlock(@NonNull World world, int x, int y, int z, Entity entity) {
        if (entity.yd < 0.0D) {
            entity.fallDistance = entity.fallDistance / 4;
        }
    }

    @Override
    public @Nullable AABBdc getCollisionAABB(@NonNull WorldSource source, @NonNull TilePosc tilePos) {
        double f = 0.125F;
        return new AABBd(tilePos.x(), tilePos.y(), tilePos.z(), tilePos.x() + 1, (double) (tilePos.y() + 1) - f, tilePos.z() + 1);
    }
}
