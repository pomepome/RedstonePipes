package redstonepipes.pipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import redstonepipes.IconProviderPipes;
import redstonepipes.RedstonePipes;
import buildcraft.api.core.IIconProvider;
import buildcraft.api.transport.IPipe;
import buildcraft.api.transport.IPipeTile;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransportItems;
import buildcraft.transport.TravelerSet;
import buildcraft.transport.TravelingItem;
import buildcraft.transport.pipes.PipeItemsIron;

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
		if(RedstonePipes.includeGate)
		{
			return (powerLevel > 0 || getMaxRedstoneOutput(paramForgeDirection) > 0) ? 1 : 2;
		}
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
		if(RedstonePipes.connectAny)
		{
			return super.canPipeConnect(tile, side);
		}
		if(tile instanceof IPipeTile)
		{
			IPipe otherPipe = ((IPipeTile)tile).getPipe();
			if(otherPipe instanceof PipeItemsIron||otherPipe instanceof PipeItemsRedstone)
			{
				return false;
			}
		}
		return super.canPipeConnect(tile, side);
	}
	/*
	 * Update methods
	 */
	public void updateOther()
	{
		container.scheduleRenderUpdate();
		updateNeighbors(true);
	}
	public void updatePower()
	{
		TravelerSet ts = transport.items;
		int stackPowerLevel = divideAndCeil(ts.size() * 15,64);
		if(stackPowerLevel >= 15)
		{
			//比較する必要無いので更新してreturn
			powerLevel = 15;
			return;
		}
		int itemsPowerLevel = 0;
		int numItems = 0;
		for(TravelingItem travelingItem : this.transport.items)
		{
			ItemStack is = travelingItem.getItemStack();
			if(is != null && is.stackSize > 0)
			{
				numItems += is.stackSize;
			}
		}
		itemsPowerLevel = divideAndCeil(numItems * 15,64);
		int newPowerLevel = Math.max(stackPowerLevel, itemsPowerLevel);
		if(powerLevel != newPowerLevel)
		{
			if(powerLevel == 0)
			{
				playSwitchOnSound();
			}
			powerLevel = newPowerLevel;
			updateOther();
			if(powerLevel == 0)
			{
				playSwitchOffSound();
			}
		}
	}
	/*
	 * Redstone methods
	 */
	@Override
	public boolean canConnectRedstone()
	{
		return true;//接続 Connect to redstone
	}
	@Override
	public int isPoweringTo(int l)
	{
		//ゲートの出力とアイテムの出力の高い方を出力
		updatePower();
	    return Math.max(this.powerLevel, super.isPoweringTo(l));
	}
	@Override
	public int isIndirectlyPoweringTo(int l)
	{
		return isPoweringTo(l);
	}
	/*
	 * Utility method
	 */
	public static int divideAndCeil(int dividend, int divisor)
	{
		//切り上げ
	    return (dividend + divisor - 1) / divisor;
	}
	/*
	 * Playing sound methods
	 */
	private void playSwitchOnSound() {
		getWorld().playSoundEffect(container.xCoord + 0.5,container.yCoord + 0.5,container.zCoord + 0.5,"random.click",0.3F,0.6F);
	}
	private void playSwitchOffSound() {
		getWorld().playSoundEffect(container.xCoord + 0.5,container.yCoord + 0.5,container.zCoord + 0.5,"random.click",0.3F,0.5F);
	}
}
