package luke.stardew.mixin;

import com.mojang.nbt.CompoundTag;
import luke.stardew.items.StardewItems;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.command.TextFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemStack.class, remap = false)
public abstract class ItemStackMixin {
	@Shadow
	public int itemID;

	@Shadow
	private CompoundTag tag;

	//TODO BlockFruit, not just ItemFruit

	@Redirect(method = "getItemDescription", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/item/Item;getTranslatedDescription(Lnet/minecraft/core/item/ItemStack;)Ljava/lang/String;"))
	private String addSpecialJarJamDesc(Item instance, ItemStack itemstack){
		if (itemID == StardewItems.jarJam.id && tag.containsKey("itemIds")){
			String[] str = tag.getString("itemIds").split("#");
			StringBuilder strFinal = new StringBuilder();
			strFinal.append("Ingredients: ");
			for (String string : str){
				Item item = Item.itemsList[Integer.parseInt(string)];
				strFinal.append(item.getTranslatedName(new ItemStack(item))).append(", ");
			}
			strFinal.delete(strFinal.length() - 2, strFinal.length());
			strFinal.append("\n").append(Item.itemsList[itemID].getTranslatedDescription(itemstack));
			return strFinal.toString();
		}else {
			return Item.itemsList[itemID].getTranslatedDescription(itemstack);
		}
	}
}
