package luke.stardew.blocks.beehive;

import com.mojang.nbt.tags.CompoundTag;
import com.mojang.nbt.tags.ListTag;
import luke.stardew.blocks.StardewBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.motion.CarriedBlock;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.packet.Packet;
import net.minecraft.core.net.packet.PacketTileEntityData;
import net.minecraft.core.player.inventory.container.Container;
import net.minecraft.core.world.World;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Random;

public class TileEntityBeehive extends TileEntity implements Container {
    public static final int SLOT_LEFT = 0;
    public static final int SLOT_RIGHT = 1;
    public static final int SLOT_RESULT = 2;

    private final Random random = new Random();
    protected ItemStack[] inventory = new ItemStack[3];
    public int currentCookTime = 0;
    public int maxCookTime = 100;

    public int getContainerSize() {
        return this.inventory.length;
    }

    public @Nullable ItemStack getItem(int slot) {
        return this.inventory[slot];
    }

    public @Nullable ItemStack removeItem(int slot, int takeAmount) {
        if (this.inventory[slot] != null) {
            if (this.inventory[slot].stackSize <= takeAmount) {
                ItemStack itemstack = this.inventory[slot];
                this.inventory[slot] = null;
                if (this.worldObj != null && slot == 2) {
                    this.worldObj.markBlockNeedsUpdate(this.tilePos.x, this.tilePos.y, this.tilePos.z);
                }

                return itemstack;
            } else {
                ItemStack itemstack1 = this.inventory[slot].splitStack(takeAmount);
                if (this.inventory[slot].stackSize <= 0) {
                    this.inventory[slot] = null;
                    if (this.worldObj != null && slot == 2) {
                        this.worldObj.markBlockNeedsUpdate(this.tilePos.x, this.tilePos.y, this.tilePos.z);
                    }
                }

                return itemstack1;
            }
        } else {
            return null;
        }
    }

    public void setItem(int slot, @Nullable ItemStack stack) {
        this.inventory[slot] = stack;
        if (stack != null && stack.stackSize > this.getMaxStackSize()) {
            stack.stackSize = this.getMaxStackSize();
        }

        if (this.worldObj != null && slot == 2 && stack == null) {
            this.worldObj.markBlockNeedsUpdate(this.tilePos.x, this.tilePos.y, this.tilePos.z);
        }

    }

    public @NonNull String getNameTranslationKey() {
        return "container.beehive.name";
    }

    public void readAdditionalData(@NonNull CompoundTag compoundTag) {
        ListTag itemsTag = compoundTag.getList("Items");
        this.inventory = new ItemStack[this.getContainerSize()];

        for (int i = 0; i < itemsTag.tagCount(); ++i) {
            CompoundTag itemTag = (CompoundTag) itemsTag.tagAt(i);
            byte slot = itemTag.getByte("Slot");
            if (slot >= SLOT_LEFT && slot < this.inventory.length) {
                this.inventory[slot] = ItemStack.readItemStackFromNbt(itemTag);
            }
        }

        this.currentCookTime = compoundTag.getShort("CookTime");
    }

    public void writeAdditionalData(@NonNull CompoundTag compoundTag) {
        compoundTag.putShort("CookTime", (short) this.currentCookTime);
        ListTag itemsTag = new ListTag();

        for (int slot = SLOT_LEFT; slot < this.inventory.length; ++slot) {
            if (this.inventory[slot] != null) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putByte("Slot", (byte) slot);
                this.inventory[slot].writeToNBT(itemTag);
                itemsTag.addTag(itemTag);
            }
        }

        compoundTag.put("Items", itemsTag);
    }

    public int getMaxStackSize() {
        return 64;
    }

    public int getCookProgressScaled(int i) {
        return this.maxCookTime == 0 ? 0 : this.currentCookTime * i / this.maxCookTime;
    }

    public boolean isProcessing() {
        return this.currentCookTime > 0;
    }

    @Override
    public void tick() {
        boolean updated = false;

        if (this.worldObj == null || !this.worldObj.isClientSide) {
            if (this.canSmelt()) {
                ++this.currentCookTime;

                if (this.currentCookTime >= this.maxCookTime) {
                    this.currentCookTime = 0;
                    this.smeltItem();
                    updated = true;
                }
            } else {
                this.currentCookTime = 0;
            }

            boolean isProcessing = this.canSmelt();
            if (this.worldObj != null) {
//                BlockLogicBeehive.updateBeehiveBlockState(this.worldObj, new TilePos(this.x, this.y, this.z), isProcessing);
            } else if (this.carriedBlock != null) {
                this.carriedBlock.blockId = isProcessing ? StardewBlocks.BEEHIVE_ACTIVE.id() : StardewBlocks.BEEHIVE_IDLE.id();
            }
        }

        if (updated) {
            this.setChanged();
        }
    }

    private boolean canSmelt() {
        if (this.inventory[SLOT_LEFT] == null && this.inventory[SLOT_RIGHT] == null) {
            return false;
        }

        ItemStack result = null;
//        for (RecipeEntryBeehive recipe : Registries.RECIPES.getAllBeehiveRecipes()) {
//            if (recipe != null && recipe.matches(this.inventory[SLOT_LEFT], this.inventory[SLOT_RIGHT])) {
//                result = recipe.getOutput();
//                break;
//            }
//        }

        if (result == null) return false;
        if (this.inventory[SLOT_RESULT] == null) return true;
        if (!this.inventory[SLOT_RESULT].isItemEqual(result)) return false;
        if (this.inventory[SLOT_RESULT].stackSize < this.getMaxStackSize() &&
            this.inventory[SLOT_RESULT].stackSize < this.inventory[SLOT_RESULT].getMaxStackSize()) return true;

        return this.inventory[SLOT_RESULT].stackSize < result.getMaxStackSize();
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack result = null;
//            for (RecipeEntryBeehive recipe : Registries.RECIPES.getAllBeehiveRecipes()) {
//                if (recipe != null && recipe.matches(this.inventory[SLOT_LEFT], this.inventory[SLOT_RIGHT])) {
//                    result = recipe.getOutput();
//                    break;
//                }
//            }

            if (result != null) {
                if (this.inventory[SLOT_RESULT] == null) {
                    this.inventory[SLOT_RESULT] = result.copy();
                } else if (this.inventory[SLOT_RESULT].isItemEqual(result)) {
                    this.inventory[SLOT_RESULT].stackSize += result.stackSize;
                }
            }

            if (this.inventory[SLOT_LEFT] != null) {
                --this.inventory[SLOT_LEFT].stackSize;
                if (this.inventory[SLOT_LEFT].stackSize <= 0) this.inventory[SLOT_LEFT] = null;
            }
            if (this.inventory[SLOT_RIGHT] != null) {
                --this.inventory[SLOT_RIGHT].stackSize;
                if (this.inventory[SLOT_RIGHT].stackSize <= 0) this.inventory[SLOT_RIGHT] = null;
            }
        }
    }

    public boolean stillValid(@NonNull Player player) {
//        if (this.worldObj != null && this.worldObj.getTileEntity(this.x, this.y, this.z) == this) {
//            return player.distanceToSqr((double) this.x + 0.5, (double) this.y + 0.5, (double) this.z + 0.5) <= 64.0;
//        }
        return false;
    }

    @Override
    public void dropContents(World world, int x, int y, int z) {
        super.dropContents(world, x, y, z);
        if (!BlockLogicBeehive.keepBeehiveInventory) {
            for (int slot = 0; slot < this.getContainerSize(); ++slot) {
                ItemStack item = this.getItem(slot);
                if (item != null) {
                    float rx = this.random.nextFloat() * 0.8F + 0.1F;
                    float ry = this.random.nextFloat() * 0.8F + 0.1F;
                    float rz = this.random.nextFloat() * 0.8F + 0.1F;

                    while (item.stackSize > 0) {
                        int stackSize = this.random.nextInt(21) + 10;
                        if (stackSize > item.stackSize) stackSize = item.stackSize;

                        item.stackSize -= stackSize;
                        EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz,
                            new ItemStack(item.itemID, stackSize, item.getMetadata()));
                        entityItem.xd = this.random.nextGaussian() * 0.05F;
                        entityItem.yd = this.random.nextGaussian() * 0.05F + 0.2F;
                        entityItem.zd = this.random.nextGaussian() * 0.05F;
                        world.entityJoinedWorld(entityItem);
                    }
                }
            }
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        return this.inventory[SLOT_RESULT] != null ? new PacketTileEntityData(this) : null;
    }

    public void sort() {
    }

    @Override
    public void heldTick(World world, Entity holder) {
        this.tick();
    }

    @Override
    public boolean canBeCarried(World world, Entity potentialHolder) {
        return true;
    }

    @Override
    public CarriedBlock getCarriedEntry(World world, Entity holder, Block<?> currentBlock, int currentMeta) {
        return super.getCarriedEntry(world, holder, currentBlock, currentMeta & -8 | 2);
    }
}
