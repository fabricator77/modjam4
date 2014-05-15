package fabricator77.scrapworld;

import fabricator77.scrapworld.biomes.ScrapMountainBiome;
import fabricator77.scrapworld.biomes.ScrapPlainsBiome;
import net.minecraft.world.biome.BiomeGenBase;

public class RegisterBiomes {
	public static BiomeGenBase scrapMountain;
	public static BiomeGenBase scrapPlains; 
	
	public RegisterBiomes() {
		scrapPlains = new ScrapMountainBiome(ScrapWorld.scrapMountainID).setBiomeName("Scrap Mountain");
		scrapMountain = new ScrapPlainsBiome(ScrapWorld.scrapPlainsID).setBiomeName("Scrap Plains");
	}

}
