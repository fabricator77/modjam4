package fabricator77.scrapworld.client.gui;

import fabricator77.scrapworld.blocks.TileEntityMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerMachine extends Container {

	public ContainerMachine(InventoryPlayer inventory, TileEntityMachine tileentityMachine) {
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return false;
	}

}
