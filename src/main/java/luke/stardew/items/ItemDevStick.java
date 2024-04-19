package luke.stardew.items;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class ItemDevStick extends Item {
	public ItemDevStick(String name, int id) {
		super(name, id);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		int meta = world.getBlockMetadata(blockX, blockY, blockZ);
		if (!world.isClientSide) {
			world.setBlockMetadataWithNotify(blockX, blockY, blockZ, meta+1);
			entityplayer.swingItem();
		}
        return false;
    }
}
