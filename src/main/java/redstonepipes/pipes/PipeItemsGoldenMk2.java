package redstonepipes.pipes;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import redstonepipes.IconProviderPipes;
import redstonepipes.RedstonePipes;
import buildcraft.api.core.IIconProvider;
import buildcraft.api.transport.IPipe;
import buildcraft.api.transport.IPipeTile;
import buildcraft.core.utils.MathUtils;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransportItems;
import buildcraft.transport.TravelingItem;
import buildcraft.transport.pipes.events.PipeEventItem;

public class PipeItemsGoldenMk2 extends Pipe<PipeTransportItems>
{
	/*
	 * constants
	 */
	public static final float MAX_SPEED = 0.5F;//アイテムの最大速度(Max traveling speed)
	public static final float ACCELERATION = 480.0F;//アイテムの加速度(Acceleration when receiving power)
	/*
	 * input
	 */
	boolean isPowered = false;//入力を受けているか(Is receiving power)

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
			//速度を制限(Limit speed)
			item.setSpeed(MathUtils.clamp(item.getSpeed(), 0.04F,0.14999999F));
			return;
		}
		//加速(Accelerate)
		item.setSpeed(MathUtils.clamp(item.getSpeed() * ACCELERATION, 0.04F,MAX_SPEED));
	}
	public boolean canPipeConnect(TileEntity tile, ForgeDirection side)
	{
		if(RedstonePipes.connectAny)
		{
			return super.canPipeConnect(tile, side);
		}
		if(tile instanceof IPipeTile)
		{
			IPipe otherPipe = ((IPipeTile)tile).getPipe();
			if(otherPipe instanceof PipeItemsGoldenMk2)
			{
				return false;
			}
		}
		return super.canPipeConnect(tile, side);
	}
}
