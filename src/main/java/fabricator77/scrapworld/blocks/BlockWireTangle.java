package fabricator77.scrapworld.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockWireTangle extends Block{
	public BlockWireTangle() {
		super(Material.web);
        float f1 = 0.015625F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f1, 1.0F);
	}

	@Override
	public int getRenderType() {
		return 1;
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
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }

}
