package luke.stardew.items;

import luke.stardew.entities.duck.ProjectileEggDuck;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.IDispensable;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ItemEggDuck extends Item implements IDispensable {
    public ItemEggDuck(String translationKey, String namespaceID, int id) {
        super(translationKey, namespaceID, id);
        this.maxStackSize = 16;
    }

    @Override
    public @Nullable ItemStack onUse(@NotNull ItemStack selfStack, @NotNull World world, @NotNull Player player) {
        selfStack.consumeItem(player);
        world.playSoundAtEntity(player, player, "random.bow", 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
        if (!world.isClientSide) {
            world.entityJoinedWorld(new ProjectileEggDuck(world, player));
        }
        return selfStack;
    }

    @Override
    public void onDispensed(@NotNull ItemStack itemStack, @NotNull World world, @NotNull Random random, @NotNull Direction direction, double x, double y, double z) {
        ProjectileEggDuck egg = new ProjectileEggDuck(world, x, y, z);
        egg.setHeading(direction.getOffsetX(), 0.1, direction.getOffsetZ(), 1.1f, 6.0f);
        world.entityJoinedWorld(egg);
    }
}
