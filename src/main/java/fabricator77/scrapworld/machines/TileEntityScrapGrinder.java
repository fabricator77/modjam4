package fabricator77.scrapworld.machines;

import cpw.mods.fml.common.FMLLog;
import fabricator77.scrapworld.ScrapWorldBlocks;
import fabricator77.scrapworld.items.ScrapItems1;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
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

public class TileEntityScrapGrinder extends TileEntity implements IMachine, IInventory{
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
    	FMLLog.info("[ScrapWorld] StoredPower "+storedPower);
    	if (storedPower > 0) operateCycle();
    	
    	//if (storedPower > 0) FMLLog.info("[ScrapWorld] StoredPower "+storedPower);
    	
    	this.markDirty();
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
    			
    			// only take scrap as inputs
    			if (item.getUnlocalizedName().equals(ScrapWorldBlocks.scrapItems1)) {
    				
    			}
    			
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
    				this.inv[i+9] = outputProduct;
					setInventorySlotContents(i+9, this.inv[i+9]);
					return;
    			}
    			if (storedPower == 0) {
    				break;
    			}
    		}
        }
	}
	
	@Override
	public boolean isMachineReady() {
		if (ready && complete) return true;
		return false;
	}
	
	public ItemStack processScrap(ItemStack itemStack, int slot)
    {
		//if (itemStack.stackSize > 1) {
		//	return itemStack;
		//}
		
		String[] itemNames = ScrapItems1.itemNames;
		
		
        int metadata = MathHelper.clamp_int(itemStack.getItemDamage(), 0, itemNames.length-1);
        String scrapType = ScrapItems1.itemNames[metadata];
        
        if (scrapType == "unknown") {
    		if (this.worldObj.rand.nextInt(5) != 0) {
    			//nothing of value could be found in this
    			//String mesage = RandomMessages.scrapMessages(itemRand);
    			//player.addChatMessage(new ChatComponentTranslation(mesage));
    			// player.addChatMessage(new ChatComponentText(mesage));
    			
    			//convert into useless version, so people cannot re-try it will
    			addItemStackToInventory(new ItemStack(ScrapWorldBlocks.scrapItems1, 1, itemNames.length-1));
    			--itemStack.stackSize;
    			return itemStack;
    		}
    		// choose a random type to make this into
    		// scrapType = itemNames[1 + world.rand.nextInt(itemNames.length-1)];
    		//boolean added = player.inventory.addItemStackToInventory(new ItemStack(this, 1, 1 + world.rand.nextInt(itemNames.length-1)));
    		//--itemStack.stackSize;
			return itemStack;
    	}
    	
    	if (scrapType == "metallic") {
    		if (this.worldObj.rand.nextInt(25) == 0) {
    			addItemStackToInventory(new ItemStack(itemStack.getItem(), 1, itemNames.length-1));
    		}
    		// random choice of iron, tin, copper
    		else {
    			int i = this.worldObj.rand.nextInt(3);
    			ItemStack item = new ItemStack(ScrapWorldBlocks.dusts, 1, i);
    			addItemStackToInventory(item);
    			
    		}
    		--itemStack.stackSize;
    		return itemStack;
    	}
    	if (scrapType == "wire") {
    		// wire to make into machines/metal
    		// heating element
    		// could also be fibre cable (rare)
    		return itemStack;
    	}
    	if (scrapType == "circuit") {
    		// random choice of wires, blank PCB, copper scrap, silicon, redstone, broken PCB, silicon chip (rare), working PCB(rare)
    		// or nothing
    		ItemStack item = new ItemStack(ScrapWorldBlocks.components1Items, 1, 1);
    		addItemStackToInventory(item);
    		--itemStack.stackSize;
    		return itemStack;
    	}
    	if (scrapType == "plastic") {
    		// random choice of rubber, plastic, plastic component, silicon chip (rare)
    		ItemStack item = new ItemStack(ScrapWorldBlocks.components1Items, 1, 3);
    		addItemStackToInventory(item);
    		--itemStack.stackSize;
    		return itemStack;
    	}
    	if (scrapType == "glass") {
    		// random choice of glass fragments, sand, fibre strands
    		int i = this.worldObj.rand.nextInt(ScrapItems1.glassLoot.length);
    		ItemStack item = ScrapItems1.glassLoot[i];
    		addItemStackToInventory(item);
    		--itemStack.stackSize;
    		return itemStack;
    	}
    	if (scrapType == "concrete") {
    		// random choice of concrete, sand, gravel, cobblestone, various stone blocks/steps/slabs
    		int i = this.worldObj.rand.nextInt(ScrapItems1.concreteLoot.length);
    		ItemStack item = ScrapItems1.concreteLoot[i];
    		addItemStackToInventory(item);
    		--itemStack.stackSize;
    		return itemStack;
    	}
    	if (scrapType == "timber") {
    		// random choice of sticks, wooden plank/stairs/slabs, logs (rare), torches
    		int i = this.worldObj.rand.nextInt(ScrapItems1.timberLoot.length);
    		ItemStack item = ScrapItems1.timberLoot[i];
    		addItemStackToInventory(item);
    		--itemStack.stackSize;
    		return itemStack;
    	}
    	if (scrapType == "food") {
    		int i = this.worldObj.rand.nextInt(ScrapItems1.foodLoot.length);
    		ItemStack item = ScrapItems1.foodLoot[i];
    		addItemStackToInventory(item);
    		--itemStack.stackSize;
    		return itemStack;
    		
    	}
    	if (scrapType == "burnt") {
    		// mostly useless but can drop charcoal sometimes
    		ItemStack item = new ItemStack(Items.coal, 1, this.worldObj.rand.nextInt(2));
    		addItemStackToInventory(item);
			--itemStack.stackSize;
    		return itemStack;
    	}
    	if (scrapType == "rare") {
    		if (this.worldObj.rand.nextInt(10) == 0) {
    			addItemStackToInventory(new ItemStack(itemStack.getItem(), 1, itemNames.length-1));
    		}
    		// random choice of diamonds, gold, emeralds, and the like
    		else {
    			int i = this.worldObj.rand.nextInt(ScrapItems1.rareLoot.length);
    			ItemStack item = ScrapItems1.rareLoot[i];
    			addItemStackToInventory(item);
    		}
    		--itemStack.stackSize;
    		return itemStack;
    	}
    	
    	return itemStack;
    }
	
	//TODO: proper scan of output stack for matching stacks, and if none found, for empty slots.
	private boolean addItemStackToInventory(ItemStack itemStack) {
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

	@Override
	public String getMachineName() {
		return "scrap_grinder";
	}
	
	//
	public boolean putItemStackIntoOutput (ItemStack itemStack) {
		for (int i=8; i<17; i++) {
			if (ItemStack.areItemStacksEqual(this.inv[i], itemStack)) {
				if (this.inv[i].stackSize + itemStack.stackSize > this.inv[i].getMaxStackSize()) {
					// too big to all fit in this stack.
					
				}
				return true;
			}
		}
		return false;
	}
}
