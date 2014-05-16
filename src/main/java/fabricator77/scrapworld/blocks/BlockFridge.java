package fabricator77.scrapworld.blocks;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;

import java.util.Iterator;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
//import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFridge extends BlockChest {//extends Block {
	
	private final Random field_149955_b = new Random();

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
	
	@Override
	public void func_149954_e(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            Block block = world.getBlock(x, y, z - 1);
            Block block1 = world.getBlock(x, y, z + 1);
            Block block2 = world.getBlock(x - 1, y, z);
            Block block3 = world.getBlock(x + 1, y, z);
            boolean flag = true;
            int l;
            Block block4;
            int i1;
            Block block5;
            boolean flag1;
            byte b0;
            int j1;

            if (block != this && block1 != this)
            {
                if (block2 != this && block3 != this)
                {
                    b0 = 3;

                    if (block.func_149730_j() && !block1.func_149730_j())
                    {
                        b0 = 3;
                    }

                    if (block1.func_149730_j() && !block.func_149730_j())
                    {
                        b0 = 2;
                    }

                    if (block2.func_149730_j() && !block3.func_149730_j())
                    {
                        b0 = 5;
                    }

                    if (block3.func_149730_j() && !block2.func_149730_j())
                    {
                        b0 = 4;
                    }
                }
                else
                {
                    l = block2 == this ? x - 1 : x + 1;
                    block4 = world.getBlock(l, y, z - 1);
                    i1 = block2 == this ? x - 1 : x + 1;
                    block5 = world.getBlock(i1, y, z + 1);
                    b0 = 3;
                    flag1 = true;

                    if (block2 == this)
                    {
                        j1 = world.getBlockMetadata(x - 1, y, z);
                    }
                    else
                    {
                        j1 = world.getBlockMetadata(x + 1, y, z);
                    }

                    if (j1 == 2)
                    {
                        b0 = 2;
                    }

                    if ((block.func_149730_j() || block4.func_149730_j()) && !block1.func_149730_j() && !block5.func_149730_j())
                    {
                        b0 = 3;
                    }

                    if ((block1.func_149730_j() || block5.func_149730_j()) && !block.func_149730_j() && !block4.func_149730_j())
                    {
                        b0 = 2;
                    }
                }
            }
            else
            {
                l = block == this ? z - 1 : z + 1;
                block4 = world.getBlock(x - 1, y, l);
                i1 = block == this ? z - 1 : z + 1;
                block5 = world.getBlock(x + 1, y, i1);
                b0 = 5;
                flag1 = true;

                if (block == this)
                {
                    j1 = world.getBlockMetadata(x, y, z - 1);
                }
                else
                {
                    j1 = world.getBlockMetadata(x, y, z + 1);
                }

                if (j1 == 4)
                {
                    b0 = 4;
                }

                if ((block2.func_149730_j() || block4.func_149730_j()) && !block3.func_149730_j() && !block5.func_149730_j())
                {
                    b0 = 5;
                }

                if ((block3.func_149730_j() || block5.func_149730_j()) && !block2.func_149730_j() && !block4.func_149730_j())
                {
                    b0 = 4;
                }
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 3);
        }
    }
	
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
		if (world.getTileEntity(x, y, z) instanceof TileEntityFridge == false) {
			world.setBlockToAir(x, y, z);
		}
		TileEntityFridge tileentitychest = (TileEntityFridge)world.getTileEntity(x, y, z);

        if (tileentitychest != null)
        {
            for (int i1 = 0; i1 < tileentitychest.getSizeInventory(); ++i1)
            {
                ItemStack itemstack = tileentitychest.getStackInSlot(i1);

                if (itemstack != null)
                {
                    float f = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = this.field_149955_b.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
                    {
                        int j1 = this.field_149955_b.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize)
                        {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.field_149955_b.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.field_149955_b.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.field_149955_b.nextGaussian() * f3);

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }
                }
            }

            world.func_147453_f(x, y, z, block);
        }

        super.breakBlock(world, x, y, z, block, metadata);
    }
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            IInventory iinventory = this.func_149951_m(world, x, y, z);

            if (iinventory != null)
            {
                player.displayGUIChest(iinventory);
            }

            return true;
        }
    }
	
	 public TileEntity createNewTileEntity(World world, int metadata)
	 {
		 TileEntityFridge tileentitychest = new TileEntityFridge();
		 return tileentitychest;
	 }
	 
	    private static boolean kitty(World p_149953_0_, int p_149953_1_, int p_149953_2_, int p_149953_3_)
	    {
	        Iterator iterator = p_149953_0_.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().getAABB((double)p_149953_1_, (double)(p_149953_2_ + 1), (double)p_149953_3_, (double)(p_149953_1_ + 1), (double)(p_149953_2_ + 2), (double)(p_149953_3_ + 1))).iterator();
	        EntityOcelot entityocelot1;

	        do
	        {
	            if (!iterator.hasNext())
	            {
	                return false;
	            }

	            EntityOcelot entityocelot = (EntityOcelot)iterator.next();
	            entityocelot1 = (EntityOcelot)entityocelot;
	        }
	        while (!entityocelot1.isSitting());

	        return true;
	    }
	 
	 public IInventory func_149951_m(World world, int p_149951_2_, int p_149951_3_, int p_149951_4_)
	    {
	        Object object = (TileEntityChest)world.getTileEntity(p_149951_2_, p_149951_3_, p_149951_4_);

	        if (object == null)
	        {
	            return null;
	        }
	        else if (world.isSideSolid(p_149951_2_, p_149951_3_ + 1, p_149951_4_, DOWN))
	        {
	            return null;
	        }
	        else if (kitty(world, p_149951_2_, p_149951_3_, p_149951_4_))
	        {
	            return null;
	        }
	        else if (world.getBlock(p_149951_2_ - 1, p_149951_3_, p_149951_4_) == this && (world.isSideSolid(p_149951_2_ - 1, p_149951_3_ + 1, p_149951_4_, DOWN) || kitty(world, p_149951_2_ - 1, p_149951_3_, p_149951_4_)))
	        {
	            return null;
	        }
	        else if (world.getBlock(p_149951_2_ + 1, p_149951_3_, p_149951_4_) == this && (world.isSideSolid(p_149951_2_ + 1, p_149951_3_ + 1, p_149951_4_, DOWN) || kitty(world, p_149951_2_ + 1, p_149951_3_, p_149951_4_)))
	        {
	            return null;
	        }
	        else if (world.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ - 1) == this && (world.isSideSolid(p_149951_2_, p_149951_3_ + 1, p_149951_4_ - 1, DOWN) || kitty(world, p_149951_2_, p_149951_3_, p_149951_4_ - 1)))
	        {
	            return null;
	        }
	        else if (world.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ + 1) == this && (world.isSideSolid(p_149951_2_, p_149951_3_ + 1, p_149951_4_ + 1, DOWN) || kitty(world, p_149951_2_, p_149951_3_, p_149951_4_ + 1)))
	        {
	            return null;
	        }
	        else
	        {
	            if (world.getBlock(p_149951_2_ - 1, p_149951_3_, p_149951_4_) == this)
	            {
	                object = new InventoryLargeChest("container.fridgeDouble", (TileEntityChest)world.getTileEntity(p_149951_2_ - 1, p_149951_3_, p_149951_4_), (IInventory)object);
	            }

	            if (world.getBlock(p_149951_2_ + 1, p_149951_3_, p_149951_4_) == this)
	            {
	                object = new InventoryLargeChest("container.fridgeDouble", (IInventory)object, (TileEntityChest)world.getTileEntity(p_149951_2_ + 1, p_149951_3_, p_149951_4_));
	            }

	            if (world.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ - 1) == this)
	            {
	                object = new InventoryLargeChest("container.fridgeDouble", (TileEntityChest)world.getTileEntity(p_149951_2_, p_149951_3_, p_149951_4_ - 1), (IInventory)object);
	            }

	            if (world.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ + 1) == this)
	            {
	                object = new InventoryLargeChest("container.fridgeDouble", (IInventory)object, (TileEntityChest)world.getTileEntity(p_149951_2_, p_149951_3_, p_149951_4_ + 1));
	            }

	            return (IInventory)object;
	        }
	    }
}
