package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.util.creativeInventory.CreativeBlocksEntrypoint;
import turniplabs.halplibe.util.creativeInventory.CreativeItemsEntrypoint;

import java.util.Arrays;
import java.util.List;

public class StardewCreativeInventory implements CreativeBlocksEntrypoint, CreativeItemsEntrypoint {
    @Override
    public void populateMisc(List<ItemStack> list) {

    }

    @Override
    public void populateStone(List<ItemStack> list) {

    }

    @Override
    public void populateWood(List<ItemStack> list) {
        list.add(new ItemStack(StardewBlocks.BEEHIVE));
        list.add(new ItemStack(StardewBlocks.LOG_APPLE));
    }

    @Override
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

    @Override
    public void populateNatural(List<ItemStack> list) {

    }

    @Override
    public void populateRedstone(List<ItemStack> list) {

    }

    @Override
    public void populateOre(List<ItemStack> list) {

    }

    @Override
    public void populateStorage(List<ItemStack> list) {

    }

    @Override
    public void populateItems(List<ItemStack> list) {
        Arrays
        .stream(StardewItems.class.getDeclaredFields())
        .filter(it -> it.getType().isAssignableFrom(Item.class))
        .forEach(
            it -> {
                Item item = null;
                try {list.add(((Item) it.get(item)).getDefaultStack());}
                catch (IllegalAccessException ignored) {}
            }
        );
    }
}
