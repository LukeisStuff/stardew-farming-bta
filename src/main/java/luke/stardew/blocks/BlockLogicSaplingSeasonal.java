package luke.stardew.blocks;

import luke.stardew.WorldFeatureTreeSeasonal;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicSaplingBase;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeFancy;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;

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
    public void growTree(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Random random) {
        WorldFeature treeBig = new WorldFeatureTreeFancy(this.leafBlock.id(), this.logBlock.id());
        WorldFeature treeSmall = new WorldFeatureTreeSeasonal(this.leafBlock.id(), this.leafFloweringBlock.id(), this.logBlock.id(), 4, this.flowerRarity);
        world.setBlock(tilePos.x(), tilePos.y(), tilePos.z(), 0);
        if (!treeSmall.place(world, random, tilePos.x(), tilePos.y(), tilePos.z()) && !treeBig.place(world, random, tilePos.x(), tilePos.y(), tilePos.z())) {
            world.setBlock(tilePos.x(), tilePos.y(), tilePos.z(), this.id());
        }

    }
}
