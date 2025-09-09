package luke.stardew.mixin;

import luke.stardew.achievements.StardewAchievements;
import luke.stardew.interfaces.IPlayerEffects;
import luke.stardew.misc.PlayerEffect;
import net.minecraft.core.achievement.stat.Stat;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(value = Player.class, remap = false)
public abstract class PlayerMixin extends Mob implements IPlayerEffects {

	@Shadow
	public abstract void addStat(Stat statbase, int i);

	@Shadow
	protected float baseSpeed;

	@Shadow
	public abstract void triggerAchievement(Stat statbase);

	@Unique
	public Map<PlayerEffect, Integer> currentEffects = new HashMap<>();

	public PlayerMixin(World world) {
		super(world);
	}

	@Override
	public void stardew_farming_bta$addEffect(PlayerEffect effect, int tick) {
		currentEffects.put(effect, tick);
	}

	@Unique
	public void decreaseEffects() {
		for (Map.Entry<PlayerEffect, Integer> entry : currentEffects.entrySet()) {
			entry.setValue(entry.getValue() - 1);
			if (entry.getValue() <= 0) {
				currentEffects.remove(entry.getKey());
			}
		}
	}

	@Inject(method = "tick", at = @At("HEAD"))
	public void addStardew(CallbackInfo ci) {
		this.triggerAchievement(StardewAchievements.STARDEW);
	}

	@Inject(method = "onLivingUpdate", at = @At(value = "FIELD", target = "Lnet/minecraft/core/entity/player/Player;speed:F", shift = At.Shift.AFTER))
	public void boostSpeed(CallbackInfo ci) {
		decreaseEffects();
		if (currentEffects.containsKey(PlayerEffect.speedBoost)) {
			this.speed = baseSpeed + PlayerEffect.speedBoost.getSpeedIncrement();
		}
	}
}
