package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.world.generate.feature.WorldFeatureFlowers;
import useless.terrainapi.api.TerrainAPI;
import useless.terrainapi.generation.overworld.OverworldConfig;
import useless.terrainapi.generation.overworld.api.ChunkDecoratorOverworldAPI;

import static luke.stardew.StardewMod.MOD_ID;

public class TerrainApiPlugin implements TerrainAPI {
	@Override
	public String getModID() {
		return MOD_ID;
	}
	public static final OverworldConfig overworldConfig = ChunkDecoratorOverworldAPI.overworldConfig;
	@Override
	public void onInitialize() {

		ChunkDecoratorOverworldAPI.randomFeatures.addFeatureSurface(new WorldFeatureFlowers(StardewBlocks.bush.id), 4);

	}
}

