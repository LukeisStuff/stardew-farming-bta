package luke.stardew.blocks;

import luke.stardew.items.StardewItems;
import net.minecraft.core.block.BlockFlower;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;
import turniplabs.halplibe.helper.TextureHelper;

import java.util.Random;

import static luke.stardew.StardewMod.MOD_ID;

public class BlockBush extends BlockFlower {
	public final int[] growthStageTextures = new int[]{
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "bushSpring.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "bushSummer.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "bushFall.png"),
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "bushWinter.png"),
	};

	public BlockBush(String key, int id) {
		super(key, id);
		setTicking(true);
	}

@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_SPRING) {
			world.setBlockMetadataWithNotify(x, y, z, 0);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_SUMMER) {
			world.setBlockMetadataWithNotify(x, y, z, 1);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_FALL) {
			world.setBlockMetadataWithNotify(x, y, z, 2);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_WINTER) {
			world.setBlockMetadataWithNotify(x, y, z, 3);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_WINTER_ENDLESS) {
			world.setBlockMetadataWithNotify(x, y, z, 3);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_HELL) {
			world.setBlockMetadataWithNotify(x, y, z, 2);
		}

	}

	public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
		if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_SPRING) {
			world.setBlockMetadataWithNotify(x, y, z, 0);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_SUMMER) {
			world.setBlockMetadataWithNotify(x, y, z, 1);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_FALL) {
			world.setBlockMetadataWithNotify(x, y, z, 2);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_WINTER) {
			world.setBlockMetadataWithNotify(x, y, z, 3);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_WINTER_ENDLESS) {
			world.setBlockMetadataWithNotify(x, y, z, 3);
		}
		else if (world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_HELL) {
			world.setBlockMetadataWithNotify(x, y, z, 2);
		}
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(Side side, int data) {
		if (data < 0 || data > 3) {
			data = 3;
		}
		return this.growthStageTextures[data];
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		switch (dropCause) {
			case PICK_BLOCK:
			case SILK_TOUCH:
				return new ItemStack[]{new ItemStack(this)};
			default: {
				if (meta == 0) {
					return new ItemStack[]{new ItemStack(StardewItems.seedsBlueberry)};
				}
				if (meta == 1) {
					return new ItemStack[]{new ItemStack(StardewItems.seedsStrawberry)};
				}
				if (meta == 2) {
					return new ItemStack[]{new ItemStack(StardewItems.seedsCorn)};
				}
				if (meta == 3) {
					return new ItemStack[]{new ItemStack(StardewItems.seedsCauliflower)};
				}
				return null;
			}
		}
	}



}
