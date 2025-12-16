package luke.stardew.items;

import luke.stardew.entities.duck.EntityEggDuck;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.IDispensable;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

import java.util.Random;

public class ItemEggDuck extends Item implements IDispensable {
    public ItemEggDuck(String translationKey, String namespaceID, int id) {
        super(translationKey, namespaceID, id);
        this.maxStackSize = 16;
    }

    @Override
    public ItemStack onUseItem(ItemStack itemstack, World world, Player player) {
        itemstack.consumeItem(player);
        world.playSoundAtEntity(player, player, "random.bow", 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
        if (!world.isClientSide) {
            world.entityJoinedWorld(new EntityEggDuck(world, player));
        }
        return itemstack;
    }

    @Override
    public void onDispensed(ItemStack stack, World world, double x, double y, double z, int xOffset, int yOffset, int zOffset, Random random) {
        EntityEggDuck egg = new EntityEggDuck(world, x, y, z);
        egg.setHeading(xOffset, 0.1, zOffset, 1.1f, 6.0f);
        world.entityJoinedWorld(egg);
    }
}
