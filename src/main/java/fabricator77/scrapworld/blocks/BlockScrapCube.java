package fabricator77.scrapworld.blocks;

import java.util.ArrayList;

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
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockScrapCube extends Block {

	public BlockScrapCube() {
		super(Material.iron);
		this.setCreativeTab(ScrapWorld.creativeTab);
	}

	@SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
		//TODO: multiple textures per block
		this.blockIcon = iconRegister.registerIcon(ScrapWorld.modid+":scrap_cube_1");
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
        	if (world.rand.nextInt(20) == 0) ret.add(new ItemStack(Items.emerald, 0, 0));
        }
        if (metadata == 1) {
        	// burnt scrap ?
        	if (world.rand.nextInt(20) == 0) ret.add(new ItemStack(Items.coal, fortune, 1));
        }
        
        return ret;
    }
}
