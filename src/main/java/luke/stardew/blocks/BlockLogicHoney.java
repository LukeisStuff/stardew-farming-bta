package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicTransparent;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
    public @Nullable AABBdc getCollisionAABB(@NotNull WorldSource source, @NotNull TilePosc tilePos) {
        float f = 0.125F;
        int x = tilePos.x();
        int y = tilePos.y();
        int z = tilePos.z();
        return new AABBd(x + f, y + f, z + f, (x + 1) - f, (y + 1) - f, (z + 1) - f);
    }

    @Override
    public void onEntityCollision(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Entity entity) {
        entity.xd *= 0.4;
        entity.yd *= 0.1;
        entity.zd *= 0.4;
        if (entity.fallDistance > 1.5F) {
            entity.fallDistance = 0.0F;
            world.playBlockSoundEffect(null, tilePos.x(), tilePos.y(), tilePos.z(), StardewBlocks.BLOCK_HONEY, EnumBlockSoundEffectType.ENTITY_LAND);
        }
    }
}
