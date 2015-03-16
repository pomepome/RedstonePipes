package redstonepipes;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import buildcraft.api.core.IIconProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class IconProviderPipes implements IIconProvider
{
	public static IconProviderPipes instance = new IconProviderPipes();
	private IIcon[] icons;
	private static final int iconCount = 5;

	public IconProviderPipes()
	{
		icons = new IIcon[iconCount];
	}
	public IIcon getIcon(int iconIndex)
	{
		if(iconIndex >= iconCount)
		{
			return null;
		}
		return icons[iconIndex];
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		for(int i = 0;i < iconCount;i++)
		{
			icons[i] = iconRegister.registerIcon("redstonepipes:"+i);
		}
	}
}
