package luke.stardew.items;

import luke.stardew.interfaces.IEntityBobberMixin;
import net.minecraft.core.entity.EntityFishingBobber;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.HumanArmorShape;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemToolFishingRodTiered extends Item {

    public ItemToolFishingRodTiered(String translationKey, String namespaceID, int id, ToolMaterial material) {
        super(translationKey, namespaceID, id);
        this.setMaxStackSize(1);
        this.setMaxDamage(material.getDurability());
    }


    @Override
    public @Nullable ItemStack onUse(@NotNull ItemStack selfStack, @NotNull World world, @NotNull Player player) {
        ItemStack canSlot = player.inventory.armorItemInSlot(HumanArmorShape.LEGS);

        if (player.bobberEntity != null) {
            int damage = player.bobberEntity.yoink();
            selfStack.damageItem(damage, player);
        } else {
            world.playSoundAtEntity(player, player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!world.isClientSide) {
                // Create bobber entity
                EntityFishingBobber bobber = new EntityFishingBobber(world, player);
                world.entityJoinedWorld(bobber);
                player.bobberEntity = bobber; // Directly set the bobber to player
            }

            if (canSlot != null && ((canSlot.itemID == StardewItems.ARMOR_CAN_WORMS.id && canSlot.getMetadata() < canSlot.getMaxDamage()) || canSlot.itemID == StardewItems.ARMOR_CAN_WORMS_GOLD.id)) {
                if (canSlot.itemID == StardewItems.ARMOR_CAN_WORMS.id) {
                    player.inventory.armorItemInSlot(HumanArmorShape.LEGS).damageItem(1, player);
                }
                // Double check if bobberEntity is not null before casting and using it
                if (player.bobberEntity instanceof IEntityBobberMixin) {
                    ((IEntityBobberMixin) player.bobberEntity).stardew_farming_bta$setBait(true);
                }
            } else if (player.inventory.consumeInventoryItem(StardewItems.WORM.id) && player.bobberEntity instanceof IEntityBobberMixin) {
                ((IEntityBobberMixin) player.bobberEntity).stardew_farming_bta$setBait(true);
            }

        }

        player.swingItem();
        return selfStack;
    }
}
