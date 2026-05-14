package luke.stardew.items;


import net.minecraft.core.item.ItemFishingRod;
import net.minecraft.core.item.material.ToolMaterial;

public class ItemToolFishingRodTiered extends ItemFishingRod {

    public ItemToolFishingRodTiered(String translationKey, String namespaceID, int id, ToolMaterial material) {
        super(translationKey, namespaceID, id);
        this.setMaxStackSize(1);
        this.setMaxDamage(material.getDurability());
    }
}
