package luke.stardew.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MinecraftServer.class, remap = false)
public abstract class MinecraftServerMixin {
	@Inject(method = "startServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/achievement/stat/StatList;init()V"))
	public void initStats(CallbackInfoReturnable<Boolean> cir) {
		ItemsAccessor.invokeInitStats();
	}
}
