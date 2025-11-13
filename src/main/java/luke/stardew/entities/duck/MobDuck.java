package luke.stardew.entities.duck;

import luke.stardew.StardewMod;
import luke.stardew.items.StardewItems;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.BlockLogicFluid;
import net.minecraft.core.entity.animal.Creature;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.item.tag.ItemTags;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;

public class MobDuck extends MobAnimal implements Creature {
	public float flap = 0.0F;
	public float flapSpeed = 0.0F;
	public float oFlapSpeed;
	public float oFlap;
	public float flapping = 1.0F;
	public int eggTimer;

	public MobDuck(World world) {
		super(world);
		this.textureIdentifier = NamespaceID.getPermanent("stardew", "duck");
		this.setSize(0.6f, 1.0f);
		this.eggTimer = this.random.nextInt(3000) + 3000;
		this.mobDrops.add(new WeightedRandomLootObject(Items.FEATHER_CHICKEN.getDefaultStack(), 0, 1));
	}

	@Override
	public int getMaxHealth() {
		return 5;
	}

	@Override
	public void updateAI() {
		boolean inWater = this.wasInWater;
		this.wasInWater = false;
		super.updateAI(); //Stop jumping in water
		this.wasInWater = inWater;

		if (this.isInWater()) {
			double yh = this.bb.minY - MathHelper.floor(this.bb.minY);

			int x = MathHelper.floor(this.x);
			int y = MathHelper.floor(this.bb.minY + 0.125);
			int y2 = MathHelper.floor(this.bb.minY + 0.6);
			int z = MathHelper.floor(this.z);
			boolean air = this.world.getBlockLogic(x, y2, z, BlockLogicFluid.class) == null;
			if (this.world != null && this.world.getBlockLogic(x, y, z, BlockLogicFluid.class) != null && air) {
				if (yh < 0.125) {
					this.yd = 0;
				}
			} else {
				this.yd = 0.1;
			}
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.checkOnWater(false);
		if (this.isInWater()) {
			this.oFlap = this.flap;
			this.oFlapSpeed = this.flapSpeed;
			this.flapSpeed = 0;
			this.flapping = 0;
			this.flap = 0;
			this.yd *= 0.1;

		} else {
			this.oFlap = this.flap;
			this.oFlapSpeed = this.flapSpeed;
			this.flapSpeed = (float) (this.flapSpeed + (this.onGround ? -1 : 4) * 0.3);
			if (this.flapSpeed < 0.0F) {
				this.flapSpeed = 0.0F;
			}

			if (this.flapSpeed > 1.0F) {
				this.flapSpeed = 1.0F;
			}

			if (!this.onGround && this.flapping < 1.0F) {
				this.flapping = 1.0F;
			}

			this.flapping = (float) (this.flapping * 0.9);
			if (!this.onGround && this.yd < 0.0) {
				this.yd *= 0.6;
			}

			this.flap += this.flapping * 2.0F;
		}

		if (this.world != null && !this.world.isClientSide && --this.eggTimer <= 0) {
			this.world.playSoundAtEntity(null, this, "mob.chickenplop", 1.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f - 1.0f);
			this.dropItem(StardewItems.EGG_DUCK.id, 1);
			this.eggTimer = this.random.nextInt(3000) + 3000;
		}
	}

	@Override
	public void causeFallDamage(float distance) {
		// No fall damage
	}

	@Override
	public int getAmbientSoundInterval() {
		return 60;
	}

	@Override
	public float getSoundVolume() {
		return 0.5f;
	}

	@Override
	public String getLivingSound() {
		return StardewMod.MOD_ID + ":mob.duck.idle";
	}

	@Override
	public String getHurtSound() {
		return StardewMod.MOD_ID + ":mob.duck.hurt";
	}

	@Override
	public String getDeathSound() {
		return StardewMod.MOD_ID + ":mob.duck.death";
	}

	@Override
	public boolean isFavouriteItem(ItemStack itemStack) {
		return itemStack != null && itemStack.getItem().hasTag(ItemTags.CHICKENS_FAVOURITE_ITEM);
	}

}
