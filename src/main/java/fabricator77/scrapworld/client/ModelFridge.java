package fabricator77.scrapworld.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelFridge extends ModelBase{
    public ModelRenderer fridgeDoor = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);
    public ModelRenderer fridgeCabinet;
    public ModelRenderer fridgeHandle;

    public ModelFridge () {
    	this.fridgeDoor.addBox(0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F);
        this.fridgeDoor.rotationPointX = 1.0F;
        this.fridgeDoor.rotationPointY = 7.0F;
        this.fridgeDoor.rotationPointZ = 15.0F;
        //TODO: shift location and shape of handle
        this.fridgeHandle = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);
        this.fridgeHandle.addBox(-1.0F, -2.0F, -12.0F, 4, 1, 1, 0.0F);
        this.fridgeHandle.rotationPointX = 6.0F;// was 8.0F
        this.fridgeHandle.rotationPointY = 3.0F;// was 7.0F
        this.fridgeHandle.rotationPointZ = 15.0F;// was 15.0F
        this.fridgeCabinet = (new ModelRenderer(this, 0, 19)).setTextureSize(64, 64);
        this.fridgeCabinet.addBox(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
        this.fridgeCabinet.rotationPointX = 1.0F;
        this.fridgeCabinet.rotationPointY = 6.0F;
        this.fridgeCabinet.rotationPointZ = 1.0F;
    }
    
    public void renderAll()
    {
        this.fridgeHandle.rotateAngleX = this.fridgeDoor.rotateAngleX;
        this.fridgeDoor.render(0.0625F);
        this.fridgeHandle.render(0.0625F);
        this.fridgeCabinet.render(0.0625F);
    }
}
