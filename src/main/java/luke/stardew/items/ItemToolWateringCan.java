package luke.stardew.items;

import luke.stardew.WateringCanRegistry;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ItemToolWateringCan extends Item {
    public ItemToolWateringCan(String translationKey, String namespaceID, int id, ToolMaterial material) {
        super(translationKey, namespaceID, id);
        this.setMaxStackSize(1);
        this.setMaxDamage(material.getDurability());
    }

    @Override
    public boolean onUseOnBlock(@NotNull ItemStack selfStack, @NotNull World world, @org.jetbrains.annotations.Nullable Player player, @NotNull TilePosc blockPos, @NotNull Side side, double xHit, double yHit) {
        if (WateringCanRegistry.water(world, blockPos)) {
            selfStack.damageItem(1, player);

            if (!world.isClientSide) {
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, blockPos.x() + 0.5F, blockPos.y() + 0.5F, blockPos.z() + 0.5F, "liquid.splash", 0.2F, 1.0F);
            }

            return true;
        }

        return false;
    }

    @Override
    public void onUseByActivator(@NotNull ItemStack selfStack, @NotNull World world, @NotNull TileEntityActivator activator, @NotNull Random random, @NotNull TilePosc blockPos, @NotNull Direction direction, double offX, double offY, double offZ) {
        if (WateringCanRegistry.water(world, blockPos.add(direction, new TilePos()))) {
            selfStack.damageItem(1, null);

            if (!world.isClientSide) {
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, blockPos.x() + 0.5F, blockPos.y() + 0.5F, blockPos.z() + 0.5F, "liquid.splash", 0.2F, 1.0F);
            }
        }
    }
}
