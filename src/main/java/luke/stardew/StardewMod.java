package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.entities.StardewEntities;
import luke.stardew.entities.duck.EntityDuck;
import luke.stardew.entities.goat.EntityGoat;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.enums.EnumCreatureType;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.AchievementHelper;
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

		SoundHelper.addSound(MOD_ID, "duckidle1.ogg");
		SoundHelper.addSound(MOD_ID, "duckidle2.ogg");
		SoundHelper.addSound(MOD_ID, "duckhurt1.ogg");
		SoundHelper.addSound(MOD_ID, "duckhurt2.ogg");
		SoundHelper.addSound(MOD_ID, "duckdeath1.ogg");

		SoundHelper.addSound(MOD_ID, "goatidle1.ogg");
		SoundHelper.addSound(MOD_ID, "goatidle2.ogg");
		SoundHelper.addSound(MOD_ID, "goatidle3.ogg");

		SoundHelper.addSound(MOD_ID, "goatdeath1.ogg");
		SoundHelper.addSound(MOD_ID, "goatdeath2.ogg");
		SoundHelper.addSound(MOD_ID, "goatdeath3.ogg");
	}


	@Override
    public void onInitialize() {
		for (Biome b : Registries.BIOMES) {
			b.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(EntityDuck.class, 51));
			b.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(EntityGoat.class, 51));
		}

        LOGGER.info("Stardew Farming initialized.");
    }

	@Override
	public void beforeGameStart() {
		new StardewBlocks().initializeBlocks();
		new StardewItems().initilizeItems();
		new StardewEntities().initializeEntities();

		AchievementPage STARDEWACHIEVEMENTS;
		STARDEWACHIEVEMENTS = new StardewAchievements();
		AchievementHelper.addPage(STARDEWACHIEVEMENTS);
	}

	@Override
	public void afterGameStart() {
		MobInfoRegistry.register(EntityDuck.class, "guidebook.section.mob.duck.name", "guidebook.section.mob.duck.desc",
			4, 10, new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(new ItemStack(Item.featherChicken), 1.0f, 0, 1)});

		MobInfoRegistry.register(EntityGoat.class, "guidebook.section.mob.goat.name", "guidebook.section.mob.goat.desc",
			10, 10, new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(new ItemStack(Item.leather), 1.0f, 0, 2), new MobInfoRegistry.MobDrop(new ItemStack(Block.wool), 1.0f, 1, 2)});

	}

	@Override
	public void beforeClientStart() {

	}

	@Override
	public void afterClientStart() {

	}
}
