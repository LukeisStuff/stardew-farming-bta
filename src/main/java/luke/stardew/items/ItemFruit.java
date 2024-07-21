package luke.stardew.items;

import luke.stardew.misc.FruitSize;
import net.minecraft.core.item.ItemFood;

public class ItemFruit extends ItemFood {
	public FruitSize size;
	public ItemFruit(String name, int id, int healAmount, int ticksPerHeal, FruitSize size, int maxStackSize) {
		super(name, id, healAmount, ticksPerHeal, false, maxStackSize);
		this.size = size;
	}
}
