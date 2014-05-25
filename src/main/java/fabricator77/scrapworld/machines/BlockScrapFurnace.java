package fabricator77.scrapworld.machines;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockScrapFurnace extends BlockMachine{
	
	@SideOnly(Side.CLIENT)
    protected IIcon topTexture;
    @SideOnly(Side.CLIENT)
    protected IIcon frontTexture;

	public BlockScrapFurnace() {
		super();
		this.setCreativeTab(ScrapWorld.creativeTab);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityScrapFurnace();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
		ItemStack heldItem = player.getCurrentEquippedItem();
		if (heldItem != null && heldItem.getItem() == Items.bucket) {
			//TODO: take/accept items and liquids
		}
        if (!world.isRemote)
        {
        	player.openGui(ScrapWorld.instance, 5, world, x, y, z);
        }
        return true;
    }
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack item)
    {
        int l = BlockPistonBase.determineOrientation(world, x, y, z, player);
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
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
        int k = metadata & 7;
        return side == k ? (this.frontTexture) : (k != 1 && k != 0 ? (side != 1 && side != 0 ? this.blockIcon : this.topTexture) : this.topTexture);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	int i = 0;
    	String machine = "machine";
        this.blockIcon = iconRegister.registerIcon(ScrapWorld.modid+":"+machine + "_side");
        this.topTexture = iconRegister.registerIcon(ScrapWorld.modid+":"+machine + "_top");
        this.frontTexture = iconRegister.registerIcon(ScrapWorld.modid+":"+getMachineName() + "_front");
    }

	@Override
	public String getMachineName() {
		return "scrap_furnace";
	}

	public int getPowerLevel(World worldObj, int xCoord, int yCoord, int zCoord) {
		return 0;
	}
}
