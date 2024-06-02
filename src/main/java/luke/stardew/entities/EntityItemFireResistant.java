package luke.stardew.entities;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;

public class EntityItemFireResistant extends EntityItem {
	public EntityItemFireResistant(World world, double d, double d1, double d2, ItemStack itemstack) {
		super(world, d, d1, d2, itemstack);
		this.fireImmune = true;
	}

	@Override
	protected void burn(int damage) {
	}
}
