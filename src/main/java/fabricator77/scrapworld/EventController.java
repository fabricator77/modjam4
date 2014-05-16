package fabricator77.scrapworld;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class EventController {
	//TODO: need to look up Player Join / Player spawn / World creation events
	public void givePlayerBook (EntityPlayer player) {
		ItemStack book = new ItemStack(Items.written_book);
		
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagList bookPages = new NBTTagList();
		
		bookPages.appendTag(new NBTTagString("In the distant future, you find yourself stranded in a world which seems alien today."));
		bookPages.appendTag(new NBTTagString("The ruined remains and literal mountains of scrap are all you have around you."));
		
		book.setTagInfo("pages", bookPages);
		book.setTagInfo("author", new NBTTagString("fabricator77"));
		book.setTagInfo("title", new NBTTagString("How did it come to this ?"));
	}
}
