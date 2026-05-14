package luke.stardew.model;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import net.minecraft.core.world.season.Seasons;
import org.jetbrains.annotations.NotNull;
import org.useless.dragonfly.models.block.StaticBlockModel;


public class BlockModelBush<T extends BlockLogic> extends BlockModelGenericProgressive<T> {
    public BlockModelBush(@NotNull Block<T> block, @NotNull String dataModelPath) {
        super(block, dataModelPath, 4);
    }

    @Override
    public @NotNull StaticBlockModel getModel(@NotNull WorldSource source, @NotNull TilePosc tilePosc) {
        var season = source.getSeasonManager().getCurrentSeason();

        if (season == Seasons.OVERWORLD_SPRING) {
            return models[0];
        }

        if (season == Seasons.OVERWORLD_SUMMER) {
            return models[1];
        }

        if (season == Seasons.OVERWORLD_FALL) {
            return models[2];
        }

        if (season == Seasons.OVERWORLD_WINTER || season == Seasons.OVERWORLD_WINTER_ENDLESS) {
            return models[3];
        }

        return models[4];
    }
}
