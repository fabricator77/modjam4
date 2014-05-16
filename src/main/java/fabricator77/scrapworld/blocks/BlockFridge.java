package fabricator77.scrapworld.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFridge extends BlockChest {//extends Block {

	public BlockFridge(int state) {
		super(state);
	}

	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(ScrapWorld.modid+":fridge");
    }
	
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }

	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

	@Override
    public int getRenderType()
    {
        return 22;//TODO: put correct custom renderer here
    }
	
	@Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z - 1) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (world.getBlock(x, y, z + 1) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
        }
        else if (world.getBlock(x - 1, y, z) == this)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (world.getBlock(x + 1, y, z) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
        }
        else
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
    }
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        this.func_149954_e(world, x, y, z);
        Block block = world.getBlock(x, y, z - 1);
        Block block1 = world.getBlock(x, y, z + 1);
        Block block2 = world.getBlock(x - 1, y, z);
        Block block3 = world.getBlock(x + 1, y, z);

        if (block == this)
        {
            this.func_149954_e(world, x, y, z - 1);
        }

        if (block1 == this)
        {
            this.func_149954_e(world, x, y, z + 1);
        }

        if (block2 == this)
        {
            this.func_149954_e(world, x - 1, y, z);
        }

        if (block3 == this)
        {
            this.func_149954_e(world, x + 1, y, z);
        }
    }
}
