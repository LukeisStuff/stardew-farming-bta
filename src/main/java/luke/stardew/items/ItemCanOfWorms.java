package luke.stardew.items;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.HumanArmorShape;
import net.minecraft.core.item.IArmorItem;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.world.World;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;


public class ItemCanOfWorms extends Item implements IArmorItem<HumanArmorShape> {
    public ItemCanOfWorms(@NonNull String name, @NonNull String namespaceId, int id) {
        super(name, namespaceId, id);
        this.setMaxStackSize(1);
        this.setMaxDamage(192);
    }

    @Override
    public boolean hasInventoryInteraction() {
        return true;
    }

    @Override
    public @Nullable ItemStack onInventoryInteract(@NonNull Player player, @NonNull Slot slot, @Nullable ItemStack stackInSlot, boolean isItemGrabbed) {
        ItemStack quiverItem;
        if (isItemGrabbed) {
            quiverItem = player.inventory.getHeldItemStack();
        } else {
            quiverItem = stackInSlot;
        }

        assert quiverItem != null;

        int totalSpace = this.getMaxDamageForStack(stackInSlot);
        int arrowCount = this.getWormCount(quiverItem);
        int freeSpace = totalSpace - arrowCount;
        if (isItemGrabbed) {
            if (stackInSlot == null) {
                int amount = Math.min(64, arrowCount);
                if (amount > 0) {
                    ItemStack arrowStack = new ItemStack(StardewItems.WORM, amount, 0);
                    if (slot.mayPlace(arrowStack)) {
                        this.setWormCount(quiverItem, arrowCount - amount);
                        stackInSlot = arrowStack;
                    }
                }
            } else if (stackInSlot.itemID == StardewItems.WORM.id) {
                int amount = Math.min(freeSpace, stackInSlot.stackSize);
                if (amount > 0) {
                    this.setWormCount(quiverItem, arrowCount + amount);
                    stackInSlot.stackSize -= amount;
                }
            }
        } else {
            ItemStack grabbedItem = player.inventory.getHeldItemStack();
            if (grabbedItem != null && grabbedItem.itemID == StardewItems.WORM.id) {
                int amount = Math.min(grabbedItem.stackSize, freeSpace);
                if (amount > 0) {
                    grabbedItem.stackSize -= amount;
                    this.setWormCount(quiverItem, arrowCount + amount);
                    if (grabbedItem.stackSize <= 0) {
                        player.inventory.setHeldItemStack(null);
                    }
                }
            } else if (grabbedItem == null) {
                int amount = Math.min(64, arrowCount);
                if (amount > 0) {
                    this.setWormCount(quiverItem, arrowCount - amount);
                    player.inventory.setHeldItemStack(new ItemStack(StardewItems.WORM, amount, 0));
                }
            }
        }

        return stackInSlot;
    }

    @Override
    public @Nullable ItemStack onUse(@NonNull ItemStack selfStack, @NonNull World world, @NonNull Player player) {
        HumanArmorShape humanArmorShape = this.getArmorShape();
        ItemStack currentArmorInSlot = player.getItemInArmorSlot(humanArmorShape);
        player.setItemInArmorSlot(humanArmorShape, selfStack);
        return currentArmorInSlot;
    }

    private int getWormCount(@NonNull ItemStack stack) {
        return stack.getMaxDamage() - stack.getMetadata();
    }

    private void setWormCount(@NonNull ItemStack stack, int count) {
        stack.setMetadata(stack.getMaxDamage() - count);
    }

    @Override
    public boolean showFullDurability() {
        return true;
    }

    public @Nullable ArmorMaterial getArmorMaterial() {
        return null;
    }

    public @NonNull HumanArmorShape getArmorShape() {
        return HumanArmorShape.LEGS;
    }
}
