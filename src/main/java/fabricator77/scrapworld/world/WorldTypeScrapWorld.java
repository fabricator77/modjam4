package fabricator77.scrapworld.world;

import fabricator77.scrapworld.world.layer.GenLayerScrapBiome;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class WorldTypeScrapWorld extends WorldType {

	public WorldTypeScrapWorld() {
		super("scrapworld");
	}

	public float getCloudHeight()
    {
        return 192.0F;
    }
	
	//TODO: custom WorldChunkManager
	public WorldChunkManager getChunkManager(World world)
    {
        return new WorldChunkManagerScrap(world);
    }
	
	public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer)
    {
        GenLayer ret = new GenLayerScrapBiome(200L, parentLayer, this);
        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdge(1000L, ret);
        return ret;
    }
}
