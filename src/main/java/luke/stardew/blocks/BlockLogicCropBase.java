package luke.stardew.blocks;

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
import net.minecraft.core.world.pos.TilePosc;
import net.minecraft.core.world.season.Season;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BlockLogicCropBase extends BlockLogicFlower implements IBonemealable {
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

    @Override
    protected boolean mayPlaceOn(@NotNull Block<?> block) {
        return block == Blocks.FARMLAND_DIRT;
    }

    public void fertilize(World world, TilePosc tilePosc) {
        world.setBlockDataNotify(tilePosc, this.maxGrowth);
    }

    public void onGrowth(World world, int x, int y, int z, int newMeta) {
        if (this.growsInto != null && newMeta >= this.maxGrowth) {
            world.setBlockAndMetadataWithNotify(x, y, z, this.growsInto.id(), 0);
        } else {
            world.setBlockMetadataWithNotify(x, y, z, newMeta);
        }
    }

    @Override
    public void updateTick(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Random rand, boolean isRandomTick) {
        super.updateTick(world, tilePos, rand, isRandomTick);;
        Season current = world.getSeasonManager().getCurrentSeason();
        if (world.getBlockLightValue(tilePos.x(), tilePos.y() + 1, tilePos.z()) >= 9 && season.contains(current)) {
            int meta = world.getBlockMetadata(tilePos.x(), tilePos.y(), tilePos.z());
            if (meta < this.maxGrowth) {
                float growthRate = this.getGrowthRate(world, tilePos);
                if (rand.nextInt((int) (100.0F / growthRate)) == 0) {
                    this.onGrowth(world, tilePos.x(), tilePos.y(), tilePos.z(), meta + 1);
                }
            }
        }

    }

    public float getGrowthRate(World world, TilePosc tilePos) {
        float growthRate = 1.0F;
        int idNegZ = world.getBlockId(tilePos.x(), tilePos.y(), tilePos.z() - 1);
        int idPosZ = world.getBlockId(tilePos.x(), tilePos.y(), tilePos.z() + 1);
        int idNegX = world.getBlockId(tilePos.x() - 1, tilePos.y(), tilePos.z());
        int idPosX = world.getBlockId(tilePos.x() + 1, tilePos.y(), tilePos.z());
        int idNegXNegZ = world.getBlockId(tilePos.x() - 1, tilePos.y(), tilePos.z() - 1);
        int idPosXNegZ = world.getBlockId(tilePos.x() + 1, tilePos.y(), tilePos.z() - 1);
        int idPosXPosZ = world.getBlockId(tilePos.x() + 1, tilePos.y(), tilePos.z() + 1);
        int idNegXPosZ = world.getBlockId(tilePos.x() - 1, tilePos.y(), tilePos.z() + 1);
        boolean xNeighbor = idNegX == this.id() || idPosX == this.id();
        boolean zNeighbor = idNegZ == this.id() || idPosZ == this.id();
        boolean diagNeighbor = idNegXNegZ == this.id() || idPosXNegZ == this.id() || idPosXPosZ == this.id() || idNegXPosZ == this.id();

        for (int dx = tilePos.x() - 1; dx <= tilePos.x() + 1; ++dx) {
            for (int dz = tilePos.z() - 1; dz <= tilePos.z() + 1; ++dz) {
                int id = world.getBlockId(dx, tilePos.y() - 1, dz);
                float growthRateMod = 0.0F;
                if (id == Blocks.FARMLAND_DIRT.id()) {
                    growthRateMod = 1.0F;
                    if (world.getBlockMetadata(dx, tilePos.y() - 1, dz) > 0) {
                        growthRateMod = 3.0F;
                    }
                }

                if (dx != tilePos.x() || dz != tilePos.z()) {
                    growthRateMod /= 4.0F;
                }

                growthRate += growthRateMod;
            }
        }

        if (diagNeighbor || xNeighbor && zNeighbor) {
            growthRate /= 2.0F;
        }

        if (this.canFertilize) {
            boolean isFertilized = BlockLogicFarmland.isFertilized(world.getBlockMetadata(tilePos.x(), tilePos.y() - 1, tilePos.z()));
            if (isFertilized) growthRate *= this.fertilizedRate;
        } else {
            if (world.getSeasonManager().getCurrentSeason() != null) {
                growthRate *= world.getSeasonManager().getCurrentSeason().cropGrowthFactor;
            }
        }

        return growthRate;
    }

    public List<ItemStack> getDrops(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        List<ItemStack> drops = new ArrayList<>();
        if (dropCause == EnumDropCause.PICK_BLOCK) {
            if (this.seedItem != null) {
                drops.add(new ItemStack(this.seedItem, 1));
            } else {
                ItemStack stack = new ItemStack(Items.SEEDS_WHEAT, 1);
                drops.add(stack);
                stack.setCustomName("If you somehow got this item, this is a bug [" + this.namespaceId() + "]");
            }
            return drops;
        }

        if (meta < maxGrowth) {
            if (this.seedItem != null) drops.add(new ItemStack(this.seedItem));
        } else {
            if (this.seedItem != null) drops.add(new ItemStack(this.seedItem, this.seedRange.get(world.rand)));
            if (this.cropItem != null) drops.add(new ItemStack(this.cropItem, this.cropRange.get(world.rand)));
        }
        return drops;
    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        return getDrops(world, dropCause, x, y, z, meta, tileEntity).toArray(new ItemStack[]{});
    }

    @Override
    public boolean onBonemealUsed(ItemStack itemstack, @Nullable Player player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
        if (world.getBlockMetadata(blockX, blockY, blockZ) >= this.maxGrowth) {
            return false;
        } else {
            if (!world.isClientSide) {
                this.onGrowth(world, blockX, blockY, blockZ, this.maxGrowth);
                if (player == null || player.getGamemode().hasBlockConsumption()) {
                    --itemstack.stackSize;
                }
            }
            return true;
        }
    }

    public void onHarvest(World world, int x, int y, int z, int meta) {
        world.setBlockAndMetadataWithNotify(x, y, z, 0, meta);
    }

    @Override
    public void onActivatorInteracted(@NotNull World world, @NotNull TilePosc tilePos, @NotNull TileEntityActivator activator, @NotNull Direction direction) {
        if (this.canHarvest) {
            int meta = world.getBlockData(tilePos);
            if (meta >= this.maxGrowth) {
                if (this.resetMeta < 0) {
                    onHarvest(world, tilePos.x(), tilePos.y(), tilePos.z(), meta);
                } else {
                    onGrowth(world, tilePos.x(), tilePos.y(), tilePos.z(), this.resetMeta);
                }
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
                onHarvest(world, tilePos.x(), tilePos.y(), tilePos.z(), meta);
            } else {
                onGrowth(world, tilePos.x(), tilePos.y(), tilePos.z(), this.resetMeta);
            }

            world.playSoundEffect(player, SoundCategory.WORLD_SOUNDS, tilePos.x() + 0.5, tilePos.y() + 0.5, tilePos.z() + 0.5, "random.pop", 0.3F, 1.0f);
            if (!world.isClientSide && this.cropItem != null)
                world.dropItem(tilePos.x(), tilePos.y(), tilePos.z(), new ItemStack(this.cropItem, this.cropRange.get(world.rand)));

            return true;
        }
        return false;
    }

}
