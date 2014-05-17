package fabricator77.scrapworld.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class HVPowerCell extends Item {
	@SideOnly(Side.CLIENT)
    private IIcon texture;
	public static final String itemName = "hv_power_cell";
	
	public HVPowerCell() {
        this.setMaxDamage(32768);
        this.setCreativeTab(ScrapWorld.creativeTab);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata)
    {
		int j = MathHelper.clamp_int(metadata, 0, 0);
        return this.texture;
    }
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 1);
        return itemName;
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, List list)
    {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 32768));
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
    	this.texture = par1IconRegister.registerIcon(ScrapWorld.modid+":" + itemName);
    }
}
