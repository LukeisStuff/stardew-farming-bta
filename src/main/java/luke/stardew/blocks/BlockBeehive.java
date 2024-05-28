package luke.stardew.blocks;

import net.minecraft.core.block.BlockRotatableHorizontal;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.world.World;

public class BlockBeehive extends BlockRotatableHorizontal {

	public BlockBeehive(String key, int id) {
		super(key, id, Material.wood);
		this.setTicking(true);
	}

	public void onBlockAdded(World world, int x, int y, int z) {
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		int l = world.getBlockMetadata(x, y, z);
			if  (player.getHeldItem() != null && player.getHeldItem().getItem() == Item.dustSugar) {
				player.getHeldItem().consumeItem(player);
				world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.beehiveIdle.id, l);
				world.playSoundAtEntity(player, player, "random.pop", 0.2F, 0.5F);
				return true;
			}
		return false;
	}

}
