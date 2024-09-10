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

				GL11.glRotatef(90, 1.0f, 0, 0);
				GL11.glScaled(0.3f, 0.3f, 0.3f);
				GL11.glTranslated(x + 0.07d + relativeX, y + 1d, z + 0.15d + relativeZ);

				model.renderAsItemEntity(tessellator, null, random, content.getStack(), content.getStack().stackSize, 0, 1, g);

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


		//TODO: igy nezed meg h block e
		/*if(model instanceof ItemModelBlock){
			BlockModel<?> blockModel = BlockModelDispatcher.getInstance().getDispatch(Block.blocksList[content.getStack().itemID]);
		}*/
	}
}
