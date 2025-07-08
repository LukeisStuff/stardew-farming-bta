package luke.stardew.helper;

import luke.stardew.StardewMod;
import luke.stardew.blocks.model.BlockModelExtended;
import net.minecraft.client.render.block.model.BlockModel;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BlockModelBuilder extends ModelBuilder<Block<?>, BlockModel<?>, BlockModelBuilder>{
    public final Map<Side, String> sideMap = new HashMap<>();
	public final List<String> extendedMap = new ArrayList<>();
	public int renderLayer = 0;
	public String counterString = "";
	public int count = 0;
    BlockModelBuilder(String modID, BlockModelDispatcher dispatcher) {
        super(modID, dispatcher);
    }

	@SuppressWarnings("unused")
	public BlockModelBuilder withMapping(String stringFormat, int side) {
		return withMapping(stringFormat, Side.getSideById(side));
	}

	@SuppressWarnings("unused")
	public BlockModelBuilder withMapping(String stringFormat, int... sides) {
		for (int sideIndex : sides) {
			this.sideMap.put(Side.getSideById(sideIndex), stringFormat);
		}
		return this;
	}

    @SuppressWarnings("unused")
    public BlockModelBuilder withMapping(String stringFormat, Side side) {
        this.sideMap.put(side, stringFormat);
        return this;
    }

    @SuppressWarnings("unused")
    public BlockModelBuilder withMapping(String stringFormat, Side... sides) {
        for (Side side : sides) {
            this.sideMap.put(side, stringFormat);
        }
        return this;
    }

    @SuppressWarnings("unused")
    public BlockModelBuilder withMapping(String stringFormat) {
        for (Side side : Side.sides) {
            this.sideMap.put(side, stringFormat);
        }
        return this;
    }

	@SuppressWarnings("unused")
	public BlockModelBuilder extMapping(String stringFormat) {
		this.extendedMap.add(stringFormat);
		return this;
	}

	@SuppressWarnings("unused")
	public BlockModelBuilder extMappingCounter(String stringFormat) {
		if (!stringFormat.contains("%d")) {
			StardewMod.LOGGER.warn("'{}' should contain %d conversion specifier!", stringFormat);
			return this;
		}

		this.counterString = stringFormat;
		return this;
	}

	@SuppressWarnings("unused")
	public BlockModelBuilder onLayer(int layer) {
		this.renderLayer = layer;
		return this;
	}

	public BlockModelBuilder count(int count) {
		this.count = count;
		return this;
	}

    @Override
    public void onBuild(Block<?> block, BlockModel<?> model, String namespaceValue) {
		//Check errors
		if (model instanceof BlockModelExtended) {
			for (String format : this.extendedMap) {
				String formatted = modID + ":block/" + String.format(format, namespaceValue);
				((BlockModelExtended<?>)model).addIcon(formatted);
			}

			if (!this.counterString.isEmpty()) {
				for (int i = 0; i < this.count; i++) {
					String formatted = modID + ":block/" + String.format(counterString, namespaceValue, i + 1); //FIXME add custom starting values
					((BlockModelExtended<?>)model).addIcon(formatted);
				}
			}
		}else if (!this.extendedMap.isEmpty()) {
			StardewMod.LOGGER.warn("Model builder of '{}' is using extended mappings, but Model does not extend BlockModelExtended!", block.namespaceId());
		}

        for (Map.Entry<Side, String> entry : this.sideMap.entrySet()) {
            String formatted = modID + ":block/" + String.format(entry.getValue(), namespaceValue);

            if (model instanceof BlockModelStandard) { //TODO: Log error for non-standard models
                ((BlockModelStandard<?>) model).setTex(0, formatted, entry.getKey());
				((BlockModelStandard<?>) model).renderLayer = this.renderLayer;
            }
        }
    }
}
