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
        list.add(new ItemStack(StardewBlocks.CANDLE));
    }

    @Override
    public void populateOrganic(List<ItemStack> list) {
        list.add(new ItemStack(StardewBlocks.THATCH));
        list.add(new ItemStack(StardewBlocks.WATERMELON));
        list.add(new ItemStack(StardewBlocks.LEAVES_APPLE));
        list.add(new ItemStack(StardewBlocks.BUSH));
        list.add(new ItemStack(StardewBlocks.BLOCK_HONEY));
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
