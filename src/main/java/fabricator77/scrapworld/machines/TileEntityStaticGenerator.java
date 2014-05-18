package fabricator77.scrapworld.machines;

import cpw.mods.fml.common.FMLLog;
import fabricator77.scrapworld.ScrapWorldBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TileEntityStaticGenerator extends TileEntityGenerator{
	
	public ItemStack[] parts = new ItemStack[]{
			new ItemStack(ScrapWorldBlocks.powerItems, 1, 0),
			new ItemStack(ScrapWorldBlocks.powerItems, 1, 0),
			new ItemStack(ScrapWorldBlocks.powerItems, 1, 0)
	};

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
		// this.worldObj.getWorldInfo().setThundering(true);
		//this.worldObj.thunderingStrength = 1.0F;
		// if (storedPower > 0) FMLLog.info("[ScrapWorld] thunderingStrength "+this.worldObj.thunderingStrength);
		
		//TODO: is on mains power, or contains powerCell
		int power = ((BlockStaticGenerator)this.blockType).getPowerLevel(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		storedPower = storedPower + power;
	}
	
	public int getPowerLevel(World world, int x, int y, int z) {
		return 0;
	}
}
