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
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;

@Mixin(value = EntityBobber.class, remap = false)
public abstract class EntityBobberMixin extends Entity implements IEntityBobberMixin {
	@Shadow
	public EntityPlayer player;
	@Shadow
	private int xTile = -1;
	@Shadow
	private int yTile = -1;
	@Shadow
	private int zTile = -1;
	@Shadow
	private int ticksInAir;
	@Shadow
	private int ticksCatchable;
	@Shadow
	public Entity hookedEntity;
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
	@Shadow
	private double velocityX;
	@Shadow
	private double velocityY;
	@Shadow
	private double velocityZ;

	@Shadow
	public abstract boolean isInGround();

	@Shadow
	public abstract void setInGround(boolean flag);

	@Unique
	public boolean isInLava = false;

	public EntityBobberMixin(World world) {
		super(world);
		this.ticksInAir = 0;
		this.ticksCatchable = 0;
		this.hookedEntity = null;
		this.setSize(0.25F, 0.25F);
		this.ignoreFrustumCheck = true;
	}

	public EntityBobberMixin(World world, double d, double d1, double d2) {
		this(world);
		this.setPos(d, d1, d2);
		this.ignoreFrustumCheck = true;
	}

	public EntityBobberMixin(World world, EntityPlayer entityplayer) {
		super(world);
		this.ticksInAir = 0;
		this.ticksCatchable = 0;
		this.hookedEntity = null;
		this.ignoreFrustumCheck = true;
		this.player = entityplayer;
		this.setSize(0.25F, 0.25F);
		this.moveTo(entityplayer.x, entityplayer.y + 1.62 - (double)entityplayer.heightOffset, entityplayer.z, entityplayer.yRot, entityplayer.xRot);
		this.x -= MathHelper.cos(this.yRot / 180.0F * 3.1415927F) * 0.16F;
		this.y -= 0.1;
		this.z -= MathHelper.sin(this.yRot / 180.0F * 3.1415927F) * 0.16F;
		this.setPos(this.x, this.y, this.z);
		this.heightOffset = 0.0F;
		float f = 0.4F;
		this.xd = -MathHelper.sin(this.yRot / 180.0F * 3.1415927F) * MathHelper.cos(this.xRot / 180.0F * 3.1415927F) * f;
		this.zd = MathHelper.cos(this.yRot / 180.0F * 3.1415927F) * MathHelper.cos(this.xRot / 180.0F * 3.1415927F) * f;
		this.yd = -MathHelper.sin(this.xRot / 180.0F * 3.1415927F) * f;
		this.func_4042_a(this.xd, this.yd, this.zd, 1.5F, 1.0F);
		this.player.bobberEntity = (EntityBobber) (Object) this; //TODO idk what to do here reddit told me to use (EntityBobber) (Object) to trick the compiler
	}

	public boolean shouldRenderAtSqrDistance(double distance) {
		double d1 = this.bb.getAverageEdgeLength() * 4.0;
		d1 *= 64.0;
		return distance < d1 * d1;
	}

	@Unique
	public void func_4042_a(double d, double d1, double d2, float f, float f1) {
		float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
		d /= f2;
		d1 /= f2;
		d2 /= f2;
		d += this.random.nextGaussian() * 0.0075 * (double)f1;
		d1 += this.random.nextGaussian() * 0.0075 * (double)f1;
		d2 += this.random.nextGaussian() * 0.0075 * (double)f1;
		d *= f;
		d1 *= f;
		d2 *= f;
		this.xd = d;
		this.yd = d1;
		this.zd = d2;
		float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
		this.yRotO = this.yRot = (float)(Math.atan2(d, d2) * 180.0 / Math.PI);
		this.xRotO = this.xRot = (float)(Math.atan2(d1, f3) * 180.0 / Math.PI);
	}

	public void lerpTo(double x, double y, double z, float yRot, float xRot, int i) {
		this.field_6387_m = x;
		this.field_6386_n = y;
		this.field_6385_o = z;
		this.field_6384_p = yRot;
		this.field_6383_q = xRot;
		this.field_6388_l = i;
		this.xd = this.velocityX;
		this.yd = this.velocityY;
		this.zd = this.velocityZ;
	}

	public void lerpMotion(double xd, double yd, double zd) {
		this.velocityX = this.xd = xd;
		this.velocityY = this.yd = yd;
		this.velocityZ = this.zd = zd;
	}

	@Inject(method = "<init>(Lnet/minecraft/core/world/World;Lnet/minecraft/core/entity/player/EntityPlayer;)V", at = @At("TAIL"))
	public void init(CallbackInfo ci){
		entityData.define(3, 0); //hasBait
	}

	@Override
	public boolean stardew_farming_bta$hasBait() {
		return entityData.getInt(3) == 1;
	}

	@Override
	public void stardew_farming_bta$setBait(boolean bool) {
		if (bool){
			entityData.set(3, 1);
		}else {
			entityData.set(3, 0);
		}
	}

	/**
	 * @author DundiGundi
	 * @reason modifying if statement that removes hookedEntity when not fishingrod held to detect for the tiered fishing rod too
	 * 			and decreasing catchTime based on material
	 * 			and making lava also a possible fishing place
	 */
	@Overwrite
	public void tick() {
		super.tick();
		if (this.field_6388_l > 0) {
			double d = this.x + (this.field_6387_m - this.x) / (double)this.field_6388_l;
			double d1 = this.y + (this.field_6386_n - this.y) / (double)this.field_6388_l;
			double d2 = this.z + (this.field_6385_o - this.z) / (double)this.field_6388_l;

			double d4;
			for(d4 = this.field_6384_p - (double)this.yRot; d4 < -180.0; d4 += 360.0) {
			}

			while(d4 >= 180.0) {
				d4 -= 360.0;
			}

			this.yRot = (float)((double)this.yRot + d4 / (double)this.field_6388_l);
			this.xRot = (float)((double)this.xRot + (this.field_6383_q - (double)this.xRot) / (double)this.field_6388_l);
			--this.field_6388_l;
			this.setPos(d, d1, d2);
			this.setRot(this.yRot, this.xRot);
		} else {
			if (!this.world.isClientSide) {
				ItemStack heldPlayerItem = this.player.getCurrentEquippedItem();
				if (this.player.removed || !this.player.isAlive() || heldPlayerItem == null || (heldPlayerItem.getItem() != Item.toolFishingrod && heldPlayerItem.getItem().getClass() != ItemToolFishingRodTiered.class) || this.distanceToSqr(this.player) > 1024.0) {
					this.remove();
					this.player.bobberEntity = null;
					return;
				}

				if (this.hookedEntity != null) {
					if (!this.hookedEntity.removed) {
						this.x = this.hookedEntity.x;
						this.y = this.hookedEntity.bb.minY + (double) this.hookedEntity.bbHeight * 0.8;
						this.z = this.hookedEntity.z;
						return;
					}

					this.hookedEntity = null;
				}
			}
			if (this.isInGround()) {
				if (this.world.getBlockId(this.xTile, this.yTile, this.zTile) == Block.rope.id) {
					this.x = (double) this.xTile + 0.5;
					this.y = (double) this.yTile + 0.5;
					this.z = (double) this.zTile + 0.5;
					return;
				}

				this.setInGround(false);
				this.xd *= this.random.nextFloat() * 0.2F;
				this.yd *= this.random.nextFloat() * 0.2F;
				this.zd *= this.random.nextFloat() * 0.2F;
				this.ticksInAir = 0;
				this.ticksCatchable = 0;
			}

			++this.ticksInAir;

			Vec3d currentPos = Vec3d.createVector(this.x, this.y, this.z);
			Vec3d nextPos = Vec3d.createVector(this.x + this.xd, this.y + this.yd, this.z + this.zd);
			HitResult hitResult = this.world.checkBlockCollisionBetweenPoints(currentPos, nextPos);
			currentPos = Vec3d.createVector(this.x, this.y, this.z);
			nextPos = Vec3d.createVector(this.x + this.xd, this.y + this.yd, this.z + this.zd);
			if (hitResult != null) {
				nextPos = Vec3d.createVector(hitResult.location.xCoord, hitResult.location.yCoord, hitResult.location.zCoord);
				if (hitResult.hitType == HitResult.HitType.TILE && this.world.getBlockId(hitResult.x, hitResult.y, hitResult.z) == Block.rope.id) {
					this.setInGround(true);
					this.xTile = hitResult.x;
					this.yTile = hitResult.y;
					this.zTile = hitResult.z;
				}
			}

			Entity entity = null;
			List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.bb.addCoord(this.xd, this.yd, this.zd).expand(1.0, 1.0, 1.0));
			double d3 = 0.0;
			Iterator<Entity> var8 = list.iterator();

			while(true) {
				Entity e;
				double d7;
				do {
					HitResult newHitResult;
					do {
						do {
							do {
								if (!var8.hasNext()) {
									if (entity != null) {
										hitResult = new HitResult(entity);
									}

									if (hitResult != null && hitResult.entity != null && hitResult.entity.hurt(this.player, 0, DamageType.COMBAT)) {
										this.hookedEntity = hitResult.entity;
									}

									this.move(this.xd, this.yd, this.zd);
									float f = MathHelper.sqrt_double(this.xd * this.xd + this.zd * this.zd);
									this.yRot = (float)(Math.atan2(this.xd, this.zd) * 180.0 / Math.PI);

									for(this.xRot = (float)(Math.atan2(this.yd, f) * 180.0 / Math.PI); this.xRot - this.xRotO < -180.0F; this.xRotO -= 360.0F) {
									}

									while(this.xRot - this.xRotO >= 180.0F) {
										this.xRotO += 360.0F;
									}

									while(this.yRot - this.yRotO < -180.0F) {
										this.yRotO -= 360.0F;
									}

									while(this.yRot - this.yRotO >= 180.0F) {
										this.yRotO += 360.0F;
									}

									this.xRot = this.xRotO + (this.xRot - this.xRotO) * 0.2F;
									this.yRot = this.yRotO + (this.yRot - this.yRotO) * 0.2F;
									float movementScale = 0.92F;
									if (this.onGround || this.horizontalCollision) {
										movementScale = 0.5F;
									}

									int k = 5;
									double d5 = 0.0;

									int catchRate;
									for(catchRate = 0; catchRate < k; ++catchRate) {
										double d8 = this.bb.minY + (this.bb.maxY - this.bb.minY) * (double)catchRate / (double)k - 0.125 + 0.125;
										double d9 = this.bb.minY + (this.bb.maxY - this.bb.minY) * (double)(catchRate + 1) / (double)k - 0.125 + 0.125;
										AABB axisalignedbb1 = AABB.getBoundingBoxFromPool(this.bb.minX, d8, this.bb.minZ, this.bb.maxX, d9, this.bb.maxZ);
										isInLava = this.world.isAABBInMaterial(axisalignedbb1, Material.lava);
										if (this.world.isAABBInMaterial(axisalignedbb1, Material.water) || this.world.isAABBInMaterial(axisalignedbb1, Material.lava)) {
											d5 += 1.0 / (double)k;
										}
									}

									if (d5 > 0.0) {
										if (this.ticksCatchable > 0) {
											--this.ticksCatchable;
										} else {
											catchRate = 500;
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
											if (player.getCurrentEquippedItem().itemID == StardewItems.toolFishingrodDiamond.id){
												materialRate = 200;
											} else if (player.getCurrentEquippedItem().itemID == StardewItems.toolFishingrodIron.id || player.getCurrentEquippedItem().itemID == StardewItems.toolFishingrodSteel.id) {
												materialRate = 100;
											}
											if (((IEntityBobberMixin)player.bobberEntity).stardew_farming_bta$hasBait()){
												baitRate = 50;
											}

											catchRate = catchRate - rainRate - algaeRate - materialRate - baitRate;
											if (this.random.nextInt(catchRate) == 0) {
												this.ticksCatchable = this.random.nextInt(30) + 10;
												this.yd -= 0.2;
												this.world.playSoundAtEntity(null, this, "random.splash", 0.25F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
												float f3 = (float)MathHelper.floor_double(this.bb.minY);

												int j1;
												double zOff;
												double xOff;
												for(j1 = 0; (float)j1 < 1.0F + this.bbWidth * 20.0F; ++j1) {
													xOff = (this.random.nextFloat() * 2.0F - 1.0F) * this.bbWidth;
													zOff = (this.random.nextFloat() * 2.0F - 1.0F) * this.bbWidth;
													this.world.spawnParticle("bubble", this.x + xOff, f3 + 1.0F, this.z + zOff, this.xd, this.yd - (double)(this.random.nextFloat() * 0.2F), this.zd, 0);
												}

												for(j1 = 0; (float)j1 < 1.0F + this.bbWidth * 20.0F; ++j1) {
													xOff = (this.random.nextFloat() * 2.0F - 1.0F) * this.bbWidth;
													zOff = (this.random.nextFloat() * 2.0F - 1.0F) * this.bbWidth;
													this.world.spawnParticle("splash", this.x + xOff, f3 + 1.0F, this.z + zOff, this.xd, this.yd, this.zd, 0);
												}
											}
										}
									}

									if (this.ticksCatchable > 0) {
										this.yd -= (double)(this.random.nextFloat() * this.random.nextFloat() * this.random.nextFloat()) * 0.2;
									}

									d7 = d5 * 2.0 - 1.0;
									this.yd += 0.04 * d7;
									if (d5 > 0.0) {
										movementScale = (float)((double)movementScale * 0.9);
										this.yd *= 0.8;
									}

									this.xd *= movementScale;
									this.yd *= movementScale;
									this.zd *= movementScale;
									this.setPos(this.x, this.y, this.z);
									return;
								}

								e = (Entity)var8.next();
							} while(!e.isPickable());
						} while(e == this.player && this.ticksInAir < 5);

						float f2 = 0.3F;
						AABB aabb = e.bb.expand(f2, f2, f2);
						newHitResult = aabb.func_1169_a(currentPos, nextPos);
					} while(newHitResult == null);

					d7 = currentPos.distanceTo(newHitResult.location);
				} while(!(d7 < d3) && d3 != 0.0);

				entity = e;
				d3 = d7;
			}
		}
	}

	/**
	 * @author DundiGundi
	 * @reason modifying catchable fishes based on type of fishing rod, position of hookedEntity, season or weather
	 */

	@Overwrite
	public int yoink() {
		int damage = 0;
		double dx;
		double dy;
		double dz;
		double distance;
		double scale;

		if (this.isInGround()) {
			dx = this.x - this.player.x;
			dy = this.y - this.player.y;
			dz = this.z - this.player.z;
			distance = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
			dx /= distance;
			dy /= distance;
			dz /= distance;
			scale = 0.6;
			dx = MathHelper.clamp(dx, -scale, scale);
			dy = MathHelper.clamp(dy, -scale, scale);
			dz = MathHelper.clamp(dz, -scale, scale);
			scale = 2.0;
			EntityPlayer var10000 = this.player;
			var10000.xd += dx * scale;
            var10000.yd += dy * scale;
            var10000.zd += dz * scale;
			damage = 5;
		}

		if (this.hookedEntity != null) {
			dx = this.player.x - this.x;
			dy = this.player.y - this.y;
			dz = this.player.z - this.z;
			distance = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
			scale = 0.1;
			Entity var15 = this.hookedEntity;
			var15.xd += dx * scale;
            var15.yd += dy * scale + (double)MathHelper.sqrt_double(distance) * 0.08;
			var15 = this.hookedEntity;
			var15.zd += dz * scale;
			damage = 3;
		} else if (this.ticksCatchable > 0) {
			if (isInLava){
				EntityItemFireResistant entityitem = new EntityItemFireResistant(this.world, this.x, this.y, this.z, new ItemStack(getCatchableFish()));
				dx = this.player.x - this.x;
				dy = this.player.y - this.y;
				dz = this.player.z - this.z;
				distance = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
				scale = 0.1;
				entityitem.xd = dx * scale;
				entityitem.yd = dy * scale + (double)MathHelper.sqrt_double(distance) * 0.08;
				entityitem.zd = dz * scale;
				this.world.entityJoinedWorld(entityitem);
			}else {
				EntityItem entityitem = new EntityItem(this.world, this.x, this.y, this.z, new ItemStack(getCatchableFish()));
				dx = this.player.x - this.x;
				dy = this.player.y - this.y;
				dz = this.player.z - this.z;
				distance = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
				scale = 0.1;
				entityitem.xd = dx * scale;
				entityitem.yd = dy * scale + (double)MathHelper.sqrt_double(distance) * 0.08;
				entityitem.zd = dz * scale;
				this.world.entityJoinedWorld(entityitem);
			}
			this.player.addStat(StatList.fishCaughtStat, 1);
			damage = 1;

		}
		if (this.isInGround()) {
			damage = 2;
		}
		this.remove();
		this.player.bobberEntity = null;
		return damage;
	}

	@Unique
	private Item getCatchableFish() {
		Season season = world.seasonManager.getCurrentSeason();
		Weather weather = world.weatherManager.getCurrentWeather();
		Item[] treasuresLowValue = {Item.olivine, Item.quartz, Item.dustRedstone, Item.coal};
		Item[] treasuresMiddleValue = {Item.oreRawIron, Item.oreRawGold};
		Item[] treasuresHighValue = {Item.diamond, Item.ingotSteelCrude};

		if (isInLava) {
			return StardewItems.fishEelLava;
		} else if (player.getCurrentEquippedItem().itemID != StardewItems.toolFishingrodGold.id) {
			if (weather == Weather.overworldStorm && world.rand.nextInt(15) == 0) {
					return StardewItems.fishSword;

			} else if (weather == Weather.overworldFog && !world.isDaytime() && world.rand.nextInt(15) == 0){
				return StardewItems.fishGhost;

			} else if (!world.canBlockSeeTheSky(MathHelper.floor_double(this.x), MathHelper.floor_double(this.y) + 1, MathHelper.floor_double(this.z)) && MathHelper.floor_double(this.y) + 1 <= 32 && world.rand.nextInt(15) == 0) {
				return StardewItems.fishStone;

			} else if (season == Seasons.OVERWORLD_SPRING) {
				return StardewItems.foodBassRaw;

			} else if (season == Seasons.OVERWORLD_SUMMER) {
				return StardewItems.foodSnapperRaw;

			} else if (season == Seasons.OVERWORLD_FALL) {
				return Item.foodFishRaw;

			} else if (season == Seasons.OVERWORLD_WINTER) {
				return StardewItems.foodSalmonRaw;
			}
		} else {
			if (world.rand.nextInt(2) == 0){
				if (world.rand.nextInt(2) == 0){
					return treasuresMiddleValue[world.rand.nextInt(treasuresMiddleValue.length)];
				} else {
					return treasuresHighValue[world.rand.nextInt(treasuresHighValue.length)];
				}
			}else {
				return treasuresLowValue[random.nextInt(treasuresLowValue.length)];
			}
		}
        return Item.bone;
    }

	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {

	}

	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {

	}
}
