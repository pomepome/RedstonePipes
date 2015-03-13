package redstonepipes;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import buildcraft.api.core.IIconProvider;
import buildcraft.api.transport.IPipe;
import buildcraft.api.transport.IPipeTile;
import buildcraft.core.utils.MathUtils;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransportItems;
import buildcraft.transport.TravelingItem;
import buildcraft.transport.pipes.events.PipeEventItem;

public class PipeItemsGoldenMk2 extends Pipe<PipeTransportItems> {

	boolean isPowered = false;

	public PipeItemsGoldenMk2(Item item) {
		super(new PipeTransportItems(), item);
	}

	@Override
	public IIconProvider getIconProvider() {
		return IconProviderPipes.instance;
	}

	@Override
	public int getIconIndex(ForgeDirection paramForgeDirection) {
		return isPowered ? 3 : 4;
	}
	public void updateEntity()
	{
		super.updateEntity();
		World w = getWorld();
		int x = container.xCoord;
		int y = container.yCoord;
		int z = container.zCoord;
		isPowered = w.isBlockIndirectlyGettingPowered(x,y,z);
	}
	public void eventHandler(PipeEventItem.AdjustSpeed event) {
		event.handled = true;
		TravelingItem item = event.item;
		if(!isPowered)
		{
			item.setSpeed(MathUtils.clamp(item.getSpeed(), 0.04F,0.14999999F));
			return;
		}
		item.setSpeed(MathUtils.clamp(item.getSpeed() * 12.0F, 0.04F,0.32F));
	}
	public boolean canPipeConnect(TileEntity tile, ForgeDirection side)
	{
		if(tile instanceof IPipeTile)
		{
			IPipe otherPipe = ((IPipeTile)tile).getPipe();
			if(otherPipe instanceof PipeItemsGoldenMk2)
			{
				return false;
			}
			return true;
		}
		return true;
	}
}
