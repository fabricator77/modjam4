package fabricator77.scrapworld;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import fabricator77.scrapworld.client.gui.GuiHandler;
import fabricator77.scrapworld.client.gui.GuiMain;
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
	
	public static Logger logger = (Logger)FMLCommonHandler.instance().getFMLLogger();
	
	//TODO: shift to dedicated config file
	public static int scrapPlainsID = 100;
	public static int scrapMountainID = 101;
	
	public static CreativeTabs creativeTab = new CreativeTabScrapWorld(CreativeTabs.getNextID(), ScrapWorld.modid);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		this.config = new Configuration(event.getSuggestedConfigurationFile());
		this.config.load();
		
		scrapPlainsID = this.config.get("biomeIDs", "scrapPlainsID", 100).getInt();
		scrapMountainID = this.config.get("biomeIDs", "scrapMountainID", 101).getInt();
		
		this.config.save();
		
		proxy.initRendering();
		
		blocks = new ScrapWorldBlocks();
	}
	@EventHandler
	public void init(FMLInitializationEvent event) {
		new RegisterBiomes();
		// ScrapWorld.logger.log(Level.INFO, "ScrapWorld");
		NetworkRegistry.INSTANCE.registerGuiHandler((Object)GuiMain.instance, new GuiHandler());
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		scrapWorldType = new WorldTypeScrapWorld();
	}
}
