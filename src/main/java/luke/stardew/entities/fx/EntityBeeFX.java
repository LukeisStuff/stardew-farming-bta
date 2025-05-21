package luke.stardew.entities.fx;

import net.minecraft.client.entity.particle.Particle;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.world.World;

import static luke.stardew.StardewMod.MOD_ID;

public class EntityBeeFX extends Particle {
	public float originalScale;

	public EntityBeeFX(World world, double d, double d1, double d2, double d3, double d4, double d5) {
		super(world, d, d1, d2, d3, d4, d5);
		this.tex = TextureRegistry.getTexture(MOD_ID + ":item/bee");
		this.rCol = 1;
		this.gCol = 1;
		this.bCol = 1;
		this.size *= 1.0f;
		this.originalScale = this.size;
	}

	@Override
	public void render(Tessellator tessellator, float partialTick, double x, double y, double z, float rotationX, float rotationXZ, float rotationZ, float rotationYZ, float rotationXY) {
		float f6 = (this.age + partialTick) / (float)this.lifetime * 32.0f;
		if (f6 < 0.0F)
			f6 = 0.0F;
		if (f6 > 1.0F)
			f6 = 1.0F;
		super.render(tessellator, partialTick, x, y, z, rotationX, rotationXZ, rotationZ, rotationYZ, rotationXY);
		this.size = 2.0F - f6 * f6 * 0.5F;

	}

	public void tick() {
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
		this.tex = TextureRegistry.getTexture(MOD_ID + ":item/bee");

	}

	@Override
	public int getParticleTexture() {
		return 2;
	}
}
