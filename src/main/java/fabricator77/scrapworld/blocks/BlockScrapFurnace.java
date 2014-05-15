package fabricator77.scrapworld.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockScrapFurnace extends BlockFurnace{
	private boolean isBurning;
	
	@SideOnly(Side.CLIENT)
    private IIcon topTexture;
    @SideOnly(Side.CLIENT)
    private IIcon side_texture;

	public BlockScrapFurnace(boolean burning) {
		super(burning);
		this.isBurning = burning;
	}
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ == 1 ? this.topTexture : (p_149691_1_ == 0 ? this.topTexture : (p_149691_1_ != p_149691_2_ ? this.blockIcon : this.side_texture));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon("furnace_side");
        this.side_texture = p_149651_1_.registerIcon(this.isBurning ? "furnace_front_on" : "furnace_front_off");
        this.topTexture = p_149651_1_.registerIcon("furnace_top");
    }
}
