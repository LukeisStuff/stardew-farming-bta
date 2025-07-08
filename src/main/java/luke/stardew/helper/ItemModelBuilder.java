package luke.stardew.helper;

import luke.stardew.items.models.ItemModelExtended;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class ItemModelBuilder extends ModelBuilder<Item, ItemModel, ItemModelBuilder> {
    public boolean full3D = false;
    public boolean fullbright = false;
    public boolean rotateWhenRendering = false;
    public boolean pointInFrontOfPlayer = false;
    public @NotNull String stringFormat = "%s";
	public final List<String> extendedMap = new ArrayList<>();
    ItemModelBuilder(String modID, ItemModelDispatcher dispatcher) {
        super(modID, dispatcher);
    }

    @SuppressWarnings("unused")
    public ItemModelBuilder withMapping(String stringFormat) {
        this.stringFormat = stringFormat;
        return this;
    }

	@SuppressWarnings("unused")
	public ItemModelBuilder extMapping(String stringFormat) {
		this.extendedMap.add(stringFormat);
		return this;
	}

    @SuppressWarnings("unused")
    public ItemModelBuilder withFull3D() {
        this.full3D = true;
        return this;
    }

    @SuppressWarnings("unused")
    public ItemModelBuilder withFullbright() {
        this.fullbright = true;
        return this;
    }

    @SuppressWarnings("unused")
    public ItemModelBuilder setPointForward() {
        this.pointInFrontOfPlayer = true;
        return this;
    }

    @SuppressWarnings("unused")
    public ItemModelBuilder rotateWhenRendering() {
        this.rotateWhenRendering = true;
        return this;
    }

    @Override
    public void onBuild(Item block, ItemModel model, String namespaceValue) {

		if (model instanceof ItemModelExtended)  {
			for (String format : this.extendedMap) {
				String formatted = modID + ":item/" + String.format(format, namespaceValue);
				((ItemModelExtended)model).addIcon(formatted);
			}
		}

        if (model instanceof ItemModelStandard) {//TODO log non-standard warning
			String formatted = modID + ":item/" + String.format(this.stringFormat, namespaceValue);
            ItemModelStandard modelStd = ((ItemModelStandard) model);
            if (this.pointInFrontOfPlayer) modelStd.setPointInfrontOfPlayer();
            if (this.full3D) modelStd.setFull3D();
            if (this.fullbright) modelStd.setFullBright();
            if (this.rotateWhenRendering) modelStd.setRotateWhenRendering();
            modelStd.icon = TextureRegistry.getTexture(formatted);
        }
    }
}
