package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.blocks.model.StardewModels;
import luke.stardew.entities.StardewEntities;
import luke.stardew.entities.duck.EntityDuck;
import luke.stardew.entities.goat.EntityGoat;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.client.sound.SoundRepository;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.enums.MobCategory;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.GameStartEntrypoint;


public class StardewMod implements ModInitializer, GameStartEntrypoint, ClientStartEntrypoint {
    public static final String MOD_ID = "stardew";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
    public void onInitialize() {
		for (Biome b : Registries.BIOMES) {
			b.getSpawnableList(MobCategory.creature).add(new SpawnListEntry(EntityDuck.class, 51));
			b.getSpawnableList(MobCategory.creature).add(new SpawnListEntry(EntityGoat.class, 51));
		}

        LOGGER.info("Stardew Farming initialized.");
    }

	@Override
	public void beforeGameStart() {
		new StardewBlocks().initializeBlocks();
		new StardewItems().initilizeItems();
		new StardewEntities().initializeEntities();

		//FIXME INSTANCE IS NULL
		//AchievementPages.register(new StardewAchievements());
	}

	@Override
	public void afterGameStart() {
		MobInfoRegistry.register(EntityDuck.class, "guidebook.section.mob.duck.name", "guidebook.section.mob.duck.desc",
			4, 10, new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(new ItemStack(Items.FEATHER_CHICKEN), 1.0f, 0, 1)});

		MobInfoRegistry.register(EntityGoat.class, "guidebook.section.mob.goat.name", "guidebook.section.mob.goat.desc",
			10, 10, new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(new ItemStack(Items.LEATHER), 1.0f, 0, 2), new MobInfoRegistry.MobDrop(new ItemStack(Blocks.WOOL), 1.0f, 1, 2)});
	}

	@Override
	public void beforeClientStart() {
		SoundRepository.registerNamespace(MOD_ID);
	}

	@Override
	public void afterClientStart() {
	}
}
