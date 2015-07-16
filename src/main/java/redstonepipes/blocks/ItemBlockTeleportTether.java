package redstonepipes.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockTeleportTether extends ItemBlock
{
	public ItemBlockTeleportTether(Block p_i45328_1_) {
		super(p_i45328_1_);
		setMaxDamage(0).setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "_" + par1ItemStack.getItemDamage();
	}
}
