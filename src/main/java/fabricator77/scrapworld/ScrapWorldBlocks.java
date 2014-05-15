package fabricator77.scrapworld;

import cpw.mods.fml.common.registry.GameRegistry;
import fabricator77.scrapworld.blocks.BlockScrapCube;
import fabricator77.scrapworld.blocks.BlockScrapSurfaceDebris;
import net.minecraft.block.Block;

public class ScrapWorldBlocks {

	public static Block scrapCube;
	public static Block scrapSurfaceDebris;

	public ScrapWorldBlocks() {
		scrapCube = new BlockScrapCube().setBlockName("scrapCube");
		GameRegistry.registerBlock(scrapCube, "scrapCube");
		scrapSurfaceDebris = new BlockScrapSurfaceDebris().setBlockName("scrapDebris");
		GameRegistry.registerBlock(scrapSurfaceDebris, "scrapDebris");
	}
}
