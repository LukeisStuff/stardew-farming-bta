package luke.stardew.entities;

import luke.stardew.entities.duck.ProjectileEggDuck;
import luke.stardew.entities.duck.MobDuck;
import luke.stardew.entities.goat.MobGoat;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.util.collection.NamespaceID;
import turniplabs.halplibe.helper.EntityHelper;

import static luke.stardew.StardewMod.MOD_ID;

public final class StardewEntities {
    private static boolean hasInit = false;

    public static void init() {
        if (!hasInit) {
            hasInit = true;
            initializeEntities();
        }

    }

    public static void initializeEntities() {
        EntityDispatcher dispatcher = EntityDispatcher.getInstance();

        dispatcher.addMapping(
            MobDuck.class,
            NamespaceID.fromPool(MOD_ID, "duck"),
            MobDuck::new,
            "guidebook.section.mob.stardew.duck.name"
        );

        dispatcher.addMapping(
            MobGoat.class,
            NamespaceID.fromPool(MOD_ID, "goat"),
            MobGoat::new,
            "guidebook.section.mob.stardew.goat.name"
        );

        dispatcher.addMapping(
            ProjectileEggDuck.class,
            NamespaceID.fromPool(MOD_ID, "duck_egg"),
            ProjectileEggDuck::new,
            "guidebook.section.mob.stardew.duck_egg.name"
        );
    }
}
