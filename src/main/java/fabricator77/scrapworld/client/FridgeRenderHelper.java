package fabricator77.scrapworld.client;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fabricator77.scrapworld.blocks.TileEntityFridge;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;

@SideOnly(Side.CLIENT)
public class FridgeRenderHelper {
	public static FridgeRenderHelper instance = new FridgeRenderHelper();
    private TileEntityChest field_147717_b = new TileEntityChest(0);
    private TileEntityFridge fridgeTE = new TileEntityFridge(0);
    /**
     * Renders a chest at 0,0,0 - used for item rendering
     */
    public void renderChest(Block block, int p_147715_2_, float p_147715_3_)
    {
    	TileEntityRendererDispatcher.instance.renderTileEntityAt(this.fridgeTE, 0.0D, 0.0D, 0.0D, 0.0F);
    }
}
