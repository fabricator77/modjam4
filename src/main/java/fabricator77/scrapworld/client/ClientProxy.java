package fabricator77.scrapworld.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fabricator77.scrapworld.common.CommonProxy;

public class ClientProxy extends CommonProxy{
	public static void initRendering () {
		//TODO: use this for custom block rendering
		// eg the fridge
		int id = RenderingRegistry.getNextAvailableRenderId();
	}

}
