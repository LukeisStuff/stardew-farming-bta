package luke.stardew.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import luke.stardew.WorldFeatureCauliflower;
import luke.stardew.WorldFeatureMelon;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.chunk.perlin.overworld.ChunkDecoratorOverworld;
import net.minecraft.core.world.generate.feature.WorldFeatureFlowers;
import net.minecraft.core.world.generate.feature.WorldFeaturePumpkin;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(value = ChunkDecoratorOverworld.class, remap = false)
public abstract class ChunkDecoratorOverworldMixin {
	@Shadow
	@Final
	private World world;

	@Inject(method = "decorate", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 104))
	public void generateCauliflower(Chunk chunk, CallbackInfo ci, @Local(name = "rand")Random rand, @Local(name = "x") int x, @Local(name = "z") int z) {
		if (rand.nextInt(64) == 0) {
			int xf = x + rand.nextInt(16) + 8;
			int zf = z + rand.nextInt(16) + 8;
			int yf = this.world.getHeightValue(x, z);
			(new WorldFeatureCauliflower()).place(this.world, rand, xf, yf, zf);
		}

		if (rand.nextInt(64) == 0) {
			int xf = x + rand.nextInt(16) + 8;
			int zf = z + rand.nextInt(16) + 8;
			int yf = this.world.getHeightValue(x, z);
			(new WorldFeatureMelon()).place(this.world, rand, xf, yf, zf);
		}

		if (rand.nextInt(12) == 0) {
			int xf = x + rand.nextInt(16) + 8;
			int zf = z + rand.nextInt(16) + 8;
			int yf = this.world.getHeightValue(x, z);
			(new WorldFeatureFlowers(StardewBlocks.BUSH.id(), 128, false)).place(this.world, rand, xf, yf, zf);
		}
	}
}
