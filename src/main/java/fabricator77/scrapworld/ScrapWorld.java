package fabricator77.scrapworld;

import net.minecraft.world.WorldType;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fabricator77.scrapworld.common.CommonProxy;
import fabricator77.scrapworld.world.WorldTypeScrapWorld;

@Mod(modid="ScrapWorld", name="Scrap World", version="1.0")
public class ScrapWorld {
	public static String modid="ScrapWorld";
	@Instance("ScarpWorld")
	public static ScrapWorld instance;
	
	@SidedProxy(clientSide="fabricator77.scrapworld.client.ClientProxy", serverSide="fabricator77.scrapworld.common.CommonProxy")
	public static CommonProxy proxy;
	
	public static WorldType scrapWorldType;
	public static Configuration config;
	public static ScrapWorldBlocks blocks;
	
	//TODO: shift to dedicated config file
	public static int scrapMountainID;
	public static int scrapPlainsID;
	
	//TODO: creative tab and 16x16 icon
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		this.config = new Configuration(event.getSuggestedConfigurationFile());
		this.config.load();
		
		
		this.config.save();
		
		blocks = new ScrapWorldBlocks();
	}
	@EventHandler
	public void init(FMLInitializationEvent event) {
		new RegisterBiomes();
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		scrapWorldType = new WorldTypeScrapWorld();
	}
}
