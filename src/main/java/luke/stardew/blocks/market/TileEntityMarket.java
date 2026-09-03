package luke.stardew.blocks.market;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.pos.TilePos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TileEntityMarket extends TileEntity {
    private @Nullable Player owner = null;

    private @Nullable ItemStack product;

    private int price = 0;
    private int unitsPerPurchase = 1;
    private int unitsSold = 0;

    @Override
    public void readAdditionalData(@NotNull CompoundTag compoundTag) {
        this.product = ItemStack.readItemStackFromNbt(compoundTag.getCompound("product"));

        this.price = compoundTag.getInteger("price");
        this.unitsSold = compoundTag.getInteger("unitsSold");
        this.unitsPerPurchase = compoundTag.getInteger("unitsPerPurchase");
    }

    @Override
    public void writeAdditionalData(@NotNull CompoundTag compoundTag) {
        if (this.product != null) {
            compoundTag.put("product", this.product.writeToNBT(new CompoundTag()));
        }

        compoundTag.putInt("price",            this.price);
        compoundTag.putInt("unitsSold",        this.unitsSold);
        compoundTag.putInt("unitsPerPurchase", this.unitsPerPurchase);
    }

    public void setProduct(ItemStack stack) {
        if (
            this.product != null
                && this.product.stackSize > 0
                && this.worldObj != null
        ) {
            var queryPos = this.tilePos.up(new TilePos());

            while (this.product.stackSize > 0) {
                var dropStack = this.product.splitStack(Math.min(this.product.getMaxStackSize(), this.product.stackSize));
                this.worldObj.dropItem(queryPos, dropStack);
            }
        }

        this.unitsSold = 0;
        this.product = stack;
    }

    public @Nullable ItemStack attemptAddToStock(ItemStack stack) {
        if (stack.stackSize > 0 && this.product != null && this.product.canStackWith(stack)) {
            this.product.stackSize += stack.stackSize;
            return null;
        }

        return stack;
    }

    public @Nullable Player getOwner() {
        return owner;
    }

    public void setOwner(@Nullable Player owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUnitsPerPurchase() {
        return unitsPerPurchase;
    }

    public void setUnitsPerPurchase(int unitsPerPurchase) {
        this.unitsPerPurchase = unitsPerPurchase;
    }

    public int getUnitsSold() {
        return unitsSold;
    }
}
