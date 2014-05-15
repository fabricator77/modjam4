package fabricator77.scrapworld.biomes;

import java.util.Arrays;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fabricator77.scrapworld.ScrapWorldBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public class ScrapMountainBiome extends BiomeGenBase {
	
	private NoiseGeneratorPerlin field_150623_aE;
    private NoiseGeneratorPerlin field_150624_aF;
    private NoiseGeneratorPerlin field_150625_aG;
    
    private long worldSeed; // seed
    private byte[] field_150621_aC;
    private boolean field_150626_aH;
    private boolean field_150620_aI;
    
    private static final BiomeGenBase.Height height = new BiomeGenBase.Height(1.0F, 0.5F);

	public ScrapMountainBiome(int par1) {
		super(par1);
		this.topBlock = ScrapWorldBlocks.scrapSurfaceDebris;
		this.field_150604_aj = 0; // topBlock metadata
		this.setHeight(height);
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityCreeper.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 10, 1, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 5, 1, 1));
	}

	public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
    }
	
	// Adapted from BiomeGenMesa custom world surfacegen
	public void genTerrainBlocks(World world, Random random, Block[] blockArray, byte[] metadataArray, int x, int z, double var7)
	{
		if (this.field_150621_aC == null || this.worldSeed != world.getSeed())
        {
            this.createSurface(world.getSeed());
        }

        if (this.field_150623_aE == null || this.field_150624_aF == null || this.worldSeed != world.getSeed())
        {
            Random random1 = new Random(this.worldSeed);
            this.field_150623_aE = new NoiseGeneratorPerlin(random1, 4);
            this.field_150624_aF = new NoiseGeneratorPerlin(random1, 1);
        }

        this.worldSeed = world.getSeed();
        double surfaceHeight = 0.0D;
        int k;
        int l;

        if (this.field_150626_aH)
        {
            k = (x & -16) + (z & 15);
            l = (z & -16) + (x & 15);
            double d1 = Math.min(Math.abs(var7), this.field_150623_aE.func_151601_a((double)k * 0.25D, (double)l * 0.25D));

            if (d1 > 0.0D)
            {
                double d2 = 0.001953125D;
                double d3 = Math.abs(this.field_150624_aF.func_151601_a((double)k * d2, (double)l * d2));
                surfaceHeight = d1 * d1 * 2.5D;
                double d4 = Math.ceil(d3 * 50.0D) + 14.0D;

                if (surfaceHeight > d4)
                {
                    surfaceHeight = d4;
                }

                surfaceHeight += 64.0D;
            }
        }

        k = x & 15;
        l = z & 15;
        int k1 = blockArray.length / 256;
        
        // l1 is height (y)
        for (int l1 = 255; l1 >= 0; --l1)
        {
            int i2 = (l * 16 + k) * k1 + l1;
            if ((blockArray[i2] == null || blockArray[i2].getMaterial() == Material.air) && l1 < (int)surfaceHeight)
            {
                blockArray[i2] = Blocks.stone;
            }

            if (l1 <= 0 + random.nextInt(5))
            {
                blockArray[i2] = Blocks.bedrock;
            }
            else
            {
            	if (l1 > 67) { // scrap mountains
                	Block block1 = blockArray[i2];

                	if (block1 != null && block1.getMaterial() != Material.air)
                	{
                		if (block1 == Blocks.stone)
                		{
                    	    // custom mountain
                			block1 = ScrapWorldBlocks.scrapCube;
                			blockArray[i2] = block1;
                		}
                    }
                }
            	else if (l1>64) {
            		Block block1 = blockArray[i2];
            		if (block1 != null && block1.getMaterial() != Material.air)
                	{
                		if (block1 == Blocks.stone)
                		{
                    	    // needs to check air is above
                			block1 = ScrapWorldBlocks.scrapCube;
                			blockArray[i2] = block1;
                			
                			int i3 = (l * 16 + k) * k1 + l1 + 1;
                			Block block2 = blockArray[i3];
                			if (block2 == null || block2.getMaterial() == Material.air) {
                				block2 = this.topBlock;
                				blockArray[i3] = block2;
                			}
                		}
                    }
            	}
            }
        }
 }
 
    public void createSurface(long p_150619_1_)
    {
        this.field_150621_aC = new byte[64];
        Arrays.fill(this.field_150621_aC, (byte)16);
        Random random = new Random(p_150619_1_);
        this.field_150625_aG = new NoiseGeneratorPerlin(random, 1);
        int j;

        for (j = 0; j < 64; ++j)
        {
            j += random.nextInt(5) + 1;

            if (j < 64)
            {
                this.field_150621_aC[j] = 1;
            }
        }

        j = random.nextInt(4) + 2;
        int k;
        int l;
        int i1;
        int j1;

        for (k = 0; k < j; ++k)
        {
            l = random.nextInt(3) + 1;
            i1 = random.nextInt(64);

            for (j1 = 0; i1 + j1 < 64 && j1 < l; ++j1)
            {
                this.field_150621_aC[i1 + j1] = 4;
            }
        }

        k = random.nextInt(4) + 2;
        int k1;

        for (l = 0; l < k; ++l)
        {
            i1 = random.nextInt(3) + 2;
            j1 = random.nextInt(64);

            for (k1 = 0; j1 + k1 < 64 && k1 < i1; ++k1)
            {
                this.field_150621_aC[j1 + k1] = 12;
            }
        }

        l = random.nextInt(4) + 2;

        for (i1 = 0; i1 < l; ++i1)
        {
            j1 = random.nextInt(3) + 1;
            k1 = random.nextInt(64);

            for (int l1 = 0; k1 + l1 < 64 && l1 < j1; ++l1)
            {
                this.field_150621_aC[k1 + l1] = 14;
            }
        }

        i1 = random.nextInt(3) + 3;
        j1 = 0;

        for (k1 = 0; k1 < i1; ++k1)
        {
            byte b0 = 1;
            j1 += random.nextInt(16) + 4;

            for (int i2 = 0; j1 + i2 < 64 && i2 < b0; ++i2)
            {
                this.field_150621_aC[j1 + i2] = 0;

                if (j1 + i2 > 1 && random.nextBoolean())
                {
                    this.field_150621_aC[j1 + i2 - 1] = 8;
                }

                if (j1 + i2 < 63 && random.nextBoolean())
                {
                    this.field_150621_aC[j1 + i2 + 1] = 8;
                }
            }
        }
	}
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_)
	{
		// TODO maybe brown instead of black
		return 0;
	}
}
