package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import luke.stardew.misc.LookupCookingIngredients;
import luke.stardew.render.TileEntityRendererStove;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.AchievementHelper;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.achievements.AchievementPage;


public class StardewMod implements ModInitializer, GameStartEntrypoint, ClientStartEntrypoint {
    public static final String MOD_ID = "stardew";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	static {
		SoundHelper.addSound(MOD_ID, "bee.ogg");
		SoundHelper.addStreaming(MOD_ID, "axolotl.ogg");
	}


	@Override
    public void onInitialize() {
        LOGGER.info("Stardew Farming initialized.");
    }

	@Override
	public void beforeGameStart() {
		new StardewBlocks().initializeBlocks();
		new StardewItems().initilizeItems();
		new StardewBlocks().initializeTiles();

		AchievementPage STARDEWACHIEVEMENTS;
		STARDEWACHIEVEMENTS = new StardewAchievements();
		AchievementHelper.addPage(STARDEWACHIEVEMENTS);
	}

	@Override
	public void afterGameStart() {
	}

	@Override
	public void beforeClientStart() {

	}

	@Override
	public void afterClientStart() {

	}
}
