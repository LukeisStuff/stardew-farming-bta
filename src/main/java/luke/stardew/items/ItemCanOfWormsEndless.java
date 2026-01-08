package luke.stardew.items;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.HumanArmorShape;
import net.minecraft.core.item.IArmorItem;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemCanOfWormsEndless extends Item implements IArmorItem<HumanArmorShape> {
    public ItemCanOfWormsEndless(@NotNull String name, @NotNull String namespaceId, int id) {
        super(name, namespaceId, id);
        this.setMaxStackSize(1);
    }

    @Override
    public @Nullable ItemStack onUse(@NotNull ItemStack selfStack, @NotNull World world, @NotNull Player player) {
        HumanArmorShape humanArmorShape = this.getArmorShape();
        ItemStack currentArmorInSlot = player.getItemInArmorSlot(humanArmorShape);
        player.setItemInArmorSlot(humanArmorShape, selfStack);
        return currentArmorInSlot;
    }

    public @Nullable ArmorMaterial getArmorMaterial() {
        return null;
    }

    public @NotNull HumanArmorShape getArmorShape() {
        return HumanArmorShape.LEGS;
    }
}
