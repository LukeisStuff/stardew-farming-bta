package luke.stardew.mixin;

import luke.stardew.items.ItemCanOfWorms;
import luke.stardew.items.ItemCanOfWormsEndless;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.MobRendererBiped;
import net.minecraft.client.render.entity.MobRendererBipedArmored;
import net.minecraft.core.entity.IArmorWearing;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.enums.HumanArmorShape;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.useless.dragonfly.models.entity.StaticEntityModel;

@Environment(EnvType.CLIENT)
@Mixin(value = MobRendererBipedArmored.class, remap = false)
public abstract class PlayerRendererAddWormCans<T extends Mob & IArmorWearing<HumanArmorShape>> extends MobRendererBiped<T> {

    protected PlayerRendererAddWormCans(float shadowSize) {
        super(shadowSize);
    }

    @Inject(method = "getAndSetupModelForLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/item/ItemStack;getItem()Lnet/minecraft/core/item/Item;", shift = At.Shift.AFTER), cancellable = true)
    private void renderWormCan(T entity, float brightness, float partialTick, int layer, CallbackInfoReturnable<StaticEntityModel> cir) {
        if (layer != 3) return;

        ItemStack stack = entity.getItemInArmorSlot(HumanArmorShape.LEGS);
        if (stack == null) return;

        Item item = stack.getItem();
        if (!(item instanceof ItemCanOfWorms || item instanceof ItemCanOfWormsEndless)) return;

        String texture = (item instanceof ItemCanOfWormsEndless) ? "/assets/stardew/textures/armor/bait_golden.png" : "/assets/stardew/textures/armor/bait.png";
        this.bindTexture(texture);

        StaticEntityModel model = this.getModel("armor.leggings");
        cir.setReturnValue(this.setupAnimations(entity, model, partialTick, layer));
    }
}
