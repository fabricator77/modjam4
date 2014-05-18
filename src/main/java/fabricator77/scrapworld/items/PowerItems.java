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

public class PowerItems extends Item {
	@SideOnly(Side.CLIENT)
    private IIcon[] textures;
	public static final String[] itemNames = new String[]{"heater_barrel", "voltage_regulator", "copper_winding", "lightbulb_off", "lightbulb_glowing", "electric_motor"};
	
	public PowerItems() {
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(ScrapWorld.creativeTab);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata)
    {
		int j = MathHelper.clamp_int(metadata, 0, itemNames.length);
        return this.textures[j];
    }
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, itemNames.length-1);
        return itemNames[i];
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, List list)
    {
		for (int i = 0; i < itemNames.length; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
    	this.textures = new IIcon[itemNames.length];

        for (int i = 0; i < textures.length; ++i)
        {
            this.textures[i] = par1IconRegister.registerIcon(ScrapWorld.modid+":" + itemNames[i]);
        }
    }
}
