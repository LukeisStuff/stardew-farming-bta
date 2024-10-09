package luke.stardew.entities.duck;

import luke.stardew.items.StardewItems;
import net.minecraft.core.HitResult;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.projectile.EntityProjectile;
import net.minecraft.core.world.World;

public class EntityEggDuck
	extends EntityProjectile {
	public EntityEggDuck(World world) {
		super(world);
		this.modelItem = StardewItems.eggDuck;
	}

	public EntityEggDuck(World world, EntityLiving entityliving) {
		super(world, entityliving);
		this.modelItem = StardewItems.eggDuck;
	}

	public EntityEggDuck(World world, double d, double d1, double d2) {
		super(world, d, d1, d2);
		this.modelItem = StardewItems.eggDuck;
	}

	@Override
	public void onHit(HitResult hitResult) {
		if (!this.world.isClientSide && this.random.nextInt(8) == 0) {
			int byte0 = 1;
			if (this.random.nextInt(32) == 0) {
				byte0 = 2;
			}
			for (int k = 0; k < byte0; ++k) {
				EntityDuck entityduck = new EntityDuck(this.world);
				entityduck.moveTo(this.x, this.y, this.z, this.yRot, 0.0f);
				this.world.entityJoinedWorld(entityduck);
			}
		}
		super.onHit(hitResult);
	}
}
