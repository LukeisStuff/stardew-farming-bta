package luke.stardew.render;

import luke.stardew.inventories.TileEntityStove;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.tileentity.TileEntityRenderer;
import net.minecraft.core.item.Item;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.Random;

public class TileEntityRendererStove extends TileEntityRenderer<TileEntityStove> {

	private final Random random = new Random();

	@Override
	public void doRender(Tessellator tessellator, TileEntityStove tileEntity, double x, double y, double z, float g) {
		float offset1Food = tileEntity.itemRenderOffset1;
		float offset2Food = tileEntity.itemRenderOffset2;
		float relative1Food = tileEntity.itemRenderRelative1;
		float relative2Food = tileEntity.itemRenderRelative2;

		float offset1Fuel = 0.1f;
		float offset2Fuel = 0.13f;
		float relative1Fuel = 0;
		float relative2Fuel = 0;

		List<TileEntityStove.StoveItem> contents = tileEntity.contentsToCook;

		for (TileEntityStove.StoveItem content : contents) {
			if (relative1Food < offset1Food * 3) {
				if (content.getStack().getItem().id == Item.stick.id) {
					relative1Food += offset1Food;
					if (relative1Food == offset1Food * 3) {
						relative1Food = 0;
						if (relative2Food < offset2Food * 2) {
							relative2Food += offset2Food;
						}
					}
					continue;
				}

				ItemModel foodModel = ItemModelDispatcher.getInstance().getDispatch(content.getStack());

				GL11.glPushMatrix();
				GL11.glTranslated( x + 0.07f + relative1Food, y + 1, z + 0.15f + relative2Food);
				GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
				GL11.glScaled(0.3f, 0.3f, 0.3f);
				foodModel.renderItemInWorld(tessellator, null, content.getStack(), 1, 1, false); //f = brightness, g = idk, but on 0 its not visible, bl = idk

				GL11.glPopMatrix();

				relative1Food += offset1Food;
				if (relative1Food == offset1Food * 3) {
					relative1Food = 0;
					if (relative2Food < offset2Food * 2) {
						relative2Food += offset2Food;
					}
				}
			}
		}

		if (tileEntity.fuel != null){
			for (int i = 0; i < tileEntity.fuel.stackSize; i++) {
				if (relative1Fuel < offset1Fuel * 4) {

					ItemModel fuelModel = ItemModelDispatcher.getInstance().getDispatch(tileEntity.fuel);

					GL11.glPushMatrix();
					GL11.glTranslated( x + 1 - relative1Fuel, y + 0.8 + relative2Fuel, z);
					GL11.glScaled(0.1f, 0.1f, 0.1f);
					fuelModel.renderItemInWorld(tessellator, null, tileEntity.fuel, 1, 1, false); //f = brightness, g = idk, but on 0 its not visible, bl = idk

					GL11.glPopMatrix();

					relative1Fuel += offset1Fuel;
					if (relative1Fuel == offset1Fuel * 4) {
						relative1Fuel = 0;
						if (relative2Fuel < offset2Fuel * 2) {
							relative2Fuel += offset2Fuel;
						}
					}
				}
			}
		}
	}
}
