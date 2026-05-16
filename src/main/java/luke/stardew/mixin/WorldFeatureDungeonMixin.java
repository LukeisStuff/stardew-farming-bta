package luke.stardew.mixin;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.WeightedRandomBag;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.world.generate.feature.WorldFeatureDungeon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WorldFeatureDungeon.class, remap = false)
public abstract class WorldFeatureDungeonMixin {
    @Shadow
    public WeightedRandomBag<WeightedRandomLootObject> chestLoot;

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    public void addLoot(CallbackInfo ci) {
        this.chestLoot.addEntry(new WeightedRandomLootObject(StardewBlocks.SAPLING_APPLE.getDefaultStack()), 50);
    }
}
