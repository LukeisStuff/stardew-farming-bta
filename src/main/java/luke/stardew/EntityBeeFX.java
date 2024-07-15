package luke.stardew;

import net.minecraft.client.entity.fx.EntityFX;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.world.World;

public class EntityBeeFX extends EntityFX {
	public float originalScale;
	private float field_672_a;
	public EntityBeeFX(World world, double d, double d1, double d2, double d3, double d4, double d5) {
		super(world, d, d1, d2, d3, d4, d5);
		this.particleTexture = TextureRegistry.getTexture("stardew:item/bee");
		particleScale *= 1.0f;
		originalScale = particleScale;
	}

	public void renderParticle(Tessellator t, float partialTick, float rotationX, float rotationXZ, float rotationZ, float rotationYZ, float rotationXY) {
		float f6 = ((float)this.particleAge + partialTick) / (float)this.particleMaxAge;
		this.particleScale = this.field_672_a * (1.0F - f6 * f6 * 0.5F);
		super.renderParticle(t, partialTick, rotationX, rotationXZ, rotationZ, rotationYZ, rotationXY);
	}

	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.particleAge++ >= this.particleMaxAge) {
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
		this.particleTexture = TextureRegistry.getTexture("stardew:item/bee");

	}

	public int getFXLayer() {
		return 2;
	}
}
