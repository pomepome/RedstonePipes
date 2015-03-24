package redstonepipes;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import buildcraft.transport.TransportProxyClient;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends ServerProxy {
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
	public void registerPipeRenderer(Item item)
	{
		MinecraftForgeClient.registerItemRenderer(item,TransportProxyClient.pipeItemRenderer);
	}
}
