package fabricator77.scrapworld.client;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntitySign;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fabricator77.scrapworld.blocks.TileEntityFridge;
import fabricator77.scrapworld.common.CommonProxy;

public class ClientProxy extends CommonProxy{
	

	public static void initRendering () {
		//TODO: use this for custom block rendering
		// eg the fridge
		//fridgeRendererID = RenderingRegistry.getNextAvailableRenderId();
		//RenderingRegistry.registerBlockHandler(fridgeRendererID, new TileEntityRenderFridge());
		//TileEntitySpecialRenderer tileentityspecialrenderer = (TileEntitySpecialRenderer)new TileEntityRenderFridge();
		//TileEntityRendererDispatcher.instance.mapSpecialRenderers.put(TileEntityRenderFridge.class, tileentityspecialrenderer);
        //tileentityspecialrenderer.func_147497_a(TileEntityRendererDispatcher.instance);
	}

}
