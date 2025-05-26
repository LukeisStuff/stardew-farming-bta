package luke.stardew.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.core.enums.EnumOS;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.halplibe.helper.network.NetworkHandler;
import turniplabs.halplibe.util.*;

@Mixin(value = Minecraft.class, remap = false)

public abstract class MinecraftMixin {
    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/achievement/stat/StatList;init()V"))
    public void initStats(CallbackInfo ci) {
        ItemsAccessor.invokeInitStats();
    }
}
