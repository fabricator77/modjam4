package fabricator77.scrapworld.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public interface IMachine {
	public abstract String getMachineName();
	
	public abstract boolean isMachineReady();
	
	public abstract boolean isMachineComplete();
	
	public abstract boolean isMachinePowered();
	
	public abstract void operateCycle();
}
