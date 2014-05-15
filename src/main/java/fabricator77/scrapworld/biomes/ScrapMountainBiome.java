package fabricator77.scrapworld.biomes;

import fabricator77.scrapworld.ScrapWorldBlocks;
import net.minecraft.world.biome.BiomeGenBase;

public class ScrapMountainBiome extends BiomeGenBase {

	public ScrapMountainBiome(int par1) {
		super(par1);
		this.topBlock = ScrapWorldBlocks.scrapCube;
	}

}
