package luke.stardew.entities.duck;

import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.entity.MobRendererChicken;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.core.entity.animal.MobChicken;
import net.minecraft.core.util.helper.MathHelper;

public class DuckRenderer extends MobRenderer<EntityDuck> {
	public DuckRenderer(ModelBase model, float shadowSize) {
		super(model, shadowSize);
	}

	@Override
	protected float limbSway(EntityDuck entity, float partialTick) {
		float flap = entity.oFlap + (entity.flap - entity.oFlap) * partialTick;
		float flapSpeed = entity.oFlapSpeed + (entity.flapSpeed - entity.oFlapSpeed) * partialTick;
		return (MathHelper.sin(flap) + 1.0F) * flapSpeed;
	}
}
