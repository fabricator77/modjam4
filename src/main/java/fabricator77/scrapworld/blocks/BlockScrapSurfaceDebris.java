package fabricator77.scrapworld.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockScrapSurfaceDebris extends Block {

	public BlockScrapSurfaceDebris() {
		super(Material.ground);
        float f1 = 0.015625F;
        this.setBlockBounds(1.0F, 0.0F, 1.0F, 1.0F, f1, 1.0F);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.lightOpacity = 255;
	}

	@Override
	public int getRenderType() {
		return 23;
	}
	
	@Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
		//TODO: multiple textures per block
		this.blockIcon = iconRegister.registerIcon(ScrapWorld.modid+":scrap_debris");
    }
}
