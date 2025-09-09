package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.entities.StardewEntities;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.crafting.LookupFuelFurnace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.ItemInitEntrypoint;


public class StardewMod implements ModInitializer, GameStartEntrypoint, ItemInitEntrypoint {
	public static final String MOD_ID = "stardew";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Stardew Farming initialized.");
	}

	@Override
	public void beforeGameStart() {
		new StardewEntities().initializeEntities();
	}

	@Override
	public void afterGameStart() {
	}

	@Override
	public void afterItemInit() {
		//Initialize here because Blocks and items may refer to vanilla items
		StardewBlocks.initializeBlocks();
		StardewItems.initializeItems();

		LookupFuelFurnace.instance.addFuelEntry(StardewBlocks.THATCH.id(), 400);

		StardewBlocks.initializeCrops();
	}
}
