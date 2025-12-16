package luke.stardew.items;

import net.minecraft.core.item.ItemFood;

public class ItemFruit extends ItemFood {
    public ItemFruit(String name, String namespaceID, int id, int healAmount, int ticksPerHeal, int maxStackSize) {
        super(name, namespaceID, id, healAmount, ticksPerHeal, false, maxStackSize);
    }
}
