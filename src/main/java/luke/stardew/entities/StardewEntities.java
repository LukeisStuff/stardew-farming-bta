package luke.stardew.entities;

import luke.stardew.StardewConfig;
import luke.stardew.entities.duck.DuckRenderer;
import luke.stardew.entities.duck.EntityDuck;
import luke.stardew.entities.duck.EntityEggDuck;
import luke.stardew.entities.duck.ModelDuck;
import luke.stardew.entities.goat.EntityGoat;
import luke.stardew.entities.goat.GoatRenderer;
import luke.stardew.entities.goat.ModelGoat;
import luke.stardew.items.StardewItems;
import net.minecraft.client.render.entity.SnowballRenderer;
import turniplabs.halplibe.helper.EntityHelper;

public class StardewEntities {
	public int entityID(String entityName) {
		return StardewConfig.cfg.getInt("Entity IDs." + entityName);
	}

	public void initializeEntities() {
		EntityHelper.createEntity(EntityDuck.class, 300, "Duck", () -> new DuckRenderer(new ModelDuck(), 0.4f));
		EntityHelper.createEntity(EntityGoat.class, 302, "Goat", () -> new GoatRenderer(new ModelGoat(), 0.7f));

		EntityHelper.createEntity(EntityEggDuck.class, 301, "DuckEgg", () -> new SnowballRenderer(StardewItems.eggDuck));
	}
}
