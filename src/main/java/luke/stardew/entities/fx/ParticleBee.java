package luke.stardew.entities.fx;

import net.minecraft.client.entity.particle.Particle;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.world.World;

import static luke.stardew.StardewMod.MOD_ID;

public class ParticleBee extends Particle {
	private static final IconCoordinate bee1 = TextureRegistry.getTexture(MOD_ID + ":particle/bee");
	private static final IconCoordinate bee2 = TextureRegistry.getTexture(MOD_ID + ":particle/bee_2");
	public float originalScale;

	public ParticleBee(World world, double d, double d1, double d2, double d3, double d4, double d5) {
		super(world, d, d1, d2, d3, d4, d5);
		this.tex = TextureRegistry.getTexture(MOD_ID + ":particle/bee");
		this.rCol = 1;
		this.gCol = 1;
		this.bCol = 1;
		this.size *= 1.2f;
		this.originalScale = this.size;
		this.lifetime = random.nextInt(20) + 20;
		this.age = 1;
	}

	@Override
	public void render(Tessellator tessellator, float partialTick, double x, double y, double z, float rotationX, float rotationXZ, float rotationZ, float rotationYZ, float rotationXY) {
		/*float f6 = (this.age + partialTick) / (float)this.lifetime * 32.0f;
		if (f6 < 0.0F)
			f6 = 0.0F;
		if (f6 > 1.0F)
			f6 = 1.0F;*/

		this.size = this.originalScale - this.originalScale * ((float) this.age / this.lifetime);
		super.render(tessellator, partialTick, x, y, z, rotationX, rotationXZ, rotationZ, rotationYZ, rotationXY);
	}

	public void tick() {
		this.tex = this.age % 2 == 0 ? bee1: bee2;

		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ >= this.lifetime) {
			this.remove();
		}

		this.move(this.xd, this.yd, this.zd);
		this.xd *= 0.96;
		this.yd *= 0.96;
		this.zd *= 0.96;
		if (this.onGround) {
			this.xd *= 0.7;
			this.zd *= 0.7;
		}
	}
}
