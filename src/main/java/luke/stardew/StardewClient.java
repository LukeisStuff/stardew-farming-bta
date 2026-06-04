package luke.stardew;

import luke.stardew.achievements.AchievementPageStardew;
import luke.stardew.achievements.StardewAchievements;
import luke.stardew.entities.duck.MobDuck;
import luke.stardew.entities.goat.MobGoat;
import luke.stardew.gui.hud.MoneyHudElement;
import luke.stardew.items.StardewItems;
import luke.stardew.particles.ParticleBee;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.achievements.data.AchievementPages;
import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.client.gui.hud.component.ComponentAnchor;
import net.minecraft.client.gui.hud.component.HudComponents;
import net.minecraft.client.gui.hud.component.layout.LayoutSnap;
import net.minecraft.client.render.particle.Particle;
import net.minecraft.client.render.particle.ParticleEntry;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.client.sound.SoundRepository;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import sunsetsatellite.catalyst.effects.api.effect.render.EffectRenderer;
import sunsetsatellite.catalyst.effects.api.effect.render.EffectRendererDispatcher;
import turniplabs.halplibe.helper.ParticleHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;

import java.io.IOException;
import java.net.URISyntaxException;

import static luke.stardew.StardewMod.LOGGER;
import static luke.stardew.StardewMod.MOD_ID;

@Environment(EnvType.CLIENT)
public class StardewClient implements ClientModInitializer, ClientStartEntrypoint {

    public static MoneyHudElement MONEY_HUD_COMPONENT;

    @Override
    public void beforeClientStart() {

        ParticleHelper.createParticle(
            "bee",
            new ParticleEntry() {
                @Override
                public Particle newParticle(@NotNull World world, double x, double y, double z, double xa, double ya, double za, int i) {
                    return new ParticleBee(world, x, y, z, xa, ya, za);
                }
            }
        );

        SoundRepository.namespaceAdded(MOD_ID);

        try {
            TextureRegistry.initializeAllFiles(MOD_ID, TextureRegistry.worldAtlas, false);
        } catch (URISyntaxException | IOException e) {
            LOGGER.error("Failed to initialize textures!");
        }
    }

    @Override
    public void afterClientStart() {
        MONEY_HUD_COMPONENT = HudComponents.register(
            new MoneyHudElement("stadew.money", 0, 0, new LayoutSnap(HudComponents.CROSSHAIR, ComponentAnchor.BOTTOM_RIGHT, ComponentAnchor.TOP_LEFT))
        );

        EffectRendererDispatcher.getInstance().addDispatch(
            StardewMod.TWEAKED_ON_COFFEE_EFFECT,
            (
                new EffectRenderer<>(StardewMod.TWEAKED_ON_COFFEE_EFFECT))
                .setIcon(TextureRegistry.getTexture(StardewItems.FOOD_COFFEE.namespaceID)
            )
        );

        initAchievementsPage();

        MobInfoRegistry.register(MobDuck.class, "guidebook.section.mob.duck.name", "guidebook.section.mob.duck.desc",
            4, 10, new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(new ItemStack(Items.FEATHER_CHICKEN), 1.0f, 0, 1)});

        MobInfoRegistry.register(MobGoat.class, "guidebook.section.mob.goat.name", "guidebook.section.mob.goat.desc",
            10, 10, new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(new ItemStack(Items.LEATHER), 1.0f, 0, 2), new MobInfoRegistry.MobDrop(new ItemStack(Blocks.WOOL), 1.0f, 1, 2)});
    }

    public static void initAchievementsPage() {
        AchievementPageStardew page = new AchievementPageStardew(MOD_ID, StardewItems.WATERING_CAN.getDefaultStack());
        page.addAchievement(StardewAchievements.STARDEW, 0, 0);
        page.addAchievement(StardewAchievements.FRUIT, 2, -2);
        page.addAchievement(StardewAchievements.VEGETABLE, -2, -2);
        page.addAchievement(StardewAchievements.EGG, 0, -4);
        page.addAchievement(StardewAchievements.APPLE, -2, 0);
        page.addAchievement(StardewAchievements.GAPPLE, -4, 1);
        page.addAchievement(StardewAchievements.BEEHIVE, 2, 0);
        page.addAchievement(StardewAchievements.CANDLE, 4, -1);
        page.addAchievement(StardewAchievements.AMATEUR_FISHER, 0, 2);
        page.addAchievement(StardewAchievements.MASTER_FISHER, 1, 4);
        AchievementPages.register(page);
    }

    @Override
    public void onInitializeClient() {
        LOGGER.info("Stardew Farming client initialized.");
    }
}
