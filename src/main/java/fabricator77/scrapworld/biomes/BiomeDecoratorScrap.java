package fabricator77.scrapworld.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenWaterlily;

public class BiomeDecoratorScrap extends BiomeDecorator{
	
	public BiomeDecoratorScrap()
    {
        this.sandGen = new WorldGenSand(Blocks.sand, 7); // gen only in grass/dirt 
        this.gravelAsSandGen = new WorldGenSand(Blocks.gravel, 6); // gen only in grass/dirt 
        this.dirtGen = new WorldGenMinable(Blocks.dirt, 32); // can replace any single block fed as argument
        this.gravelGen = new WorldGenMinable(Blocks.gravel, 32); // can replace any single block fed as argument
        this.coalGen = new WorldGenMinable(Blocks.coal_ore, 16); // can replace any single block fed as argument
        this.ironGen = new WorldGenMinable(Blocks.iron_ore, 8); // can replace any single block fed as argument
        this.goldGen = new WorldGenMinable(Blocks.gold_ore, 8); // can replace any single block fed as argument
        this.redstoneGen = new WorldGenMinable(Blocks.redstone_ore, 7); // can replace any single block fed as argument
        this.diamondGen = new WorldGenMinable(Blocks.diamond_ore, 7); // can replace any single block fed as argument
        this.lapisGen = new WorldGenMinable(Blocks.lapis_ore, 6); // can replace any single block fed as argument
        //this.yellowFlowerGen = new WorldGenFlowers(Blocks.yellow_flower);
        //this.mushroomBrownGen = new WorldGenFlowers(Blocks.brown_mushroom);
        //this.mushroomRedGen = new WorldGenFlowers(Blocks.red_mushroom);
        //this.bigMushroomGen = new WorldGenBigMushroom();
        //this.reedGen = new WorldGenReed();
        //this.cactusGen = new WorldGenCactus();
        //this.waterlilyGen = new WorldGenWaterlily();
        //this.flowersPerChunk = 0;
        //this.grassPerChunk = 0;
        
        this.sandPerChunk = 1;
        this.sandPerChunk2 = 3;
        this.clayPerChunk = 1;
        this.generateLakes = true;
    }

}
