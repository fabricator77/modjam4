package fabricator77.scrapworld.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;

public class TileEntityFridge extends TileEntityChest{
	private String customName;

	public TileEntityFridge()
    {
		super();
        //this.cachedChestType = -1;
    }

    @SideOnly(Side.CLIENT)
    public TileEntityFridge(int par1)
    {
    	super(par1);
        //this.cachedChestType = par1;
    }
    
    @Override
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.customName : "container.fridgeHalf";
    }
    
    @Override
    public boolean hasCustomInventoryName()
    {
        return this.customName != null && this.customName.length() > 0;
    }
    
    @Override
    public void func_145976_a(String p_145976_1_)
    {
        this.customName = p_145976_1_;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        // NBTTagList nbttaglist = tag.getTagList("Items", 10);
        //this.chestContents = new ItemStack[this.getSizeInventory()];

        if (tag.hasKey("CustomName", 8))
        {
            this.customName = tag.getString("CustomName");
        }

        /**
        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.chestContents.length)
            {
                this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        */
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        //NBTTagList nbttaglist = new NBTTagList();

        /**
        for (int i = 0; i < this.chestContents.length; ++i)
        {
            if (this.chestContents[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.chestContents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        */

        //tag.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            tag.setString("CustomName", this.customName);
        }
    }
}
