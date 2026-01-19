package luke.stardew.compat.aether;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.ModelEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class StardewAetherCompatibility implements PreLaunchEntrypoint, GameStartEntrypoint, ModelEntrypoint, RecipeEntrypoint {

    public static boolean IS_AETHER_LOADED = false;

    public static ModelEntrypoint modelEntryPointDelegate;
    public static RecipeEntrypoint recipeEntrypointDelegate;


    @Override
    public void onPreLaunch() {
        FabricLoader loader = FabricLoader.getInstance();

        IS_AETHER_LOADED = loader.isModLoaded("aether");

        if (IS_AETHER_LOADED) {

            try {
                modelEntryPointDelegate = (ModelEntrypoint) Class
                    .forName("luke.stardew.compat.aether.StardewAetherModels")
                    .getConstructor()
                    .newInstance();

                recipeEntrypointDelegate = (RecipeEntrypoint) Class
                    .forName("luke.stardew.compat.aether.StardewAetherRecipes")
                    .getConstructor()
                    .newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void callInit(String classPath, String methodName) {
        try {
            Class.forName(classPath).getMethod(methodName).invoke(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void beforeGameStart() {
        if (IS_AETHER_LOADED) {
            callInit("luke.stardew.compat.aether.StardewAetherItems", "init");
        }
    }

    @Override
    public void afterGameStart() {
    }

    @Override
    public void initBlockColors(BlockColorDispatcher dispatcher) {
        if (IS_AETHER_LOADED) modelEntryPointDelegate.initBlockColors(dispatcher);
    }

    @Override
    public void initBlockModels(BlockModelDispatcher dispatcher) {
        if (IS_AETHER_LOADED) modelEntryPointDelegate.initBlockModels(dispatcher);
    }

    @Override
    public void initItemModels(ItemModelDispatcher dispatcher) {
        if (IS_AETHER_LOADED) modelEntryPointDelegate.initItemModels(dispatcher);
    }

    @Override
    public void initEntityModels(EntityRenderDispatcher dispatcher) {
        if (IS_AETHER_LOADED) modelEntryPointDelegate.initEntityModels(dispatcher);
    }

    @Override
    public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {
        if (IS_AETHER_LOADED) modelEntryPointDelegate.initTileEntityModels(dispatcher);
    }

    @Override
    public void onRecipesReady() {
        if (IS_AETHER_LOADED) recipeEntrypointDelegate.onRecipesReady();
    }

    @Override
    public void initNamespaces() {
        if (IS_AETHER_LOADED) recipeEntrypointDelegate.initNamespaces();
    }
}
