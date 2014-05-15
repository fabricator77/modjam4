package fabricator77.scrapworld.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockCompressedScrapCube extends Block {

	public BlockCompressedScrapCube() {
		super(Material.iron);
		this.setCreativeTab(ScrapWorld.creativeTab);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
		this.blockIcon = iconRegister.registerIcon(ScrapWorld.modid+":compressed_scrap_cube");
    }

}
