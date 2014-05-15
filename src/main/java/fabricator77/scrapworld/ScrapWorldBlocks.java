package fabricator77.scrapworld;

import cpw.mods.fml.common.registry.GameRegistry;
import fabricator77.scrapworld.blocks.BlockScrapCube;
import fabricator77.scrapworld.blocks.BlockScrapFurnace;
import fabricator77.scrapworld.blocks.BlockScrapSurfaceDebris;
import fabricator77.scrapworld.blocks.BlockWireTangle;
import fabricator77.scrapworld.items.Dusts;
import fabricator77.scrapworld.items.ScrapItems1;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ScrapWorldBlocks {

	public static Block scrapCube;
	public static Block scrapSurfaceDebris;
	
	public static Block wireTangle;
	public static Block scrapFurnace;
	public static Block litScrapFurnace;
	
	public static Item scrapItems1;
	public static Item dusts;

	public ScrapWorldBlocks() {
		scrapCube = new BlockScrapCube().setBlockName("scrapCube").setHardness(3.0F).setResistance(5.0F);
		GameRegistry.registerBlock(scrapCube, "scrapCube");
		scrapSurfaceDebris = new BlockScrapSurfaceDebris().setBlockName("scrapDebris");
		GameRegistry.registerBlock(scrapSurfaceDebris, "scrapDebris");
		
		wireTangle = new BlockWireTangle().setBlockName("wireTangle").setHardness(4.0F);
		GameRegistry.registerBlock(wireTangle, "wireTangle");
		
		scrapFurnace = new BlockScrapFurnace(false).setHardness(3.5F).setBlockName("scrapFurnace");
		GameRegistry.registerBlock(scrapFurnace, "scrapFurnace");
		litScrapFurnace = new BlockScrapFurnace(false).setHardness(3.5F).setBlockName("litScrapFurnace");
		GameRegistry.registerBlock(litScrapFurnace, "litScrapFurnace");
		
		
		scrapItems1 = new ScrapItems1();
		GameRegistry.registerItem(scrapItems1, "scrapItems1");
		
		dusts = new Dusts();
		GameRegistry.registerItem(dusts, "dusts");
	}
}
