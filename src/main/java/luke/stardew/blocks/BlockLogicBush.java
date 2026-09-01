package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.WeightedRandomBag;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFlower;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Season;
import net.minecraft.core.world.season.Seasons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;


public class BlockLogicBush extends BlockLogicFlower {

    protected static final HashMap<Season, WeightedRandomBag<ItemStack[]>> LOOT_BAG = new HashMap<>();

    static {
        var springBag = new WeightedRandomBag<ItemStack[]>();
        var summerBag = new WeightedRandomBag<ItemStack[]>();
        var fallBag = new WeightedRandomBag<ItemStack[]>();
        var winterBag = new WeightedRandomBag<ItemStack[]>();

        LOOT_BAG.put(Seasons.OVERWORLD_SPRING, springBag);
        LOOT_BAG.put(Seasons.OVERWORLD_SUMMER, summerBag);
        LOOT_BAG.put(Seasons.OVERWORLD_FALL, fallBag);
        LOOT_BAG.put(Seasons.OVERWORLD_WINTER, winterBag);
        LOOT_BAG.put(Seasons.OVERWORLD_WINTER_ENDLESS, winterBag);

        // spring
        springBag.addEntry(new ItemStack[]{new ItemStack(StardewItems.SEEDS_CARROT)}, 1);
        springBag.addEntry( new ItemStack[]{new ItemStack(StardewItems.SEEDS_BLUEBERRY)}, 1);
        springBag.addEntry( new ItemStack[]{new ItemStack(StardewItems.SEEDS_PINEAPPLE)}, 1);

        // summer
        summerBag.addEntry( new ItemStack[]{new ItemStack(StardewItems.SEEDS_TOMATO)}, 1);
        summerBag.addEntry( new ItemStack[]{new ItemStack(StardewItems.SEEDS_POTATO)}, 1);
        summerBag.addEntry( new ItemStack[]{new ItemStack(StardewItems.SEEDS_STRAWBERRY)}, 1);

        // fall
        fallBag.addEntry( new ItemStack[]{new ItemStack(StardewItems.SEEDS_CORN)}, 1);
        fallBag.addEntry( new ItemStack[]{new ItemStack(StardewItems.SEEDS_GRAPES)}, 1);


        // winter
        winterBag.addEntry( new ItemStack[]{new ItemStack(StardewItems.BEANS_COFFEE)}, 1);
        winterBag.addEntry( new ItemStack[]{new ItemStack(StardewItems.SEEDS_CRANBERRIES)}, 1);
    }

    public BlockLogicBush(Block<?> block) {
        super(block);
        block.setTicking(true);
    }

    @Override
    public @NotNull ItemStack @Nullable [] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
        switch (dropCause) {
            case PICK_BLOCK, SILK_TOUCH:
                return new ItemStack[]{new ItemStack(this)};
            default:
               var lootBag = LOOT_BAG.get(world.getSeasonManager().getCurrentSeason());

               if (lootBag != null) { return lootBag.getRandom(); }
               return null;
        }
    }

}
