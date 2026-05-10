package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import net.minecraft.core.world.season.Seasons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockLogicBeehive extends BlockLogicRotatable {

    public BlockLogicBeehive(Block<?> block) {
        super(block, Materials.WOOD);
    }

    @Override
    public boolean onInteracted(@NotNull World world, @NotNull TilePosc tile, @NotNull Player player, @Nullable Side side, double xHit, double yHit) {
        int l = world.getBlockMetadata(tile.x(), tile.y(), tile.z());
        ItemStack stack = player.getHeldItem();
        if (world.getSeasonManager().getCurrentSeason() != Seasons.OVERWORLD_WINTER && stack != null && stack.getItem().equals(Items.DUST_SUGAR)) {
            stack.consumeItem(player);
            world.setBlockAndMetadataWithNotify(tile.x(), tile.y(), tile.z(), StardewBlocks.BEEHIVE_IDLE.id(), l);
            world.playSoundAtEntity(player, player, "random.pop", 0.2F, 0.5F);
            return true;
        }
        return false;
    }
}
