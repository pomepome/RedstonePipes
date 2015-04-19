package redstonepipes.pipes;

import java.util.LinkedList;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import redstonepipes.IconProviderPipes;
import redstonepipes.RedstonePipes;
import buildcraft.api.core.IIconProvider;
import buildcraft.api.transport.IPipe;
import buildcraft.api.transport.IPipeTile;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransportItems;
import buildcraft.transport.pipes.PipeItemsCobblestone;
import buildcraft.transport.pipes.events.PipeEventItem;

public class PipeAdvancedInsertion extends Pipe<PipeTransportItems>
{
	public PipeAdvancedInsertion(Item item)
	{
		super(new PipeTransportItems(),item);
	}
	@Override
	public IIconProvider getIconProvider()
	{
		return IconProviderPipes.instance;
	}

	@Override
	public int getIconIndex(ForgeDirection paramForgeDirection)
	{
		return 7;
	}
	public void eventHandler(PipeEventItem.FindDest event)
	{
		LinkedList<ForgeDirection> nonPipesList = new LinkedList();
		LinkedList<ForgeDirection> pipesList = new LinkedList();
		event.item.blacklist.add(event.item.input.getOpposite());
		for (ForgeDirection o : event.destinations)
		{
			if ((!event.item.blacklist.contains(o))
				&& (this.container.pipe.outputOpen(o))
				&& (this.container.isPipeConnected(o)))
			{
				TileEntity entity = this.container.getTile(o);
				if ((entity instanceof IPipeTile))
				{
					pipesList.add(o);
				} else
				{
					nonPipesList.add(o);
				}
			}
		}
		event.destinations.clear();
		if (nonPipesList.isEmpty())
		{
			event.destinations.addAll(pipesList);
		} else
		{
			event.destinations.addAll(nonPipesList);
		}
	}
	public boolean canPipeConnect(TileEntity tile, ForgeDirection side)
	{
		if(RedstonePipes.aiConnectAny)
		{
			return super.canPipeConnect(tile, side);
		}
		if(tile instanceof IPipeTile)
		{
			IPipe otherPipe = ((IPipeTile)tile).getPipe();
			if(otherPipe instanceof PipeItemsCobblestone)
			{
				return false;
			}
		}
		return super.canPipeConnect(tile, side);
	}
}
