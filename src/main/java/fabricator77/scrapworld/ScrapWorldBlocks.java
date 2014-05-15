package fabricator77.scrapworld;

import cpw.mods.fml.common.registry.GameRegistry;
import fabricator77.scrapworld.blocks.BlockCompressedScrapCube;
import fabricator77.scrapworld.blocks.BlockScrapCube;
import fabricator77.scrapworld.blocks.BlockScrapFurnace;
import fabricator77.scrapworld.blocks.BlockScrapSurfaceDebris;
import fabricator77.scrapworld.blocks.BlockWireTangle;
import fabricator77.scrapworld.items.Dusts;
import fabricator77.scrapworld.items.Ingots;
import fabricator77.scrapworld.items.ScrapItems1;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

public class ScrapWorldBlocks {

	public static Block scrapCube;
	public static Block scrapSurfaceDebris;
	public static Block compressedScrapCube;
	
	public static Block wireTangle;
	public static Block scrapFurnace;
	public static Block litScrapFurnace;
	
	public static Item scrapItems1;
	public static Item dusts;
	public static Item ingots;

	public ScrapWorldBlocks() {
		scrapCube = new BlockScrapCube().setBlockName("scrapCube").setHardness(3.0F).setResistance(5.0F);
		GameRegistry.registerBlock(scrapCube, "scrapCube");
		scrapSurfaceDebris = new BlockScrapSurfaceDebris().setBlockName("scrapDebris");
		GameRegistry.registerBlock(scrapSurfaceDebris, "scrapDebris");
		
		compressedScrapCube = new BlockCompressedScrapCube().setBlockName("compressedScrapCube");
		GameRegistry.registerBlock(compressedScrapCube, "compressedScrapCube");
		
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
		// also need ingots for some dusts
		ingots = new Ingots();
		GameRegistry.registerItem(ingots, "ingots");
		
		
		registerCraftingRecipes();
		registerFurnaceRecipes();
		registerInOreDictionary();
	}
	
	private void registerCraftingRecipes () {
		//make 9 'useless' scrap items into one compresedScrapCube
		GameRegistry.addRecipe(new ItemStack(compressedScrapCube, 1, 0), new Object[] { "sss", "sss", "sss", Character.valueOf('s'), new ItemStack(scrapItems1, 1, 11)});
	}
	
	private void registerFurnaceRecipes () {
		//TODO: tin and copper ingots
		//iron dust to iron ingot
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(dusts, 1, 2), new ItemStack(Items.iron_ingot, 1, 1), 0.0F);
		//gold dust to gold ingot
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(dusts, 1, 3), new ItemStack(Items.gold_ingot, 1, 1), 0.0F);
	}
	
	private void registerInOreDictionary () {
		// metals
		OreDictionary.registerOre("dustTin", new ItemStack(dusts, 1, 0));
		OreDictionary.registerOre("dustCopper", new ItemStack(dusts, 1, 1));
		OreDictionary.registerOre("dustIron", new ItemStack(dusts, 1, 2));
		OreDictionary.registerOre("dustGold", new ItemStack(dusts, 1, 3));
		// gems
		OreDictionary.registerOre("dustEmerald", new ItemStack(dusts, 1, 4));
		OreDictionary.registerOre("dustDiamond", new ItemStack(dusts, 1, 5));
		//materials
		OreDictionary.registerOre("dustGlass", new ItemStack(dusts, 1, 6));
		OreDictionary.registerOre("dustConcrete", new ItemStack(dusts, 1, 7));
		OreDictionary.registerOre("woodDust", new ItemStack(dusts, 1, 8));
		OreDictionary.registerOre("dustCharcoal", new ItemStack(dusts, 1, 9));
	}
}
