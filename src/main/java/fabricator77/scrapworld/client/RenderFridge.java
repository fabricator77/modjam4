package fabricator77.scrapworld.client;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderFridge extends TileEntitySpecialRenderer {
	
	private ModelFridge field_147510_h = new ModelFridge();
    private ModelFridge field_147511_i = new ModelLargeFridge();

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float var8) {

	}
}
