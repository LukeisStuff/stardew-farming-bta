package luke.stardew.items;

import luke.stardew.interfaces.IPlayerEffects;
import luke.stardew.misc.PlayerEffect;
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

    public ItemCoffee(String name, String namespaceID, int id, int healAmount, int ticksPerHeal, Supplier<Item> bucket) {
        super(name, namespaceID, id, healAmount, ticksPerHeal, false, 1);
        this.bucketSupplier = bucket;
    }

    @Override
    public @Nullable ItemStack onUse(@NotNull ItemStack selfStack, @NotNull World world, @NotNull Player player) {
        super.onUse(selfStack, world, player);
        ((IPlayerEffects) player).stardew_farming_bta$addEffect(PlayerEffect.speedBoost, 240);
        return new ItemStack(bucketSupplier.get().asItem());
    }

}
