package redstonepipes;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.oredict.ShapedOreRecipe;
import buildcraft.transport.TransportProxyClient;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void onInit(FMLInitializationEvent event)
	{
		if(!RedstonePipes.enabled)
		{
			return;
		}
		registerPipeRenderer(RedstonePipes.pipeRedstone);
		registerPipeRenderer(RedstonePipes.pipeGlass);
		registerPipeRenderer(RedstonePipes.pipeGoldMk2);
		registerPipeRenderer(RedstonePipes.pipeVoid);
	}
	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonePipes.pipeRedstone,8),"RGR",'R',Items.redstone,'G',"blockGlass"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonePipes.pipeGlass,32),"GDG",'G',"blockGlass",'D',"gemDiamond"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonePipes.pipeGoldMk2,8)," R ","GWG",'R',Items.redstone,'G',Items.gold_ingot,'W',"blockGlass"));
	}
	@Override
	public void registerPipeRenderer(Item item)
	{
		MinecraftForgeClient.registerItemRenderer(item,TransportProxyClient.pipeItemRenderer);
	}
}
