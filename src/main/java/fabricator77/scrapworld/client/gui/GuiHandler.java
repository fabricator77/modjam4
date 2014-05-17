package fabricator77.scrapworld.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IGuiHandler;
import fabricator77.scrapworld.blocks.TileEntityMachine;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity != null && tileEntity instanceof TileEntityMachine){
			return new ContainerMachine(player.inventory, (TileEntityMachine)tileEntity);	
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity != null && tileEntity instanceof TileEntityMachine){
			return new GuiMachine(player.inventory, (TileEntityMachine)tileEntity);		 
		}
		return null;
	}

}
