package luke.stardew.items;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

import java.util.ArrayList;
import java.util.List;

public class ItemJam extends ItemFood {
	private final int healAmount;
	private List<Item> ingredients = new ArrayList<>();

	public ItemJam(String name, int id, int healAmount, int ticksPerHeal, boolean favouriteWolfMeat, int maxStackSize) {
		super(name, id, healAmount, ticksPerHeal, favouriteWolfMeat, maxStackSize);
		this.healAmount = healAmount;
		this.maxStackSize = maxStackSize;
	}

	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (entityplayer.getHealth() < entityplayer.getMaxHealth() && itemstack.consumeItem(entityplayer)) {
			entityplayer.heal(this.healAmount);
			entityplayer.inventory.insertItem(new ItemStack(Item.jar, 1), true);
		}
			return itemstack;
		}

	public int getHealAmount() {
		return this.healAmount;
	}

	public void setIngredients(List<Item> items){
		ingredients.addAll(items);
	}

	@Override
	public String getTranslatedDescription(ItemStack itemstack) {
		StringBuilder ingredientList = new StringBuilder();
		int originalSize = ingredients.size();
		for (int i = 1; i < originalSize; i++) {
			ingredients.remove(ingredients.size() - 1);
		}
		for (Item ingredient : ingredients) {
			ingredientList.append(getTranslatedName(new ItemStack(ingredient))).append(" | ");
		}
		return ingredientList + " ///// " + super.getTranslatedDescription(itemstack);
	}
}
