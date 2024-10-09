package luke.stardew.entities.duck;

import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.util.helper.MathHelper;

public class DuckRenderer
	extends LivingRenderer<EntityDuck> {
	public DuckRenderer(ModelBase modelbase, float f) {
		super(modelbase, f);
	}

	private void renderDuck(EntityDuck entity, double d, double d1, double d2, float f, float f1) {
		super.render(entity, d, d1, d2, f, f1);
	}

	protected float getWingRotation(EntityDuck entity, float f) {
		float f1 = entity.field_756_e + (entity.field_752_b - entity.field_756_e) * f;
		float f2 = entity.field_757_d + (entity.destPos - entity.field_757_d) * f;
		return (MathHelper.sin(f1) + 1.0f) * f2;
	}

	@Override
	protected float ticksExisted(EntityDuck entity, float partialTick) {
		return this.getWingRotation(entity, partialTick);
	}

	@Override
	public void render(EntityDuck entity, double x, double y, double z, float yaw, float partialTick) {
		this.renderDuck(entity, x, y, z, yaw, partialTick);
	}

	@Override
	public void doRender(Tessellator tessellator, EntityDuck entity, double x, double y, double z, float yaw, float partialTick) {
		this.renderDuck(entity, x, y, z, yaw, partialTick);
	}
}
