package luke.stardew.mixin;

import com.mojang.nbt.CompoundTag;
import luke.stardew.interfaces.IEntityBobberMixin;
import luke.stardew.entities.EntityItemFireResistant;
import luke.stardew.items.ItemToolFishingRodTiered;
import luke.stardew.items.StardewItems;
import net.minecraft.core.HitResult;
import net.minecraft.core.achievement.stat.StatList;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityBobber;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.util.phys.Vec3d;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Season;
import net.minecraft.core.world.season.Seasons;
import net.minecraft.core.world.weather.Weather;
import org.checkerframework.common.aliasing.qual.Unique;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = EntityBobber.class, remap = false)
public class EntityBobberMixin extends Entity implements IEntityBobberMixin {
	@Shadow
	public EntityPlayer angler;
	@Shadow
	private int xTile = -1;
	@Shadow
	private int yTile = -1;
	@Shadow
	private int zTile = -1;
	@Shadow
	private int inTile = 0;
	@Shadow
	private boolean inGround = false;
	@Shadow
	public int shake = 0;
	@Shadow
	private int ticksInGround;
	@Shadow
	private int ticksInAir = 0;
	@Shadow
	private int ticksCatchable = 0;
	@Shadow
	public Entity bobber = null;
	@Shadow
	private int field_6388_l;
	@Shadow
	private double field_6387_m;
	@Shadow
	private double field_6386_n;
	@Shadow
	private double field_6385_o;
	@Shadow
	private double field_6384_p;
	@Shadow
	private double field_6383_q;

	@Unique
	private boolean isInLava = false;

	public EntityBobberMixin(World world) {
		super(world);
	}

	@Inject(method = "<init>(Lnet/minecraft/core/world/World;Lnet/minecraft/core/entity/player/EntityPlayer;)V", at = @At("TAIL"))
	private void init(CallbackInfo ci){
		entityData.define(3, 0); //hasBait
	}

	@Override
	public boolean hasBait() {
		return entityData.getInt(3) == 1;
	}

	@Override
	public void setBait(boolean bool) {
		if (bool){
			entityData.set(3, 1);
		}else {
			entityData.set(3, 0);
		}
	}

	/**
	 * @author DundiGundi
	 * @reason modifying if statement that removes bobber when not fishingrod held to detect for the tiered fishing rod too
	 * 			and decreasing catchTime based on material
	 * 			and making lava also a possible fishing place
	 */
	@Overwrite
	public void tick() {
		block34: {
			super.tick();
			if (this.field_6388_l > 0) {
				double d4;
				double d = this.x + (this.field_6387_m - this.x) / (double)this.field_6388_l;
				double d1 = this.y + (this.field_6386_n - this.y) / (double)this.field_6388_l;
				double d2 = this.z + (this.field_6385_o - this.z) / (double)this.field_6388_l;
				for (d4 = this.field_6384_p - (double)this.yRot; d4 < -180.0; d4 += 360.0) {
				}
				while (true) {
					if (!(d4 >= 180.0)) {
						this.yRot = (float)((double)this.yRot + d4 / (double)this.field_6388_l);
						this.xRot = (float)((double)this.xRot + (this.field_6383_q - (double)this.xRot) / (double)this.field_6388_l);
						--this.field_6388_l;
						this.setPos(d, d1, d2);
						this.setRot(this.yRot, this.xRot);
						return;
					}
					d4 -= 360.0;
				}
			}
			if (!this.world.isClientSide) {
				ItemStack itemstack = this.angler.getCurrentEquippedItem();
				if (this.angler.removed || !this.angler.isAlive() || itemstack == null || (itemstack.getItem() != Item.toolFishingrod  && itemstack.getItem().getClass() != ItemToolFishingRodTiered.class) || this.distanceToSqr(this.angler) > 1024.0) {
					this.remove();
					this.angler.fishEntity = null;
					return;
				}
				if (this.bobber != null) {
					if (!this.bobber.removed) {
						this.x = this.bobber.x;
						this.y = this.bobber.bb.minY + (double)this.bobber.bbHeight * 0.8;
						this.z = this.bobber.z;
						return;
					}
					this.bobber = null;
				}
			}
			if (this.shake > 0) {
				--this.shake;
			}
			if (this.inGround) {
				int i = this.world.getBlockId(this.xTile, this.yTile, this.zTile);
				if (i != this.inTile) {
					this.inGround = false;
					this.xd *= this.random.nextFloat() * 0.2f;
					this.yd *= this.random.nextFloat() * 0.2f;
					this.zd *= this.random.nextFloat() * 0.2f;
					this.ticksInGround = 0;
					this.ticksInAir = 0;
					break block34;
				} else {
					++this.ticksInGround;
					if (this.ticksInGround == 1200) {
						this.remove();
					}
					return;
				}
			}
			++this.ticksInAir;
		}
		Vec3d vec3d = Vec3d.createVector(this.x, this.y, this.z);
		Vec3d vec3d1 = Vec3d.createVector(this.x + this.xd, this.y + this.yd, this.z + this.zd);
		HitResult movingobjectposition = this.world.checkBlockCollisionBetweenPoints(vec3d, vec3d1);
		vec3d = Vec3d.createVector(this.x, this.y, this.z);
		vec3d1 = Vec3d.createVector(this.x + this.xd, this.y + this.yd, this.z + this.zd);
		if (movingobjectposition != null) {
			vec3d1 = Vec3d.createVector(movingobjectposition.location.xCoord, movingobjectposition.location.yCoord, movingobjectposition.location.zCoord);
		}
		Entity entity = null;
		List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.bb.addCoord(this.xd, this.yd, this.zd).expand(1.0, 1.0, 1.0));
		double d3 = 0.0;
        for (Entity value : list) {
            double d6;
            float f2;
			HitResult movingobjectposition1;
            if (!value.isPickable() || value == this.angler && this.ticksInAir < 5 || (movingobjectposition1 = value.bb.expand(f2 = 0.3f, f2, f2).func_1169_a(vec3d, vec3d1)) == null || !((d6 = vec3d.distanceTo(movingobjectposition1.location)) < d3) && d3 != 0.0)
                continue;
            entity = value;
            d3 = d6;
        }
		if (entity != null) {
			movingobjectposition = new HitResult(entity);
		}
		if (movingobjectposition != null && movingobjectposition.entity != null && movingobjectposition.entity.hurt(this.angler, 0, DamageType.COMBAT)) {
			this.bobber = movingobjectposition.entity;
		}
		if (this.inGround) {
			return;
		}
		this.move(this.xd, this.yd, this.zd);
		float f = MathHelper.sqrt_double(this.xd * this.xd + this.zd * this.zd);
		this.yRot = (float)(Math.atan2(this.xd, this.zd) * 180.0 / 3.1415927410125732);
		this.xRot = (float)(Math.atan2(this.yd, f) * 180.0 / 3.1415927410125732);
		while (this.xRot - this.xRotO < -180.0f) {
			this.xRotO -= 360.0f;
		}
		while (this.xRot - this.xRotO >= 180.0f) {
			this.xRotO += 360.0f;
		}
		while (this.yRot - this.yRotO < -180.0f) {
			this.yRotO -= 360.0f;
		}
		while (this.yRot - this.yRotO >= 180.0f) {
			this.yRotO += 360.0f;
		}
		this.xRot = this.xRotO + (this.xRot - this.xRotO) * 0.2f;
		this.yRot = this.yRotO + (this.yRot - this.yRotO) * 0.2f;
		float f1 = 0.92f;
		if (this.onGround || this.horizontalCollision) {
			f1 = 0.5f;
		}
		int k = 5;
		double d5 = 0.0;
		for (int l = 0; l < k; ++l) {
			double d8 = this.bb.minY + (this.bb.maxY - this.bb.minY) * (double)l / (double)k - 0.125 + 0.125;
			double d9 = this.bb.minY + (this.bb.maxY - this.bb.minY) * (double)(l + 1) / (double)k - 0.125 + 0.125;
			AABB axisalignedbb1 = AABB.getBoundingBoxFromPool(this.bb.minX, d8, this.bb.minZ, this.bb.maxX, d9, this.bb.maxZ);
            isInLava = this.world.isAABBInMaterial(axisalignedbb1, Material.lava);
			if (!this.world.isAABBInMaterial(axisalignedbb1, Material.water) && !this.world.isAABBInMaterial(axisalignedbb1, Material.lava)) continue;
			d5 += 1.0 / (double)k;
		}
		if (d5 > 0.0) {
			if (this.ticksCatchable > 0) {
				--this.ticksCatchable;
			} else {
				int catchRate = 500;
				int rainRate = 0;
				int algaeRate = 0;
				int materialRate = 0;
				int baitRate = 0;
				if (this.world.canBlockBeRainedOn(MathHelper.floor_double(this.x), MathHelper.floor_double(this.y) + 1, MathHelper.floor_double(this.z))) {
					rainRate = 100;
				}
				if (this.world.getBlockId(MathHelper.floor_double(this.x), MathHelper.floor_double(this.y) + 1, MathHelper.floor_double(this.z)) == Block.algae.id) {
					algaeRate = 50;
				}
				if (angler.getCurrentEquippedItem().itemID == StardewItems.toolFishingrodDiamond.id){
					materialRate = 200;
				} else if (angler.getCurrentEquippedItem().itemID == StardewItems.toolFishingrodIron.id || angler.getCurrentEquippedItem().itemID == StardewItems.toolFishingrodSteel.id) {
					materialRate = 100;
				}
				if (((IEntityBobberMixin)angler.fishEntity).hasBait()){
					baitRate = 50;
				}
				if (this.random.nextInt(catchRate - rainRate - algaeRate - materialRate - baitRate) == 0) {
					double zOff;
					this.ticksCatchable = this.random.nextInt(30) + 10;
					this.yd -= 0.2f;
					this.world.playSoundAtEntity(null, this, "random.splash", 0.25f, 1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.4f);
					float f3 = MathHelper.floor_double(this.bb.minY);
					int i1 = 0;
					while ((float)i1 < 1.0f + this.bbWidth * 20.0f) {
						double xOff = (this.random.nextFloat() * 2.0f - 1.0f) * this.bbWidth;
						zOff = (this.random.nextFloat() * 2.0f - 1.0f) * this.bbWidth;
						this.world.spawnParticle("bubble", this.x + xOff, f3 + 1.0f, this.z + zOff, this.xd, this.yd - (double)(this.random.nextFloat() * 0.2f), this.zd);
						++i1;
					}
					int j1 = 0;
					while ((float)j1 < 1.0f + this.bbWidth * 20.0f) {
						double xOff = (this.random.nextFloat() * 2.0f - 1.0f) * this.bbWidth;
						zOff = (this.random.nextFloat() * 2.0f - 1.0f) * this.bbWidth;
						this.world.spawnParticle("splash", this.x + xOff, f3 + 1.0f, this.z + zOff, this.xd, this.yd, this.zd);
						++j1;
					}
				}
			}
		}
		if (this.ticksCatchable > 0) {
			this.yd -= (double)(this.random.nextFloat() * this.random.nextFloat() * this.random.nextFloat()) * 0.2;
		}
		double d7 = d5 * 2.0 - 1.0;
		this.yd += (double)0.04f * d7;
		if (d5 > 0.0) {
			f1 = (float)((double)f1 * 0.9);
			this.yd *= 0.8;
		}
		this.xd *= f1;
		this.yd *= f1;
		this.zd *= f1;
		this.setPos(this.x, this.y, this.z);
	}

	/**
	 * @author DundiGundi
	 * @reason modifying catchable fishes based on type of fishing rod, position of bobber, season or weather
	 */
	@Overwrite
	public int catchFish() {
		int byte0 = 0;
		if (this.bobber != null) {
			double d = this.angler.x - this.x;
			double d2 = this.angler.y - this.y;
			double d4 = this.angler.z - this.z;
			double d6 = MathHelper.sqrt_double(d * d + d2 * d2 + d4 * d4);
			double d8 = 0.1;
			this.bobber.xd += d * d8;
			this.bobber.yd += d2 * d8 + (double)MathHelper.sqrt_double(d6) * 0.08;
			this.bobber.zd += d4 * d8;
			byte0 = 3;
		} else if (this.ticksCatchable > 0) {
			if (isInLava){
				EntityItemFireResistant entityitem = new EntityItemFireResistant(this.world, this.x, this.y, this.z, new ItemStack(getCatchableFish()));
				double d1 = this.angler.x - this.x;
				double d3 = this.angler.y - this.y;
				double d5 = this.angler.z - this.z;
				double d7 = MathHelper.sqrt_double(d1 * d1 + d3 * d3 + d5 * d5);
				double d9 = 0.1;
				entityitem.xd = d1 * d9;
				entityitem.yd = d3 * d9 + (double)MathHelper.sqrt_double(d7) * 0.08;
				entityitem.zd = d5 * d9;
				this.world.entityJoinedWorld(entityitem);
			}else {
				EntityItem entityitem = new EntityItem(this.world, this.x, this.y, this.z, new ItemStack(getCatchableFish()));
				double d1 = this.angler.x - this.x;
				double d3 = this.angler.y - this.y;
				double d5 = this.angler.z - this.z;
				double d7 = MathHelper.sqrt_double(d1 * d1 + d3 * d3 + d5 * d5);
				double d9 = 0.1;
				entityitem.xd = d1 * d9;
				entityitem.yd = d3 * d9 + (double)MathHelper.sqrt_double(d7) * 0.08;
				entityitem.zd = d5 * d9;
				this.world.entityJoinedWorld(entityitem);
			}
			this.angler.addStat(StatList.fishCaughtStat, 1);
			byte0 = 1;

		}
		if (this.inGround) {
			byte0 = 2;
		}
		this.remove();
		this.angler.fishEntity = null;
		return byte0;
	}

	@org.spongepowered.asm.mixin.Unique
	private Item getCatchableFish() {
		Season season = world.seasonManager.getCurrentSeason();
		Weather weather = world.weatherManager.getCurrentWeather();

		if (isInLava) {
			return StardewItems.fishEelLava;
		} else {
			if (weather == Weather.overworldStorm && world.rand.nextInt(15) == 0) {
					return StardewItems.fishSword;

				} else if (weather == Weather.overworldFog && !world.isDaytime() && world.rand.nextInt(15) == 0){
					return StardewItems.fishGhost;

// add way to check position of bobber
// 				} else if (world.rand.nextInt(15) == 0){
//					return StardewItems.fishStone;
//
				} else if (season == Seasons.OVERWORLD_SPRING) {
					return StardewItems.foodBassRaw;

				} else if (season == Seasons.OVERWORLD_SUMMER) {
					return StardewItems.foodSnapperRaw;

				} else if (season == Seasons.OVERWORLD_FALL) {
					return Item.foodFishRaw;

				} else if (season == Seasons.OVERWORLD_WINTER) {
					return StardewItems.foodSalmonRaw;
				}
			}
        return Item.bone;
    }

	@Override
	public void init() {

	}

	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {

	}

	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {

	}
}
