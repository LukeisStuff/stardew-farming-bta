package luke.stardew.entities.goat;

import net.minecraft.client.render.model.Cube;
import net.minecraft.client.render.model.ModelQuadruped;

public class ModelGoat extends ModelQuadruped {
	Cube udders;
	Cube horn1;
	Cube horn2;
	Cube tail;
	Cube beard;
	Cube ear1;
	Cube ear2;

	public ModelGoat() {

		//Positive +X = RIGHT
		//Negative -X = LEFT

		//Positive +Y = DOWN
		//Negative -Y = UP

		//Positive +Z = BACK
		//Negative -Z = FORWARD


		super(8, 0.0f);
		this.head = new Cube(0, 0);
		this.head.addBox(-3.0f, -4.0f, -10.0f, 6, 8, 10);
		this.head.setRotationPoint(0.0f, 7.0f, -8.0f);

		this.ear1 = new Cube(13, 24);
		this.ear1.addBox(-4.0f, -3.0f, -2.5f, 1, 6, 2);
		this.ear1.setRotationPoint(0.0f, 7.0f, -8.0f);

		this.ear2 = new Cube(13, 24);
		this.ear2.addBox(3.0f, -3.0f, -2.5f, 1, 6, 2);
		this.ear2.setRotationPoint(0.0f, 7.0f, -8.0f);

		this.horn1 = new Cube(22, 0);
		this.horn1.addBox(-2.0f, -7.0f, -2.0f, 1, 3, 1);
		this.horn1.setRotationPoint(0.0f, 7.0f, -8.0f);

		this.horn2 = new Cube(22, 0);
		this.horn2.addBox(1.0f, -7.0f, -2.0f, 1, 3, 1);
		this.horn2.setRotationPoint(0.0f, 7.0f, -8.0f);

		this.beard = new Cube(0, -4);
		this.beard.addBox(0.0f, 4.0f, -7.0f, 0, 6, 4);
		this.beard.setRotationPoint(0.0f, 7.0f, -8.0f);

		this.udders = new Cube(54, 0);
		this.udders.addBox(-2.0f, -3.0f, 0.0f, 4, 6, 1);
		this.udders.setRotationPoint(0.0f, 17.0f, 5.0f);
		this.udders.rotateAngleX = 1.570796f;

		this.body = new Cube(28, 6);
		this.body.addBox(-4.0f, -8.0f, -7.0f, 8, 16, 10);
		this.body.setRotationPoint(0.0f, 9.0f, 0.0f);

		this.tail = new Cube(30, -4);
		this.tail.addBox(0.0f, -7.0f, 7.0f, 0, 6, 4);
		this.tail.setRotationPoint(0.0f, 9.0f, 0.0f);

		this.leg1 = new Cube(0, 21);
		this.leg1.addBox(-1.0f, 0.0f, -2.0f, 3, 8, 3);
		this.leg1.setRotationPoint(-2.0f, 16, 6.0f);

		this.leg2 = new Cube(0, 21);
		this.leg2.addBox(-2.0f, 0.0f, -2.0f, 3, 8, 3);
		this.leg2.setRotationPoint(2.0f, 16, 6.0f);

		this.leg3 = new Cube(0, 21);
		this.leg3.addBox(-1.0f, 0.0f, -2.0f, 3, 8, 3);
		this.leg3.setRotationPoint(-2.0f, 16, -4.0f);

		this.leg4 = new Cube(0, 21);
		this.leg4.addBox(-2.0f, 0.0f, -2.0f, 3, 8, 3);
		this.leg4.setRotationPoint(2.0f, 16, -4.0f);

		this.leg1.rotationPointX -= 1.0f;
		this.leg2.rotationPointX += 1.0f;
		this.leg1.rotationPointZ += 0.0f;
		this.leg2.rotationPointZ += 0.0f;
		this.leg3.rotationPointX -= 1.0f;
		this.leg4.rotationPointX += 1.0f;
		this.leg3.rotationPointZ -= 1.0f;
		this.leg4.rotationPointZ -= 1.0f;
	}

	@Override
	public void render(float limbSwing, float limbYaw, float limbPitch, float headYaw, float headPitch, float scale) {
		super.render(limbSwing, limbYaw, limbPitch, headYaw, headPitch, scale);
		this.horn1.render(scale);
		this.horn2.render(scale);
		this.udders.render(scale);
		this.tail.render(scale);
		this.beard.render(scale);
		this.ear1.render(scale);
		this.ear2.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbYaw, float limbPitch, float headYaw, float headPitch, float scale) {
		super.setRotationAngles(limbSwing, limbYaw, limbPitch, headYaw, headPitch, scale);
		this.horn1.rotateAngleY = this.head.rotateAngleY;
		this.horn1.rotateAngleX = this.head.rotateAngleX;
		this.horn2.rotateAngleY = this.head.rotateAngleY;
		this.horn2.rotateAngleX = this.head.rotateAngleX;

		this.ear1.rotateAngleY = this.head.rotateAngleY;
		this.ear1.rotateAngleX = this.head.rotateAngleX;
		this.ear2.rotateAngleY = this.head.rotateAngleY;
		this.ear2.rotateAngleX = this.head.rotateAngleX;

		this.beard.rotateAngleY = this.head.rotateAngleY;
		this.beard.rotateAngleX = this.head.rotateAngleX;
	}
}
