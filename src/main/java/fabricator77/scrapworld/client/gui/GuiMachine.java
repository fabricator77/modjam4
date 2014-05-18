package fabricator77.scrapworld.client.gui;

import org.lwjgl.opengl.GL11;

import fabricator77.scrapworld.ScrapWorld;
import fabricator77.scrapworld.machines.TileEntityMachine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiMachine extends GuiContainer{

	public Container container;
	private ResourceLocation guiTextures = new ResourceLocation(ScrapWorld.modid+":textures/gui/machine_slots.png");
	public TileEntityMachine tileentity;

	public GuiMachine(InventoryPlayer inventory, TileEntityMachine tileentityMachine) {
		super(new ContainerMachine(inventory, tileentityMachine));
		tileentity = tileentityMachine;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        String s = this.tileentity.hasCustomInventoryName() ? this.tileentity.getInventoryName() : I18n.format(this.tileentity.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(guiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}
