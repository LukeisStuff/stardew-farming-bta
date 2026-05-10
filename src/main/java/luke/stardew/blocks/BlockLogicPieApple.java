package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicEdible;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

public class BlockLogicPieApple extends BlockLogicEdible {
    public BlockLogicPieApple(Block<?> block) {
        super(block, 6, () -> StardewItems.FOOD_APPLE_PIE);
    }

    private void addSlice(World world, TilePosc tilePos, Player entityplayer) {
        if (entityplayer.isSneaking()) {
            entityplayer.inventory.insertItem(new ItemStack(StardewItems.FOOD_APPLE_PIE_SLICE), true);
        } else {
            if (entityplayer.getHealth() >= entityplayer.getMaxHealth()) {
                return;
            }
            entityplayer.heal(this.getHealAmount(world, tilePos));
        }

        int newData = world.getBlockMetadata(tilePos.x(), tilePos.y(), tilePos.z()) + 1;
        if (newData >= this.maxBites) {
            world.setBlockWithNotify(tilePos.x(), tilePos.y(), tilePos.z(), 0);
        } else {
            world.setBlockMetadataWithNotify(tilePos.x(), tilePos.y(), tilePos.z(), newData);
            world.markBlockDirty(tilePos.x(), tilePos.y(), tilePos.z());
        }
    }

    @Override
    public boolean onInteracted(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Player player, @Nullable Side side, double xHit, double yHit) {
        this.addSlice(world, tilePos, player);
        return true;
    }

    @Override
    public AABBdc getBoundsFromState(@NotNull WorldSource source, @NotNull TilePosc tilePos) {
        int meta = source.getBlockData(tilePos);
        float pix = 0.0625F;
        float height = 0.375F;
        float xMin = pix;
        if (meta >= 2) xMin = 1F / 3F;
        if (meta >= 4) xMin = 2F / 3F;

        return new AABBd(xMin, 0.0F, pix, 1.0F - pix, height, 1.0F - pix);
    }

    @Override
    public int getHealAmount(@NotNull World world, @NotNull TilePosc tilePosc) {
        return 3;
    }
}
