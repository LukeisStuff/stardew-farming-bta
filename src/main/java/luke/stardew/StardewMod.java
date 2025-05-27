package luke.stardew;

import luke.stardew.achievements.StardewAchievements;
import luke.stardew.blocks.StardewBlocks;
import luke.stardew.entities.StardewEntities;
import luke.stardew.entities.duck.MobDuck;
import luke.stardew.entities.fx.ParticleBee;
import luke.stardew.entities.goat.MobGoat;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.client.sound.SoundRepository;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.enums.MobCategory;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.ParticleHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.ItemInitEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

import java.io.IOException;
import java.net.URISyntaxException;


public class StardewMod implements ModInitializer, GameStartEntrypoint, ClientStartEntrypoint, ItemInitEntrypoint, RecipeEntrypoint {
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

		//AchievementPages.register(new StardewAchievements());
	}

	@Override
	public void afterGameStart() {
		MobInfoRegistry.register(MobDuck.class, "guidebook.section.mob.duck.name", "guidebook.section.mob.duck.desc",
			4, 10, new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(new ItemStack(Items.FEATHER_CHICKEN), 1.0f, 0, 1)});

		MobInfoRegistry.register(MobGoat.class, "guidebook.section.mob.goat.name", "guidebook.section.mob.goat.desc",
			10, 10, new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(new ItemStack(Items.LEATHER), 1.0f, 0, 2), new MobInfoRegistry.MobDrop(new ItemStack(Blocks.WOOL), 1.0f, 1, 2)});
	}

	@Override
	public void beforeClientStart() {
		ParticleHelper.createParticle("bee", (world, x, y, z, xa, ya, za, id) -> new ParticleBee(world, x, y, z, xa, ya, za));

		SoundRepository.registerNamespace(MOD_ID);

		try {
			TextureRegistry.initializeAllFiles(MOD_ID, TextureRegistry.particleAtlas, false);
		} catch (URISyntaxException | IOException e) {
			LOGGER.error("Failed to initialize textures!");
		}
	}

	@Override
	public void afterClientStart() {

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

		StardewAchievements.init();
	}

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}
}
