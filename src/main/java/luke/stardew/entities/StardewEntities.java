package luke.stardew.entities;

import luke.stardew.entities.duck.MobDuck;
import luke.stardew.entities.duck.ProjectileEggDuck;
import luke.stardew.entities.goat.MobGoat;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.util.collection.NamespaceID;

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
        EntityDispatcher.getInstance().addMapping(MobDuck.class, NamespaceID.fromPool(MOD_ID, "duck"), MobDuck::new, "guidebook.section.mob.duck.name");
        EntityDispatcher.getInstance().addMapping(MobGoat.class, NamespaceID.fromPool(MOD_ID, "goat"), MobGoat::new, "guidebook.section.mob.goat.name");
        EntityDispatcher.getInstance().addMapping(ProjectileEggDuck.class, NamespaceID.fromPool(MOD_ID, "egg_duck"), ProjectileEggDuck::new);
    }
}
