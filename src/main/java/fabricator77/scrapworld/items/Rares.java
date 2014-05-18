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

public class Rares extends Item {
	@SideOnly(Side.CLIENT)
    private IIcon[] textures;
	
	public Rares() {
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(ScrapWorld.creativeTab);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata)
    {
        int j = MathHelper.clamp_int(metadata, 0, 2);
        return this.textures[j];
    }
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int metadata = par1ItemStack.getItemDamage();
        if (metadata == 0) {
        	return "plant";
        }
        //else {
        //	return "book";
        //}
        return "";
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, List list)
    {
        for (int i = 0; i < 1; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.textures = new IIcon[2];

        this.textures[0] = par1IconRegister.registerIcon(ScrapWorld.modid+":plant");
        //this.textures[1] = par1IconRegister.registerIcon(ScrapWorld.modid+":book");
    }
}
