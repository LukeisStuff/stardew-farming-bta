package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.entities.StardewEntities;
import luke.stardew.entities.duck.MobDuck;
import luke.stardew.entities.goat.MobGoat;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.enums.MobCategory;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.ItemInitEntrypoint;


public class StardewMod implements ModInitializer, GameStartEntrypoint, ItemInitEntrypoint {
    public static final String MOD_ID = "stardew";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
    public void onInitialize() {
		for (Biome b : Registries.BIOMES) {
			b.getSpawnableList(MobCategory.creature).add(new SpawnListEntry(MobDuck.class, 51));
			b.getSpawnableList(MobCategory.creature).add(new SpawnListEntry(MobGoat.class, 51));
		}

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
		StardewItems.initilizeItems();

		StardewBlocks.WATERMELON.asItem().withTags(StardewItems.IS_FRUIT);
		Items.FOOD_APPLE.withTags(StardewItems.IS_FRUIT);
		Items.FOOD_CHERRY.withTags(StardewItems.IS_FRUIT);

		LookupFuelFurnace.instance.addFuelEntry(StardewBlocks.THATCH.id(), 400);

		StardewBlocks.initializeCrops();
	}
}
