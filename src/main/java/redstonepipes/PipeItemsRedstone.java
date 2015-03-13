package redstonepipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import buildcraft.api.core.IIconProvider;
import buildcraft.api.transport.IPipe;
import buildcraft.api.transport.IPipeTile;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransportItems;
import buildcraft.transport.TravelingItem;
import buildcraft.transport.pipes.PipeItemsIron;
import buildcraft.transport.pipes.events.PipeEventItem;

public class PipeItemsRedstone extends Pipe<PipeTransportItems> {

	private int powerLevel = 0;

	public PipeItemsRedstone(Item item) {
		super(new PipeTransportItems(), item);
	}

	@Override
	public IIconProvider getIconProvider() {
		return IconProviderPipes.instance;
	}

	@Override
	public int getIconIndex(ForgeDirection paramForgeDirection) {
		return powerLevel > 0 ? 1 : 2;
	}
	@Override
	public void updateEntity()
	{
		super.updateEntity();
	    updatePower();
	}
	@Override
	public boolean canPipeConnect(TileEntity tile, ForgeDirection side)
	{
		if(tile instanceof IPipeTile)
		{
			IPipe otherPipe = ((IPipeTile)tile).getPipe();
			if(otherPipe instanceof PipeItemsIron)
			{
				return false;
			}
			return true;
		}
		return true;
	}
	public void eventHandler(PipeEventItem.Entered event)
	{
		powerLevel = getAppositePower(event.item);
		this.getWorld().playSoundEffect(container.xCoord + 0.5,container.yCoord + 0.5,container.zCoord + 0.5,"random.click",0.3F,0.6F);
		updatePower();
	}

	public void eventHandler(PipeEventItem.ReachedEnd event)
	{
		powerLevel = 0;
		updatePower();
	}
	public void eventHandler(PipeEventItem.DropItem event)
	{
		powerLevel = 0;
		updatePower();
	}
	public void updatePower()
	{
		container.scheduleRenderUpdate();
		this.updateNeighbors(true);
	}
	/**
	 * @param TravelingItem
	 * @return Power output
	 */
	public int getAppositePower(TravelingItem item)
	{
		ItemStack is = item.getItemStack();
		if(is == null)
		{
			return 0;
		}
		return (int)Math.ceil(15 * ((double)is.stackSize / is.getMaxStackSize()));
	}
	@Override
	public boolean canConnectRedstone()
	{
		return true;
	}
	@Override
	public int isIndirectlyPoweringTo(int l)
	{
	    return Math.max(this.powerLevel, super.isIndirectlyPoweringTo(l));
	}
}
