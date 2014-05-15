package fabricator77.scrapworld.biomes;

import fabricator77.scrapworld.ScrapWorldBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class ScrapPlainsBiome extends BiomeGenBase {

	public ScrapPlainsBiome(int par1) {
		super(par1);
		this.topBlock = ScrapWorldBlocks.scrapSurfaceDebris;
		
		this.spawnableCreatureList.clear();
	}
}
