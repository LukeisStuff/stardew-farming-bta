package luke.stardew.mixin;

import luke.stardew.items.StardewItems;
import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.client.render.model.ModelBiped;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PlayerRenderer.class, remap = false)
public class PlayerRendererMixin extends LivingRenderer<EntityPlayer> {

	@Shadow
	@Final
	private ModelBiped modelArmorChestplate;

	public PlayerRendererMixin(ModelBase model, float shadowSize) {
		super(model, shadowSize);
	}

	@Inject(method = "setArmorModel", at = @At("HEAD"), cancellable = true)
	private void addCanOfWormsRender(EntityPlayer entity, int renderPass, float partialTick, CallbackInfoReturnable<Boolean> cir) {
		ItemStack itemstack = entity.inventory.armorItemInSlot(3 - renderPass);
		if (itemstack != null) {
			Item item = itemstack.getItem();
			if (item == StardewItems.armorCanOfWorms) {
				this.loadTexture("/assets/stardew/armor/canOfWorms.png");
				ModelBiped modelbiped = this.modelArmorChestplate;
				modelbiped.bipedRightLeg.showModel = renderPass == 2 || renderPass == 3;
				modelbiped.bipedLeftLeg.showModel = false;
				this.setRenderPassModel(modelbiped);
				cir.setReturnValue(true);
			}
			if (item == StardewItems.armorCanOfWormsGolden) {
				this.loadTexture("/assets/stardew/armor/canOfWorms_golden.png");
				ModelBiped modelbiped = this.modelArmorChestplate;
				modelbiped.bipedRightLeg.showModel = renderPass == 2 || renderPass == 3;
				modelbiped.bipedLeftLeg.showModel = false;
				this.setRenderPassModel(modelbiped);
				cir.setReturnValue(true);
			}
		}
	}
}
