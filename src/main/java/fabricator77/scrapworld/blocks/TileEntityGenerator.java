package fabricator77.scrapworld.blocks;

public class TileEntityGenerator extends TileEntityMachine{
	//override names in Parent class
	public static String machineName = BlockGenerator.machineName;

	@Override
	public String getInventoryName() {
		machineName = ((BlockGenerator)this.blockType).machineName;
		return "container.generator."+machineName;
	}
	
	@Override
	public void getPower () {
		//TODO: is on mains power, or contains powerCell
		int power = ((BlockGenerator)this.blockType).getPowerLevel(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
	}
}
