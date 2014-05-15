package fabricator77.scrapworld.world.layer;

import org.apache.logging.log4j.Level;

import fabricator77.scrapworld.RegisterBiomes;
import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerScrapBiome extends GenLayer {
	
	private BiomeGenBase[] biomes;

	public GenLayerScrapBiome(long par1, GenLayer parentLayer, WorldType worldType) {
		super(par1);
		this.parent = parentLayer;
		biomes = new BiomeGenBase[] {RegisterBiomes.scrapMountain, RegisterBiomes.scrapPlains};
	}

	@Override
	public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] aint = this.parent.getInts(par1, par2, par3, par4);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        for (int i1 = 0; i1 < par4; ++i1)
        {
            for (int j1 = 0; j1 < par3; ++j1)
            {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                int k1 = aint[j1 + i1 * par3];

                if (k1 == 1)
                {
                    aint1[j1 + i1 * par3] = this.biomes[this.nextInt(this.biomes.length)].biomeID;
                }
                else if (k1 == 2)
                {
                    aint1[j1 + i1 * par3] = this.biomes[this.nextInt(this.biomes.length)].biomeID;
                }
                else if (k1 == 3)
                {
                    aint1[j1 + i1 * par3] = this.biomes[this.nextInt(this.biomes.length)].biomeID;
                }
                else if (k1 == 4)
                {
                    aint1[j1 + i1 * par3] = this.biomes[this.nextInt(this.biomes.length)].biomeID;
                }
                else
                {
                	//TODO: replace ocean/unknown biomes with something
                    //aint1[j1 + i1 * par3] = BiomeGenBase.mushroomIsland.biomeID;
                    
                	aint1[j1 + i1 * par3] = this.biomes[this.nextInt(this.biomes.length)].biomeID;
                }
                
                ScrapWorld.logger.log(Level.INFO, "GenLayerScrapBiome.getInts="+aint1[j1 + i1 * par3]);
            }
        }

        return aint1;
    }
}
