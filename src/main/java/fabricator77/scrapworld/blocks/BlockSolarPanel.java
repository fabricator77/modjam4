package fabricator77.scrapworld.blocks;

import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockSolarPanel extends BlockMachine{
	public static final String machineName = "solar_panel";

	
	// code from Minecraft BlockDayLightDetector
	public int getLightLevel(World world, int x, int y, int z)
    {
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
}
