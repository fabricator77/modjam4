package fabricator77.scrapworld.machines;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class TileEntityScrapFurnace extends TileEntityScrapGrinder{

	//IMachine fields
		@Override
	    public void operateCycle() {
			
	    	// only first 9 slots are inputs
	    	for (int i = 0; i < 9; ++i)
	        {
	    		// this.inv[i] = getStackInSlot(i);
	    		// setInventorySlotContents(i, this.inv[i]);
	    		if (this.inv[i] == null) {}
	    		else {
	    			int damage = this.inv[i].getItemDamage();
	    			int stackSize = this.inv[i].stackSize;
	    			Item item = this.inv[i].getItem();
	    			//FMLLog.info("[ScrapWorld] Found "+item);
	    			
	    			//Check to see if this item burns
	    			ItemStack outputProduct = FurnaceRecipes.smelting().getSmeltingResult(this.inv[i]);
	    			if (outputProduct != null) {
	    				// reduce input items by 1
	    				if (stackSize > 1) {
	        				this.inv[i].stackSize--;
	        			}
	    				else {
	    					this.inv[i] = null;
	    				}
	    				setInventorySlotContents(i, this.inv[i]);
	    				// output result.
	    				//this.inv[i+9] = outputProduct;
						//setInventorySlotContents(i+9, this.inv[i+9]);
						
						putItemStackIntoOutput(outputProduct);
						return;
	    			}
	    			if (storedPower == 0) {
	    				break;
	    			}
	    		}
	        }
		}
		
		@Override
		public String getMachineName() {
			return "scrap_furnace";
		}
}
