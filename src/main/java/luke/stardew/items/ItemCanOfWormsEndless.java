package luke.stardew.items;

import net.minecraft.core.item.Item;
import net.minecraft.core.util.collection.NamespaceID;

public class ItemCanOfWormsEndless extends Item {
	public ItemCanOfWormsEndless(String translationKey, String namespaceID, int id) {
		super(translationKey, namespaceID, id);
		this.setMaxStackSize(1);
	}
}
