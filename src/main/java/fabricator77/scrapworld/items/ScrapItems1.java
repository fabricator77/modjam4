package fabricator77.scrapworld.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ScrapItems1 extends Item {
	
	public String[] itemNames = new String[]{"unknown", "metalic", "plastic", "glass", "concrete", "rare"};
	
	@SideOnly(Side.CLIENT)
    private IIcon[] textures;
	
	public ScrapItems1 () {
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata)
    {
        int j = MathHelper.clamp_int(metadata, 0, itemNames.length-1);
        return this.textures[j];
    }
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
        return "scrap_" + itemNames[i];
    }
	
	//TODO: right click method to drop random item contained in this scrap
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (!player.canPlayerEdit(par4, par5, par6, par7, itemStack))
        {
            return false;
        }
        else
        {
        	//TODO: do something with itemStack.getItemDamage()
        	int scrapType = itemStack.getItemDamage();
        	if (scrapType == 0) {
        		if (world.rand.nextInt(5) != 0) {
        			//TODO: nothing of value could be found in this
        			return false;
        		}
        		// choose a random type to make this into
        		scrapType = world.rand.nextInt(itemNames.length-1) + 1;
        	}
        	return true;
        }
    }
}
