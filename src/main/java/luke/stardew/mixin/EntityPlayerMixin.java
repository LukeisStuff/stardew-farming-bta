package luke.stardew.mixin;

import luke.stardew.StardewAchievements;
import luke.stardew.StardewMod;
import luke.stardew.items.ItemToolFishingRodTiered;
import luke.stardew.items.StardewItems;
import net.minecraft.core.achievement.stat.Stat;
import net.minecraft.core.entity.EntityBobber;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EntityPlayer.class, remap = false)
public abstract class EntityPlayerMixin extends EntityLiving {

	@Shadow
	public abstract void addStat(Stat statbase, int i);

	public EntityPlayerMixin(World world) {
		super(world);
	}

	@Inject(method = "tick", at = @At(value = "HEAD"), cancellable = true)
	public void tick(CallbackInfo ci) {
		this.addStat(StardewAchievements.STARDEW, 1);
	}
}
