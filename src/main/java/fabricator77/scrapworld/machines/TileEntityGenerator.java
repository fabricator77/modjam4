package fabricator77.scrapworld.machines;

import net.minecraft.world.World;

public class TileEntityGenerator extends TileEntityMachine{

	@Override
	public String getInventoryName() {
		return "container."+((IMachineBlock)this.blockType).getMachineName();
	}
	
	@Override
	public String getMachineName() {
		return ((IMachineBlock)this.blockType).getMachineName();
	}
	
	@Override
	public void getPower () {
		//TODO: is on mains power, or contains powerCell
		int power = ((BlockGenerator)this.blockType).getPowerLevel(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		storedPower = storedPower + power;
	}
	
	public int getPowerLevel(World world, int x, int y, int z) {
		return 0;
	}
	
}
