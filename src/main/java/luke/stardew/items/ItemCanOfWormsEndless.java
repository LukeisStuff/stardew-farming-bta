package luke.stardew.items;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.HumanArmorShape;
import net.minecraft.core.item.IArmorItem;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.world.World;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ItemCanOfWormsEndless extends Item implements IArmorItem<HumanArmorShape> {
    public ItemCanOfWormsEndless(@NonNull String name, @NonNull String namespaceId, int id) {
        super(name, namespaceId, id);
        this.setMaxStackSize(1);
    }

    @Override
    public @Nullable ItemStack onUse(@NonNull ItemStack selfStack, @NonNull World world, @NonNull Player player) {
        HumanArmorShape humanArmorShape = this.getArmorShape();
        ItemStack currentArmorInSlot = player.getItemInArmorSlot(humanArmorShape);
        player.setItemInArmorSlot(humanArmorShape, selfStack);
        return currentArmorInSlot;
    }

    public @Nullable ArmorMaterial getArmorMaterial() {
        return null;
    }

    public @NonNull HumanArmorShape getArmorShape() {
        return HumanArmorShape.LEGS;
    }
}
