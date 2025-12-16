package luke.stardew.mixin;

import luke.stardew.items.ItemCanOfWorms;
import luke.stardew.items.ItemCanOfWormsEndless;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.entity.MobRendererPlayer;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.client.render.model.ModelBiped;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.IArmorItem;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(value = MobRendererPlayer.class, remap = false)
public abstract class PlayerRendererAddWormCans extends MobRenderer<Player> {

    @Shadow
    @Final
    private ModelBiped modelArmorChestplate;

    protected PlayerRendererAddWormCans(ModelBase model, float shadowSize) {
        super(model, shadowSize);
    }

    @Inject(method = "prepareArmor(Lnet/minecraft/core/entity/player/Player;IF)Z", at = @At("HEAD"), cancellable = true)
    private void renderWormCans(Player player, int renderPass, float partialTick, CallbackInfoReturnable<Boolean> cir) {
        ItemStack stack = (renderPass > 3) ? player.inventory.armorInventory[renderPass] : player.inventory.armorItemInSlot(3 - renderPass);
        if (stack == null) return;

        Item item = stack.getItem();
        if (!(item instanceof ItemCanOfWorms) && !(item instanceof ItemCanOfWormsEndless)) return;

        IArmorItem armor = (IArmorItem) item;
        if (renderPass <= 3 && armor.getArmorPiece() != 3 - renderPass) return;

        ModelBiped model = this.modelArmorChestplate;
        model.head.visible = false;
        model.hair.visible = false;
        model.body.visible = false;
        model.armLeft.visible = false;
        model.armRight.visible = false;
        model.legLeft.visible = false;
        model.legRight.visible = false;

        if (item instanceof ItemCanOfWorms) {
            this.bindTexture("/assets/stardew/textures/armor/bait.png");
        } else {
            this.bindTexture("/assets/stardew/textures/armor/bait_golden.png");
        }

        model.legRight.visible = true;
        this.setArmorModel(model);
        cir.setReturnValue(true);
    }
}
