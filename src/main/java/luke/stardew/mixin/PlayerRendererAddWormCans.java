package luke.stardew.mixin;

import luke.stardew.items.ItemCanOfWorms;
import luke.stardew.items.ItemCanOfWormsEndless;
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
public abstract class PlayerRendererAddWormCans extends MobRenderer<Player> {

	@Shadow
	@Final
	private ModelBiped modelArmorChestplate;

	public PlayerRendererAddWormCans(ModelBase model, float shadowSize) {
		super(model, shadowSize);
	}

	@Inject(method = "prepareArmor(Lnet/minecraft/core/entity/player/Player;IF)Z", at = @At("HEAD"), cancellable = true)
	public void addCanOfWormsRender(Player player, int renderPass, float partialTick, CallbackInfoReturnable<Boolean> info) {
		ItemStack armorStack = player.inventory.armorInventory[renderPass];
		if (armorStack == null) return;
		Item item = armorStack.getItem();

		if (item instanceof ItemCanOfWorms && renderPass == 3) {
			String path = "/assets/minecraft/textures/armor/quiver.png";
			this.bindTexture(path);
			ModelBiped modelbiped = this.modelArmorChestplate;
			modelbiped.legRight.visible = true;
			modelbiped.legLeft.visible = false;
			this.setArmorModel(modelbiped);
			info.setReturnValue(true);
			return;
		}
		if (item instanceof ItemCanOfWormsEndless && renderPass == 3) {
			String path = "/assets/minecraft/textures/armor/quiver_golden.png";
			this.bindTexture(path);
			ModelBiped modelbiped = this.modelArmorChestplate;
			modelbiped.legRight.visible = true;
			modelbiped.legLeft.visible = false;
			this.setArmorModel(modelbiped);
			info.setReturnValue(true);
        }
	}
}
