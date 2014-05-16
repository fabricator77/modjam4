package fabricator77.scrapworld.blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMachine extends TileEntity implements IMachine {
	
	private boolean ready = false;
	private boolean complete = false;
	private boolean powered = false;
	private ItemStack[] parts = new ItemStack[]{}; //read/write to NBT
	private int numParts = 3; //TODO read/write this to NBT also
	
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
    	 //TODO: Entity updates
    	// includes block being placed/loaded ?
    	checkIfComplete();
    }
    
    //IMachine fields
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
		int missingParts =0;
		for (int i=0; i<parts.length; i++) {
			if (parts[i].stackSize == 0) {
				missingParts++;
			}
		}
		if (missingParts == 0) {
			complete = true;
		}
		//TODO: write to NBT
	}

}
