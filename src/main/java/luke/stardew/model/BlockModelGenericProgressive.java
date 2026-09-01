package luke.stardew.model;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import org.jetbrains.annotations.NotNull;
import org.useless.dragonfly.models.block.StaticBlockModel;

public class BlockModelGenericProgressive<T extends BlockLogic> extends BlockModelGeneric<T> {

    public final StaticBlockModel[] models;


    public BlockModelGenericProgressive(@NotNull Block<T> block, @NotNull String dataModelPath, int amount) {
        super(block, BlockModelDispatcher.loadDataModel(dataModelPath + "/0"));

        this.models = new StaticBlockModel[amount];

        for(int i = 0; i < this.models.length; ++i) {
            this.models[i] = BlockModelDispatcher.loadDataModel(dataModelPath + "/" + i).asModel();
        }

    }

    @Override
    public @NotNull StaticBlockModel getModelFromData(int data) {
        return this.models[data % this.models.length];
    }
}
