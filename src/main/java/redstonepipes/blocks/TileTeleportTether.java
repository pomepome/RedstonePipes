package redstonepipes.blocks;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import redstonepipes.RedstonePipes;

public class TileTeleportTether extends TileEntity
{
	private Ticket chunkTicket;
	public List<ChunkCoordIntPair> getLoadArea()
	{
		int damage = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		int loadDistance = damage + 1;
		List<ChunkCoordIntPair> loadArea = new LinkedList<ChunkCoordIntPair>();
		for(int x = -loadDistance;x < loadDistance + 1;x++)
		{
			for(int z = -loadDistance;z < loadDistance + 1;z++)
			{
				ChunkCoordIntPair chuncCoords = new ChunkCoordIntPair((xCoord >> 4) + x, (zCoord >> 4) + z);
				loadArea.add(chuncCoords);
			}
		}
		return loadArea;
	}
	@Override
	public void validate() {
		super.validate();
		if(!worldObj.isRemote && chunkTicket == null) {
			Ticket ticket = ForgeChunkManager.requestTicket(RedstonePipes.instance, worldObj, Type.NORMAL);
			if(ticket != null) {
				forceChunkLoading(ticket);
			}
		}
	}
	@Override
	public void invalidate() {
		super.invalidate();
		stopChunkLoading();
	}

	public void setLoadDistance(int dist) {
		forceChunkLoading(chunkTicket);
	}

	public void forceChunkLoading(Ticket ticket) {
		stopChunkLoading();
		chunkTicket = ticket;
		for(ChunkCoordIntPair coord : getLoadArea()) {
			ForgeChunkManager.forceChunk(chunkTicket, coord);
		}
	}

	public void unforceChunkLoading() {
		for(Object obj : chunkTicket.getChunkList()) {
			ChunkCoordIntPair coord = (ChunkCoordIntPair) obj;
			ForgeChunkManager.unforceChunk(chunkTicket, coord);
		}
	}

	public void stopChunkLoading() {
		if(chunkTicket != null) {
			ForgeChunkManager.releaseTicket(chunkTicket);
			chunkTicket = null;
		}
	}
}
