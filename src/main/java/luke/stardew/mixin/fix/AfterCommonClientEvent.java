package luke.stardew.mixin.fix;

import luke.stardew.StardewClient;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Minecraft.class, priority = 1100)
public abstract class AfterCommonClientEvent{

    @Inject(method = "startGame", at = @At("TAIL"))
    private void afterCommonClientEvent(CallbackInfo ci){
        StardewClient.afterCommonClientStart();
    }
}
