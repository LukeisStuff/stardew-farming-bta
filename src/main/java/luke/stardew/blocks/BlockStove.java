package luke.stardew.blocks;

import luke.stardew.inventories.TileEntityStove;
import luke.stardew.misc.LookupCookingIngredients;
import luke.stardew.misc.LookupFuelStove;
import net.minecraft.core.Global;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockTileEntityRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import java.util.Random;

public class BlockStove extends BlockTileEntityRotatable {
	protected Random stoveRand = new Random();
	protected final boolean isActive;
	protected static boolean keepStoveInventory = false;
	protected TileEntityStove tileEntityStove;

	public BlockStove(String key, int id, boolean flag) {
		super(key, id, Material.stone);
		this.isActive = flag;
	}

	private boolean isIngredient(ItemStack item){
		return LookupCookingIngredients.instance.getIngredientList().containsKey(item.itemID);
	}

	private boolean isFuel(ItemStack item){
		return LookupFuelStove.instance.getFuelList().containsKey(item.itemID);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}

	@Override
	public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
		super.onBlockPlaced(world, x, y, z, side, entity, sideHeight);
		tileEntityStove.blockMetadata = world.getBlockMetadata(x, y, z);
	}

	@Override
	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		switch (dropCause) {
			case PICK_BLOCK:
			case EXPLOSION:
			case PROPER_TOOL:
			case SILK_TOUCH:
				return new ItemStack[]{new ItemStack(StardewBlocks.stoveIdle)};
			default:
				return null;
		}
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if (this.isActive) {
			int l = world.getBlockMetadata(x, y, z);
			double poxX = (double)x + 0.5;
			double posY = (double)y + 0.0 + (double)(rand.nextFloat() * 6.0F / 16.0F);
			double posZ = (double)z + 0.5;
			double f3 = 0.5199999809265137;
			double f4 = (double)(rand.nextFloat() * 0.6F - 0.3F);
			if (l == 4) {
				world.spawnParticle("smoke", poxX - f3, posY, posZ + f4, 0.0, 0.0, 0.0, 0);
				world.spawnParticle("flame", poxX - f3, posY, posZ + f4, 0.0, 0.0, 0.0, 0);
			} else if (l == 5) {
				world.spawnParticle("smoke", poxX + f3, posY, posZ + f4, 0.0, 0.0, 0.0, 0);
				world.spawnParticle("flame", poxX + f3, posY, posZ + f4, 0.0, 0.0, 0.0, 0);
			} else if (l == 2) {
				world.spawnParticle("smoke", poxX + f4, posY, posZ - f3, 0.0, 0.0, 0.0, 0);
				world.spawnParticle("flame", poxX + f4, posY, posZ - f3, 0.0, 0.0, 0.0, 0);
			} else if (l == 3) {
				world.spawnParticle("smoke", poxX + f4, posY, posZ + f3, 0.0, 0.0, 0.0, 0);
				world.spawnParticle("flame", poxX + f4, posY, posZ + f3, 0.0, 0.0, 0.0, 0);
			}
		}

		TileEntityStove tileEntity = (TileEntityStove) world.getBlockTileEntity(x, y, z);
		float offsetX = tileEntity.itemRenderOffset1;
		float offsetZ = tileEntity.itemRenderOffset2;
		float relativeX = tileEntity.itemRenderRelative1;
		float relativeZ = tileEntity.itemRenderRelative2;

		if (tileEntity.isBurning()){
			for (TileEntityStove.StoveItem content : tileEntity.contentsToCook){
				if (relativeX < offsetX * 3) {
					if (content.getStack().getItem().id != Item.stick.id){
						world.spawnParticle("smoke", x + 0.2f + relativeX, y + 1.2f, z + 0.24f + relativeZ, 0, 0, 0, 0);
					}

					relativeX += offsetX;
					if (relativeX == offsetX * 3) {
						relativeX = 0;
						if (relativeZ < offsetZ * 2) {
							relativeZ += offsetZ;
						}
					}
				}
			}
		}
	}

	@Override
	public boolean onBlockRightClicked(World world, int x, int y, int z, EntityPlayer player, Side side, double xPlaced, double yPlaced) {
		if (!world.isClientSide) {
			TileEntityStove tileEntityStove = (TileEntityStove) world.getBlockTileEntity(x, y, z);

			if (tileEntityStove == null) return false;
			if (player.getHeldItem() == null) return false;

			ItemStack heldItem = player.getHeldItem();

			if (world.getBlockId(x, y + 1, z) == 0){
				if((isIngredient(heldItem) && tileEntityStove.addContentToCook(new ItemStack(heldItem))) || (isFuel(heldItem)) && tileEntityStove.addFuel(heldItem)){
					player.getHeldItem().consumeItem(player);
				}
			}
		}
		return true;
	}

	public static void updateStoveBlockState(boolean lit, World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		if (tileentity == null) {
			String msg = "Stove is missing Tile Entity at x: " + x + " y: " + y + " z: " + z + ", block will be removed!";
			if (Global.BUILD_CHANNEL.isUnstableBuild()) {
				throw new RuntimeException(msg);
			} else {
				world.setBlockWithNotify(x, y, z, 0);
				System.out.println(msg);
			}
		}else {
			keepStoveInventory = true;
			if (lit) {
				world.setBlockWithNotify(x, y, z, StardewBlocks.stoveActive.id);
			} else {
				world.setBlockWithNotify(x, y, z, StardewBlocks.stoveIdle.id);
			}

			keepStoveInventory = false;
			world.setBlockMetadataWithNotify(x, y, z, meta);
			tileentity.validate();
			world.setBlockTileEntity(x, y, z, tileentity);
		}
	}

	@Override
	protected TileEntity getNewBlockEntity() {
		tileEntityStove = new TileEntityStove();
		return tileEntityStove;
	}

	@Override
	public void onBlockRemoved(World world, int x, int y, int z, int data) {
		if (!keepStoveInventory) {
			TileEntityStove tileEntityStove = (TileEntityStove) world.getBlockTileEntity(x, y, z);

			for (TileEntityStove.StoveItem stoveItem : tileEntityStove.contentsToCook) {
				if (stoveItem.getStack().itemID != Item.stick.id) {
					float f = this.stoveRand.nextFloat() * 0.8f + 0.1f;
					float f1 = this.stoveRand.nextFloat() * 0.8f + 0.1f;
					float f2 = this.stoveRand.nextFloat() * 0.8f + 0.1f;

					EntityItem entityitem = new EntityItem(world, (float) x + f, (float) y + f1, (float) z + f2, new ItemStack(stoveItem.getStack().itemID, stoveItem.getStack().stackSize, stoveItem.getStack().getMetadata()));
					float f3 = 0.05f;
					entityitem.xd = (float) this.stoveRand.nextGaussian() * f3;
					entityitem.yd = (float) this.stoveRand.nextGaussian() * f3 + 0.2f;
					entityitem.zd = (float) this.stoveRand.nextGaussian() * f3;
					world.entityJoinedWorld(entityitem);
				}
			}
			if (tileEntityStove.fuel != null) {
				float f = this.stoveRand.nextFloat() * 0.8f + 0.1f;
				float f1 = this.stoveRand.nextFloat() * 0.8f + 0.1f;
				float f2 = this.stoveRand.nextFloat() * 0.8f + 0.1f;

				while (tileEntityStove.fuel.stackSize > 0) {
					int i1 = this.stoveRand.nextInt(21) + 10;
					if (i1 > tileEntityStove.fuel.stackSize) {
						i1 = tileEntityStove.fuel.stackSize;
					}

					tileEntityStove.fuel.stackSize -= i1;
					EntityItem entityitem = new EntityItem(world, (float)x + f, (float)y + f1, (float)z + f2, new ItemStack(tileEntityStove.fuel.itemID, i1, tileEntityStove.fuel.getMetadata()));
					float f3 = 0.05f;
					entityitem.xd = (float)this.stoveRand.nextGaussian() * f3;
					entityitem.yd = (float)this.stoveRand.nextGaussian() * f3 + 0.2f;
					entityitem.zd = (float)this.stoveRand.nextGaussian() * f3;
					world.entityJoinedWorld(entityitem);
				}
			}
		}

		super.onBlockRemoved(world, x, y, z, data);
	}
}
