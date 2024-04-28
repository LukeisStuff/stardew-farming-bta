package luke.stardew;

import net.minecraft.client.entity.fx.EntityFX;
import net.minecraft.client.entity.fx.EntityFireflyFX;
import net.minecraft.client.entity.fx.EntityFlameFX;
import net.minecraft.client.render.Tessellator;
import net.minecraft.core.Global;
import net.minecraft.core.world.World;
import turniplabs.halplibe.helper.TextureHelper;

import static luke.stardew.StardewMod.MOD_ID;

public class EntityBeeFX extends EntityFX {
	private static final int bee = TextureHelper.getOrCreateItemTextureIndex(MOD_ID, "bee.png");
	public float originalScale;
	public EntityBeeFX(World world, double d, double d1, double d2, double d3, double d4, double d5) {
		super(world, d, d1, d2, d3, d4, d5);
		this.particleTextureIndex = bee;
		particleScale *= 1.0f;
		originalScale = particleScale;
	}

	public void renderParticle(Tessellator t, float partialTick, float rotationX, float rotationXZ, float rotationZ, float rotationYZ, float rotationXY) {
		float f6 = ((float) this.particleAge + partialTick) / (float) this.particleMaxAge;
		this.particleScale = originalScale * (1.0f - f6 * f6 * 0.5f);
		float texMinU = (float) (this.particleTextureIndex % Global.TEXTURE_ATLAS_WIDTH_TILES) / Global.TEXTURE_ATLAS_WIDTH_TILES;
		float texMaxU = texMinU + 1f / Global.TEXTURE_ATLAS_WIDTH_TILES;
		float texMinV = (float) (this.particleTextureIndex / Global.TEXTURE_ATLAS_WIDTH_TILES) / Global.TEXTURE_ATLAS_WIDTH_TILES;
		float texMaxV = texMinV + 1f / Global.TEXTURE_ATLAS_WIDTH_TILES;
		float scale = 0.1f * this.particleScale;
		float posX = (float) (this.xo + (this.x - this.xo) * (double) partialTick - lerpPosX);
		float posY = (float) (this.yo + (this.y - this.yo) * (double) partialTick - lerpPosY);
		float posZ = (float) (this.zo + (this.z - this.zo) * (double) partialTick - lerpPosZ);
		float brightness = this.getBrightness(partialTick);
		t.setColorOpaque_F(this.particleRed * brightness, this.particleGreen * brightness, this.particleBlue * brightness);
		t.addVertexWithUV(posX - rotationX * scale - rotationYZ * scale, posY - rotationXZ * scale, posZ - rotationZ * scale - rotationXY * scale, texMaxU, texMaxV);
		t.addVertexWithUV(posX - rotationX * scale + rotationYZ * scale, posY + rotationXZ * scale, posZ - rotationZ * scale + rotationXY * scale, texMaxU, texMinV);
		t.addVertexWithUV(posX + rotationX * scale + rotationYZ * scale, posY + rotationXZ * scale, posZ + rotationZ * scale + rotationXY * scale, texMinU, texMinV);
		t.addVertexWithUV(posX + rotationX * scale - rotationYZ * scale, posY - rotationXZ * scale, posZ + rotationZ * scale - rotationXY * scale, texMinU, texMaxV);
	}

	@Override
	public void tick() {
		super.tick();
		this.particleTextureIndex = bee;
	}

	public int getFXLayer() {
		return 2;
	}
}
