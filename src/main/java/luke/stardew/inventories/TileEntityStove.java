package luke.stardew.inventories;

import com.mojang.nbt.CompoundTag;
import luke.stardew.blocks.BlockStove;
import luke.stardew.misc.LookupCookingIngredients;
import luke.stardew.render.TileEntityRendererStove;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileEntityStove extends TileEntity {
	private final Random random = new Random();

	public int blockMetadata = 0;

	public final List<StoveItem> contentsToCook = new ArrayList<>();
	public final int amountToCook = 6; //divisible by 2 !!
	public ItemStack fuel = null;
	public final int maxFuelAmount = 8; //divisible by 2 !!
	public int maxCookTime = 200;
	public int currentBurnTime = 0;

	public float itemRenderOffset1 = 0.3f;
	public float itemRenderOffset2 = 0.4f;
	public float itemRenderRelative1 = 0;
	public float itemRenderRelative2 = 0;

	public TileEntityStove(){
		for (int i = 0; i < amountToCook; i++) {
			contentsToCook.add(new StoveItem(new ItemStack(Item.stick), maxCookTime));
		}
	}

	@Override
	public void readFromNBT(CompoundTag CompoundTag) {
		super.readFromNBT(CompoundTag);

		for (int i = 0; i < amountToCook; i++) {
			contentsToCook.set(i, new StoveItem(new ItemStack(Item.itemsList[CompoundTag.getInteger("ContentsToCook" + i)]), CompoundTag.getShort("CookTimeLeft" + i)));
		}

		if (CompoundTag.containsKey("Fuel")){
			fuel = new ItemStack(Item.itemsList[CompoundTag.getInteger("Fuel")], CompoundTag.getShort("FuelAmount"));
		}
		this.currentBurnTime = CompoundTag.getShort("BurnTime");
	}

	@Override
	public void writeToNBT(CompoundTag CompoundTag) {
		super.writeToNBT(CompoundTag);

		for (int i = 0; i < contentsToCook.size(); i++) {
			CompoundTag.putInt("ContentsToCook" + i, contentsToCook.get(i).getStack().itemID);
			CompoundTag.putShort("CookTimeLeft" + i, (short) contentsToCook.get(i).getCookTimeLeft());
		}

		CompoundTag.putShort("BurnTime", (short) currentBurnTime);
		if (fuel != null){
			CompoundTag.putInt("Fuel", fuel.itemID);
			CompoundTag.putShort("FuelAmount", (short) fuel.stackSize);
		}
	}

	private int hasAvailableSpace(){
		for (int i = 0; i < contentsToCook.size(); i++) {
			if (contentsToCook.get(i).stack.itemID == Item.stick.id){
				return i;
			}
		}
		return -1;
	}

	public boolean addContentToCook(ItemStack stack){
		int place = hasAvailableSpace();
		if (place >= 0){
			contentsToCook.set(place, new StoveItem(stack, maxCookTime));
			return true;
		}
		return false;
	}

	public boolean addFuel(Item item){
		if (fuel != null && fuel.itemID == item.id && fuel.stackSize < maxFuelAmount){
			++fuel.stackSize;
			return true;
		} else if (fuel == null) {
			fuel = new ItemStack(item);
		}
		return false;
	}

	private boolean canProduce() {
		for (StoveItem content : contentsToCook){
			if (content.stack.itemID != Item.stick.id) return true;
		}
		return false;
	}

	public boolean isBurning() {
		return this.currentBurnTime > 0;
	}

	private int getBurnTimeFromItem(ItemStack itemStack) {
		if (itemStack == null) {
			return 0;
		}
		return LookupFuelFurnace.instance.getFuelYield(itemStack.getItem().id);
	}

	public void dropBucket(){
		float f = this.random.nextFloat() * 0.8f + 0.1f;
		float f1 = this.random.nextFloat() * 0.8f + 0.1f;
		float f2 = this.random.nextFloat() * 0.8f + 0.1f;

		EntityItem entityitem = new EntityItem(this.worldObj, (float)x + f, (float)y + f1, (float)z + f2, new ItemStack(Item.bucket, 1));
		float f3 = 0.05f;
		entityitem.xd = (float)this.random.nextGaussian() * f3;
		entityitem.yd = (float)this.random.nextGaussian() * f3 + 0.2f;
		entityitem.zd = (float)this.random.nextGaussian() * f3;
		this.worldObj.entityJoinedWorld(entityitem);
	}

	protected void updateStove() {
		BlockStove.updateStoveBlockState(this.currentBurnTime > 0, this.worldObj, this.x, this.y, this.z);
	}

	@Override
	public void tick() {
		boolean isBurnTimeHigherThan0 = this.currentBurnTime > 0;
		boolean stoveUpdated = false;

		if (this.currentBurnTime > 0) {
			--this.currentBurnTime;
		}
		if (this.worldObj != null && !this.worldObj.isClientSide) {
			if (this.currentBurnTime == 0 && this.canProduce()) {
				if (fuel != null){
					--fuel.stackSize;
					currentBurnTime = getBurnTimeFromItem(fuel);
					if (fuel.stackSize == 0) {
						if (fuel.itemID == Item.bucketLava.id) {
							dropBucket();
						}
						fuel = null;
					}
				}

				if (this.currentBurnTime > 0) {
					stoveUpdated = true;
				}
			}

			if (this.isBurning() && this.canProduce()) {
				for (int i = 0; i < contentsToCook.size(); i++) {
					StoveItem content = contentsToCook.get(i);
					if (!content.isCookable()){
						continue;
					}

					--content.cookTimeLeft;
					if (content.cookTimeLeft <= 0){
						worldObj.dropItem(x, y + 1, z, new ItemStack(LookupCookingIngredients.instance.getResults(content.stack.itemID), 1));
						contentsToCook.set(i, new StoveItem(new ItemStack(Item.stick), maxCookTime));
					}
				}
			}

			if (isBurnTimeHigherThan0 != this.currentBurnTime > 0) {
				stoveUpdated = true;
				this.updateStove();
			}
		}
		if (stoveUpdated) {
			this.onInventoryChanged();
		}
	}

	public class StoveItem{
		private final ItemStack stack;
		private int cookTimeLeft;

		public ItemStack getStack() {
			return stack;
		}

		public int getCookTimeLeft() {
			return cookTimeLeft;
		}

		public boolean isCookable() {
			return stack.itemID != Item.stick.id;
		}

		public StoveItem(ItemStack stack, int cookTimeLeft) {
			this.stack = stack;
			this.cookTimeLeft = cookTimeLeft;
		}
	}
}
