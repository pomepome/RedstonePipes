package redstonepipes;

import java.io.File;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import redstonepipes.pipes.PipeItemsGlass;
import redstonepipes.pipes.PipeItemsGoldenMk2;
import redstonepipes.pipes.PipeItemsRedstone;
import buildcraft.core.CreativeTabBuildCraft;
import buildcraft.transport.BlockGenericPipe;
import buildcraft.transport.Pipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "RedstonePipes",name="RedstonePipes",version="0.4",dependencies="required-after:BuildCraft|Transport")
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
	public static boolean connectAny;

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
		enabled = config.get("general","enabled",true, "is the mod enabled").getBoolean(true);
		includeGate = config.get("general","includeGate",false,"If true,Redstone Pipe will change color including gate output.").getBoolean(false);
		connectAny = config.get("general","connectAny",false,"If true,this mod's pipes will connect to any pipes/machines").getBoolean(false);
		config.save();
		if(!enabled)
		{
			return;
		}
		pipeRedstone = registerPipe(PipeItemsRedstone.class,"RedstonePipe");
		pipeGlass = registerPipe(PipeItemsGlass.class,"GlassPipe");
		pipeGoldMk2 = registerPipe(PipeItemsGoldenMk2.class,"GoldenPipeMk2");
		proxy.onPreInit(e);
	}
	public Item registerPipe(Class<? extends Pipe<?>> clas,String unlocalizedName)
	{
		Item pipe = BlockGenericPipe.registerPipe(clas,CreativeTabBuildCraft.PIPES);
		pipe.setUnlocalizedName(unlocalizedName);
		return pipe;
	}
}
