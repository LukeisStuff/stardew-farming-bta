package luke.stardew.entities;

import luke.stardew.StardewConfig;
import luke.stardew.entities.duck.EntityDuck;
import luke.stardew.entities.duck.EntityEggDuck;
import luke.stardew.entities.goat.MobGoat;
import net.minecraft.core.util.collection.NamespaceID;
import turniplabs.halplibe.helper.EntityHelper;

import static luke.stardew.StardewMod.MOD_ID;

public class StardewEntities {
	public int entityID(String entityName) {
		return StardewConfig.cfg.getInt("Entity IDs." + entityName);
	}

	public String entityKey(String string) {
		return MOD_ID + ".entity." + string;
	}

	public void initializeEntities() {
		//, () -> new DuckRenderer(new ModelDuck(), 0.4f)
		EntityHelper.createEntity(EntityDuck.class, NamespaceID.getPermanent(MOD_ID, "duck"), entityKey("duck"));
		//, () -> new GoatRenderer(new ModelGoat(), 0.7f)
		EntityHelper.createEntity(MobGoat.class, NamespaceID.getPermanent(MOD_ID, "goat"), entityKey("goat"));
		//() -> new SnowballRenderer(StardewItems.eggDuck)
		EntityHelper.createEntity(EntityEggDuck.class, NamespaceID.getPermanent(MOD_ID, "duck_egg"), entityKey("duck.egg"));
	}
}
