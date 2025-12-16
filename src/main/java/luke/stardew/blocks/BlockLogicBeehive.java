package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockLogicBeehive extends BlockLogicRotatable {

    public BlockLogicBeehive(Block<?> block) {
        super(block, Material.wood);
        block.setTicking(true);
    }

    @Override
    public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xHit, double yHit) {
        int l = world.getBlockMetadata(x, y, z);
        ItemStack stack = player.getHeldItem();
        if (stack != null && stack.getItem().equals(Items.DUST_SUGAR)) {
            player.getHeldItem().consumeItem(player);
            world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.BEEHIVE_IDLE.id(), l);
            world.playSoundAtEntity(player, player, "random.pop", 0.2F, 0.5F);
            return true;
        }
        return false;
    }

}
