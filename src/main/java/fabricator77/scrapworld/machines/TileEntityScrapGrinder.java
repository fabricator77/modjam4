package fabricator77.scrapworld.machines;

import cpw.mods.fml.common.FMLLog;
import fabricator77.scrapworld.ScrapWorldBlocks;
import fabricator77.scrapworld.items.ScrapItems1;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityScrapGrinder extends TileEntityScrapFurnace {
    
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
    			
    			// only take scrap as inputs
    			if (item.getUnlocalizedName().equals(ScrapWorldBlocks.scrapItems1)) {
    				
    			}
    			
    			//Check to see if this item burns
    			ItemStack outputProduct = processScrap(this.inv[i].copy());
    			if (outputProduct != null) {
    				
    				FMLLog.info("[ScrapWorld] outputProduct="+outputProduct);
    				
    				//setInventorySlotContents(i, this.inv[i]);
    				// output result.
    				//this.inv[i+9] = outputProduct;
					//setInventorySlotContents(i+9, this.inv[i+9]);
					
					boolean succeed = putItemStackIntoOutput(outputProduct);
					if (succeed) {
						// reduce input items by 1
	    				if (stackSize > 1) {
	        				this.inv[i].stackSize--;
	        			}
	    				else {
	    					this.inv[i] = null;
	    				}
	    				setInventorySlotContents(i, this.inv[i]);
					}
					return;
    			}
    			if (storedPower == 0) {
    				break;
    			}
    		}
        }
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack item) {
		super.setInventorySlotContents(slot, item);
	}
	
	public ItemStack processScrap(ItemStack itemStack)
    {
		if (itemStack == null) {
			return null;
		}
		//if (itemStack.stackSize > 1) {
		//	return itemStack;
		//}
		
		String[] itemNames = ScrapItems1.itemNames;
		
		
        int metadata = MathHelper.clamp_int(itemStack.getItemDamage(), 0, itemNames.length-1);
        String scrapType = ScrapItems1.itemNames[metadata];
        
        if (scrapType == "unknown") {
    		if (this.worldObj.rand.nextInt(5) != 0) {
    			//nothing of value could be found in this
    			//convert into useless version, so people cannot re-try it will
    			return new ItemStack(ScrapWorldBlocks.scrapItems1, 1, itemNames.length-1);
    		}
    		// choose a random type to make this into
    		scrapType = itemNames[1 + this.worldObj.rand.nextInt(itemNames.length-1)];
    	}
    	
    	if (scrapType == "metallic") {
    		if (this.worldObj.rand.nextInt(25) == 0) {
    			return new ItemStack(ScrapWorldBlocks.scrapItems1, 1, itemNames.length-1);
    		}
    		// random choice of iron, tin, copper
    		else {
    			int i = this.worldObj.rand.nextInt(3);
    			return new ItemStack(ScrapWorldBlocks.dusts, 1, i);
    			
    		}
    	}
    	if (scrapType == "wire") {
    		// wire to make into machines/metal
    		// heating element
    		// could also be fibre cable (rare)
    		return itemStack.copy();
    	}
    	if (scrapType == "circuit") {
    		// random choice of wires, blank PCB, copper scrap, silicon, redstone, broken PCB, silicon chip (rare), working PCB(rare)
    		// or nothing
    		return new ItemStack(ScrapWorldBlocks.components1Items, 1, 1);
    	}
    	if (scrapType == "plastic") {
    		// random choice of rubber, plastic, plastic component, silicon chip (rare)
    		return new ItemStack(ScrapWorldBlocks.components1Items, 1, 3);
    	}
    	if (scrapType == "glass") {
    		// random choice of glass fragments, sand, fibre strands
    		int i = this.worldObj.rand.nextInt(ScrapItems1.glassLoot.length);
    		return ScrapItems1.glassLoot[i];
    	}
    	if (scrapType == "concrete") {
    		// random choice of concrete, sand, gravel, cobblestone, various stone blocks/steps/slabs
    		int i = this.worldObj.rand.nextInt(ScrapItems1.concreteLoot.length);
    		return ScrapItems1.concreteLoot[i];
    	}
    	if (scrapType == "timber") {
    		// random choice of sticks, wooden plank/stairs/slabs, logs (rare), torches
    		int i = this.worldObj.rand.nextInt(ScrapItems1.timberLoot.length);
    		return ScrapItems1.timberLoot[i];
    	}
    	if (scrapType == "food") {
    		int i = this.worldObj.rand.nextInt(ScrapItems1.foodLoot.length);
    		return ScrapItems1.foodLoot[i];
    		
    	}
    	if (scrapType == "burnt") {
    		// mostly useless but can drop charcoal sometimes
    		return new ItemStack(Items.coal, 1, this.worldObj.rand.nextInt(2));
    	}
    	if (scrapType == "rare") {
    		if (this.worldObj.rand.nextInt(10) == 0) {
    			return new ItemStack(itemStack.getItem(), 1, itemNames.length-1);
    		}
    		// random choice of diamonds, gold, emeralds, and the like
    		else {
    			int i = this.worldObj.rand.nextInt(ScrapItems1.rareLoot.length);
    			return ScrapItems1.rareLoot[i];
    		}
    	}
    	
    	// anything this machine cannot process, goes to output
    	return itemStack.copy();
    }
	


	@Override
	public String getMachineName() {
		return "scrap_grinder";
	}
}
