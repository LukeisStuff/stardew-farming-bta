package luke.stardew.entities;

import luke.stardew.entities.duck.ProjectileEggDuck;
import luke.stardew.entities.duck.MobDuck;
import luke.stardew.entities.goat.MobGoat;
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
        var helper = new EntityHelper();
        helper.createEntity(MobDuck.class, NamespaceID.getPermanent(MOD_ID, "duck"), null);
        helper.createEntity(MobGoat.class, NamespaceID.getPermanent(MOD_ID, "goat"),null);
        helper.createEntity(ProjectileEggDuck.class, NamespaceID.getPermanent(MOD_ID, "duck_egg"), null);

        helper.createEntity(ProjectileTomato.class, NamespaceID.getPermanent(MOD_ID, "tomato"), null);
    }
}
