package luke.stardew.items;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.ItemSeeds;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ItemSeedsStake extends ItemSeeds {
    public ItemSeedsStake(String name, String namespaceId, int id, Block<?> cropsBlock) {
        super(name, namespaceId, id, cropsBlock);
    }

    @Override
    public boolean onUseOnBlock(@NonNull ItemStack selfStack, @NonNull World world, @Nullable Player player, @NonNull TilePosc blockPos, @NonNull Side side, double xHit, double yHit) {
        if (world.getBlock(blockPos.x(), blockPos.y(), blockPos.z()) == StardewBlocks.PLANT_STAKE) {
            if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == StardewItems.SEEDS_GRAPES.id && world.getBlockId(blockPos.x(), blockPos.y() - 1, blockPos.z()) == Blocks.FARMLAND_DIRT.id()) {
                player.getCurrentEquippedItem().consumeItem(player);
                world.setBlockAndMetadataWithNotify(blockPos.x(), blockPos.y(), blockPos.z(), StardewBlocks.CROPS_GRAPE_BOTTOM.id(), 0);
                player.swingItem();
                world.playBlockSoundEffect(player, blockPos.x() + 0.5F, blockPos.y() + 0.5F, blockPos.z() + 0.5F, StardewBlocks.CROPS_GRAPE_BOTTOM, EnumBlockSoundEffectType.PLACE);
            }
            if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == StardewItems.BEANS_COFFEE.id && world.getBlockId(blockPos.x(), blockPos.y() - 1, blockPos.z()) == Blocks.FARMLAND_DIRT.id()) {
                player.getCurrentEquippedItem().consumeItem(player);
                world.setBlockAndMetadataWithNotify(blockPos.x(), blockPos.y(), blockPos.z(), StardewBlocks.CROPS_BEANS_BOTTOM.id(), 0);
                player.swingItem();
                world.playBlockSoundEffect(player, blockPos.x() + 0.5F, blockPos.y() + 0.5F, blockPos.z() + 0.5F, StardewBlocks.CROPS_BEANS_BOTTOM, EnumBlockSoundEffectType.PLACE);
            }
        }

        return false;
    }
}
