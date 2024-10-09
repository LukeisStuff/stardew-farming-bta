package luke.stardew.entities.duck;

import net.minecraft.client.render.model.Cube;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.core.util.helper.MathHelper;

public class ModelDuck
	extends ModelBase {
	public Cube head;
	public Cube neck;
	public Cube body;
	public Cube rightLeg;
	public Cube leftLeg;
	public Cube rightWing;
	public Cube leftWing;
	public Cube tail;
	public Cube bill;

	public ModelDuck() {
		//Positive +X = RIGHT
		//Negative -X = LEFT

		//Positive +Y = DOWN
		//Negative -Y = UP

		//Positive +Z = BACK
		//Negative -Z = FORWARD


		this.head = new Cube(0, 0);
		this.head.addBox(-2.0f, -4.0f, -3.0f, 4, 4, 5);
		this.head.setRotationPoint(0.0f, 10, -1.0f);

		this.neck = new Cube(18, 0);
		this.neck.addBox(-1.5f, -3.0f, -1.5f, 3, 3, 3);
		this.neck.setRotationPoint(0.0f, 13, -1.0f);

		this.bill = new Cube(30, 0);
		this.bill.addBox(-1.5f, -2.0f, -6.0f, 3, 2, 3);
		this.bill.setRotationPoint(0.0f, 10, -1.0f);

		this.body = new Cube(0, 9);
		this.body.addBox(-3.0f, -3.0f, -3.0f, 6, 10, 6);
		this.body.setRotationPoint(0.0f, 16, 0.0f);


		this.rightLeg = new Cube(26, 7);
		this.rightLeg.addBox(-1.0f, 0.0f, -3.0f, 3, 5, 4);
		this.rightLeg.setRotationPoint(-2.5f, 19, 2.0f);

		this.leftLeg = new Cube(26, 7);
		this.leftLeg.addBox(-1.0f, 0.0f, -3.0f, 3, 5, 4);
		this.leftLeg.setRotationPoint(1.5f, 19, 2.0f);


		this.rightWing = new Cube(19, 18);
		this.rightWing.addBox(-1.0f, 0.0f, -3.0f, 1, 5, 9);
		this.rightWing.setRotationPoint(-3.0f, 13, 2.0f);

		this.leftWing = new Cube(19, 18);
		this.leftWing.addBox(-0.0f, 0.0f, -3.0f, 1, 5, 9);
		this.leftWing.setRotationPoint(3.0f, 13, 2.0f);

		this.tail = new Cube(0, 26);
		this.tail.addBox(-2.0f, -4.0f, 0.0f, 4, 5, 1);
		this.tail.setRotationPoint(0.0f, 16, 7.0f);
	}

	@Override
	public void render(float limbSwing, float limbYaw, float limbPitch, float headYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbYaw, limbPitch, headYaw, headPitch, scale);
		this.head.render(scale);
		this.neck.render(scale);
		this.bill.render(scale);
		this.body.render(scale);
		this.rightLeg.render(scale);
		this.leftLeg.render(scale);
		this.rightWing.render(scale);
		this.leftWing.render(scale);
		this.tail.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbYaw, float limbPitch, float headYaw, float headPitch, float scale) {
		this.head.rotateAngleX = headPitch / 57.29578f;
		this.head.rotateAngleY = headYaw / 57.29578f;
		this.bill.rotateAngleX = this.head.rotateAngleX;
		this.bill.rotateAngleY = this.head.rotateAngleY;
		this.body.rotateAngleX = 1.570796f;
		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbYaw;
		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.141593f) * 1.4f * limbYaw;
		this.rightWing.rotateAngleZ = limbPitch;
		this.leftWing.rotateAngleZ = -limbPitch;
	}
}
