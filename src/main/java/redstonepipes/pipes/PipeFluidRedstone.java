package redstonepipes.pipes;

import static redstonepipes.RedstonePipes.*;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import redstonepipes.IconProviderPipes;
import redstonepipes.RedstonePipes;
import buildcraft.api.core.IIconProvider;
import buildcraft.api.transport.IPipe;
import buildcraft.api.transport.IPipeTile;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransportFluids;

public class PipeFluidRedstone extends Pipe<PipeTransportFluids>
{
	private int powerLevel;
	public PipeFluidRedstone(Item item) {
		super(new PipeTransportFluids(), item);
		transport.flowRate = 160;
		transport.travelDelay = 4;
	}
	@Override
	public IIconProvider getIconProvider() {
		return IconProviderPipes.instance;
	}
	@Override
	public int getIconIndex(ForgeDirection paramForgeDirection) {
		if(RedstonePipes.includeGate)
		{
			return (powerLevel > 0 || getMaxRedstoneOutput(paramForgeDirection) > 0) ? 5 : 6;
		}
		return powerLevel > 0 ? 5 : 6;
	}
	@Override
	public void updateEntity()
	{
		super.updateEntity();
	    updatePower();
	}
	private void updateOther()
	{
		container.scheduleRenderUpdate();
		updateNeighbors(true);
	}
	private int getFluidAmount()
	{
		FluidStack stack = transport.renderCache[ForgeDirection.UNKNOWN.ordinal()];
		return stack == null ? 0 : stack.amount;
	}
	private void updatePower()
	{
		int maxCapacity = transport.getCapacity();
		int oldLevel = powerLevel;
		int capacity = getFluidAmount();
		powerLevel = divideAndCeil(getFluidAmount() * 15,PipeTransportFluids.LIQUID_IN_PIPE);
		if(oldLevel == 0 && powerLevel > 0)
		{
			playSwitchOnSound();
		}
		if(oldLevel > 0 && powerLevel == 0)
		{
			playSwitchOffSound();
		}
		updateOther();
	}
	@Override
	public int isPoweringTo(int l)
	{
		//ゲートの出力と液体の出力の高い方を出力
	    return Math.max(this.powerLevel, super.isPoweringTo(l));
	}
	@Override
	public boolean canConnectRedstone()
	{
		return true;//接続 Connect to redstone
	}
	public boolean canPipeConnect(TileEntity tile, ForgeDirection side)
	{
		if(tile instanceof IPipeTile && !connectAny)
		{
			IPipe otherPipe = ((IPipeTile)tile).getPipe();
			if(otherPipe instanceof PipeFluidRedstone)
			{
				return false;
			}
		}
		return super.canPipeConnect(tile, side);
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
