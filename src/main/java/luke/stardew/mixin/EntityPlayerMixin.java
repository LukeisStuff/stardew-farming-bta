package luke.stardew.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.mojang.nbt.CompoundTag;
import luke.stardew.StardewMod;
import luke.stardew.items.ItemToolFishingRodTiered;
import luke.stardew.items.StardewItems;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityBobber;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.TextureHelper;

@Mixin(value = EntityPlayer.class, remap = false)
public abstract class EntityPlayerMixin extends EntityLiving {

	@Shadow
	public EntityBobber fishEntity = null;

	@Shadow
	public abstract void remove();

	public EntityPlayerMixin(World world) {
		super(world);
	}

	@Inject(method = "getItemIcon", at = @At(value = "HEAD"), cancellable = true)
	private void changingTieredFishingRodTexture(ItemStack itemstack, CallbackInfoReturnable cir){
		if (itemstack.getItem().getClass() == ItemToolFishingRodTiered.class && this.fishEntity != null) {
			if (itemstack.itemID == StardewItems.toolFishingRodIron.id) {
				cir.setReturnValue(TextureHelper.getOrCreateItemTextureIndex(StardewMod.MOD_ID, "fishingrod_iron_active.png"));
			}
			if (itemstack.itemID == StardewItems.toolFishingRodGold.id) {
				cir.setReturnValue(TextureHelper.getOrCreateItemTextureIndex(StardewMod.MOD_ID, "fishingrod_gold_active.png"));
			}
			if (itemstack.itemID == StardewItems.toolFishingRodDiamond.id) {
				cir.setReturnValue(TextureHelper.getOrCreateItemTextureIndex(StardewMod.MOD_ID, "fishingrod_diamond_active.png"));
			}
			if (itemstack.itemID == StardewItems.toolFishingRodSteel.id) {
				cir.setReturnValue(TextureHelper.getOrCreateItemTextureIndex(StardewMod.MOD_ID, "fishingrod_steel_active.png"));
			}

		}
	}
}
