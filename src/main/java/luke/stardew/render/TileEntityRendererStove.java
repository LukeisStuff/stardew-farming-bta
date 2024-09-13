package luke.stardew.render;

import luke.stardew.inventories.TileEntityStove;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.ItemModelCoal;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.tileentity.TileEntityRenderer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class TileEntityRendererStove extends TileEntityRenderer<TileEntityStove> {

	@Override
	public void doRender(Tessellator tessellator, TileEntityStove tileEntity, double x, double y, double z, float g) {
		float offset1Food = tileEntity.itemRenderOffset1;
		float offset2Food = tileEntity.itemRenderOffset2;

		float offset1Fuel = 0.13f;
		float offset2Fuel = 0.13f;

		//TODO: rendering bug, when the world is loaded back
		renderItemsBasedOnMetadata(tessellator, tileEntity.blockMetadata, x, y, z, tileEntity.contentsToCook, 2, tileEntity.amountToCook / 2, offset2Food, offset1Food);
		renderItemsBasedOnMetadata(tessellator, tileEntity.blockMetadata, x, y, z, tileEntity.fuel, 2, tileEntity.maxFuelAmount / 2, offset2Fuel, offset1Fuel);


	}

	private void renderItemsBasedOnMetadata(Tessellator tessellator, int metadata, double x, double y, double z, List<TileEntityStove.StoveItem> contents, int verticalAmount, int horizontalAmount, float offsetValueVertical, float offsetValueHorizontal){
		float currentVertical = 0;
		float currentHorizontal = 0;

		for (TileEntityStove.StoveItem content : contents) {
			if (content.getStack().getItem().id == Item.stick.id) {

				currentHorizontal += offsetValueHorizontal;
				if (currentHorizontal == offsetValueHorizontal * horizontalAmount) {
					currentHorizontal = 0;
					if (currentVertical < offsetValueVertical * verticalAmount) {
						currentVertical += offsetValueVertical;
					}
				}
				continue;
			}

			ItemModel foodModel = ItemModelDispatcher.getInstance().getDispatch(content.getStack());

			GL11.glPushMatrix();
			switch (metadata) {
				case 2:
					GL11.glTranslated( x + 0.062f + currentHorizontal, y + 1, z + 0.14f + currentVertical); //default
					break;
				case 3:
					GL11.glTranslated( x + 0.938f - currentHorizontal, y + 1, z + 0.86f - currentVertical); // 0.938f = 1 - defX | 0.86f = 1 - defZ
					GL11.glRotatef(180, 0.0f, 1.0f, 0.0f);
					break;
				case 4:
					GL11.glTranslated( x + 0.14f + currentVertical, y + 1, z + 0.938f - currentHorizontal); // 0.14f = defZ | 0.938f = 1 - defX
					GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
					break;
				case 5:
					GL11.glTranslated( x + 0.86f - currentVertical, y + 1, z + 0.062f + currentHorizontal); // 0.86f = 1 - defZ | 0.062f = defX
					GL11.glRotatef(90, 0.0f, -1.0f, 0.0f);
					break;
			}
			GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
			GL11.glScaled(0.3f, 0.3f, 0.3f);

			foodModel.renderItemInWorld(tessellator, null, content.getStack(), 1, 1, false); //f = brightness, g = alpha, bl = worldTransform

			GL11.glPopMatrix();

			currentHorizontal += offsetValueHorizontal;
			if (currentHorizontal == offsetValueHorizontal * horizontalAmount) {
				currentHorizontal = 0;
				if (currentVertical < offsetValueVertical * verticalAmount) {
					currentVertical += offsetValueVertical;
				}
			}
		}
	}

	private void renderItemsBasedOnMetadata(Tessellator tessellator, int metadata, double x, double y, double z, ItemStack contents, int verticalAmount, int horizontalAmount, float offsetValueVertical, float offsetValueHorizontal){
		if (contents != null){
			float currentVertical = 0;
			float currentHorizontal = 0;
			ItemModel fuelModel;

			//TODO: charcoal texture not working
			if (contents.itemID == Item.coal.id && contents.getMetadata() == 1){
				fuelModel = new ItemModelCoal(Item.coal, null);
			}else {
				fuelModel = ItemModelDispatcher.getInstance().getDispatch(contents);
			}

			for (int i = 0; i < contents.stackSize; i++) {

				GL11.glPushMatrix();
				switch (metadata) {
					case 2:
						GL11.glTranslated( x + 0.64f - currentHorizontal, y + 0.64f + currentVertical, z); //default
						break;
					case 3:
						GL11.glTranslated( x + 0.36f + currentHorizontal, y + 0.64f + currentVertical, z + 1); // 0.36f = 1 - defX | Y = defY | + 1
						GL11.glRotatef(180, 0.0f, 1.0f, 0.0f);
						break;
					case 4:
						GL11.glTranslated( x, y + 0.64f + currentVertical, z + 0.36f + currentHorizontal); // X | Y = defY | 0.36f = 1 - defX
						GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
						break;
					case 5:
						GL11.glTranslated( x + 1, y + 0.64f + currentVertical, z + 0.64f - currentHorizontal); // + 1 | Y = defY | Z = defX
						GL11.glRotatef(90, 0.0f, -1.0f, 0.0f);
						break;
				}
				GL11.glScaled(0.1f, 0.1f, 0.1f);
				fuelModel.renderItemInWorld(tessellator, null, contents.getItem().getDefaultStack(), 1, 1, false); //f = brightness, g = alpha, bl = worldTransform

				GL11.glPopMatrix();

				currentHorizontal += offsetValueHorizontal;
				if (currentHorizontal == offsetValueHorizontal * horizontalAmount) {
					currentHorizontal = 0;
					if (currentVertical < offsetValueVertical * verticalAmount) {
						currentVertical += offsetValueVertical;
					}
				}
			}
		}
	}
}
