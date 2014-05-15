package fabricator77.scrapworld.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fabricator77.scrapworld.RandomMessages;
import fabricator77.scrapworld.ScrapWorld;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ScrapItems1 extends Item {
	
	// last one is always "useless"
	public static final String[] itemNames = new String[]{"unknown", "metallic", "wire", "circuit", "plastic", "glass", "concrete", "rare", "timber", "useless"};
	
	@SideOnly(Side.CLIENT)
    private IIcon[] textures;
	
	public ScrapItems1 () {
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata)
    {
        int j = MathHelper.clamp_int(metadata, 0, itemNames.length-1);
        return this.textures[j];
    }
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, itemNames.length-1);
        return "scrap_" + itemNames[i];
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, List list)
    {
        for (int i = 0; i < itemNames.length; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.textures = new IIcon[itemNames.length];

        for (int i = 0; i < textures.length; ++i)
        {
            this.textures[i] = par1IconRegister.registerIcon(ScrapWorld.modid+":scrap_" + itemNames[i]);
        }
    }
	
	//TODO: right click method to drop random item contained in this scrap
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		if (itemStack.stackSize > 1) {
			return itemStack;
		}
        //TODO: do something with itemStack.getItemDamage()
        	int metadata = MathHelper.clamp_int(itemStack.getItemDamage(), 0, itemNames.length-1);
        	String scrapType = itemNames[metadata];
        	
        	
        	if (scrapType == "unknown") {
        		if (world.rand.nextInt(5) != 0) {
        			//nothing of value could be found in this
        			String mesage = RandomMessages.scrapMessages(itemRand);
        			//player.addChatMessage(new ChatComponentTranslation(mesage));
        			player.addChatMessage(new ChatComponentText(mesage));
        			//convert into useless version, so people cannot spam right click
        			
        			itemStack.setItemDamage(itemNames.length);
        			--itemStack.stackSize;
        			return itemStack;
        		}
        		// choose a random type to make this into
        		scrapType = itemNames[1 + world.rand.nextInt(itemNames.length-1)];
        	}
        	
        	if (scrapType == "metallic") {
        		// random choice of iron, tin, copper or nothing
        		// usually nothing
        	}
        	if (scrapType == "wire") {
        		// wire to make into machines/metal
        		// heating element
        		// could also be fibre cable (rare)
        	}
        	if (scrapType == "circuit") {
        		// random choice of wires, blank PCB, copper scrap, silicon, redstone, broken PCB, silicon chip (rare), working PCB(rare)
        		// or nothing
        	}
        	if (scrapType == "plastic") {
        		// random choice of rubber, plastic, plastic component, silicon chip (rare)
        	}
        	if (scrapType == "glass") {
        		// random choice of glass fragments, sand, fibre strands
        	}
        	
        	--itemStack.stackSize;
        	
        	return itemStack;
    }
}
