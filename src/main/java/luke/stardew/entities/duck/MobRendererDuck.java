package luke.stardew.entities.duck;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.core.util.helper.MathHelper;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.useless.dragonfly.models.entity.BoneTransform;
import org.useless.dragonfly.models.entity.StaticEntityModel;

@Environment(EnvType.CLIENT)
public class MobRendererDuck extends MobRenderer<MobDuck> {
    public MobRendererDuck(float shadowSize) {
        super(shadowSize);
    }

    @Override
    protected @Nullable StaticEntityModel getAndSetupModelForLayer(@NonNull MobDuck entity, float brightness, float partialTick, int layer) {
        StaticEntityModel model = this.getModel("main");
        model.resetBones();
        float limbSwing = this.getLimbSwing(entity, partialTick);
        float limbYaw = this.getLimbYaw(entity, partialTick);
        float limbPitch = this.getLimbPitch(entity, partialTick);
        BoneTransform leg0 = model.getTransform("leg0");
        BoneTransform leg1 = model.getTransform("leg1");
        BoneTransform wing0 = model.getTransform("wing0");
        BoneTransform wing1 = model.getTransform("wing1");
        leg0.rotX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbYaw;
        leg1.rotX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbYaw;
        wing0.rotZ = limbPitch;
        wing1.rotZ = -limbPitch;
        return model;
    }

    @Override
    protected float getLimbPitch(@NonNull MobDuck entity, float partialTick) {
        float flap = MathHelper.lerp(entity.oFlap, entity.flap, partialTick);
        float flapSpeed = MathHelper.lerp(entity.oFlapSpeed, entity.flapSpeed, partialTick);
        return (MathHelper.sin(flap) + 1.0F) * flapSpeed;
    }

    @Override
    protected void preRenderTransform(@NonNull MobDuck entity, double x, double y, double z, float yaw, float partialTick) {
        super.preRenderTransform(entity, x, y, z, yaw, partialTick);
        GLRenderer.modelM4f().translate(0.0F, 0.3F, 0.0F);
    }
}
