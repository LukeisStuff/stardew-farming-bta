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

public class BlockLogicPieApple extends BlockLogicEdible {
    public BlockLogicPieApple(Block<?> block) {
        super(block, 6, 3, () -> StardewItems.FOOD_APPLE_PIE);
    }

    private void addSlice(World world, int x, int y, int z, Player entityplayer) {
        if (entityplayer.isSneaking()) {
            entityplayer.inventory.insertItem(new ItemStack(StardewItems.FOOD_APPLE_PIE_SLICE), true);
        } else {
            if (entityplayer.getHealth() >= entityplayer.getMaxHealth()) {
                return;
            }
            entityplayer.heal(this.healAmount);
        }

        int newData = world.getBlockMetadata(x, y, z) + 1;
        if (newData >= this.maxBites) {
            world.setBlockWithNotify(x, y, z, 0);
        } else {
            world.setBlockMetadataWithNotify(x, y, z, newData);
            world.markBlockDirty(x, y, z);
        }
    }

    @Override
    public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xPlaced, double yPlaced) {
        this.addSlice(world, x, y, z, player);
        return true;
    }

    @Override
    public AABB getBlockBoundsFromState(WorldSource world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        float pix = 0.0625F;
        float height = 0.375F;
        float xMin = pix;
        if (meta >= 2) xMin = 1F / 3F;
        if (meta >= 4) xMin = 2F / 3F;

        return AABB.getTemporaryBB(xMin, 0.0F, pix, 1.0F - pix, height, 1.0F - pix);
    }
}
