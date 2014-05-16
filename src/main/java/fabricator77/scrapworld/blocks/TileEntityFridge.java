package fabricator77.scrapworld.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntityChest;

public class TileEntityFridge extends TileEntityChest{
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

}
