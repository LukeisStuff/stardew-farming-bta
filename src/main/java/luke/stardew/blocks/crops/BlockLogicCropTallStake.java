package luke.stardew.blocks.crops;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;

import java.util.List;

public class BlockLogicCropTallStake extends BlockLogicCropTall {

    public BlockLogicCropTallStake(Block<?> block) {
        super(block);
    }

    @Override
    public void onHarvest(World world, TilePosc tilePos, int meta) {
        if (this.growTopMeta < 0) {
            world.setBlockTypeDataNotify(tilePos, StardewBlocks.PLANT_STAKE,0);
            world.setBlockTypeDataNotify(tilePos, Blocks.AIR,0);
        } else {
            world.setBlockTypeDataNotify(tilePos, StardewBlocks.PLANT_STAKE,0);
            world.setBlockTypeDataNotify(tilePos, Blocks.AIR,0);
        }
    }

    @Override
    public List<ItemStack> getHarvestResult(World world, EnumDropCause dropCause, TilePosc tilePos, int meta, TileEntity tile) {
        var drops = super.getHarvestResult(world, dropCause, tilePos, meta, tile);
        drops.add(new ItemStack(Items.STICK));
        return drops;
    }
}
