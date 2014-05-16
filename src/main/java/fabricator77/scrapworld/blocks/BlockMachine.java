package fabricator77.scrapworld.blocks;

import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMachine extends Block {

	public BlockMachine() {
		super(Material.iron);
		this.setCreativeTab(ScrapWorld.creativeTab);
	}

}
