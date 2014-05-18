package fabricator77.scrapworld;

import fabricator77.scrapworld.biomes.*;
import net.minecraft.world.biome.BiomeGenBase;

public class RegisterBiomes {
	public static BiomeGenBase scrapMountain;
	public static BiomeGenBase scrapPlains;
	public static BiomeGenBase scrapRiver;
	public static BiomeGenBase scrapOcean; 
	
	public RegisterBiomes() {
		scrapPlains = new ScrapMountainBiome(ScrapWorld.scrapMountainID).setBiomeName("Scrap Mountain");
		scrapMountain = new ScrapPlainsBiome(ScrapWorld.scrapPlainsID).setBiomeName("Scrap Plains");
		scrapRiver = new ScrapRiverBiome(ScrapWorld.scrapRiverID).setBiomeName("Scrap River");
		scrapOcean = new ScrapOceanBiome(ScrapWorld.scrapOceanID).setBiomeName("Scrap Ocean");
	}

}
