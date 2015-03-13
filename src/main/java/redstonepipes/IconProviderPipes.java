package redstonepipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import buildcraft.api.core.IIconProvider;

public class IconProviderPipes implements IIconProvider
{
	public static IconProviderPipes instance = new IconProviderPipes();
	public static List<IIcon> icons = new ArrayList<IIcon>();
	public IIcon getIcon(int iconIndex)
	{
		if(iconIndex >= icons.size())
		{
			return null;
		}
		return icons.get(iconIndex);
	}
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		icons.add(iconRegister.registerIcon("redstonepipes:pipeItemsGlass"));
		icons.add(iconRegister.registerIcon("redstonepipes:pipeItemsRedstone_powered"));
		icons.add(iconRegister.registerIcon("redstonepipes:pipeItemsRedstone_standard"));
		icons.add(iconRegister.registerIcon("redstonepipes:pipeItemsGoldenMk2_powered"));
		icons.add(iconRegister.registerIcon("redstonepipes:pipeItemsGoldenMk2_standard"));
		icons.add(iconRegister.registerIcon("redstonepipes:pipePowerVoid"));
	}
}
