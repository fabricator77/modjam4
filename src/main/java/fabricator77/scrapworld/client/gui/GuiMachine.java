package fabricator77.scrapworld.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class GuiMachine extends GuiContainer{

	public Container container;

	public GuiMachine(Container container) {
		super(container);
		this.container = container;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		
	}

}
