package fabricator77.scrapworld.client;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fabricator77.scrapworld.ScrapWorld;
import fabricator77.scrapworld.blocks.BlockFridge;
import fabricator77.scrapworld.blocks.TileEntityFridge;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class TileEntityRenderFridge extends TileEntitySpecialRenderer { //implements ISimpleBlockRenderingHandler {
	
    private static final ResourceLocation field_147505_d = new ResourceLocation(ScrapWorld.modid+":textures/entity/fridge_double.png");
    private static final ResourceLocation field_147504_g = new ResourceLocation(ScrapWorld.modid+":textures/entity/fridge.png");
	
	private ModelFridge field_147510_h = new ModelFridge();
    private ModelFridge field_147511_i = new ModelLargeFridge();
    
    public TileEntityRenderFridge () {
    	
    }

	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float var8) {
		this.renderTileEntityAt((TileEntityChest)te, x, y, z, var8);
	}
    public void renderTileEntityAt(TileEntityChest te, double p_147502_2_, double p_147502_4_, double p_147502_6_, float p_147502_8_)
    {
		int i;

        if (!te.hasWorldObj())
        {
            i = 0;
        }
        else
        {
            Block block = te.getBlockType();
            i = te.getBlockMetadata();

            if (block instanceof BlockFridge && i == 0)
            {
                try
                {
                ((BlockFridge)block).func_149954_e(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);
                }
                catch (ClassCastException e)
                {
                    FMLLog.severe("Attempted to render a chest at %d,  %d, %d that was not a chest", te.xCoord, te.yCoord, te.zCoord);
                }
                i = te.getBlockMetadata();
            }

            te.checkForAdjacentChests();
        }

        if (te.adjacentChestZNeg == null && te.adjacentChestXNeg == null)
        {
        	ModelFridge modelchest;

            if (te.adjacentChestXPos == null && te.adjacentChestZPos == null)
            {
                modelchest = this.field_147510_h;
                this.bindTexture(field_147504_g);
            }
            else
            {
                modelchest = this.field_147511_i;
                this.bindTexture(field_147505_d);
            }

            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float)p_147502_2_, (float)p_147502_4_ + 1.0F, (float)p_147502_6_ + 1.0F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            short short1 = 0;

            if (i == 2)
            {
                short1 = 180;
            }

            if (i == 3)
            {
                short1 = 0;
            }

            if (i == 4)
            {
                short1 = 90;
            }

            if (i == 5)
            {
                short1 = -90;
            }

            if (i == 2 && te.adjacentChestXPos != null)
            {
                GL11.glTranslatef(1.0F, 0.0F, 0.0F);
            }

            if (i == 5 && te.adjacentChestZPos != null)
            {
                GL11.glTranslatef(0.0F, 0.0F, -1.0F);
            }

            GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            float f1 = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * p_147502_8_;
            float f2;

            if (te.adjacentChestZNeg != null)
            {
                f2 = te.adjacentChestZNeg.prevLidAngle + (te.adjacentChestZNeg.lidAngle - te.adjacentChestZNeg.prevLidAngle) * p_147502_8_;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            if (te.adjacentChestXNeg != null)
            {
                f2 = te.adjacentChestXNeg.prevLidAngle + (te.adjacentChestXNeg.lidAngle - te.adjacentChestXNeg.prevLidAngle) * p_147502_8_;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            modelchest.fridgeDoor.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
            modelchest.renderAll();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
	}

    
    // ISimpleBlockRenderingHandler methods below
	//@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		
	}

	//@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return true;
	}

	//@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	//@Override
	public int getRenderId() {
		return ScrapWorld.proxy.fridgeRendererID;
	}
}
