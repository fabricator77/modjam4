package fabricator77.scrapworld.blocks;

import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachine extends BlockContainer{

	public BlockMachine() {
		super(Material.iron);
		this.setCreativeTab(ScrapWorld.creativeTab);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		// TODO Auto-generated method stub
		return null;
	}

}
