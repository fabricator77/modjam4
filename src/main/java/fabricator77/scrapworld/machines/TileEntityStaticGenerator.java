package fabricator77.scrapworld.machines;

import net.minecraft.world.World;

public class TileEntityStaticGenerator extends TileEntityGenerator{

	@Override
	public String getInventoryName() {
		return "container."+BlockStaticGenerator.machineName;
	}
	
	@Override
	public String getMachineName() {
		return ((IMachineBlock)this.blockType).getMachineName();
	}
	
	@Override
	public void getPower () {
		this.worldObj.getWorldInfo().setThundering(true);
		//this.worldObj.thunderingStrength = 1.0F;
		
		//TODO: is on mains power, or contains powerCell
		int power = ((BlockStaticGenerator)this.blockType).getPowerLevel(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		storedPower = storedPower + power;
	}
	
	public int getPowerLevel(World world, int x, int y, int z) {
		return 0;
	}
}
