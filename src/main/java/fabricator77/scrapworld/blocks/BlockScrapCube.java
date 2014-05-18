package fabricator77.scrapworld.blocks;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fabricator77.scrapworld.ScrapWorld;
import fabricator77.scrapworld.ScrapWorldBlocks;
import fabricator77.scrapworld.items.ScrapItems1;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockScrapCube extends Block {
	
	public static final String[] blockNames = new String[]{"unknown", "timber" }; //, "metallic", "wire", "circuit", "plastic", "glass", "concrete", "timber", "food", "burnt", "rare", "useless"};

	@SideOnly(Side.CLIENT)
    private IIcon[] textures;
	
	public BlockScrapCube() {
		super(Material.wood);
		this.setCreativeTab(ScrapWorld.creativeTab);
	}
	
	@Override
	public int damageDropped(int par1)
    {
        return par1;
    }

	@SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
		this.textures = new IIcon[blockNames.length];
		for (int i = 0; i < blockNames.length; ++i)
        {
			textures[i] = iconRegister.registerIcon(ScrapWorld.modid+":scrap_cube_"+blockNames[i]);
        }
		this.blockIcon = textures[0];
    }
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int var1, int metadata)
    {
        return this.textures[metadata % this.textures.length];
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        for (int i = 0; i < blockNames.length; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }
	
	@Override 
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (world.rand.nextInt(10) == 0) {
        	ret.add(new ItemStack(ScrapWorldBlocks.scrapItems1, 1, world.rand.nextInt(ScrapItems1.itemNames.length)));
        }
        else {
        	ret.add(new ItemStack(ScrapWorldBlocks.scrapItems1, 1, 0));
        }
        if (metadata == 0) {
        	//TODO: change to drop 2x items or drop rare scrap item/loot
        	if (world.rand.nextInt(20) == 0) ret.add(new ItemStack(Items.gold_nugget, 1, 0));
        }
        if (metadata == 1) {
        	// timber scrap always drops timber scrap items
        	ret = new ArrayList<ItemStack>();
        	ret.add(new ItemStack(ScrapWorldBlocks.scrapItems1, 1, 7));
        }
        if (metadata == 9) {
        	// burnt scrap
        	if (world.rand.nextInt(20) == 0) ret.add(new ItemStack(Items.coal, fortune, 1));
        }
        
        return ret;
    }
}
