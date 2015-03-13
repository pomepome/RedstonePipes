package redstonepipes;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.ForgeDirection;
import buildcraft.api.core.IIconProvider;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransportItems;
import buildcraft.transport.pipes.events.PipeEventItem;

public class PipeItemsGlass extends Pipe<PipeTransportItems> {

	public PipeItemsGlass(Item item) {
		super(new PipeTransportItems(), item);
	}

	@Override
	public IIconProvider getIconProvider() {
		return IconProviderPipes.instance;
	}

	@Override
	public int getIconIndex(ForgeDirection paramForgeDirection) {
		return 0;
	}

	public void eventHandler(PipeEventItem.AdjustSpeed event)
	{
		event.handled = true;
	}

}
