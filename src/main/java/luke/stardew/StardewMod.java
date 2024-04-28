package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.TextureHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class StardewMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "stardew";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final int[] floweringOverlayApple = TextureHelper.getOrCreateBlockTexture(MOD_ID, "overlayApple.png");
	public static final int[] floweringOverlayFlower = TextureHelper.getOrCreateBlockTexture(MOD_ID, "overlayAppleFlower.png");


	static {
		TextureHelper.getOrCreateItemTexture(MOD_ID, "bee.png");
	}


	@Override
    public void onInitialize() {
        LOGGER.info("Stardew Farming initialized.");
    }

	@Override
	public void beforeGameStart() {
		new StardewBlocks().initializeBlocks();
		new StardewItems().initilizeItems();
	}

	@Override
	public void afterGameStart() {
		new StardewRecipes().initializeRecipes();
	}

	@Override
	public void onRecipesReady() {

	}
}
