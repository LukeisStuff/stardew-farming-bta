package luke.stardew.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.World;

import java.util.List;

public class BlockLogicCropTallStake extends BlockLogicCropTall {

    public BlockLogicCropTallStake(Block<?> block) {
        super(block);
    }

    @Override
    public void onHarvest(World world, int x, int y, int z, int meta) {
        if (this.growTopMeta < 0) {
            world.setBlockAndMetadataWithNotify(x, y - 1, z, StardewBlocks.PLANT_STAKE.id(), 0);
            world.setBlockAndMetadataWithNotify(x, y, z, 0, 0);
        } else {
            world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.PLANT_STAKE.id(), 0);
            world.setBlockAndMetadataWithNotify(x, y + 1, z, 0, 0);
        }
    }

    @Override
    public List<ItemStack> getDrops(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        List<ItemStack> drops = super.getDrops(world, dropCause, x, y, z, meta, tileEntity);
        drops.add(new ItemStack(Items.STICK));
        return drops;
    }
}
