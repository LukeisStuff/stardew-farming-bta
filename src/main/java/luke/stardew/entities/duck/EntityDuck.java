package luke.stardew.entities.duck;

import luke.stardew.items.StardewItems;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.animal.EntityChicken;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;

public class EntityDuck extends EntityChicken {
	public EntityDuck(World world) {
		super(world);
		this.textureIdentifier = new NamespaceID("stardew", "duck");
		this.setSize(0.6f, 1.0f);
		this.timeUntilNextEgg = this.random.nextInt(3000) + 3000;
		this.mobDrops.add(new WeightedRandomLootObject(Item.featherChicken.getDefaultStack(), 0, 1));
	}

	@Override
	public int getMaxHealth() {
		return 5;
	}

	@Override
	public int getSkinVariant() {
		int skinVariantCount = 3;
		return this.entityData.getByte(1) % skinVariantCount;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.field_756_e = this.field_752_b;
		this.field_757_d = this.destPos;
		this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3);
		if (this.destPos < 0.0f) {
			this.destPos = 0.0f;
		}
		if (this.destPos > 1.0f) {
			this.destPos = 1.0f;
		}
		if (!this.onGround && this.field_755_h < 1.0f) {
			this.field_755_h = 1.0f;
		}
		this.field_755_h = (float)((double)this.field_755_h * 0.9);
		if (!this.onGround && this.yd < 0.0) {
			this.yd *= 0.6;
		}
		this.field_752_b += this.field_755_h * 2.0f;
		if (!this.world.isClientSide && --this.timeUntilNextEgg <= 0) {
			this.world.playSoundAtEntity(null, this, "mob.chickenplop", 1.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f - 1.0f);
			this.spawnAtLocation(StardewItems.eggDuck.id, 1);
			this.timeUntilNextEgg = this.random.nextInt(3000) + 3000;
		}
	}

	@Override
	public int getTalkInterval() {
		return 60;
	}

	protected float getSoundVolume() {
		return 0.5f;
	}

	@Override
	public String getLivingSound() {
		return "stardew.duckidle";
	}

	@Override
	protected String getHurtSound() {
		return "stardew.duckhurt";
	}

	@Override
	protected String getDeathSound() {
		return "stardew.duckdeath";
	}

}
