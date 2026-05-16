package luke.stardew.blocks.crops;

import luke.stardew.misc.Range;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFarmland;
import net.minecraft.core.block.BlockLogicFlower;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.IBonemealable;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import net.minecraft.core.world.season.Season;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BlockLogicCropBase extends BlockLogicFlower implements IBonemealable, IStardewCrop {

    public float fertilizedRate;
    public int maxGrowth;
    public boolean canFertilize;
    public Item seedItem = Items.SEEDS_WHEAT;
    public Item cropItem;
    public Range seedRange = Range.EMPTY;
    public Range cropRange = Range.ONE;
    public int resetMeta = -1;
    public Set<Season> season = new HashSet<>();
    public Block<?> growsInto;
    public boolean canHarvest;

    // constructors

    public BlockLogicCropBase(Block<?> block) {
        super(block);
        block.setTicking(true);
        this.fertilizedRate = 1.0F;
        this.maxGrowth = 5;
        this.canFertilize = true;
        this.canHarvest = true;
        this.setBlockBounds(0, 0.0F, 0, 1, 0.25F, 1);
    }

    public BlockLogicCropBase withGrowth(int maxGrowth) {
        this.maxGrowth = maxGrowth;
        return this;
    }

    public BlockLogicCropBase withSeed(Item seedItem, int seedMin, int seedMax) {
        this.seedItem = seedItem;
        this.seedRange = new Range(seedMin, seedMax);
        return this;
    }

    public BlockLogicCropBase withCrop(Item cropItem, int cropMin, int cropMax) {
        this.cropItem = cropItem;
        this.cropRange = new Range(cropMin, cropMax);
        return this;
    }

    public BlockLogicCropBase withCrop(Item cropItem) {
        this.cropItem = cropItem;
        this.cropRange = Range.ONE;
        return this;
    }

    public BlockLogicCropBase withFertilizedRate(float fertilizedRate) {
        this.fertilizedRate = fertilizedRate;
        return this;
    }

    public BlockLogicCropBase withProperSeason(Season... seasons) {
        this.season.clear();
        for (Season s : seasons) {
            if (s != null) this.season.add(s);
        }
        return this;
    }

    public BlockLogicCropBase withResetMeta(int resetMeta) {
        this.resetMeta = resetMeta;
        return this;
    }

    public BlockLogicCropBase notFertilized() {
        this.canFertilize = false;
        return this;
    }

    public BlockLogicCropBase growsInto(Block<?> block) {
        this.growsInto = block;
        return this;
    }

    public void noHarvest() {
        this.canHarvest = false;
    }

    // </constructors>

    @Override
    protected boolean mayPlaceOn(@NotNull Block<?> block) {
        return block.getLogic() instanceof BlockLogicFarmland;
    }

    @Override
    public void updateTick(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Random rand, boolean isRandomTick) {
        super.updateTick(world, tilePos, rand, isRandomTick);
        Season current = world.getSeasonManager().getCurrentSeason();

        if (world.getBlockLightValue(tilePos.up(new TilePos())) >= 9 && season.contains(current)) {
            int meta = world.getBlockData(tilePos);

            if (meta < this.maxGrowth) {
                float growthRate = this.getGrowthRate(world, tilePos);

                if (rand.nextInt((int) (100.0F / growthRate)) == 0) {
                    this.onGrowth(world, tilePos, meta + 1);
                }
            }
        }

    }

    @Override
    public @NotNull ItemStack @Nullable [] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, @NotNull TilePosc tilePos, int data, @Nullable TileEntity tileEntity) {
        return getHarvestResult(world, dropCause, tilePos, data, tileEntity).toArray(new ItemStack[]{});
    }

    @Override
    public boolean onBonemealUsed(@NotNull ItemStack itemStack, @Nullable Player player, @NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
        var blockData = world.getBlockData(tilePos);

        if (blockData >= this.maxGrowth) {
            return false;
        }

        if (!world.isClientSide) {
            this.onGrowth(world, tilePos, blockData+1);
            if (player == null || player.getGamemode().hasBlockConsumption()) {
                --itemStack.stackSize;
            }
        }

        return true;
    }



    @Override
    public void onActivatorInteracted(@NotNull World world, @NotNull TilePosc tilePos, @NotNull TileEntityActivator activator, @NotNull Direction direction) {
        if (this.canHarvest) {
            int meta = world.getBlockData(tilePos);
            if (meta >= this.maxGrowth) {

                if (this.resetMeta < 0) onHarvest(world, tilePos, meta);
                else onGrowth(world, tilePos, this.resetMeta);

                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, tilePos.x() + 0.5, tilePos.y() + 0.5, tilePos.z() + 0.5, "random.pop", 0.3F, 1.0f);
                if (!world.isClientSide && this.cropItem != null) {
                    world.dropItem(tilePos, new ItemStack(this.cropItem, this.cropRange.get(world.rand)));
                }
            }
        }
    }


    @Override
    public boolean onInteracted(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Player player, @Nullable Side side, double xHit, double yHit) {
        if (!this.canHarvest) return false;

        int meta = world.getBlockData(tilePos);
        if (meta >= this.maxGrowth) {
            if (this.resetMeta < 0) {
                onHarvest(world, tilePos, meta);
            } else {
                onGrowth(world, tilePos, this.resetMeta);
            }

            world.playSoundEffect(player, SoundCategory.WORLD_SOUNDS, tilePos.x() + 0.5, tilePos.y() + 0.5, tilePos.z() + 0.5, "random.pop", 0.3F, 1.0f);
            if (!world.isClientSide && this.cropItem != null)
                world.dropItem(tilePos.x(), tilePos.y(), tilePos.z(), new ItemStack(this.cropItem, this.cropRange.get(world.rand)));

            return true;
        }
        return false;
    }

    @Override
    public void fertilize(World world, TilePosc tilePosc) {
        world.setBlockDataNotify(tilePosc, this.maxGrowth);
    }

    @Override
    public void onGrowth(World world, TilePosc tilePos, int newMeta) {
        if (this.growsInto != null && newMeta >= this.maxGrowth) {
            world.setBlockTypeDataNotify(tilePos, this.growsInto, 0);
        } else {
            world.setBlockDataNotify(tilePos, 0);
        }
    }

    @Override
    public float getGrowthRate(World world, TilePosc tilePos) {
        float growthRate = 1.0F;

        TilePos[] offsets = new TilePos[8];

        for (int i = 0; i <= 4; i++) {
            offsets[i] = new TilePos(tilePos).add(Direction.horizontal[i]);
        }

        offsets[4] = new TilePos(tilePos).add(Direction.NORTH).add(Direction.EAST);
        offsets[5] = new TilePos(tilePos).add(Direction.NORTH).add(Direction.WEST);
        offsets[6] = new TilePos(tilePos).add(Direction.SOUTH).add(Direction.EAST);
        offsets[7] = new TilePos(tilePos).add(Direction.NORTH).add(Direction.WEST);

        for (TilePos offset : offsets) {
            var pos = offset.down(new TilePos());

            float growthRateModifier = 0;

            var block = world.getBlockType(pos);

            if (block.getLogic() instanceof BlockLogicFarmland) {
                growthRateModifier = Math.max(1, world.getBlockData(pos));
            }

            if (!pos.equals(tilePos)) growthRateModifier /= 4;

            growthRate += growthRateModifier;
        }

        for (TilePos offset : offsets) {
            if (world.getBlockType(offset) == this.block) {
                growthRate /= 2.0f;
                break;
            }
        }

        if (this.canFertilize) {
            boolean isFertilized = BlockLogicFarmland.isFertilized(world.getBlockData(tilePos.down(new TilePos())));
            if (isFertilized) growthRate *= this.fertilizedRate;
        }

        else {
            if (world.getSeasonManager().getCurrentSeason() != null) {
                growthRate *= world.getSeasonManager().getCurrentSeason().cropGrowthFactor;
            }
        }

        return growthRate;
    }

    @Override
    public void onHarvest(World world, TilePosc pos, int meta) {
        world.setBlockTypeDataNotify(pos, Blocks.AIR, 0);
    }

    @Override
    public List<ItemStack> getHarvestResult(World world, EnumDropCause dropCause, TilePosc tilePos, int meta, TileEntity tile) {
        List<ItemStack> drops = new ArrayList<>();

        if (dropCause == EnumDropCause.PICK_BLOCK) {
            if (this.seedItem != null) {
                drops.add(new ItemStack(this.seedItem, 1));
            }

            else {
                ItemStack stack = new ItemStack(Items.SEEDS_WHEAT, 1);
                drops.add(stack);
                stack.setCustomName("If you somehow got this item, this is a bug [" + this.namespaceId() + "]");
            }

            return drops;
        }

        if (meta < maxGrowth) {
            if (this.seedItem != null) drops.add(new ItemStack(this.seedItem));
        }

        else {
            if (this.seedItem != null) drops.add(new ItemStack(this.seedItem, this.seedRange.get(world.rand)));
            if (this.cropItem != null) drops.add(new ItemStack(this.cropItem, this.cropRange.get(world.rand)));
        }

        return drops;
    }

}
