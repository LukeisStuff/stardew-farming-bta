package luke.stardew.mixin;

import luke.stardew.items.StardewItems;
import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.entity.MobRendererPlayer;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.client.render.model.ModelBiped;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MobRendererPlayer.class, remap = false)
public abstract class PlayerRendererMixin extends MobRenderer<Player> {

	@Shadow
	@Final
	private ModelBiped modelArmorChestplate;

	public PlayerRendererMixin(ModelBase model, float shadowSize) {
		super(model, shadowSize);
	}

	@Inject(method = "prepareArmor(Lnet/minecraft/core/entity/player/Player;IF)Z", at = @At("HEAD"), cancellable = true)
	public void addCanOfWormsRender(Player entity, int renderPass, float partialTick, CallbackInfoReturnable<Boolean> cir) {
		ItemStack itemstack = entity.inventory.armorItemInSlot(3 - renderPass);
		if (itemstack != null) {
			Item item = itemstack.getItem();
			if (item.equals(StardewItems.ARMOR_CAN_OF_WORMS)) {
				this.bindTexture("/assets/stardew/armor/canOfWorms.png");
				ModelBiped modelbiped = this.modelArmorChestplate;
				modelbiped.legRight.visible = renderPass == 2 || renderPass == 3;
				modelbiped.legLeft.visible = false;
				this.setArmorModel(modelbiped);
				cir.setReturnValue(true);
			}
			if (item == StardewItems.ARMOR_CAN_OF_WORMS_GOLDEN) {
				this.bindTexture("/assets/stardew/armor/canOfWorms_golden.png");
				ModelBiped modelbiped = this.modelArmorChestplate;
				modelbiped.legRight.visible = renderPass == 2 || renderPass == 3;
				modelbiped.legLeft.visible = false;
				this.setArmorModel(modelbiped);
				cir.setReturnValue(true);
			}
		}
	}
}
