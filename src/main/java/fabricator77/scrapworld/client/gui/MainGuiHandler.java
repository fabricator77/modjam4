package fabricator77.scrapworld.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IGuiHandler;
import fabricator77.scrapworld.machines.TileEntityGenerator;
import fabricator77.scrapworld.machines.TileEntityMachine;
import fabricator77.scrapworld.machines.TileEntityScrapGrinder;
import fabricator77.scrapworld.machines.TileEntitySolarPanel;
import fabricator77.scrapworld.machines.TileEntityStaticGenerator;

public class MainGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity != null && tileEntity instanceof TileEntityMachine){
			//if (ID == 1) return new ContainerGenerator(player.inventory, (TileEntityMachine)tileEntity);
			
			if (ID == 2) return new ContainerGenerator(player.inventory, (TileEntityGenerator)tileEntity);
			if (ID == 3) return new ContainerGenerator(player.inventory, (TileEntitySolarPanel)tileEntity);
			if (ID == 4) return new ContainerGenerator(player.inventory, (TileEntityStaticGenerator)tileEntity);
			
			
		}
		if(tileEntity != null && tileEntity instanceof TileEntityScrapGrinder){
			if (ID == 5) return new ContainerScrapGrinder(player.inventory, (TileEntityScrapGrinder)tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity != null && tileEntity instanceof TileEntityMachine){
			//if (ID == 1) return new GuiGenerator(player.inventory, (TileEntityMachine)tileEntity);
			
			if (ID == 2) return new GuiGenerator(player.inventory, (TileEntityGenerator)tileEntity);
			if (ID == 3) return new GuiGenerator(player.inventory, (TileEntitySolarPanel)tileEntity);
			if (ID == 4) return new GuiGenerator(player.inventory, (TileEntityStaticGenerator)tileEntity);
			
			
		}
		if(tileEntity != null && tileEntity instanceof TileEntityScrapGrinder){
			if (ID == 5) return new GuiScrapGrinder(player.inventory, (TileEntityScrapGrinder)tileEntity);
		}
		return null;
	}

}
