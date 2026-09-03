package luke.stardew.mixin;

import luke.stardew.interfaces.IEntityBobberMixin;
import luke.stardew.items.StardewItems;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.HumanArmorShape;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFishingRod;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemFishingRod.class, remap = false)
public abstract class ItemFishingRodMixin extends Item {

    private ItemFishingRodMixin(@NotNull NamespaceID namespaceId, @NotNull String translationKey, int id) {
        super(namespaceId, translationKey, id);
    }

    @Inject(method = "onUse", at = @At(value = "TAIL"))
    public void addBaitFunctions(ItemStack selfStack, World world, Player player, CallbackInfoReturnable<ItemStack> cir) {
        if (player.bobberEntity != null) {
            ItemStack canSlot = player.getItemInArmorSlot(HumanArmorShape.LEGS);

            if (player.inventory.consumeInventoryItem(StardewItems.WORM.id)) {
                ((IEntityBobberMixin) player.bobberEntity).stardew_farming_bta$setBait(true);
            } else if (canSlot != null) {
                if (canSlot.getItem().equals(StardewItems.ARMOR_CAN_OF_WORMS_GOLDEN)) {
                    ((IEntityBobberMixin) player.bobberEntity).stardew_farming_bta$setBait(true);
                } else if (canSlot.getItem().equals(StardewItems.ARMOR_CAN_OF_WORMS) && canSlot.getMetadata() < canSlot.getMaxDamage()) {
                    canSlot.damageItem(1, player);
                    ((IEntityBobberMixin) player.bobberEntity).stardew_farming_bta$setBait(true);
                }
            }
        }
    }
}
