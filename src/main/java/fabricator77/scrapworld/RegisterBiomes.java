package fabricator77.scrapworld;

import fabricator77.scrapworld.biomes.*;
import net.minecraft.world.biome.BiomeGenBase;

public class RegisterBiomes {
	public static BiomeGenBase scrapMountain;
	public static BiomeGenBase scrapPlains;
	public static BiomeGenBase scrapRiver;
	public static BiomeGenBase scrapOcean; 
	
	public RegisterBiomes() {
		scrapPlains = new ScrapPlainsBiome(ScrapWorld.scrapPlainsID).setBiomeName("Scrap Plains");
		scrapMountain = new ScrapMountainBiome(ScrapWorld.scrapMountainID).setBiomeName("Scrap Mountain");
		scrapRiver = new ScrapRiverBiome(ScrapWorld.scrapRiverID).setBiomeName("Scrap River");
		scrapOcean = new ScrapOceanBiome(ScrapWorld.scrapOceanID).setBiomeName("Scrap Ocean");
	}
}
