package luke.stardew.misc;

import luke.stardew.items.StardewItems;
import net.minecraft.core.item.Item;

import java.util.HashMap;
import java.util.Map;

public class LookupCookingIngredients {
	public static final LookupCookingIngredients instance = new LookupCookingIngredients();
	protected final Map<Integer, Item> ingredientList = new HashMap<>();
	protected LookupCookingIngredients() {
		this.register();
	}
	protected void register() {
		LookupCookingIngredients.instance.addIngredientEntry(Item.foodPorkchopRaw.id, Item.foodPorkchopCooked);
		LookupCookingIngredients.instance.addIngredientEntry(StardewItems.foodSalmonRaw.id, StardewItems.foodSalmonCooked);
		LookupCookingIngredients.instance.addIngredientEntry(StardewItems.foodBassRaw.id, StardewItems.foodBassCooked);
		LookupCookingIngredients.instance.addIngredientEntry(StardewItems.foodSnapperRaw.id, StardewItems.foodSnapperCooked);
		LookupCookingIngredients.instance.addIngredientEntry(Item.eggChicken.id, StardewItems.eggCooked);
	}

	public void addIngredientEntry(int id, Item resultItem) {
		this.ingredientList.put(id, resultItem);
	}

	public Item getResults(int id) {
		return this.ingredientList.get(id) == null ? null : this.ingredientList.get(id);
	}

	public Map<Integer, Item> getIngredientList() {
		return this.ingredientList;
	}
}
