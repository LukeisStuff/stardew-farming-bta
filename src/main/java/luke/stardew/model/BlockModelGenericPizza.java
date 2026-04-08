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
public class BlockModelGenericPizza<T extends BlockLogic> extends BlockModelGeneric<T> {
    public final StaticBlockModel[] models = new StaticBlockModel[4];

    public BlockModelGenericPizza(@NonNull Block<T> block) {
        super(block, BlockModelDispatcher.loadDataModel("stardew:block/pizza/0"));

        for(int i = 0; i < this.models.length; ++i) {
            this.models[i] = BlockModelDispatcher.loadDataModel("stardew:block/pizza/" + i).asModel();
        }
    }

    @Override
    public @NonNull StaticBlockModel getModelFromData(int data) {
        return this.models[data % this.models.length];
    }
}
