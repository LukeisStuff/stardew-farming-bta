package luke.stardew.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import org.jspecify.annotations.NonNull;
import org.useless.dragonfly.models.block.StaticBlockModel;

@Environment(EnvType.CLIENT)
public class BlockModelGenericCropsDouble<T extends BlockLogic> extends BlockModelGeneric<T> {
    public final int cropLength;
    public final String cropName;
    public final boolean isTop;
    public final StaticBlockModel[] models;

    public BlockModelGenericCropsDouble(@NonNull Block<T> block, String cropName, int cropLength, boolean isTop) {
        super(block, BlockModelDispatcher.loadDataModel("stardew:block/crops_" + cropName + "/stage0" + (isTop ? "_top" : "_bottom")));

        this.cropName = cropName;
        this.cropLength = cropLength;
        this.isTop = isTop;
        this.models = new StaticBlockModel[cropLength];

        String suffix = isTop ? "_top" : "_bottom";
        for (int i = 0; i < cropLength; ++i) {
            String modelPath = "stardew:block/crops_" + cropName + "/stage" + i + suffix;
            this.models[i] = BlockModelDispatcher.loadDataModel(modelPath).asModel();
        }
    }

    @Override
    public @NonNull StaticBlockModel getModelFromData(int data) {
        return this.models[Math.min(data, cropLength - 1)];
    }
}
