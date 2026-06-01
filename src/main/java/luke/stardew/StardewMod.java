package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import luke.stardew.entities.NetEntryTomato;
import luke.stardew.entities.StardewEntities;
import luke.stardew.entities.duck.NetEntryEggDuck;
import luke.stardew.items.StardewItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Global;
import net.minecraft.core.block.material.MaterialColor;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.net.entity.NetEntityHandler;
import net.minecraft.core.sound.SoundTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sunsetsatellite.catalyst.effects.api.attribute.Attributes;
import sunsetsatellite.catalyst.effects.api.attribute.type.IntAttribute;
import sunsetsatellite.catalyst.effects.api.effect.Effect;
import sunsetsatellite.catalyst.effects.api.effect.EffectTimeType;
import sunsetsatellite.catalyst.effects.api.effect.Effects;
import sunsetsatellite.catalyst.effects.api.modifier.ModifierType;
import sunsetsatellite.catalyst.effects.api.modifier.type.IntModifier;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.ItemInitEntrypoint;

import java.util.List;

import static net.minecraft.core.data.registry.Registries.NAMESPACES;

public class StardewMod implements ModInitializer, GameStartEntrypoint, ItemInitEntrypoint {
    public static final String MOD_ID = "stardew";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static IntAttribute TWEAK_SPEED_ATTRIBUTE = (IntAttribute) new IntAttribute("attribute.stardew.tweak_speed", 0).setAsDefault();
    public static Effect TWEAKED_ON_COFFEE_EFFECT;

    @Override
    public void onInitialize() {
        LOGGER.info("Stardew Farming initialized.");
    }

    @Override
    public void beforeGameStart() {
        NAMESPACES.register(MOD_ID, MOD_ID);

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
        Attributes.getInstance().register("stardew:tweak_speed", TWEAK_SPEED_ATTRIBUTE);

        TWEAKED_ON_COFFEE_EFFECT = new Effect(
            "stardew.effect.tweaked_out",
            "stardew:tweaked",
            List.of(
                new IntModifier(TWEAK_SPEED_ATTRIBUTE, ModifierType.ADD, 1)
            ),
            EffectTimeType.ADD,
            4
        )
        .setDefaultDuration(Global.TICKS_PER_SECOND * 20)
        .setDurationIncrease(Global.TICKS_PER_SECOND * 20);


        Effects.getInstance().register(TWEAKED_ON_COFFEE_EFFECT.id, TWEAKED_ON_COFFEE_EFFECT);
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
