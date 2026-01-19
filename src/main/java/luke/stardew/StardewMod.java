package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.entities.StardewEntities;
import luke.stardew.entities.duck.NetEntryEggDuck;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.sound.SoundTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.ItemInitEntrypoint;

import static net.minecraft.core.net.entity.NetEntityHandler.registerNetworkEntry;

public class StardewMod implements ModInitializer, GameStartEntrypoint, ItemInitEntrypoint {
    public static final String MOD_ID = "stardew";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Stardew Farming initialized.");
    }

    @Override
    public void beforeGameStart() {
        StardewConfig.init();
        StardewEntities.init();
        StardewBlocks.init();
        StardewItems.init();

        registerNetworkEntry(new NetEntryEggDuck(), 300);

        SoundTypes.loadSoundsJson(MOD_ID);
    }

    @Override
    public void afterGameStart() {
    }

    @Override
    public void afterItemInit() {
        LookupFuelFurnace.instance.addFuelEntry(StardewItems.FIBER.id, 200);
        LookupFuelFurnace.instance.addFuelEntry(StardewBlocks.THATCH.id(), 300);

        LookupFuelFurnace.instance.addFuelEntry(StardewBlocks.LOG_APPLE.id(), 300);
        LookupFuelFurnace.instance.addFuelEntry(StardewBlocks.LOG_APPLE_GOLDEN.id(), 300);

        LookupFuelFurnace.instance.addFuelEntry(StardewBlocks.BEEHIVE.id(), 300);
        LookupFuelFurnace.instance.addFuelEntry(StardewBlocks.BEEHIVE_HONEY.id(), 300);
        LookupFuelFurnace.instance.addFuelEntry(StardewBlocks.BEEHIVE_IDLE.id(), 300);

        StardewBlocks.initializeCrops();
    }
}
