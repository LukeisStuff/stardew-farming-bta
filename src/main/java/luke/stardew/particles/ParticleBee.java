package luke.stardew.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.particle.Particle;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.world.World;

import static luke.stardew.StardewMod.MOD_ID;


@Environment(EnvType.CLIENT)
public class ParticleBee extends Particle {
    private static final IconCoordinate BEE_1 = TextureRegistry.getTexture(MOD_ID + ":particle/bee");
    private static final IconCoordinate BEE_2 = TextureRegistry.getTexture(MOD_ID + ":particle/bee_2");
    private final float originalScale;

    public ParticleBee(World world, double x, double y, double z, double xa, double ya, double za) {
        super(world, x, y, z, xa, ya, za);
        this.tex = BEE_1;
        this.rCol = this.gCol = this.bCol = 1.0F;
        this.size *= 1.2F;
        this.originalScale = this.size;
        this.lifetime = random.nextInt(20) + 20;
        this.age = 0;
        this.init();
    }

    @Override
    public void tick() {
        this.tex = (this.age & 1) == 0 ? BEE_1 : BEE_2;
        this.size = this.originalScale * (1.0F - (float)this.age / (float)this.lifetime);
        super.tick();
    }
}
