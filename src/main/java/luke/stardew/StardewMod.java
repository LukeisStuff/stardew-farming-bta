package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.blocks.beehive.GuidebookSectionBeehive;
import luke.stardew.blocks.beehive.RecipeEntryBeehive;
import luke.stardew.entities.StardewEntities;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.guidebook.GuidebookSections;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.ItemInitEntrypoint;

import static net.minecraft.core.data.registry.Registries.NAMESPACES;

public class StardewMod implements ModInitializer, GameStartEntrypoint, ItemInitEntrypoint {
    public static final String MOD_ID = "stardew";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Stardew Farming initialized.");
    }

    @Override
    public void beforeGameStart() {
        Registries.RECIPE_TYPES.register("stardew:beehive", RecipeEntryBeehive.class);
        NAMESPACES.register("stardew", "stardew");

        StardewConfig.init();
        StardewEntities.init();
        StardewBlocks.init();
        StardewItems.init();

        SoundTypes.loadSoundsJson(MOD_ID);
    }

    @Override
    public void afterGameStart() {
        GuidebookSections.register(new GuidebookSectionBeehive("guidebook.section.beehive", new ItemStack(StardewBlocks.BEEHIVE_ACTIVE), 0x606060, 0x00A29C));
    }

    @Override
    public void afterItemInit() {
        LookupFuelFurnace.instance.addFuelEntry(StardewItems.FIBER.id, 200);
        LookupFuelFurnace.instance.addFuelEntry(StardewBlocks.THATCH.id(), 300);

        LookupFuelFurnace.instance.addFuelEntry(StardewBlocks.LOG_APPLE.id(), 300);
        LookupFuelFurnace.instance.addFuelEntry(StardewBlocks.LOG_APPLE_GOLDEN.id(), 300);

        LookupFuelFurnace.instance.addFuelEntry(StardewBlocks.BEEHIVE_ACTIVE.id(), 300);
        LookupFuelFurnace.instance.addFuelEntry(StardewBlocks.BEEHIVE_IDLE.id(), 300);

        StardewBlocks.initializeCrops();
    }
}
