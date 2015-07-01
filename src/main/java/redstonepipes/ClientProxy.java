package redstonepipes;

import static buildcraft.BuildCraftTransport.*;
import static redstonepipes.RedstonePipes.*;

import buildcraft.transport.TransportProxyClient;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ClientProxy extends CommonProxy {
	@Override
	public void onInit(FMLInitializationEvent event)
	{
		if(!RedstonePipes.enabled)
		{
			return;
		}
		registerPipeRenderer(pipeRedstone);
		registerPipeRenderer(pipeGlass);
		registerPipeRenderer(pipeGoldMk2);
		registerPipeRenderer(pipeFluidRedstone);
		registerPipeRenderer(pipeAdvancedInsertion);
	}
	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(pipeRedstone,8),"RGR",'R',Items.redstone,'G',"blockGlass"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(pipeGlass,32),"GDG",'G',"blockGlass",'D',"gemDiamond"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(pipeGoldMk2,8)," R ","GWG",'R',Items.redstone,'G',Items.gold_ingot,'W',"blockGlass"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(pipeAdvancedInsertion,8)," R ","SWS",'R',Items.redstone,'S',Blocks.stone,'W',"blockGlass"));
		GameRegistry.addRecipe(new ItemStack(pipeFluidRedstone),"S","P",'S',pipeWaterproof,'P',pipeRedstone);
		GameRegistry.addRecipe(new ItemStack(TeleportTether),"III","IRI","III",'I',Items.iron_ingot,'R',Items.redstone);

	}
	@Override
	public void registerPipeRenderer(Item item)
	{
		MinecraftForgeClient.registerItemRenderer(item,TransportProxyClient.pipeItemRenderer);
	}
}