package fabricator77.scrapworld;

import cpw.mods.fml.common.registry.GameRegistry;
import fabricator77.scrapworld.blocks.BlockScrapCube;
import fabricator77.scrapworld.blocks.BlockScrapSurfaceDebris;
import fabricator77.scrapworld.items.ScrapItems1;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ScrapWorldBlocks {

	public static Block scrapCube;
	public static Block scrapSurfaceDebris;
	
	public static Item scrapItems1;

	public ScrapWorldBlocks() {
		scrapCube = new BlockScrapCube().setBlockName("scrapCube");
		GameRegistry.registerBlock(scrapCube, "scrapCube");
		scrapSurfaceDebris = new BlockScrapSurfaceDebris().setBlockName("scrapDebris");
		GameRegistry.registerBlock(scrapSurfaceDebris, "scrapDebris");
		
		scrapItems1 = new ScrapItems1();
		GameRegistry.registerItem(scrapItems1, "scrapItems1");
	}
}
