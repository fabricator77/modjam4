package fabricator77.scrapworld.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockMachine extends BlockContainer{
	
	public String machineName = "machine";
	
	@SideOnly(Side.CLIENT)
    protected IIcon topTexture;
    @SideOnly(Side.CLIENT)
    protected IIcon frontTexture;

	public BlockMachine() {
		super(Material.iron);
		this.setCreativeTab(ScrapWorld.creativeTab);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityMachine();
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
            TileEntityMachine tileentityMachine = (TileEntityMachine)world.getTileEntity(x, y, z);

            if (tileentityMachine != null)
            {
                // player.func_146102_a(tileentityMachine);
            	//TODO: GUI calls ?
            }

            return true;
        }
    }
	
	//TODO: check if this is needed for modded TEs
	@Override
	public int tickRate(World world)
    {
        return 4;
    }

	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        int k = metadata & 16;
        return side == k ? (this.frontTexture) : (k != 1 && k != 0 ? (side != 1 && side != 0 ? this.blockIcon : this.topTexture) : this.topTexture);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(ScrapWorld.modid+":"+machineName + "_side");
        this.topTexture = iconRegister.registerIcon(ScrapWorld.modid+":"+machineName + "_top");
        this.frontTexture = iconRegister.registerIcon(ScrapWorld.modid+":"+machineName + "_front");
    }
}
