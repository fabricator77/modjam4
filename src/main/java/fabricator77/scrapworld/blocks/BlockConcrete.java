package fabricator77.scrapworld.blocks;

import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockConcrete extends Block {

	public BlockConcrete() {
		super(Material.ground);
		this.setCreativeTab(ScrapWorld.creativeTab);
	}
}
