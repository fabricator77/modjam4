package fabricator77.scrapworld.blocks;

public class TileEntityGenerator extends TileEntityMachine{

	@Override
	public String getInventoryName() {
		int metadata = this.blockMetadata;
		return "container.generator";
	}
}
