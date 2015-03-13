package redstonepipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ServerProxy
{
	public void onPreInit(FMLPreInitializationEvent e)
	{
		GameRegistry.addRecipe(new ItemStack(RedstonePipes.pipeRedstone,8),"RGR",'R',Items.redstone,'G',Blocks.glass);
		GameRegistry.addRecipe(new ItemStack(RedstonePipes.pipeGlass,32),"GDG",'G',Blocks.glass,'D',Items.diamond);
		GameRegistry.addRecipe(new ItemStack(RedstonePipes.pipeGoldMk2,8)," R ","GWG",'R',Items.redstone,'G',Items.gold_ingot,'W',Blocks.glass);
	}
}
