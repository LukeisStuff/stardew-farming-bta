package luke.stardew.blocks;

import luke.stardew.WorldFeatureTreeSeasonal;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicSaplingBase;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeatureInterface;
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
        WorldFeatureInterface treeBig = new WorldFeatureTreeFancy(this.leafBlock.id(), this.logBlock.id());
        WorldFeatureInterface treeSmall = new WorldFeatureTreeSeasonal(this.leafBlock.id(), this.leafFloweringBlock.id(), this.logBlock.id(), 4, this.flowerRarity);
        world.setBlockType(tilePos, Blocks.AIR);
        if (!treeSmall.place(world, random, tilePos) && !treeBig.place(world, random, tilePos)) {
            world.setBlockType(tilePos, this.block);
        }

    }
}
