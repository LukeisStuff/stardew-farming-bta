package luke.stardew.items;

import net.minecraft.core.item.IArmorItem;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.material.ArmorMaterial;
import org.jetbrains.annotations.Nullable;

public class ItemCanOfWormsEndless extends Item implements IArmorItem {
	public ItemCanOfWormsEndless(String translationKey, String namespaceID, int id) {
		super(translationKey, namespaceID, id);
		this.setMaxStackSize(1);
	}

	@Override
	public @Nullable ArmorMaterial getArmorMaterial() {
		return null;
	}

	@Override
	public int getArmorPiece() {
		return IArmorItem.PIECE_LEGS;
	}
}
