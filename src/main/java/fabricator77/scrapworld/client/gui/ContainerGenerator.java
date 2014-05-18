package fabricator77.scrapworld.client.gui;

import fabricator77.scrapworld.machines.TileEntityMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGenerator extends Container {
	
	private TileEntityMachine tileEntity;

	public ContainerGenerator(InventoryPlayer inventoryPlayer, TileEntityMachine tileentityMachine) {
		this.tileEntity = tileentityMachine;
        int i;
        int j;

        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 3; ++j)
            {
            	// inventory (IInventory), slotIndex (int), xDisplayPosition (int), yDisplayPosition (int)
            	// xDisplayPosition was  62 + j * 18
                this.addSlotToContainer(new Slot(tileentityMachine, j + i * 3, 56 + j * 24, 17 + i * 18));
            }
        }
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
