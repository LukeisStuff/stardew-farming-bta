package luke.stardew.mixin;

import luke.stardew.interfaces.IEntityBobberMixin;
import luke.stardew.items.StardewItems;
import net.minecraft.core.entity.EntityFishingBobber;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.HumanArmorShape;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFishingRod;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemFishingRod.class, remap = false)
public abstract class ItemFishingRodMixin extends Item {

    protected ItemFishingRodMixin(@NonNull NamespaceID namespaceId, @NonNull String translationKey, int id) {
        super(namespaceId, translationKey, id);
    }

    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void onUse(ItemStack selfStack, World world, Player player, CallbackInfoReturnable<ItemStack> cir) {
        if (player.bobberEntity != null) {
            ItemStack canSlot = player.inventory.armorItemInSlot(HumanArmorShape.LEGS);

            if (canSlot != null
                && ((canSlot.itemID == StardewItems.ARMOR_CAN_WORMS.id
                && canSlot.getMetadata() < canSlot.getMaxDamage()) || canSlot.itemID == StardewItems.ARMOR_CAN_WORMS_GOLD.id)) {
                if (canSlot.itemID == StardewItems.ARMOR_CAN_WORMS.id) {
                    canSlot.damageItem(1, player);
                }
                ((IEntityBobberMixin) player.bobberEntity).stardew_farming_bta$setBait(true);
            } else if (player.inventory.consumeInventoryItem(StardewItems.WORM.id)) {
                ((IEntityBobberMixin) player.bobberEntity).stardew_farming_bta$setBait(true);
            }
            int damage = player.bobberEntity.yoink();
            selfStack.damageItem(damage, player);
        } else {
            world.playSoundAtEntity(player, player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!world.isClientSide) {
                world.entityJoinedWorld(new EntityFishingBobber(world, player));
            }
        }

        player.swingItem();
        cir.setReturnValue(selfStack);
    }
}
