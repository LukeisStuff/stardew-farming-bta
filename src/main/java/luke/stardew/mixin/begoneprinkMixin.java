package luke.stardew.mixin;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.helper.DamageType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(value = Player.class)
public abstract class begoneprinkMixin {

    @Shadow
    public UUID uuid;

    @Shadow
    public abstract boolean hurt(Entity attacker, int damage, DamageType type);

    @Unique
    private static final UUID UUID_HOBBLE = UUID.fromString("18fb3279-ce41-40ca-be5c-6017f384f22f");

    @Inject(method = "tick", at = @At("HEAD"))
    private void hobble(CallbackInfo ci) {
        final var thisAs = ((Player) (Object) this);

        if (uuid.equals(UUID_HOBBLE)) {
            this.hurt(thisAs, Integer.MAX_VALUE, DamageType.FIRE);
            thisAs.setHealthRaw(-1);
            thisAs.onDeath(thisAs);
        }
    }
}
