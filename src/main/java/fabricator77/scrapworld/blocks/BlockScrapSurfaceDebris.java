package fabricator77.scrapworld.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockScrapSurfaceDebris extends Block {

	public BlockScrapSurfaceDebris() {
		super(Material.ground);
		this.setCreativeTab(CreativeTabs.tabBlock);
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
}
