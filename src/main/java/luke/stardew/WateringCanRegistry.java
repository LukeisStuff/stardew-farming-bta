package luke.stardew;

import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;

import java.util.HashMap;
import java.util.Map;

public class WateringCanRegistry {

    @FunctionalInterface
    public interface WateringLambda {
        boolean call(World world, TilePosc tilePosc, int blockData);
    }

    private static final WateringCanRegistry INSTANCE = new WateringCanRegistry();

    private final Map<Block<?>, WateringLambda> map = new HashMap<>();

    public static void register(Block<?> block, WateringLambda wateringLambda) {
        if (INSTANCE.map.containsKey(block)) {
            throw new RuntimeException("Watering interaction already defined for \"%s\"!".formatted(block.namespaceId()));
        }

        INSTANCE.map.put(block, wateringLambda);
    }

    static {
        register(
            Blocks.FARMLAND_DIRT,
            (world, tilePosc, blockData) -> {
                if (blockData == 0) return world.setBlockTypeDataNotify(tilePosc, Blocks.FARMLAND_DIRT, 1);
                return false;
            }
        );

        register(
            Blocks.MUD_BAKED,
            (world, tilePosc, blockData) -> world.setBlockTypeDataNotify(tilePosc, Blocks.MUD, blockData)
        );

        register(
            Blocks.SPONGE_DRY,
            (world, tilePosc, blockData) -> world.setBlockTypeDataNotify(tilePosc, Blocks.SPONGE_WET, blockData)
        );

        ///

        register(
            Blocks.FIRE,
            (world, tilePosc, blockData) -> {
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, tilePosc.x() + 0.5, tilePosc.y() + 0.5, tilePosc.z() + 0.5, "random.fizz", 0.3F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
                return world.setBlockTypeDataNotify(tilePosc, Blocks.AIR, 0);
            }
        );

        register(
            Blocks.BRAZIER_ACTIVE,
            (world, tilePosc, blockData) -> {
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, tilePosc.x() + 0.5, tilePosc.y() + 0.5, tilePosc.z() + 0.5, "random.fizz", 0.3F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
                return world.setBlockTypeDataNotify(tilePosc, Blocks.BRAZIER_INACTIVE, blockData);
            }
        );

        register(
            StardewBlocks.CANDLE_ACTIVE,
            (world, tilePosc, blockData) -> {
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, tilePosc.x() + 0.5, tilePosc.y() + 0.5, tilePosc.z() + 0.5, "random.fizz", 0.3F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
                return world.setBlockTypeDataNotify(tilePosc, StardewBlocks.CANDLE, blockData);
            }
        );

        register(
            Blocks.PUMICE_WET,
            (world, tilePosc, blockData) -> {
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, tilePosc.x() + 0.5, tilePosc.y() + 0.5, tilePosc.z() + 0.5, "random.fizz", 0.3F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
                return world.setBlockTypeDataNotify(tilePosc, Blocks.PUMICE_DRY, blockData);
            }
        );

        register(
            Blocks.MAGMA,
            (world, tilePosc, blockData) -> {
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, tilePosc.x() + 0.5, tilePosc.y() + 0.5, tilePosc.z() + 0.5, "random.fizz", 0.3F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
                return world.setBlockTypeDataNotify(tilePosc, Blocks.COBBLE_BASALT, blockData);
            }
        );

        register(
            Blocks.EMBER,
            (world, tilePosc, blockData) -> {
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, tilePosc.x() + 0.5, tilePosc.y() + 0.5, tilePosc.z() + 0.5, "random.fizz", 0.3F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
                return world.setBlockTypeDataNotify(tilePosc, Blocks.BRIMSAND, blockData);
            }
        );
    }

    public static boolean water(World world, TilePosc tilePos) {
        var blockType = world.getBlockType(tilePos);
        var blockData = world.getBlockData(tilePos);
        var caller = INSTANCE.map.get(blockType);

        if (caller != null) {
            return caller.call(world, tilePos, blockData);
        }

        return false;
    }
}
