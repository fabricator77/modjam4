package fabricator77.scrapworld.blocks;

import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class BlockPower extends BlockSlab {

	public BlockPower() {
		super(false, Material.iron);
		this.setCreativeTab(ScrapWorld.creativeTab);
	}

	@Override
	public String func_150002_b(int var1) {
		return null;
	}

}
