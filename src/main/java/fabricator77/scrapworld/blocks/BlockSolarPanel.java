package fabricator77.scrapworld.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockSolarPanel extends BlockGenerator{
	public static final String machineName = "solar_panel";

	
	// code from Minecraft BlockDayLightDetector
	@Override
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
