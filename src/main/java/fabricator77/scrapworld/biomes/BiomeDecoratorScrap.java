package fabricator77.scrapworld.biomes;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LAKE;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND_PASS2;

import java.util.Random;

import fabricator77.scrapworld.ScrapWorldBlocks;
import fabricator77.scrapworld.biomes.worldgen.WorldGenConcretePillar;
import fabricator77.scrapworld.biomes.worldgen.WorldGenSurfaceItems;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeDecoratorScrap extends BiomeDecorator{
	
	private int wireTanglePerChunk;
	private int concretePillarPerChunk;

	public BiomeDecoratorScrap()
    {
        this.sandGen = new WorldGenSand(Blocks.sand, 7); // gen only in grass/dirt 
        this.gravelAsSandGen = new WorldGenSand(Blocks.gravel, 6); // gen only in grass/dirt 
        
        this.dirtGen = new WorldGenMinable(Blocks.dirt, 32, ScrapWorldBlocks.scrapCube);
        this.gravelGen = new WorldGenMinable(Blocks.gravel, 32, ScrapWorldBlocks.scrapCube);
        this.coalGen = new WorldGenMinable(Blocks.coal_ore, 16, ScrapWorldBlocks.scrapCube);
        this.ironGen = new WorldGenMinable(Blocks.iron_ore, 8, ScrapWorldBlocks.scrapCube);
        this.goldGen = new WorldGenMinable(Blocks.gold_ore, 8, ScrapWorldBlocks.scrapCube);
        this.redstoneGen = new WorldGenMinable(Blocks.redstone_ore, 7, ScrapWorldBlocks.scrapCube);
        this.diamondGen = new WorldGenMinable(Blocks.diamond_ore, 7, ScrapWorldBlocks.scrapCube);
        this.lapisGen = new WorldGenMinable(Blocks.lapis_ore, 6, ScrapWorldBlocks.scrapCube);
        //this.yellowFlowerGen = new WorldGenFlowers(Blocks.yellow_flower);
        //this.mushroomBrownGen = new WorldGenFlowers(Blocks.brown_mushroom);
        //this.mushroomRedGen = new WorldGenFlowers(Blocks.red_mushroom);
        //this.bigMushroomGen = new WorldGenBigMushroom();
        //this.reedGen = new WorldGenReed();
        //this.cactusGen = new WorldGenCactus();
        //this.waterlilyGen = new WorldGenWaterlily();
        //this.flowersPerChunk = 0;
        //this.grassPerChunk = 1;
        
        this.sandPerChunk = 1;
        this.sandPerChunk2 = 3;
        this.clayPerChunk = 1;
        this.generateLakes = true;
        // added
        this.wireTanglePerChunk = 1;
        
        this.concretePillarPerChunk = 1;
    }

	/**
	@Override
	public void decorateChunk(World world, Random random, BiomeGenBase biome, int x, int z)
    {
        if (this.currentWorld != null)
        {
            throw new RuntimeException("Already decorating!!");
        }
        else
        {
            this.currentWorld = world;
            this.randomGenerator = random;
            this.chunk_X = x;
            this.chunk_Z = z;
            this.genDecorations(biome);
            this.currentWorld = null;
            this.randomGenerator = null;
        }
    }
    */
	
	@Override
	protected void genDecorations(BiomeGenBase biome)
    {
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
        this.generateOres();
        int i;
        int j;
        int k;

        boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SAND);
        for (i = 0; doGen && i < this.sandPerChunk2; ++i)
        {
            j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.sandGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, CLAY);
        for (i = 0; doGen && i < this.clayPerChunk; ++i)
        {
            j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.clayGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SAND_PASS2);
        for (i = 0; doGen && i < this.sandPerChunk; ++i)
        {
            j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.gravelAsSandGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
        }
        
        int l, i1;
        
        for (j = 0; j < this.wireTanglePerChunk; ++j)
        {
            k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            i1 = this.randomGenerator.nextInt(this.currentWorld.getHeightValue(k, l) * 2);
            WorldGenerator worldgenerator = new WorldGenSurfaceItems(ScrapWorldBlocks.wireTangle, 1);
            worldgenerator.generate(this.currentWorld, this.randomGenerator, k, i1, l);
        }
        
        for (j = 0; j < this.concretePillarPerChunk; ++j)
        {
        	if (this.randomGenerator.nextInt(5) == 0) break;
        	
            k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            i1 = this.randomGenerator.nextInt(this.currentWorld.getHeightValue(k, l) * 2);
            WorldGenerator worldgenerator = new WorldGenConcretePillar(ScrapWorldBlocks.concreteBlock, 1);
            worldgenerator.generate(this.currentWorld, this.randomGenerator, k, i1, l);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, LAKE);
        if (doGen && this.generateLakes)
        {
            for (j = 0; j < 50; ++j)
            {
                k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                l = this.randomGenerator.nextInt(this.randomGenerator.nextInt(248) + 8);
                i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                (new WorldGenLiquids(Blocks.flowing_water)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
            }

            for (j = 0; j < 20; ++j)
            {
                k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                l = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(240) + 8) + 8);
                i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                (new WorldGenLiquids(Blocks.flowing_lava)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
            }
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
    }
}
