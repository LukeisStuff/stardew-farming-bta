package luke.stardew.blocks.crops;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;

import java.util.List;

public interface IStardewCrop {
    void fertilize(World world, TilePosc tilePos);
    void onGrowth(World world, TilePosc tilePos, int newMeta);
    float getGrowthRate(World world, TilePosc tilePos);
    void onHarvest(World world, TilePosc tilePos, int meta);
    List<ItemStack> getHarvestResult(World world, EnumDropCause dropCause, TilePosc tilePos, int meta, TileEntity tile);
}
