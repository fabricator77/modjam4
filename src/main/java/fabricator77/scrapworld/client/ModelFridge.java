package fabricator77.scrapworld.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelFridge extends ModelBase{
    public ModelRenderer fridgeLid = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);
    public ModelRenderer fridgeBelow;
    public ModelRenderer fridgeHandle;

    public ModelFridge () {
    	this.fridgeLid.addBox(0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F);
        this.fridgeLid.rotationPointX = 1.0F;
        this.fridgeLid.rotationPointY = 7.0F;
        this.fridgeLid.rotationPointZ = 15.0F;
        //TODO: shift location and shape of handle
        this.fridgeHandle = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);
        this.fridgeHandle.addBox(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
        this.fridgeHandle.rotationPointX = 8.0F;
        this.fridgeHandle.rotationPointY = 7.0F;
        this.fridgeHandle.rotationPointZ = 15.0F;
        this.fridgeBelow = (new ModelRenderer(this, 0, 19)).setTextureSize(64, 64);
        this.fridgeBelow.addBox(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
        this.fridgeBelow.rotationPointX = 1.0F;
        this.fridgeBelow.rotationPointY = 6.0F;
        this.fridgeBelow.rotationPointZ = 1.0F;
    }
}
