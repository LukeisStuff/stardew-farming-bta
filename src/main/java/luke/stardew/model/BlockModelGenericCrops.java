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
public class BlockModelGenericCrops<T extends BlockLogic> extends BlockModelGeneric<T> {
    public final int cropLength;
    public final String cropName;
    public final StaticBlockModel[] models;

    public BlockModelGenericCrops(@NonNull Block<T> block, String cropName, int cropLength) {
        super(block, BlockModelDispatcher.loadDataModel("stardew:block/crops_" + cropName + "/stage0"));
        this.cropName = cropName;
        this.cropLength = cropLength;
        this.models = new StaticBlockModel[cropLength];

        for (int i = 0; i < cropLength; ++i) {
            this.models[i] = BlockModelDispatcher.loadDataModel("stardew:block/crops_" + cropName + "/stage" + i).asModel();
        }
    }

    @Override
    public @NonNull StaticBlockModel getModelFromData(int data) {
        return this.models[Math.min(data, cropLength - 1)];
    }
}
