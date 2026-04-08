package luke.stardew.items;

import luke.stardew.entities.duck.ProjectileEggDuck;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.IDispensable;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Random;

public class ItemEggDuck extends Item implements IDispensable {
    public ItemEggDuck(@NonNull String name, @NonNull String namespaceId, int id) {
        super(name, namespaceId, id);
        this.maxStackSize = 16;
    }

    @Override
    public @Nullable ItemStack onUse(@NonNull ItemStack selfStack, @NonNull World world, @NonNull Player player) {
        selfStack.consumeItem(player);
        world.playSoundAtEntity(player, player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
            world.entityJoinedWorld(new ProjectileEggDuck(world, player));
        }

        return selfStack;
    }

    @Override
    public void onUseByActivator(@NonNull ItemStack selfStack, @NonNull World world, @NonNull TileEntityActivator activator, @NonNull Random random, @NonNull TilePosc blockPos, @NonNull Direction direction, double offX, double offY, double offZ) {
        ProjectileEggDuck projectileEgg = new ProjectileEggDuck(world, (double) blockPos.x() + offX, (double) blockPos.y() + offY, (double) blockPos.z() + offZ);
        projectileEgg.setHeading((double) direction.getOffsetX() * 0.6, direction.getOffsetY() == 0 ? 0.1 : (double) direction.getOffsetY() * 0.6, (float) direction.getOffsetZ() * 0.6F, 1.1F, 6.0F);
        world.entityJoinedWorld(projectileEgg);
        --selfStack.stackSize;
    }

    public void onDispensed(@NonNull ItemStack selfStack, @NonNull World world, @NonNull Random random, @NonNull Direction direction, double x, double y, double z) {
        ProjectileEggDuck projectileEgg = new ProjectileEggDuck(world, x, y, z);
        projectileEgg.setHeading(direction.getOffsetX(), (double) direction.getOffsetY() + 0.1, direction.getOffsetZ(), 1.1F, 6.0F);
        world.entityJoinedWorld(projectileEgg);
    }
}
