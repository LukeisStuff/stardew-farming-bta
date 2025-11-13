package luke.stardew.entities;

import luke.stardew.entities.duck.EntityEggDuck;
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

	public static String entityKey(String string) {
		return MOD_ID + ".entity." + string;
	}

	public static void initializeEntities() {
		EntityHelper.createEntity(MobDuck.class, NamespaceID.getPermanent(MOD_ID, "duck"), entityKey("duck"));
		EntityHelper.createEntity(MobGoat.class, NamespaceID.getPermanent(MOD_ID, "goat"), entityKey("goat"));
		EntityHelper.createEntity(EntityEggDuck.class, NamespaceID.getPermanent(MOD_ID, "duck_egg"), entityKey("duck.egg"));
	}
}
