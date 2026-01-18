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
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockLogicCropBase extends BlockLogicFlower implements IBonemealable {
    public float fertilizedRate;
    public int maxGrowth;
    public boolean canFertilize;
    public Item seedItem = Items.SEEDS_WHEAT;
    public Item cropItem;
    public Range seedRange = Range.EMPTY;
    public Range cropRange = Range.ONE;
    public int resetMeta = -1;
    public Block<?> growsInto;
    public boolean canHarvest;

    public BlockLogicCropBase(@NonNull Block<?> block) {
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
    public boolean mayPlaceOn(@NonNull Block<?> block) {
        return block == Blocks.FARMLAND_DIRT;
    }

    @Override
    public void updateTick(@NonNull World world, @NonNull TilePosc tilePos, @NonNull Random rand, boolean isRandomTick) {
        super.updateTick(world, tilePos, rand, isRandomTick);
        if (world.getBlockLightValue(tilePos.up(new TilePos())) >= 9) {
            int data = world.getBlockData(tilePos);
            if (data < this.maxGrowth) {
                float growthRate = this.getGrowthRate(world, tilePos);
                if (rand.nextInt((int) (100.0F / growthRate)) == 0) {
                    this.onGrowth(world, tilePos, data + 1);
                }
            }
        }
    }

    public float getGrowthRate(@NotNull World world, @NotNull TilePosc tilePos) {
        float growthRate = 1.0F;
        TilePos queryPos = new TilePos();
        Block<?> bNegZ = world.getBlockType(tilePos.north(queryPos));
        Block<?> bPosZ = world.getBlockType(tilePos.south(queryPos));
        Block<?> bNegX = world.getBlockType(tilePos.west(queryPos));
        Block<?> bPosX = world.getBlockType(tilePos.east(queryPos));
        Block<?> bNegXNegZ = world.getBlockType(tilePos.west(queryPos).north(queryPos));
        Block<?> bPosXNegZ = world.getBlockType(tilePos.east(queryPos).north(queryPos));
        Block<?> bPosXPosZ = world.getBlockType(tilePos.east(queryPos).south(queryPos));
        Block<?> bNegXPosZ = world.getBlockType(tilePos.west(queryPos).south(queryPos));
        boolean xNeighbor = bNegX == this.block || bPosX == this.block;
        boolean zNeighbor = bNegZ == this.block || bPosZ == this.block;
        boolean diagNeighbor = bNegXNegZ == this.block || bPosXNegZ == this.block || bPosXPosZ == this.block || bNegXPosZ == this.block;
        TilePos localPos = new TilePos(tilePos);

        for(localPos.x = tilePos.x() - 1; localPos.x <= tilePos.x() + 1; ++localPos.x) {
            for(localPos.z = tilePos.z() - 1; localPos.z <= tilePos.z() + 1; ++localPos.z) {
                Block<?> block = world.getBlockType(tilePos.down(queryPos));
                float growthRateMod = 0.0F;
                if (block == Blocks.FARMLAND_DIRT) {
                    growthRateMod = 1.0F;
                    if (world.getBlockData(tilePos.down(queryPos)) > 0) {
                        growthRateMod = 3.0F;
                    }
                }

                if (!tilePos.equals(localPos)) {
                    growthRateMod /= 4.0F;
                }

                growthRate += growthRateMod;
            }
        }

        if (diagNeighbor || xNeighbor && zNeighbor) {
            growthRate /= 2.0F;
        }

        boolean isFertilized = BlockLogicFarmland.isFertilized(world.getBlockData(tilePos.down(queryPos)));
        if (!isFertilized) {
            if (world.getSeasonManager().getCurrentSeason() != null) {
                growthRate *= world.getSeasonManager().getCurrentSeason().cropGrowthFactor;
            }
        } else {
            growthRate *= 1.5F;
        }

        return growthRate;
    }

    protected void onGrowth(@NonNull World world, @NonNull TilePosc tilePos, int data) {
        if (this.growsInto != null && data >= this.maxGrowth) {
            world.setBlockAndMetadataWithNotify(tilePos.x(), tilePos.y(), tilePos.z(), this.growsInto.id(), 0);
        } else {
            world.setBlockDataNotify(tilePos, data);
        }
    }

    @Override
    public @NonNull ItemStack @Nullable [] getBreakResult(@NonNull World world, @NonNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
        if (dropCause == EnumDropCause.PICK_BLOCK) {
            return this.seedItem != null ?
                new ItemStack[]{new ItemStack(this.seedItem, 1)} :
                new ItemStack[]{new ItemStack(Items.SEEDS_WHEAT, 1)};
        }

        List<ItemStack> drops = new ArrayList<>();
        if (data < this.maxGrowth) {
            if (this.seedItem != null) {
                drops.add(new ItemStack(this.seedItem));
            }
        } else {
            if (this.seedItem != null) {
                drops.add(new ItemStack(this.seedItem, this.seedRange.get(world.rand)));
            }
            if (this.cropItem != null) {
                drops.add(new ItemStack(this.cropItem, this.cropRange.get(world.rand)));
            }
        }
        return drops.toArray(new ItemStack[0]);
    }

    @Override
    public boolean onBonemealUsed(@NonNull ItemStack itemStack, @Nullable Player player, @NonNull World world, @NonNull TilePosc tilePos, @NonNull Side side, double xHit, double yHit) {
        if (world.getBlockData(tilePos) >= this.maxGrowth) {
            return false;
        } else {
            if (!world.isClientSide) {
                this.onGrowth(world, tilePos, this.maxGrowth);
                if (player == null || player.getGamemode().hasBlockConsumption()) {
                    --itemStack.stackSize;
                }
            }
            return true;
        }
    }

    public void onHarvest(@NonNull World world, int x, int y, int z, int meta) {
        world.setBlockAndMetadataWithNotify(x, y, z, 0, meta);
    }

    @Override
    public void onActivatorInteract(@NonNull World world, int x, int y, int z, @NonNull TileEntityActivator activator, @NonNull Direction direction) {
        if (this.canHarvest) {
            int meta = world.getBlockMetadata(x, y, z);
            if (meta >= this.maxGrowth) {
                if (this.resetMeta < 0) {
                    onHarvest(world, x, y, z, meta);
                } else {
                    onGrowth(world, new TilePos(x, y, z), this.resetMeta);
                }
                world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, x + 0.5, y + 0.5, z + 0.5, "random.pop", 0.3F, 1.0f);
                if (!world.isClientSide && this.cropItem != null) {
                    world.dropItem(x, y, z, new ItemStack(this.cropItem, this.cropRange.get(world.rand)));
                }
            }
        }
    }

    @Override
    public boolean onBlockRightClicked(@NonNull World world, int x, int y, int z, @NonNull Player player, @NonNull Side side, double xHit, double yHit) {
        if (!this.canHarvest) return false;
        int meta = world.getBlockMetadata(x, y, z);
        if (meta >= this.maxGrowth) {
            if (this.resetMeta < 0) {
                onHarvest(world, x, y, z, meta);
            } else {
                onGrowth(world, new TilePos(x, y, z), this.resetMeta);
            }

            world.playSoundEffect(player, SoundCategory.WORLD_SOUNDS, x + 0.5, y + 0.5, z + 0.5, "random.pop", 0.3F, 1.0f);
            if (!world.isClientSide && this.cropItem != null) {
                world.dropItem(x, y, z, new ItemStack(this.cropItem, this.cropRange.get(world.rand)));
            }

            return true;
        }
        return false;
    }
}
