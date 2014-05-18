package fabricator77.scrapworld.machines;

import cpw.mods.fml.common.FMLLog;
import fabricator77.scrapworld.ScrapWorldBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class TileEntitySolarPanel extends TileEntityGenerator{
	
	public ItemStack[] parts = new ItemStack[]{
			new ItemStack(ScrapWorldBlocks.powerItems, 1, 0),
			new ItemStack(ScrapWorldBlocks.powerItems, 1, 0),
			new ItemStack(ScrapWorldBlocks.powerItems, 1, 0)
	};

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
		int power = ((BlockSolarPanel)this.blockType).getPowerLevel(this.worldObj, this.xCoord, this.yCoord+1, this.zCoord) * 10;
		storedPower = storedPower + power;
	}
}
