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
		int light = ((BlockGenerator)this.blockType).getLightLevel(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
	}
}
