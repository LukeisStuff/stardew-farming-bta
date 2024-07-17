package luke.stardew.entities.fx;

import net.minecraft.client.entity.fx.EntityFX;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.world.World;

import static luke.stardew.StardewMod.MOD_ID;

public class EntityBeeFX extends EntityFX {
	public float originalScale;

	public EntityBeeFX(World world, double d, double d1, double d2, double d3, double d4, double d5) {
		super(world, d, d1, d2, d3, d4, d5);
		this.particleTexture = TextureRegistry.getTexture(MOD_ID + ":item/bee");
		this.particleRed = 1;
		this.particleGreen = 1;
		this.particleBlue = 1;
		particleScale *= 1.0f;
		originalScale = particleScale;
	}

	public void renderParticle(Tessellator tessellator, float partialTick, float rotationX, float rotationXZ, float rotationZ, float rotationYZ, float rotationXY) {
		float f6 = (this.particleAge + partialTick) / (float)this.particleMaxAge * 32.0f;
		if (f6 < 0.0F)
			f6 = 0.0F;
		if (f6 > 1.0F)
			f6 = 1.0F;
		super.renderParticle(tessellator, partialTick, rotationX, rotationXZ, rotationZ, rotationYZ, rotationXY);
		this.particleScale = 2.0F - f6 * f6 * 0.5F;

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
		this.particleTexture = TextureRegistry.getTexture(MOD_ID + ":item/bee");

	}

	public int getFXLayer() {
		return 2;
	}
}
