package luke.stardew.items;

import luke.stardew.interfaces.IEntityBobberMixin;
import net.minecraft.core.entity.EntityFishingBobber;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.HumanArmorShape;
import net.minecraft.core.enums.IArmorShape;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.world.World;

public class ItemToolFishingRodTiered extends Item {

    public ItemToolFishingRodTiered(String translationKey, String namespaceID, int id, ToolMaterial material) {
        super(translationKey, namespaceID, id);
        this.setMaxStackSize(1);
        this.setMaxDamage(material.getDurability());
    }

    @Override
    public ItemStack onUse(ItemStack itemstack, World world, Player entityplayer) {
        ItemStack canSlot = entityplayer.inventory.armorItemInSlot(HumanArmorShape.LEGS);

        if (entityplayer.bobberEntity != null) {
            int damage = entityplayer.bobberEntity.yoink();
            itemstack.damageItem(damage, entityplayer);
        } else {
            world.playSoundAtEntity(entityplayer, entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!world.isClientSide) {
                // Create bobber entity
                EntityFishingBobber bobber = new EntityFishingBobber(world, entityplayer);
                world.entityJoinedWorld(bobber);
                entityplayer.bobberEntity = bobber; // Directly set the bobber to entityplayer
            }

            if (canSlot != null && ((canSlot.itemID == StardewItems.ARMOR_CAN_OF_WORMS.id && canSlot.getMetadata() < canSlot.getMaxDamage()) || canSlot.itemID == StardewItems.ARMOR_CAN_OF_WORMS_GOLDEN.id)) {
                if (canSlot.itemID == StardewItems.ARMOR_CAN_OF_WORMS.id) {
                    entityplayer.inventory.armorItemInSlot(HumanArmorShape.LEGS).damageItem(1, entityplayer);
                }
                // Double check if bobberEntity is not null before casting and using it
                if (entityplayer.bobberEntity instanceof IEntityBobberMixin) {
                    ((IEntityBobberMixin) entityplayer.bobberEntity).stardew_farming_bta$setBait(true);
                }
            } else if (entityplayer.inventory.consumeInventoryItem(StardewItems.WORM.id) && entityplayer.bobberEntity instanceof IEntityBobberMixin) {
                ((IEntityBobberMixin) entityplayer.bobberEntity).stardew_farming_bta$setBait(true);
            }

        }

        entityplayer.swingItem();
        return itemstack;
    }
}
