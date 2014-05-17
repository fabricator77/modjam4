package fabricator77.scrapworld.blocks;

public class TileEntityGenerator extends TileEntityMachine{
	//override names in Parent class
	public static final String[] machineNames = BlockGenerator.machineNames;

	@Override
	public String getInventoryName() {
		int metadata = this.blockMetadata;
		metadata = metadata % machineNames.length;
		return "container.generator."+machineNames[metadata];
	}
	
	@Override
	public void getPower () {
		//TODO: is on mains power, or contains powerCell
		if (this.blockMetadata == 0) {
			//TODO: check for fuel etc
		}
		if (this.blockMetadata == 1) {
			int light = ((BlockGenerator)this.blockType).getLightLevel(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		}
		if (this.blockMetadata == 2) {
			int light = ((BlockGenerator)this.blockType).getStormLevel(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		}
	}
}
