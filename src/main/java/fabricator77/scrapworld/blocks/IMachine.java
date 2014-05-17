package fabricator77.scrapworld.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public interface IMachine {
	public abstract boolean isMachineReady();
	
	public abstract boolean isMachineComplete();
	
	public abstract boolean isMachinePowered();
}
