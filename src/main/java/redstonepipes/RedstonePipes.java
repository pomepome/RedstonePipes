package redstonepipes;

import java.io.File;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import buildcraft.core.CreativeTabBuildCraft;
import buildcraft.transport.BlockGenericPipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "RedstonePipes",name="RedstonePipes",version="0.3",dependencies="required-after:BuildCraft|Transport")
public class RedstonePipes
{

	/*
	 * ConfigFile
	 */
	File configF;
	/*
	 * Pipes
	 */
	public static Item pipeRedstone;
	public static Item pipeGlass;
	public static Item pipeGoldMk2;
	/*
	 * Misc
	 */
	public static boolean enabled;
	public static boolean includeGate;

	@Mod.Instance("RedstonePipes")
	RedstonePipes instance;
	ClientProxy proxy = new ClientProxy();
	@EventHandler
	public void init(FMLInitializationEvent e)
	{
		proxy.onInit(e);
	}
	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		configF = e.getSuggestedConfigurationFile();
		Configuration config = new Configuration(configF);
		config.load();
		enabled = config.getBoolean("enabled","general",true, "is the mod enabled");
		includeGate = config.getBoolean("includeGate","general",false,"If true,Redstone Pipe will change color including gate output.");
		config.save();
		if(!enabled)
		{
			return;
		}
		pipeRedstone = BlockGenericPipe.registerPipe(PipeItemsRedstone.class,CreativeTabBuildCraft.PIPES);
		pipeRedstone.setUnlocalizedName("RedstonePipe");
		pipeGlass = BlockGenericPipe.registerPipe(PipeItemsGlass.class,CreativeTabBuildCraft.PIPES);
		pipeGlass.setUnlocalizedName("GlassPipe");
		pipeGoldMk2 = BlockGenericPipe.registerPipe(PipeItemsGoldenMk2.class,CreativeTabBuildCraft.PIPES);
		pipeGoldMk2.setUnlocalizedName("GoldenPipeMk2");
		proxy.onPreInit(e);
	}
}
