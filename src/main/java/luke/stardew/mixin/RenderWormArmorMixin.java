package luke.stardew.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import luke.stardew.items.StardewItems;
import net.minecraft.client.render.entity.MobRendererBiped;
import net.minecraft.client.render.entity.MobRendererBipedArmored;
import net.minecraft.core.entity.IArmorWearing;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.enums.HumanArmorShape;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.useless.dragonfly.models.entity.StaticEntityModel;

@Mixin(MobRendererBipedArmored.class)
public abstract class RenderWormArmorMixin<T extends Mob & IArmorWearing<HumanArmorShape>> extends MobRendererBiped<T> {
    public RenderWormArmorMixin(float shadowSize) {
        super(shadowSize);
    }


    @WrapMethod(method = "getAndSetupModelForLayer")
    public @Nullable StaticEntityModel renderWorms(@NotNull T entity, float brightness, float partialTick, int layer, Operation<StaticEntityModel> original) {
        ItemStack itemstack = entity.getItemInArmorSlot(HumanArmorShape.LEGS);

        if (layer == 0 || itemstack == null) return original.call(entity, brightness, partialTick, layer);

        var item = itemstack.getItem();

        if (item.equals(StardewItems.ARMOR_CAN_OF_WORMS)) {
            this.bindTexture("/assets/stardew/textures/armor/bait.png");
        }

        if (item.equals(StardewItems.ARMOR_CAN_OF_WORMS_GOLDEN)) {
            this.bindTexture("/assets/stardew/textures/armor/bait_golden.png");
        }

        StaticEntityModel model;
        switch (layer) {
            case 1 -> model = this.getModel("armor.helmet");
            case 2 -> model = this.getModel("armor.chestplate");
            case 3 -> model = this.getModel("armor.leggings");
            case 4 -> model = this.getModel("armor.boots");
            default -> {
                return null;
            }
        }

        return this.setupAnimations(entity, model, partialTick, layer);
    }
}
