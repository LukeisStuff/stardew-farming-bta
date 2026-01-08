package luke.stardew.items;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemJam extends ItemFood {
    public final int healAmount;

    public ItemJam(String name, String namespaceID, int id, int healAmount, int ticksPerHeal, boolean favouriteWolfMeat, int maxStackSize) {
        super(name, namespaceID, id, healAmount, ticksPerHeal, favouriteWolfMeat, maxStackSize);
        this.healAmount = healAmount;
        this.maxStackSize = maxStackSize;
    }

    @Override
    public @Nullable ItemStack onUse(@NotNull ItemStack selfStack, @NotNull World world, @NotNull Player player) {
        if (player.getHealth() < player.getMaxHealth() && selfStack.consumeItem(player)) {
            player.heal(this.healAmount);
            player.inventory.insertItem(new ItemStack(Items.JAR, 1), true);
        }
        return selfStack;
    }

}
