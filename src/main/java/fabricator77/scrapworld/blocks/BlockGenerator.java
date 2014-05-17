package fabricator77.scrapworld.blocks;

import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockGenerator extends BlockMachine{
	// public static final String machineName = "generator";

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
		ItemStack heldItem = player.getCurrentEquippedItem();
		if (heldItem != null && heldItem.getItem() == Items.bucket) {
			//TODO: take/accept items and liquids
		}
        //if (world.isRemote)
        //{
        	player.openGui(ScrapWorld.instance, 2, world, x, y, z);
        //}
        return true;
    }
	
	public int getPowerLevel(World world, int x, int y, int z) {
        return 0;
    }
}
