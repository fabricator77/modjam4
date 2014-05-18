package fabricator77.scrapworld.blocks;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class TileEntitySolarPanel extends TileEntityMachine{

	@Override
	public String getInventoryName() {
		return "container."+BlockSolarPanel.machineName;
	}
	
	@Override
	public String getMachineName() {
		return ((IMachineBlock)this.blockType).getMachineName();
	}
	
	@Override
    public void updateEntity() {
    	if (this.worldObj == null || !this.worldObj.isRemote || this.worldObj.getTotalWorldTime() % 20L != 0L)
        {
    		return;
        }
    	//FMLLog.info("[ScrapWorld] Ticking TileEntityMachine");
    	 //TODO: Entity updates
    	// includes block being placed/loaded ?
    	checkIfComplete();
    	// if (!complete) return;
    	getPower();
    	operateCycle();
    	
    	if (storedPower > 0) FMLLog.info("[ScrapWorld] StoredPower "+storedPower);
    	
    	this.markDirty();
    }
	
	@Override
	public void getPower () {
		//TODO: is on mains power, or contains powerCell
		//int power = ((BlockSolarPanel)this.blockType).getPowerLevel(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		int power = this.getPowerLevel(this.worldObj, this.xCoord, this.yCoord+1, this.zCoord);
		storedPower = storedPower + power;
	}
	
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
            FMLLog.info("[ScrapWorld] lightLevel "+lightLevel);
            return lightLevel;
        }
        return 0;
    }
}
