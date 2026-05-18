//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package luke.stardew.model;

import luke.stardew.entities.duck.MobDuck;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.core.entity.animal.MobChicken;
import net.minecraft.core.util.helper.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.models.entity.BoneTransform;
import org.useless.dragonfly.models.entity.StaticEntityModel;

@Environment(EnvType.CLIENT)
public class MobRendererDuck<T extends MobDuck> extends MobRenderer<T> {
    public MobRendererDuck(float shadowSize) {
        super(shadowSize);
    }

    protected @Nullable StaticEntityModel getAndSetupModelForLayer(@NotNull T entity, float brightness, float partialTick, int layer) {
        StaticEntityModel model = this.getModel("main");
        model.resetBones();
        float limbSwing = this.getLimbSwing(entity, partialTick);
        float limbYaw = this.getLimbYaw(entity, partialTick);
        float limbPitch = this.getLimbPitch(entity, partialTick);
        BoneTransform leg0 = model.getTransform("leg0");
        BoneTransform leg1 = model.getTransform("leg1");
        BoneTransform wing0 = model.getTransform("wing0");
        BoneTransform wing1 = model.getTransform("wing1");
        leg0.rotX =  (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbYaw);
        leg1.rotX =  (MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbYaw);
        wing0.rotZ = limbPitch;
        wing1.rotZ = (-limbPitch);
        return model;
    }

    @Override
    protected float getLimbPitch(@NotNull T entity, float partialTick) {
        float flap = MathHelper.lerp(entity.oFlap, entity.flap, partialTick);
        float flapSpeed = MathHelper.lerp(entity.oFlapSpeed, entity.flapSpeed, partialTick);
        return (MathHelper.sin(flap) + 1.0F) * flapSpeed;
    }

    protected void preRenderTransform(@NotNull T entity, double x, double y, double z, float yaw, float partialTick) {
        super.preRenderTransform(entity, x, y, z, yaw, partialTick);
        GLRenderer.modelM4f().translate(0.0F, 0.3F, 0.0F);
    }

    @Override
    protected void bindTexture(@NotNull String texturePath) {
        super.bindTexture(texturePath);
    }
}
