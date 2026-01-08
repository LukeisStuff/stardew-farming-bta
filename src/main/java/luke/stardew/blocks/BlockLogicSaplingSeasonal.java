package luke.stardew.blocks;

import luke.stardew.WorldFeatureTreeSeasonal;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicSaplingBase;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeFancy;
import net.minecraft.core.world.pos.TilePosc;
import org.jspecify.annotations.NonNull;

import java.util.Random;

public class BlockLogicSaplingSeasonal extends BlockLogicSaplingBase {
    public final Block<?> logBlock;
    public final Block<?> leafBlock;
    public final Block<?> leafFloweringBlock;
    public final int flowerRarity;

    public BlockLogicSaplingSeasonal(Block<?> block, Block<?> logBlock, Block<?> leafBlock, Block<?> leafFloweringBlock, int flowerRarity) {
        super(block);
        this.logBlock = logBlock;
        this.leafBlock = leafBlock;
        this.leafFloweringBlock = leafFloweringBlock;
        this.flowerRarity = flowerRarity;
    }

    @Override
    public void growTree(@NonNull World world, @NonNull TilePosc tilePosc, @NonNull Random random) {
        WorldFeature treeBig = new WorldFeatureTreeFancy(this.leafBlock.id(), this.logBlock.id());
        WorldFeature treeSmall = new WorldFeatureTreeSeasonal(this.leafBlock.id(), this.leafFloweringBlock.id(), this.logBlock.id(), 4, this.flowerRarity);
        world.setBlock(tilePosc.x(), tilePosc.y(), tilePosc.z(), 0);
        if (!treeSmall.place(world, random, tilePosc.x(), tilePosc.y(), tilePosc.z()) && !treeBig.place(world, random, tilePosc.x(), tilePosc.y(), tilePosc.z())) {
            world.setBlock(tilePosc.x(), tilePosc.y(), tilePosc.z(), this.id());
        }

    }
}
