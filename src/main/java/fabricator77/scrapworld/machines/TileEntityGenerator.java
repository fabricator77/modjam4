package fabricator77.scrapworld.machines;

import cpw.mods.fml.common.FMLLog;
import fabricator77.scrapworld.ScrapWorldBlocks;
import net.minecraft.item.Item;
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
	
	@Override
    public void operateCycle() {
    	int count = 0;
    	for (int i = 0; i < this.inv.length; ++i)
        {
    		// this.inv[i] = getStackInSlot(i);
    		// setInventorySlotContents(i, this.inv[i]);
    		if (this.inv[i] == null) {}
    		else {
    			count++;
    			int damage = this.inv[i].getItemDamage();
    			int stackSize = this.inv[i].stackSize;//TODO: charge stacked cells evenly
    			Item item = this.inv[i].getItem();
    			//FMLLog.info("[ScrapWorld] Found "+item);
    			//Attempt to change power cells
    			// if (Item.getIdFromItem(item) == Item.getIdFromItem(ScrapWorldBlocks.hvPowerCell)) {
    			if (item.getUnlocalizedName().equals(ScrapWorldBlocks.hvPowerCell.getUnlocalizedName())  ) {
    				if (damage > 0) {
    					// stacked cells can require a lot of stored power to charge.
    					if (storedPower < stackSize) {
    						return; // try again next second
    					}
    					
    					int chargingRate = 256;
    					// take into account available power
    					if (storedPower < chargingRate) {
    						chargingRate = storedPower;
    					}
    					//alter charging rate if cells are stacked
    					if (chargingRate % stackSize > 0) {
    						chargingRate = chargingRate-(chargingRate % stackSize);
    					}
    					// divide charging rate over available cells
    					if (stackSize > 0) {
    						chargingRate = chargingRate / stackSize;
    					}
    					// finally drain the power actually used
    					storedPower = storedPower - (chargingRate * stackSize);
    					//FMLLog.info("[ScrapWorld] Charging "+damage);
    					this.inv[i].setItemDamage(damage - chargingRate);
    					
    					setInventorySlotContents(i, this.inv[i]);
    					// this.inv[i].getItem().notify();
    					return;
    				}
    			}
    			if (storedPower == 0) {
    				break;
    			}
    		}
        }
    	if (count > 0) {
    		//FMLLog.info("[ScrapWorld] TileEntityMachine.count="+count);
    	}

	}
}
