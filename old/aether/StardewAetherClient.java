package luke.stardew.compat.aether;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import turniplabs.halplibe.util.ModelEntrypoint;

import static luke.stardew.compat.aether.StardewAetherMod.IS_AETHER_LOADED;

@Environment(EnvType.CLIENT)
public class StardewAetherClient implements ModelEntrypoint {

    public static ModelEntrypoint modelEntryPointDelegate;

    static {
        if (IS_AETHER_LOADED) {
            try {

                modelEntryPointDelegate = (ModelEntrypoint) Class
                    .forName("luke.stardew.compat.aether.StardewAetherModels")
                    .getConstructor()
                    .newInstance();

            } catch (Exception e) {
                throw new RuntimeException("Failed to init Aether model delegate", e);
            }
        }
    }

    @Override
    public void initBlockColors(BlockColorDispatcher dispatcher) {
        if (modelEntryPointDelegate != null) {
            modelEntryPointDelegate.initBlockColors(dispatcher);
        }
    }

    @Override
    public void initBlockModels(BlockModelDispatcher dispatcher) {
        if (modelEntryPointDelegate != null) {
            modelEntryPointDelegate.initBlockModels(dispatcher);
        }
    }

    @Override
    public void initItemModels(ItemModelDispatcher dispatcher) {
        if (modelEntryPointDelegate != null) {
            modelEntryPointDelegate.initItemModels(dispatcher);
        }
    }

    @Override
    public void initEntityModels(EntityRendererDispatcher dispatcher) {
        if (modelEntryPointDelegate != null) {
            modelEntryPointDelegate.initEntityModels(dispatcher);
        }
    }

    @Override
    public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {
        if (modelEntryPointDelegate != null) {
            modelEntryPointDelegate.initTileEntityModels(dispatcher);
        }
    }
}
