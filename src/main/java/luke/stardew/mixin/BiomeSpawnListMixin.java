package luke.stardew.mixin;

import luke.stardew.entities.duck.MobDuck;
import luke.stardew.entities.goat.MobGoat;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = Biome.class, remap = false)
public class BiomeSpawnListMixin {
	@Shadow
	protected List<SpawnListEntry> spawnableCreatureList;

	@Inject(method = "<init>", at = @At("TAIL"))
	public void injectMethod(String key, CallbackInfo ci) {
		this.spawnableCreatureList.add(new SpawnListEntry(MobDuck.class, 51));
		this.spawnableCreatureList.add(new SpawnListEntry(MobGoat.class, 51));
	}
}
