package luke.stardew.entities.duck;

import luke.stardew.StardewMod;
import luke.stardew.items.StardewItems;
import net.minecraft.client.sound.SoundRepository;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;

public class EntityDuck extends MobAnimal {
	public float flap = 0.0F;
	public float flapSpeed = 0.0F;
	public float oFlapSpeed;
	public float oFlap;
	public float flapping = 1.0F;
	public int eggTimer;
	public EntityDuck(World world) {
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
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.oFlap = this.flap;
		this.oFlapSpeed = this.flapSpeed;
		this.flapSpeed = (float)((double)this.flapSpeed + (double)(this.onGround ? -1 : 4) * 0.3);
		if (this.flapSpeed < 0.0F) {
			this.flapSpeed = 0.0F;
		}

		if (this.flapSpeed > 1.0F) {
			this.flapSpeed = 1.0F;
		}

		if (!this.onGround && this.flapping < 1.0F) {
			this.flapping = 1.0F;
		}

		this.flapping = (float)((double)this.flapping * 0.9);
		if (!this.onGround && this.yd < 0.0) {
			this.yd *= 0.6;
		}

		this.flap += this.flapping * 2.0F;
		if (this.world != null && !this.world.isClientSide && --this.eggTimer <= 0) {
			this.world.playSoundAtEntity(null, this, "mob.chickenplop", 1.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f - 1.0f);
			this.dropItem(StardewItems.eggDuck.id, 1);
			this.eggTimer = this.random.nextInt(3000) + 3000;
		}
	}

	@Override
	public int getAmbientSoundInterval() {
		return 60;
	}

	@Override
	protected float getSoundVolume() {
		return 0.5f;
	}

	@Override
	public String getLivingSound() {
		return StardewMod.MOD_ID + ":mob.duck.idle";
	}

	@Override
	protected String getHurtSound() {
		return StardewMod.MOD_ID + ":mob.duck.hurt";
	}

	@Override
	protected String getDeathSound() {
		return StardewMod.MOD_ID + ":mob.duck.death";
	}

}
