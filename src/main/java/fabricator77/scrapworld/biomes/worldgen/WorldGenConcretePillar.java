package fabricator77.scrapworld.biomes.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenConcretePillar extends WorldGenerator
{
    private Block block;
    private int metadata;

    public WorldGenConcretePillar(Block block, int metadata)
    {
        this.block = block;
        this.metadata = metadata;
    }

    @Override
    public boolean generate(World world, Random par2Random, int par3, int par4, int par5)
    {
        Block block;

        do
        {
            block = world.getBlock(par3, par4, par5);
            if (block.isAir(world, par3, par4, par5))
            {
                break;
            }
            --par4;
        } while (par4 > 0);

        for (int l = 60; l < 70; ++l)
        {
            int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int j1 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (world.isAirBlock(i1, j1, k1) && world.isAirBlock(i1, j1+1, k1) && world.isAirBlock(i1, j1+2, k1) && world.getBlock(i1, j1-1, k1).isBlockNormalCube())
            {
                world.setBlock(i1, j1, k1, this.block, this.metadata, 2);
                world.setBlock(i1, j1+1, k1, this.block, this.metadata, 2);
                world.setBlock(i1, j1+2, k1, this.block, this.metadata, 2);
            }
        }

        return true;
    }
}
