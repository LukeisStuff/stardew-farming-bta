package luke.stardew.blocks;

import luke.stardew.EntityBeeFX;
import luke.stardew.StardewAchievements;
import luke.stardew.items.StardewItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.block.BlockRotatableHorizontal;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;

import java.util.Objects;
import java.util.Random;

import static net.minecraft.core.entity.animal.EntityFireflyCluster.FireflyColor.GREEN;

public class BlockBeehiveActive extends BlockRotatableHorizontal {
	protected final boolean isActive;

	public BlockBeehiveActive(String key, int id, boolean flag) {
		super(key, id, Material.wood);
		this.setTicking(true);
		this.isActive = flag;
	}

	public void onBlockAdded(World world, int x, int y, int z) {
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		switch (dropCause) {
			case PICK_BLOCK:
			case EXPLOSION:
			case PROPER_TOOL:
			case SILK_TOUCH:
				return new ItemStack[]{new ItemStack(StardewBlocks.beehive)};
			default:
				return null;
		}
	}

	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
		double h = 0.5;
		double q = 0.25;
		double random = (world.rand.nextInt(1) - Math.random());
		if (meta == 2) {
			if (rand.nextInt(2) == 0) {
				mc.effectRenderer.addEffect(new EntityBeeFX(world, (double) x + h, (double) y - random, z - q, 0.0, 0.0, 0.0));
				if (rand.nextInt(2) == 0) {
					world.playSoundEffect(null, SoundCategory.ENTITY_SOUNDS, (double) x + 0.5, (double) y + 0.5, (double) z + 0.5, "stardew.bee", 0.2F, rand.nextFloat() * 0.4F + 0.8F);
				}
			}
		} else if (meta == 3) {
			if (rand.nextInt(2) == 0) {
				mc.effectRenderer.addEffect(new EntityBeeFX(world, x + h, (double) y - random, z + 1 + q, 0.0, 0.0, 0.0));
				if (rand.nextInt(2) == 0) {
					world.playSoundEffect(null, SoundCategory.ENTITY_SOUNDS, (double) x + 0.5, (double) y + 0.5, (double) z + 0.5, "stardew.bee", 0.2F, rand.nextFloat() * 0.4F + 0.8F);
				}
			}
		} else if (meta == 4) {
			if (rand.nextInt(2) == 0) {
				mc.effectRenderer.addEffect(new EntityBeeFX(world, x - q, (double) y - random, z + h, 0.0, 0.0, 0.0));
				if (rand.nextInt(2) == 0) {
					world.playSoundEffect(null, SoundCategory.ENTITY_SOUNDS, (double) x + 0.5, (double) y + 0.5, (double) z + 0.5, "stardew.bee", 0.2F, rand.nextFloat() * 0.4F + 0.8F);
				}
			}
		} else if (meta == 5) {
			if (rand.nextInt(2) == 0) {
				mc.effectRenderer.addEffect(new EntityBeeFX(world, x + 1 + q , (double) y - random, (double) z + h, 0.0, 0.0, 0.0));
				if (rand.nextInt(2) == 0) {
					world.playSoundEffect(null, SoundCategory.ENTITY_SOUNDS, (double) x + 0.5, (double) y + 0.5, (double) z + 0.5, "stardew.bee", 0.2F, rand.nextFloat() * 0.4F + 0.8F);
				}
			}
		} else {
			if (rand.nextInt(2) == 0) {
				mc.effectRenderer.addEffect(new EntityBeeFX(world, x, y, z, 0.0, 0.0, 0.0));
				if (rand.nextInt(2) == 0) {
					world.playSoundEffect(null, SoundCategory.ENTITY_SOUNDS, (double) x + 0.5, (double) y + 0.5, (double) z + 0.5, "stardew.bee", 0.2F, rand.nextFloat() * 0.4F + 0.8F);
				}
			}
		}

	}


	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (world.seasonManager.getCurrentSeason() != Seasons.OVERWORLD_WINTER) {
			int l = world.getBlockMetadata(x, y, z);
			if (rand.nextInt(50) == 0) {
				world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.beehiveHoney.id, l);
			}
		}
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		int l = world.getBlockMetadata(x, y, z);
		if (this.isActive) {
			world.setBlockAndMetadataWithNotify(x, y, z, StardewBlocks.beehive.id, l);
			world.playSoundAtEntity(player, player, "random.pop", 0.2F, 0.5F);
			player.inventory.insertItem(new ItemStack(StardewItems.honey, 1), true);
			player.triggerAchievement(StardewAchievements.BEEHIVE);
			return true;
		}
        return false;
    }
}
