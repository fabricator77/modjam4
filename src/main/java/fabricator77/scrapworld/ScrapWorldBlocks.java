package fabricator77.scrapworld;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import fabricator77.scrapworld.blocks.*;
import fabricator77.scrapworld.client.RenderFridge;
import fabricator77.scrapworld.client.RenderMachine;
import fabricator77.scrapworld.client.TileEntityRenderFridge;
import fabricator77.scrapworld.client.TileEntityRenderMachine;
import fabricator77.scrapworld.items.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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
	
	public static Block concreteBlock;
	public static Block fridge;
	public static Block machine;
	public static Block generator;
	public static Block power;
	
	public static Item scrapItems1;
	public static Item dusts;
	public static Item ingots;
	public static Item rares;
	public static Item powerItems;
	public static Item hvPowerCell;

	public ScrapWorldBlocks() {
		scrapCube = new BlockScrapCube().setBlockName("scrapCube").setHardness(3.0F).setResistance(5.0F);
		GameRegistry.registerBlock(scrapCube, ItemBlock.class, "scrapCube");
		scrapSurfaceDebris = new BlockScrapSurfaceDebris().setBlockName("scrapDebris");
		GameRegistry.registerBlock(scrapSurfaceDebris, "scrapDebris");
		
		compressedScrapCube = new BlockCompressedScrapCube().setHardness(4.0F).setBlockName("compressedScrapCube");
		GameRegistry.registerBlock(compressedScrapCube, "compressedScrapCube");
		
		wireTangle = new BlockWireTangle().setBlockName("wireTangle").setHardness(4.0F);
		GameRegistry.registerBlock(wireTangle, "wireTangle");
		
		/**
		scrapFurnace = new BlockScrapFurnace(false).setHardness(3.5F).setBlockName("scrapFurnace");
		GameRegistry.registerBlock(scrapFurnace, "scrapFurnace");
		litScrapFurnace = new BlockScrapFurnace(false).setHardness(3.5F).setBlockName("litScrapFurnace");
		GameRegistry.registerBlock(litScrapFurnace, "litScrapFurnace");
		*/
		
		concreteBlock = new BlockConcrete().setHardness(4F).setBlockName("concreteBlock");
		GameRegistry.registerBlock(concreteBlock, "concreteBlock");
		
		fridge = new BlockFridge(0).setHardness(3.5F).setBlockName("fridge");
		GameRegistry.registerBlock(fridge, "fridge");
		// old system
		//ClientRegistry.registerTileEntity(TileEntityFridge.class, "tileEntityFridge", new TileEntityRenderFridge());
		
		GameRegistry.registerTileEntity(TileEntityFridge.class, "tileEntityFridge");
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFridge.class, new TileEntityRenderFridge());
		RenderingRegistry.registerBlockHandler(new RenderFridge());
		
		machine = new BlockMachine().setHardness(3.5F).setBlockName("machine");
		GameRegistry.registerBlock(machine, "machine");
		generator = new BlockGenerator().setHardness(3.5F).setBlockName("generator");
		GameRegistry.registerBlock(generator, "generator");
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMachine.class, new TileEntityRenderMachine());
		RenderingRegistry.registerBlockHandler(new RenderMachine());
		
		power = new BlockPower().setHardness(3.5F).setBlockName("power");
		GameRegistry.registerBlock(power, "power");
		//TODO: TileEntity
		
		
		scrapItems1 = new ScrapItems1();
		GameRegistry.registerItem(scrapItems1, "scrapItems1");
		
		dusts = new Dusts();
		GameRegistry.registerItem(dusts, "dusts");
		// also need ingots for some dusts
		ingots = new Ingots();
		GameRegistry.registerItem(ingots, "ingots");
		
		rares = new Rares();
		GameRegistry.registerItem(rares, "rares");
		
		powerItems = new PowerItems();
		GameRegistry.registerItem(powerItems, "powerItems");
		
		hvPowerCell = new HVPowerCell();
		GameRegistry.registerItem(hvPowerCell, "hvPowerCell");
		
		
		registerCraftingRecipes();
		registerFurnaceRecipes();
		registerInOreDictionary();
	}
	
	private void registerCraftingRecipes () {
		//make 9 'useless' scrap items into one compresedScrapCube
		GameRegistry.addRecipe(new ItemStack(compressedScrapCube, 1, 0), new Object[] { "sss", "sss", "sss", Character.valueOf('s'), new ItemStack(scrapItems1, 1, 11)});
	}
	
	private void registerFurnaceRecipes () {
		// tin dust to tin ingot
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(dusts, 1, 0), new ItemStack(ingots, 1, 0), 0.0F);
		// copper dust to copper ingot
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(dusts, 1, 1), new ItemStack(ingots, 1, 1), 0.0F);
		// iron dust to iron ingot
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(dusts, 1, 2), new ItemStack(Items.iron_ingot, 1, 1), 0.0F);
		// gold dust to gold ingot
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(dusts, 1, 3), new ItemStack(Items.gold_ingot, 1, 1), 0.0F);
		//TODO: recipes for diamond/emerald dust 
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

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityMachine.class, "tileEntityMachine");
		GameRegistry.registerTileEntity(TileEntityGenerator.class, "tileEntityGenerator");
	}
}
