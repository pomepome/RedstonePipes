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
		registerItemPipe(RedstonePipes.pipeRedstone);
		registerItemPipe(RedstonePipes.pipeGlass);
		registerItemPipe(RedstonePipes.pipeGoldMk2);
	}
	public void registerItemPipe(Item item)
	{
		MinecraftForgeClient.registerItemRenderer(item,TransportProxyClient.pipeItemRenderer);
	}
}
