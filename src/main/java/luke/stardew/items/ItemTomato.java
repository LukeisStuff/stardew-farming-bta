package luke.stardew.items;

import luke.stardew.entities.ProjectileTomato;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.IDispensable;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.world.World;

import java.util.Random;

public class ItemTomato extends ItemFood implements IDispensable {
    public ItemTomato(String name, String namespaceId, int id, int healAmount, int ticksPerHeal, boolean favouriteWolfMeat, int maxStackSize) {
        super(name, namespaceId, id, healAmount, ticksPerHeal, favouriteWolfMeat, maxStackSize);
    }

    @Override
    public ItemStack onUseItem(ItemStack itemstack, World world, Player player) {
        if (player.getHealth() < player.getMaxHealth() && player.getHealth() + player.getTotalHealingRemaining() < player.getMaxHealth()) {
            if (itemstack.consumeItem(player)) {
                player.eatFood(this);
                world.playSoundAtEntity(player, player, this.getTicksPerHeal() >= 10 ? "random.bite_extended" : "random.bite", 0.5F + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.1F, 1.1F + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.1F);
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
    public void onUseByActivator(ItemStack itemStack, TileEntityActivator activatorBlock, World world, Random random, int blockX, int blockY, int blockZ, double offX, double offY, double offZ, Direction direction) {
        ProjectileTomato projectileTomato = new ProjectileTomato(world, (double) blockX + offX, (double) blockY + offY, (double) blockZ + offZ);
        projectileTomato.setHeading((double) direction.getOffsetX() * 0.6, direction.getOffsetY() == 0 ? 0.1 : (double) direction.getOffsetY() * 0.6, (float) direction.getOffsetZ() * 0.6F, 1.1F, 6.0F);
        world.entityJoinedWorld(projectileTomato);
        --itemStack.stackSize;
    }

    public void onDispensed(ItemStack itemStack, World world, double x, double y, double z, int xOffset, int yOffset, int zOffset, Random random) {
        ProjectileTomato projectileTomato = new ProjectileTomato(world, x, y, z);
        projectileTomato.setHeading(xOffset, (double) yOffset + 0.1, zOffset, 1.1F, 6.0F);
        world.entityJoinedWorld(projectileTomato);
    }
}
