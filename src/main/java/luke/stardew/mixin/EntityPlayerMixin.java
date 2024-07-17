package luke.stardew.mixin;

import luke.stardew.StardewAchievements;
import net.minecraft.core.achievement.stat.Stat;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityPlayer.class, remap = false)
public abstract class EntityPlayerMixin extends EntityLiving {

	@Shadow
	public abstract void addStat(Stat statbase, int i);

	public EntityPlayerMixin(World world) {
		super(world);
	}

	@Inject(method = "tick", at = @At(value = "HEAD"))
	public void tick(CallbackInfo ci) {
		this.addStat(StardewAchievements.STARDEW, 1);
		//ci.cancel();
	}
}
