package luke.stardew.entities.goat;

import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.client.render.tessellator.Tessellator;

public class GoatRenderer
	extends LivingRenderer<EntityGoat> {
	public GoatRenderer(ModelBase modelbase, float f) {
		super(modelbase, f);
	}

	public void renderCow(EntityGoat entitygoat, double d, double d1, double d2, float f, float f1) {
		super.render(entitygoat, d, d1, d2, f, f1);
	}

	@Override
	public void render(EntityGoat entity, double x, double y, double z, float yaw, float partialTick) {
		this.renderCow(entity, x, y, z, yaw, partialTick);
	}

	@Override
	public void doRender(Tessellator tessellator, EntityGoat entity, double x, double y, double z, float yaw, float partialTick) {
		this.renderCow(entity, x, y, z, yaw, partialTick);
	}
}
