package redstonepipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ServerProxy
{
	public void onPreInit(FMLPreInitializationEvent e)
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonePipes.pipeRedstone,8),"RGR",'R',Items.redstone,'G',"blockGlass"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonePipes.pipeGlass,32),"GDG",'G',"blockGlass",'D',"gemDiamond"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonePipes.pipeGoldMk2,8)," R ","GWG",'R',Items.redstone,'G',Items.gold_ingot,'W',"blockGlass"));
	}
}
