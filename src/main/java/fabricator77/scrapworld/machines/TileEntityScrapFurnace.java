package fabricator77.scrapworld.machines;

import cpw.mods.fml.common.FMLLog;
import fabricator77.scrapworld.ScrapWorldBlocks;
import net.minecraft.entity.player.EntityPlayer;
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

public class TileEntityScrapFurnace extends TileEntity implements IMachine, IInventory{
	
	private boolean ready = false;
	private boolean complete = false;
	private boolean powered = false;
	private ItemStack[] parts = new ItemStack[]{
			new ItemStack(ScrapWorldBlocks.powerItems, 1, 0),
			new ItemStack(ScrapWorldBlocks.powerItems, 1, 0),
			new ItemStack(ScrapWorldBlocks.powerItems, 1, 0)
	};
	private int numParts = 3;
	
	public int storedPower = 0;
	
	public ItemStack[] inv = new ItemStack[9 + 9 + 1];
	
	public int batterySlot = 18;
	
	@Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);

        if (tag.hasKey("ready"))
        {
            ready = tag.getBoolean("ready");
        }
        
        NBTTagList nbttaglist = tag.getTagList("Parts", 10);
        this.parts = new ItemStack[numParts];
        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.parts.length)
            {
                this.parts[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        
        nbttaglist = tag.getTagList("Inv", 10);
        this.inv = new ItemStack[inv.length];
        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.inv.length)
            {
                this.inv[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }
	
	@Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setBoolean("ready", this.ready);
        
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.parts.length; ++i)
        {
            if (this.parts[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.parts[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        tag.setTag("Parts", nbttaglist);
        
        nbttaglist = new NBTTagList();
        for (int i = 0; i < this.inv.length; ++i)
        {
            if (this.inv[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inv[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        tag.setTag("Inv", nbttaglist);
    }


	@Override
    public Packet getDescriptionPacket() {
    	NBTTagCompound tagCompound = new NBTTagCompound();
    	this.writeToNBT(tagCompound);
    	return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 2, tagCompound);
    }
     
    @Override
    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet) {
    	readFromNBT(packet.func_148857_g());
    }

    @Override
    public void updateEntity() {
    	if (this.worldObj == null || this.worldObj.isRemote || this.worldObj.getTotalWorldTime() % 20L != 0L)
        {
    		return;
        }
    	//FMLLog.info("[ScrapWorld] Ticking TileEntityMachine");
    	 //TODO: Entity updates
    	// includes block being placed/loaded ?
    	checkIfComplete();
    	// if (!complete) return;
    	getPower();
    	//FMLLog.info("[ScrapWorld] StoredPower "+storedPower);
    	if (storedPower > 0) operateCycle();
    	
    	//if (storedPower > 0) FMLLog.info("[ScrapWorld] StoredPower "+storedPower);
    	
    	this.markDirty();
    }
	
	
	@Override
	public boolean isMachineReady() {
		if (ready && complete) return true;
		return false;
	}

	@Override
	public boolean isMachineComplete() {
		return complete;
	}

	@Override
	public boolean isMachinePowered() {
		return powered;
	}
    
    // Actual code
	public void checkIfComplete () {
		int missingParts = 0;
		//TODO: specific machines need specific parts in specific slots
		for (int i=0; i<parts.length; i++) {
			if (parts[i].stackSize == 0) {
				missingParts++;
			}
		}
		if (missingParts == 0) {
			complete = true;
		}
		this.markDirty();
	}
	
	public void getPower () {
		//TODO: is on mains power ?

		int chargingRate = 256;
		if ((storedPower + chargingRate) > 32767) {
			// full don't need power
			return;
		}
		
		// see if Power Cell in slot
		if (this.inv[batterySlot] == null) {}
		else {
			int damage = this.inv[batterySlot].getItemDamage();
			int stackSize = this.inv[batterySlot].stackSize;
			Item item = this.inv[batterySlot].getItem();
			if (item instanceof IBattery) {	
				if (damage < 32767) {
					if (32767 - damage < chargingRate) {
						chargingRate = damage;
					}
					this.inv[batterySlot].setItemDamage(damage + chargingRate);
					storedPower = storedPower + chargingRate;
					
					setInventorySlotContents(batterySlot, this.inv[batterySlot]);
				}
			}
		}
	}

	
	public void onChunkUnload()
    {
		//TODO: save power status/network
    }

	// IInventory
	@Override
	public int getSizeInventory() {
		if (complete) {
			return inv.length;
		}
		return parts.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inv[slot];
	}

	//Definately problems in here somewhere
	//cannot take items out of machine
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize <= amount) {
				setInventorySlotContents(slot, null);
			}
			else {
				stack = stack.splitStack(amount);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item) {
		inv[slot] = item;

        if (item != null && item.stackSize > this.getInventoryStackLimit())
        {
        	item.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
	}
	
	@Override
	public String getInventoryName() {

		return "container."+this.getMachineName();
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		if (slot == batterySlot) {
			Item item = itemStack.getItem();
			if (item instanceof IBattery && itemStack.stackSize == 1) {
				return true;
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	public boolean putItemStackIntoOutput (ItemStack itemStack) {
		boolean partialTranfer = false;
		if (itemStack == null) return false;
		
		for (int i=9; i<18; i++) {
			if (this.inv[i] == null) {} // do nothing with empty slots for now
			else if (this.inv[i].stackSize == this.inv[i].getMaxStackSize()) {} // do nothing with full slots
			// ok now see if slot contains this item
			else if (this.inv[i].getUnlocalizedName().equals(itemStack.getUnlocalizedName())) {
				
				//TODO: code from broken version
				if (this.inv[i].stackSize + itemStack.stackSize > this.inv[i].getMaxStackSize()) {
					// too big to all fit in this stack.
					this.inv[i].stackSize = this.inv[i].getMaxStackSize();
					setInventorySlotContents(i, this.inv[i]);
					
					int overflow = (this.inv[i].stackSize + itemStack.stackSize - this.inv[i].getMaxStackSize());
					//TODO: still possible loose items this way
					// if all other output slots are full
					itemStack.stackSize = overflow;
					partialTranfer = true;
					FMLLog.info("[ScrapWorld] overflow");
				}
				else {
					this.inv[i].stackSize = this.inv[i].stackSize + itemStack.stackSize;
					setInventorySlotContents(i, this.inv[i]);
					FMLLog.info("[ScrapWorld] normal");
					return true;
				}
				
				//TODO: end of untested old code
			}
		}
		for (int i=9; i<18; i++) {
			if (this.inv[i] == null) {
				this.inv[i] = itemStack.copy();
				setInventorySlotContents(i, this.inv[i]);
				FMLLog.info("[ScrapWorld] empty");
				return true;
			}
		}
		return false;
	}

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
