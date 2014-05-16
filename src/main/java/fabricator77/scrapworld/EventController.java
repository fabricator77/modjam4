package fabricator77.scrapworld;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class EventController {
	//TODO: need to look up Player Join / Player spawn / World creation events
	public void givePlayerBook (EntityPlayer player) {
		ItemStack book = new ItemStack(Items.written_book);
		
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagList bookPages = new NBTTagList();		
	}
}
