package fabricator77.scrapworld;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FridgeLoot {
	public static ItemStack[] loot = new ItemStack[]{
		new ItemStack(Blocks.flower_pot, 1, 0),
		new ItemStack(Items.snowball, 4, 0),
		new ItemStack(Items.cake,1,0),
		new ItemStack(Blocks.yellow_flower, 1, 0),
		new ItemStack(Blocks.red_flower, 1, 0), // 9 possible versions
		new ItemStack(Items.dye), // 16 possible versions
		// new ItemStack(Items.egg,1,0), // not sure, as can be used to spawn chickens
	};
}
