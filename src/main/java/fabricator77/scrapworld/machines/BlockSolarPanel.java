package fabricator77.scrapworld.machines;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockSolarPanel extends BlockGenerator{
	public static final String machineName = "solar_panel";
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntitySolarPanel();
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack item)
    {
        int l = BlockPistonBase.determineOrientation(world, x, y, z, player);
        if (1 < 2) l = 2;
        FMLLog.info("[ScrapWorld] direction="+l);
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
    }

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (!world.isRemote) // leaving this here, breaks moving items in/out of inv
        {
        	player.openGui(ScrapWorld.instance, 3, world, x, y, z);
        }
        return true;
    }
	
	// code from Minecraft BlockDayLightDetector
	public int getPowerLevel(World world, int x, int y, int z)
    {
		
		// detect light level (day light)
        if (!world.provider.hasNoSky)
        {
            int l = world.getBlockMetadata(x, y, z);
            int lightLevel = world.getSavedLightValue(EnumSkyBlock.Sky, x, y, z) - world.skylightSubtracted;
            float f = world.getCelestialAngleRadians(1.0F);

            if (f < (float)Math.PI)
            {
                f += (0.0F - f) * 0.2F;
            }
            else
            {
                f += (((float)Math.PI * 2F) - f) * 0.2F;
            }

            lightLevel = Math.round((float)lightLevel * MathHelper.cos(f));

            if (lightLevel < 0)
            {
                lightLevel = 0;
            }

            if (lightLevel > 15)
            {
                lightLevel = 15;
            }
            return lightLevel;
        }
        return 0;
    }
	
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	int i = 0;
        this.blockIcon = iconRegister.registerIcon(ScrapWorld.modid+":"+machineName + "_side");
        this.topTexture = iconRegister.registerIcon(ScrapWorld.modid+":"+machineName + "_top");
        this.frontTexture = this.blockIcon;
    }
}
