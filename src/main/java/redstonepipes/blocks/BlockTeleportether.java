package redstonepipes.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redstonepipes.RedstonePipes;

public class BlockTeleportether extends BlockContainer {

	public BlockTeleportether() {
		super(Material.ground);
		setHardness(5f).setBlockName("TeleportTether").setCreativeTab(RedstonePipes.instance.tab).setBlockTextureName("redstonepipes:tether");
		GameRegistry.registerBlock(this,"TeleportTether");
		GameRegistry.registerTileEntity(TileTeleportTether.class, "TeleportTether");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileTeleportTether();
	}

}
