package redstonepipes.blocks;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redstonepipes.RedstonePipes;

public class BlockTeleportether extends BlockContainer {

	public BlockTeleportether() {
		super(Material.ground);
		setHardness(5f).setBlockName("Teleport-Tether").setCreativeTab(RedstonePipes.instance.tab).setBlockTextureName("redstonepipes:tether");
		GameRegistry.registerBlock(this,ItemBlockTeleportTether.class,"Teleport-Tether");
		GameRegistry.registerTileEntity(TileTeleportTether.class, "Teleport-Tether");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileTeleportTether();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < 4; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

}
