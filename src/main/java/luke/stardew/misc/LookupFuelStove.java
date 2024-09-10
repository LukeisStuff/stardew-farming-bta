package luke.stardew.misc;

import net.minecraft.core.item.Item;

import java.util.HashMap;
import java.util.Map;

public class LookupFuelStove {
	public static final LookupFuelStove instance = new LookupFuelStove();
	protected final Map<Integer, Integer> fuelList = new HashMap<>();

	protected LookupFuelStove() {
		this.register();
	}

	protected void register() {
		this.addFuelEntry(Item.coal.id, 1600);
		this.addFuelEntry(Item.nethercoal.id, 6400);
		this.addFuelEntry(Item.bucketLava.id, 20000);
	}

	public void addFuelEntry(int id, int fuelYield) {
		this.fuelList.put(id, fuelYield);
	}

	public int getFuelYield(int id) {
		return this.fuelList.get(id) == null ? 0 : (Integer)this.fuelList.get(id);
	}

	public Map<Integer, Integer> getFuelList() {
		return this.fuelList;
	}
}
