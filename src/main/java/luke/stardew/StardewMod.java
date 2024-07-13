package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.Texture;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.entity.player.EntityPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.AchievementHelper;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.achievements.AchievementPage;


public class StardewMod implements ModInitializer, GameStartEntrypoint, ClientStartEntrypoint {
    public static final String MOD_ID = "stardew";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	static {
		//TODO bee png for fx
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
