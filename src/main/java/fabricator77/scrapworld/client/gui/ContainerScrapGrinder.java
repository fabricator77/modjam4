package fabricator77.scrapworld.client.gui;

import fabricator77.scrapworld.machines.TileEntityScrapGrinder_old;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerScrapGrinder extends Container {
	
	private TileEntityScrapGrinder_old tileEntity;

	public ContainerScrapGrinder(InventoryPlayer inventoryPlayer, TileEntityScrapGrinder_old tileentityMachine) {
		this.tileEntity = tileentityMachine;
        int i;
        int j;

        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 3; ++j)
            {
            	// inventory (IInventory), slotIndex (int), xDisplayPosition (int), yDisplayPosition (int)
            	//left (input)
                this.addSlotToContainer(new Slot(tileentityMachine, j + i * 3, 20 + j * 18, 17 + i * 18));
                //right (input)
                this.addSlotToContainer(new Slot(tileentityMachine, 9 + j + i * 3, 104 + j * 18, 17 + i * 18));
            }
        }
        
        // Power cell slot
        this.addSlotToContainer(new Slot(tileentityMachine, 18, 56 + 24, 17 + 2 * 18));
        
        bindPlayerInventory(inventoryPlayer);
	}
	
	// needed
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		int i, j;
		for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.tileEntity.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotInt)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotInt);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotInt < 9)
            {
                if (!this.mergeItemStack(itemstack1, 9, 45, true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 9, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}
