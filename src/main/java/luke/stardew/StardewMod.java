package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.entities.NetEntryTomato;
import luke.stardew.entities.StardewEntities;
import luke.stardew.entities.duck.NetEntryEggDuck;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.material.MaterialColor;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.item.Items;
import net.minecraft.core.net.entity.NetEntityHandler;
import net.minecraft.core.sound.SoundTypes;
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
        StardewConfig.init();
        StardewEntities.init();
        StardewBlocks.init();
        StardewItems.init();

        NetEntityHandler.registerNetworkEntry(new NetEntryEggDuck(), 300);
        NetEntityHandler.registerNetworkEntry(new NetEntryTomato(), 301);

        SoundTypes.loadSoundsJson(MOD_ID);
    }

    @Override
    public void afterGameStart() {
        StardewItems.FOOD_COFFEE.setContainerItem(Items.BUCKET);
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

        MaterialColor.registerManualBlockColor(StardewBlocks.THATCH, 0, MaterialColor.paintedYellow);
        MaterialColor.registerManualBlockColor(StardewBlocks.MUSHROOM_TRUFFLE, 0, MaterialColor.paintedBlack);
        MaterialColor.registerManualBlockColor(StardewBlocks.BLOCK_HONEY, 0, MaterialColor.paintedYellow);

        MaterialColor.registerManualBlockColor(StardewBlocks.CAULIFLOWER, 1, MaterialColor.paintedWhite);
        MaterialColor.registerManualBlockColor(StardewBlocks.CAULIFLOWER, 0, MaterialColor.paintedGreen);

        MaterialColor.registerManualBlockColor(StardewBlocks.LEAVES_APPLE_GOLDEN, 0, MaterialColor.paintedYellow);
        MaterialColor.registerManualBlockColor(StardewBlocks.LEAVES_APPLE_GOLDEN_FLOWERING, 0, MaterialColor.paintedYellow);
        MaterialColor.registerManualBlockColor(StardewBlocks.SAPLING_APPLE_GOLDEN, 0, MaterialColor.paintedYellow);

        MaterialColor.registerManualBlockColor(StardewBlocks.LEAVES_APPLE, 0, MaterialColor.paintedRed);
        MaterialColor.registerManualBlockColor(StardewBlocks.LEAVES_APPLE_FLOWERING, 0, MaterialColor.paintedRed);
        MaterialColor.registerManualBlockColor(StardewBlocks.SAPLING_APPLE, 0, MaterialColor.paintedRed);


        StardewBlocks.initializeCrops();
    }
}
