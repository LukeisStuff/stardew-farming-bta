package luke.stardew.render;

import luke.stardew.inventories.TileEntityStove;
import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.tileentity.TileEntityRenderer;
import net.minecraft.core.item.Item;
import net.minecraft.core.world.settings.WorldSettingCollection;
import net.minecraft.core.world.settings.WorldSettingEnum;
import net.minecraft.core.world.settings.WorldSettings;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TileEntityRendererStove extends TileEntityRenderer<TileEntityStove> {

	private final Random random = new Random();

	@Override
	public void doRender(Tessellator tessellator, TileEntityStove tileEntity, double x, double y, double z, float g) {
		float offsetX = tileEntity.itemRenderOffsetX;
		float offsetZ = tileEntity.itemRenderOffsetZ;
		float relativeX = tileEntity.itemRenderRelativeX;
		float relativeZ = tileEntity.itemRenderRelativeZ;
		/* we need this shape:
			#	#
			#	#
			#	#
		 */
		List<TileEntityStove.StoveItem> contents = tileEntity.contentsToCook;

		for (TileEntityStove.StoveItem content : contents) {
			if (relativeX < offsetX * 3) {
				if (content.getStack().getItem().id == Item.stick.id) {
					continue;
				}

				ItemModel model = ItemModelDispatcher.getInstance().getDispatch(content.getStack());

				GL11.glPushMatrix();
				GL11.glTranslated( x + 0.07f + relativeX, y + 1, z + 0.15f + relativeZ);
				GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
				GL11.glScaled(0.3f, 0.3f, 0.3f);
				model.renderItemInWorld(tessellator, null, content.getStack(), 1, 1, false);

				GL11.glPopMatrix();

				relativeX += offsetX;
				if (relativeX == offsetX * 3) {
					relativeX = 0;
					if (relativeZ < offsetZ * 2) {
						relativeZ += offsetZ;
					}
				}
			}
		}
	}
}
