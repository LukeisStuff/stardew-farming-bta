package luke.stardew.items;

import luke.stardew.entities.ProjectileTomato;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.IDispensable;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ItemTomato extends ItemFood implements IDispensable {
    public ItemTomato(String name, String namespaceId, int id, int healAmount, int ticksPerHeal, boolean favouriteWolfMeat, int maxStackSize) {
        super(name, namespaceId, id, healAmount, ticksPerHeal, favouriteWolfMeat, maxStackSize);
    }

    @Override
    public ItemStack onUse(ItemStack itemstack, World world, Player player) {
        if (player.getHealth() < player.getMaxHealth() && player.getHealth() + player.getTotalHealingRemaining() < player.getMaxHealth()) {
            if (itemstack.consumeItem(player)) {
                player.eatFood(itemstack);
                world.playSoundAtEntity(player, player, this.getTicksPerHeal(itemstack) >= 10 ? "random.bite_extended" : "random.bite", 0.5F + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.1F, 1.1F + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.1F);
            }
        } else {
            itemstack.consumeItem(player);
            world.playSoundAtEntity(player, player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!world.isClientSide) {
                world.entityJoinedWorld(new ProjectileTomato(world, player));
            }
        }

        return itemstack;
    }

    @Override
    public void onUseByActivator(@NotNull ItemStack selfStack, @NotNull World world, @NotNull TileEntityActivator activator, @NotNull Random random, @NotNull TilePosc blockPos, @NotNull Direction direction, double offX, double offY, double offZ) {
       ProjectileTomato projectileTomato = new ProjectileTomato(world, (double) blockPos.x() + offX, (double) blockPos.y() + offY, (double) blockPos.z() + offZ);
        projectileTomato.setHeading((double) direction.getOffsetX() * 0.6, direction.getOffsetY() == 0 ? 0.1 : (double) direction.getOffsetY() * 0.6, (float) direction.getOffsetZ() * 0.6F, 1.1F, 6.0F);
        world.entityJoinedWorld(projectileTomato);
        --selfStack.stackSize;
    }

    @Override
    public void onDispensed(@NotNull ItemStack itemStack, @NotNull World world, @NotNull Random random, @NotNull Direction direction, double x, double y, double z) {
        ProjectileTomato projectileTomato = new ProjectileTomato(world, x, y, z);
        projectileTomato.setHeading(direction.getOffsetX(), (double) direction.getOffsetY() + 0.1, direction.getOffsetZ(), 1.1F, 6.0F);
        world.entityJoinedWorld(projectileTomato);
    }
}
