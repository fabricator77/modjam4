package fabricator77.scrapworld;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabScrapWorld extends CreativeTabs {
	public CreativeTabScrapWorld(int par1, String par2Str) {
		super(par1, par2Str);
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ScrapWorldBlocks.dusts, 1, 10);
	}

	@Override
	public Item getTabIconItem() {
		return null;
	}
}
