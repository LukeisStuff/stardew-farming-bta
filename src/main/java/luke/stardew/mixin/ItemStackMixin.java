package luke.stardew.mixin;

import com.mojang.nbt.tags.CompoundTag;
import luke.stardew.items.StardewItems;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.net.command.TextFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemStack.class, remap = false)
public abstract class ItemStackMixin {
	@Shadow
	public int itemID;

	@Shadow
	private CompoundTag tag;

	//TODO BlockFruit, not just ItemFruit

	@Inject(method = "getItemDescription", at = @At(value = "HEAD"), cancellable = true)
	public void addSpecialJarJamDesc(CallbackInfoReturnable<String> cir){
		if (itemID == StardewItems.JAR_JAM.id && tag.containsKey("itemIds")){
			I18n i18n = I18n.getInstance();

			StringBuilder strFinal = new StringBuilder();
			strFinal.append(i18n.translateKey("stardew.ingredients")).append(": ");

			String[] strings = tag.getString("itemIds").split(", ");
			for (String string : strings) {
				strFinal.append(i18n.translateNameKey(string)).append(", ");
			}

			String ingredients = strFinal.delete(strFinal.length() - 2, strFinal.length()).append("\n\n").toString();
			String desc = TextFormatting.formatted(Item.itemsList[this.itemID].getTranslatedDescription((ItemStack) (Object) this), TextFormatting.LIGHT_GRAY);

			cir.setReturnValue(ingredients + desc);
		}
	}
}
