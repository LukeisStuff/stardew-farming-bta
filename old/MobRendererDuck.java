package luke.stardew.entities.duck;

import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.core.util.helper.MathHelper;

public class MobRendererDuck extends MobRenderer<MobDuck> {
    public MobRendererDuck(ModelBase model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    public float limbSway(MobDuck entity, float partialTick) {
        float flap = entity.oFlap + (entity.flap - entity.oFlap) * partialTick;
        float flapSpeed = entity.oFlapSpeed + (entity.flapSpeed - entity.oFlapSpeed) * partialTick;
        return (MathHelper.sin(flap) + 1.0F) * flapSpeed;
    }
}
