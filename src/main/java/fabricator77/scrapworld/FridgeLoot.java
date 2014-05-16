package fabricator77.scrapworld;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;

public class FridgeLoot {
	public static WeightedRandomChestContent[] loot = new WeightedRandomChestContent[]{
		new WeightedRandomChestContent(new ItemStack(Blocks.flower_pot, 1, 0), 0, 1, 10),
		new WeightedRandomChestContent(new ItemStack(Items.snowball, 4, 0), 0, 4, 10),
		new WeightedRandomChestContent(new ItemStack(Items.cake,1,0), 0, 1, 10),
		new WeightedRandomChestContent(new ItemStack(Blocks.yellow_flower, 1, 0), 0, 1, 10),
		new WeightedRandomChestContent(new ItemStack(Blocks.red_flower, 1, 0), 0, 1, 10),// 9 possible versions
		new WeightedRandomChestContent(new ItemStack(Items.dye), 0, 1, 10),// 16 possible versions
		// new ItemStack(Items.egg,1,0), // not sure, as can be used to spawn chickens
		new WeightedRandomChestContent(new ItemStack(Items.glass_bottle), 1, 10, 10),
		new WeightedRandomChestContent(new ItemStack(Items.potionitem), 1, 10, 10),//water bottle
	};
}
