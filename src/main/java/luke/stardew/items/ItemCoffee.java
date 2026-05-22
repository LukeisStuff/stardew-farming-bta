package luke.stardew.items;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ItemCoffee extends ItemFood {

    private final Supplier<Item> bucketSupplier;

    public ItemCoffee(String name, String namespaceID, int id, int capacity, int healAmount, int ticksPerHeal, Supplier<Item> bucket) {
        super(name, namespaceID, id, healAmount, ticksPerHeal, false, 1);
        this.bucketSupplier = bucket;
        this.setMaxDamage(capacity);
    }

    @Override
    public @Nullable ItemStack onUse(@NotNull ItemStack selfStack, @NotNull World world, @NotNull Player player) {
        if (player.getHealth() >= player.getMaxHealth()) return selfStack;
        if (player.getHealth() + player.getTotalHealingRemaining() > player.getMaxHealth()) return selfStack;

        player.eatFood(selfStack);

        selfStack.damageItem(1, player);

        world.playSoundAtEntity(
            player, player,
            this.getTicksPerHeal(selfStack) >= 10 ? "random.bite_extended" : "random.bite",
            0.5F + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.1F,
            1.1F + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.1F
        );

        //((IPlayerEffects) player).stardew_farming_bta$addEffect(PlayerEffect.speedBoost, 240);

        if (selfStack.getMetadata() >= this.getMaxDamage()) {
            return new ItemStack(bucketSupplier.get().asItem());
        }

        return selfStack;
    }

}
