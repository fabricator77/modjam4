package fabricator77.scrapworld.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IGuiHandler;
import fabricator77.scrapworld.machines.TileEntityGenerator;
import fabricator77.scrapworld.machines.TileEntityMachine;
import fabricator77.scrapworld.machines.TileEntitySolarPanel;
import fabricator77.scrapworld.machines.TileEntityStaticGenerator;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity != null && tileEntity instanceof TileEntityMachine){
			if (ID == 1) return new ContainerMachine(player.inventory, (TileEntityMachine)tileEntity);
			if (ID == 2) return new ContainerMachine(player.inventory, (TileEntityGenerator)tileEntity);
			if (ID == 3) return new ContainerMachine(player.inventory, (TileEntitySolarPanel)tileEntity);
			if (ID == 4) return new ContainerMachine(player.inventory, (TileEntityStaticGenerator)tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity != null && tileEntity instanceof TileEntityMachine){
			if (ID == 1) return new GuiMachine(player.inventory, (TileEntityMachine)tileEntity);
			if (ID == 2) return new GuiMachine(player.inventory, (TileEntityGenerator)tileEntity);
			if (ID == 3) return new GuiMachine(player.inventory, (TileEntitySolarPanel)tileEntity);
			if (ID == 4) return new GuiMachine(player.inventory, (TileEntityStaticGenerator)tileEntity);
		}
		return null;
	}

}
