package luke.stardew.mixin;

import com.mojang.nbt.tags.CompoundTag;
import luke.stardew.entities.EntityItemFireResistant;
import luke.stardew.interfaces.IEntityBobberMixin;
import luke.stardew.items.ItemToolFishingRodTiered;
import luke.stardew.items.StardewItems;
import net.minecraft.core.achievement.stat.StatList;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityFishingBobber;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.util.phys.HitResult;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Season;
import net.minecraft.core.world.season.Seasons;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.Weathers;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;

@Mixin(value = EntityFishingBobber.class, remap = false)
public abstract class EntityBobberMixin extends Entity implements IEntityBobberMixin {
	@Shadow
	public Player owner;
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
	private int lerpSteps;
	@Shadow
	private double lerpX;
	@Shadow
	private double lerpY;
	@Shadow
	private double lerpZ;
	@Shadow
	private double lerpYRot;
	@Shadow
	private double lerpXRot;
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

	public EntityBobberMixin(World world, Player player) {
		super(world);
		this.ticksInAir = 0;
		this.ticksCatchable = 0;
		this.hookedEntity = null;
		this.ignoreFrustumCheck = true;
		this.owner = player;
		this.setSize(0.25F, 0.25F);
		this.moveTo(player.x, player.y + 1.62 - (double) player.heightOffset, player.z, player.yRot, player.xRot);
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
		this.owner.bobberEntity = (EntityFishingBobber) (Object) this; //TODO idk what to do here reddit told me to use (EntityBobber) (Object) to trick the compiler
	}

	public boolean shouldRenderAtSqrDistance(double distance) {
		double d1 = this.bb.getSize() * 4.0;
		d1 *= 64.0;
		return distance < d1 * d1;
	}

	@Unique
	public void func_4042_a(double d, double d1, double d2, float f, float f1) {
		float f2 = MathHelper.sqrt(d * d + d1 * d1 + d2 * d2);
		d /= f2;
		d1 /= f2;
		d2 /= f2;
		d += this.random.nextGaussian() * 0.0075 * (double) f1;
		d1 += this.random.nextGaussian() * 0.0075 * (double) f1;
		d2 += this.random.nextGaussian() * 0.0075 * (double) f1;
		d *= f;
		d1 *= f;
		d2 *= f;
		this.xd = d;
		this.yd = d1;
		this.zd = d2;
		float f3 = MathHelper.sqrt(d * d + d2 * d2);
		this.yRotO = this.yRot = (float) (Math.atan2(d, d2) * 180.0 / Math.PI);
		this.xRotO = this.xRot = (float) (Math.atan2(d1, f3) * 180.0 / Math.PI);
	}

	public void lerpTo(double x, double y, double z, float yRot, float xRot, int i) {
		this.lerpX = x;
		this.lerpY = y;
		this.lerpZ = z;
		this.lerpYRot = yRot;
		this.lerpXRot = xRot;
		this.lerpSteps = i;
		this.xd = this.velocityX;
		this.yd = this.velocityY;
		this.zd = this.velocityZ;
	}

	public void lerpMotion(double xd, double yd, double zd) {
		this.velocityX = this.xd = xd;
		this.velocityY = this.yd = yd;
		this.velocityZ = this.zd = zd;
	}

	@Inject(method = "<init>(Lnet/minecraft/core/world/World;Lnet/minecraft/core/entity/player/Player;)V", at = @At("TAIL"))
	public void init(CallbackInfo ci) {
		entityData.define(3, 0, Integer.class);
	}

	@Unique
	public void checkBait() {
		try {
			entityData.getInt(3);
		} catch (Exception e) {
			entityData.define(3, 0, Integer.class);
		}
	}

	@Override
	public boolean stardew_farming_bta$hasBait() {
		checkBait();
		return entityData.getInt(3) == 1;
	}

	@Override
	public void stardew_farming_bta$setBait(boolean bool) {
		checkBait();
		entityData.set(3, bool ? 1 : 0);
	}

	/**
	 * @author DundiGundi
	 * @reason modifying if statement that removes hookedEntity when not fishingrod held to detect for the tiered fishing rod too
	 * and decreasing catchTime based on material
	 * and making lava also a possible fishing place
	 */
	@Overwrite
	public void tick() {
		super.tick();
		if (this.lerpSteps > 0) {
			double d = this.x + (this.lerpX - this.x) / (double) this.lerpSteps;
			double d1 = this.y + (this.lerpY - this.y) / (double) this.lerpSteps;
			double d2 = this.z + (this.lerpZ - this.z) / (double) this.lerpSteps;

			double d4;
			for (d4 = this.lerpYRot - (double) this.yRot; d4 < -180.0; d4 += 360.0) {
			}

			while (d4 >= 180.0) {
				d4 -= 360.0;
			}

			this.yRot = (float) ((double) this.yRot + d4 / (double) this.lerpSteps);
			this.xRot = (float) ((double) this.xRot + (this.lerpXRot - (double) this.xRot) / (double) this.lerpSteps);
			--this.lerpSteps;
			this.setPos(d, d1, d2);
			this.setRot(this.yRot, this.xRot);
		} else {
			assert this.world != null;
			if (!this.world.isClientSide) {
				ItemStack heldPlayerItem = this.owner.getCurrentEquippedItem();
				if (this.owner.removed || !this.owner.isAlive() || heldPlayerItem == null || (!heldPlayerItem.getItem().equals(Items.TOOL_FISHINGROD) && heldPlayerItem.getItem().getClass() != ItemToolFishingRodTiered.class) || this.distanceToSqr(this.owner) > 1024.0) {
					this.remove();
					this.owner.bobberEntity = null;
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
				if (this.world.getBlockId(this.xTile, this.yTile, this.zTile) == Blocks.ROPE.id()) {
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

			Vec3 currentPos = Vec3.getTempVec3(this.x, this.y, this.z);
			Vec3 nextPos = Vec3.getTempVec3(this.x + this.xd, this.y + this.yd, this.z + this.zd);
			HitResult hitResult = this.world.checkBlockCollisionBetweenPoints(currentPos, nextPos);
			currentPos = Vec3.getTempVec3(this.x, this.y, this.z);
			nextPos = Vec3.getTempVec3(this.x + this.xd, this.y + this.yd, this.z + this.zd);
			if (hitResult != null) {
				nextPos = Vec3.getTempVec3(hitResult.location.x, hitResult.location.y, hitResult.location.z);
				if (hitResult.hitType == HitResult.HitType.TILE && this.world.getBlockId(hitResult.x, hitResult.y, hitResult.z) == Blocks.ROPE.id()) {
					this.setInGround(true);
					this.xTile = hitResult.x;
					this.yTile = hitResult.y;
					this.zTile = hitResult.z;
				}
			}

			Entity entity = null; //FIXME: .grow() used to be .add(), not sure if .grow() is the right method
			List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.bb.grow(this.xd, this.yd, this.zd).expand(1.0, 1.0, 1.0));
			double d3 = 0.0;
			Iterator<Entity> var8 = list.iterator();

			while (true) {
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

									if (hitResult != null && hitResult.entity != null && hitResult.entity.hurt(this.owner, 0, DamageType.COMBAT)) {
										this.hookedEntity = hitResult.entity;
									}

									this.move(this.xd, this.yd, this.zd);
									float f = MathHelper.sqrt(this.xd * this.xd + this.zd * this.zd);
									this.yRot = (float) (Math.atan2(this.xd, this.zd) * 180.0 / Math.PI);

									for (this.xRot = (float) (Math.atan2(this.yd, f) * 180.0 / Math.PI); this.xRot - this.xRotO < -180.0F; this.xRotO -= 360.0F) {
									}

									while (this.xRot - this.xRotO >= 180.0F) {
										this.xRotO += 360.0F;
									}

									while (this.yRot - this.yRotO < -180.0F) {
										this.yRotO -= 360.0F;
									}

									while (this.yRot - this.yRotO >= 180.0F) {
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
									for (catchRate = 0; catchRate < k; ++catchRate) {
										double d8 = this.bb.minY + (this.bb.maxY - this.bb.minY) * (double) catchRate / (double) k - 0.125 + 0.125;
										double d9 = this.bb.minY + (this.bb.maxY - this.bb.minY) * (double) (catchRate + 1) / (double) k - 0.125 + 0.125;
										AABB axisalignedbb1 = AABB.getTemporaryBB(this.bb.minX, d8, this.bb.minZ, this.bb.maxX, d9, this.bb.maxZ);
										isInLava = this.world.isAABBInMaterial(axisalignedbb1, Material.lava);
										if (this.world.isAABBInMaterial(axisalignedbb1, Material.water) || this.world.isAABBInMaterial(axisalignedbb1, Material.lava)) {
											d5 += 1.0 / (double) k;
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
											if (this.world.canBlockBeRainedOn(MathHelper.floor(this.x), MathHelper.floor(this.y) + 1, MathHelper.floor(this.z))) {
												rainRate = 100;
											}
											if (this.world.getBlockId(MathHelper.floor(this.x), MathHelper.floor(this.y) + 1, MathHelper.floor(this.z)) == Blocks.ALGAE.id()) {
												algaeRate = 50;
											}
											if (owner.getCurrentEquippedItem().itemID == StardewItems.TOOL_FISHINGROD_DIAMOND.id) {
												materialRate = 200;
											} else if (owner.getCurrentEquippedItem().itemID == StardewItems.TOOL_FISHINGROD_IRON.id || owner.getCurrentEquippedItem().itemID == StardewItems.TOOL_FISHINGROD_STEEL.id) {
												materialRate = 100;
											}
											assert owner.bobberEntity != null;
											if (((IEntityBobberMixin) owner.bobberEntity).stardew_farming_bta$hasBait()) {
												baitRate = 50;
											}

											catchRate = catchRate - rainRate - algaeRate - materialRate - baitRate;
											if (this.random.nextInt(catchRate) == 0) {
												this.ticksCatchable = this.random.nextInt(30) + 10;
												this.yd -= 0.2;
												this.world.playSoundAtEntity(null, this, "random.splash", 0.25F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
												float f3 = (float) MathHelper.floor(this.bb.minY);

												int j1;
												double zOff;
												double xOff;
												for (j1 = 0; (float) j1 < 1.0F + this.bbWidth * 20.0F; ++j1) {
													xOff = (this.random.nextFloat() * 2.0F - 1.0F) * this.bbWidth;
													zOff = (this.random.nextFloat() * 2.0F - 1.0F) * this.bbWidth;
													this.world.spawnParticle("bubble", this.x + xOff, f3 + 1.0F, this.z + zOff, this.xd, this.yd - (double) (this.random.nextFloat() * 0.2F), this.zd, 0);
												}

												for (j1 = 0; (float) j1 < 1.0F + this.bbWidth * 20.0F; ++j1) {
													xOff = (this.random.nextFloat() * 2.0F - 1.0F) * this.bbWidth;
													zOff = (this.random.nextFloat() * 2.0F - 1.0F) * this.bbWidth;
													this.world.spawnParticle("splash", this.x + xOff, f3 + 1.0F, this.z + zOff, this.xd, this.yd, this.zd, 0);
												}
											}
										}
									}

									if (this.ticksCatchable > 0) {
										this.yd -= (double) (this.random.nextFloat() * this.random.nextFloat() * this.random.nextFloat()) * 0.2;
									}

									d7 = d5 * 2.0 - 1.0;
									this.yd += 0.04 * d7;
									if (d5 > 0.0) {
										movementScale = (float) ((double) movementScale * 0.9);
										this.yd *= 0.8;
									}

									this.xd *= movementScale;
									this.yd *= movementScale;
									this.zd *= movementScale;
									this.setPos(this.x, this.y, this.z);
									return;
								}

								e = var8.next();
							} while (!e.isPickable());
						} while (e == this.owner && this.ticksInAir < 5);

						float f2 = 0.3F;
						AABB aabb = e.bb.expand(f2, f2, f2);
						newHitResult = aabb.clip(currentPos, nextPos);
					} while (newHitResult == null);

					d7 = currentPos.distanceTo(newHitResult.location);
				} while (!(d7 < d3) && d3 != 0.0);

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
			dx = this.x - this.owner.x;
			dy = this.y - this.owner.y;
			dz = this.z - this.owner.z;
			distance = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
			dx /= distance;
			dy /= distance;
			dz /= distance;
			scale = 0.6;
			dx = MathHelper.clamp(dx, -scale, scale);
			dy = MathHelper.clamp(dy, -scale, scale);
			dz = MathHelper.clamp(dz, -scale, scale);
			scale = 2.0;
			Player var10000 = this.owner;
			var10000.xd += dx * scale;
			var10000.yd += dy * scale;
			var10000.zd += dz * scale;
			damage = 5;
		}

		if (this.hookedEntity != null) {
			dx = this.owner.x - this.x;
			dy = this.owner.y - this.y;
			dz = this.owner.z - this.z;
			distance = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
			scale = 0.1;
			Entity var15 = this.hookedEntity;
			var15.xd += dx * scale;
			var15.yd += dy * scale + (double) MathHelper.sqrt(distance) * 0.08;
			var15 = this.hookedEntity;
			var15.zd += dz * scale;
			damage = 3;
		} else if (this.ticksCatchable > 0) {
			if (isInLava) {
				EntityItemFireResistant entityitem = new EntityItemFireResistant(this.world, this.x, this.y, this.z, new ItemStack(getCatchableFish()));
				dx = this.owner.x - this.x;
				dy = this.owner.y - this.y;
				dz = this.owner.z - this.z;
				distance = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
				scale = 0.1;
				entityitem.xd = dx * scale;
				entityitem.yd = dy * scale + (double) MathHelper.sqrt(distance) * 0.08;
				entityitem.zd = dz * scale;
				assert this.world != null;
				this.world.entityJoinedWorld(entityitem);
			} else {
				EntityItem entityitem = new EntityItem(this.world, this.x, this.y, this.z, new ItemStack(getCatchableFish()));
				dx = this.owner.x - this.x;
				dy = this.owner.y - this.y;
				dz = this.owner.z - this.z;
				distance = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
				scale = 0.1;
				entityitem.xd = dx * scale;
				entityitem.yd = dy * scale + (double) MathHelper.sqrt(distance) * 0.08;
				entityitem.zd = dz * scale;
				assert this.world != null;
				this.world.entityJoinedWorld(entityitem);
			}
			this.owner.addStat(StatList.fishCaughtStat, 1);
			damage = 1;

		}
		if (this.isInGround()) {
			damage = 2;
		}
		this.remove();
		this.owner.bobberEntity = null;
		return damage;
	}

	@Unique
	public Item getCatchableFish() {
		assert world != null;
		Season season = world.seasonManager.getCurrentSeason();
		Weather weather = world.weatherManager.getCurrentWeather();
		Item[] treasuresLowValue = {Items.OLIVINE, Items.QUARTZ, Items.DUST_REDSTONE, Items.COAL};
		Item[] treasuresMiddleValue = {Items.ORE_RAW_IRON, Items.ORE_RAW_GOLD};
		Item[] treasuresHighValue = {Items.DIAMOND, Items.INGOT_STEEL_CRUDE};

		if (isInLava) {
			return StardewItems.FISH_EEL_LAVA;
		} else if (this.owner.getCurrentEquippedItem().itemID != StardewItems.TOOL_FISHINGROD_GOLD.id) {
			if (weather == Weathers.OVERWORLD_STORM && world.rand.nextInt(15) == 0) {
				return StardewItems.FISH_SWORD;

			} else if (weather == Weathers.OVERWORLD_FOG && !world.isDaytime() && world.rand.nextInt(15) == 0) {
				return StardewItems.FISH_GHOST;

			} else if (!world.canBlockSeeTheSky(MathHelper.floor(this.x), MathHelper.floor(this.y) + 1, MathHelper.floor(this.z)) && MathHelper.floor(this.y) + 1 <= 32 && world.rand.nextInt(15) == 0) {
				return StardewItems.FISH_STONE;

			} else if (season == Seasons.OVERWORLD_SPRING) {
				return StardewItems.FOOD_BASS_RAW;

			} else if (season == Seasons.OVERWORLD_SUMMER) {
				return StardewItems.FOOD_SNAPPER_RAW;

			} else if (season == Seasons.OVERWORLD_FALL) {
				return Items.FOOD_FISH_RAW;

			} else if (season == Seasons.OVERWORLD_WINTER) {
				return StardewItems.FOOD_SALMON_RAW;
			}
		} else {
			if (world.rand.nextInt(2) == 0) {
				if (world.rand.nextInt(2) == 0) {
					return treasuresMiddleValue[world.rand.nextInt(treasuresMiddleValue.length)];
				} else {
					return treasuresHighValue[world.rand.nextInt(treasuresHighValue.length)];
				}
			} else {
				return treasuresLowValue[random.nextInt(treasuresLowValue.length)];
			}
		}
		return Items.BONE;
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {

	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {

	}
}
