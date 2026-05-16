package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.core.item.ItemStack;
import java.util.List;

public class StardewCreativeInventory {
    public void populateMisc(List<ItemStack> list) {

    }

    public void populateStone(List<ItemStack> list) {

    }

    public void populateWood(List<ItemStack> list) {
        list.add(new ItemStack(StardewBlocks.BEEHIVE));
        list.add(new ItemStack(StardewBlocks.LOG_APPLE));
    }

    public void populateOrganic(List<ItemStack> list) {
        list.add(new ItemStack(StardewBlocks.WATERMELON));
        list.add(new ItemStack(StardewBlocks.CAULIFLOWER));
        list.add(new ItemStack(StardewBlocks.LEAVES_APPLE));
        list.add(new ItemStack(StardewBlocks.LEAVES_APPLE_FLOWERING));
        list.add(new ItemStack(StardewBlocks.LEAVES_APPLE_GOLDEN));
        list.add(new ItemStack(StardewBlocks.LEAVES_APPLE_GOLDEN_FLOWERING));
        list.add(new ItemStack(StardewBlocks.BLOCK_HONEY));
        list.add(new ItemStack(StardewBlocks.SAPLING_APPLE));
        list.add(new ItemStack(StardewBlocks.SAPLING_APPLE_GOLDEN));
        list.add(new ItemStack(StardewItems.BUSH));
        list.add(new ItemStack(StardewBlocks.THATCH));
    }
}
