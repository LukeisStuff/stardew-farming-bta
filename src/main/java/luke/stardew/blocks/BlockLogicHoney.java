package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicTransparent;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

public class BlockLogicHoney extends BlockLogicTransparent {

    public BlockLogicHoney(Block<?> block) {
        super(block, Materials.LEAVES);
        block.setTicking(true);
    }

    @Override
    public boolean isSolidRender() {
        return false;
    }

    @Override
    public @Nullable AABBdc getCollisionAABB(@NonNull WorldSource source, @NonNull TilePosc tilePos) {
        double f = 0.125F;
        return new AABBd(tilePos.x(), tilePos.y(), tilePos.z(), tilePos.x() + 1, (double)(tilePos.y() + 1) - f, tilePos.z() + 1);
    }

    @Override
    public void onEntityCollidedWithBlock(@NonNull World world, int x, int y, int z, Entity entity) {
        entity.xd *= 0.4;
        entity.yd *= 0.1;
        entity.zd *= 0.4;
        if (entity.fallDistance > 1.5F) {
            entity.fallDistance = 0.0F;
            world.playBlockSoundEffect(null, x, y, z, StardewBlocks.BLOCK_HONEY, EnumBlockSoundEffectType.ENTITY_LAND);
        }
    }
}
