package fabricator77.scrapworld.machines;

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
	public void getPower () {
		//TODO: is on mains power, or contains powerCell
		int power = ((BlockSolarPanel)this.blockType).getPowerLevel(this.worldObj, this.xCoord, this.yCoord+1, this.zCoord);
		storedPower = storedPower + power;
	}
}
