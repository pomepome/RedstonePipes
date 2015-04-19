package redstonepipes;

import java.io.File;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import redstonepipes.pipes.PipeAdvancedInsertion;
import redstonepipes.pipes.PipeFluidRedstone;
import redstonepipes.pipes.PipeItemsGlass;
import redstonepipes.pipes.PipeItemsGoldenMk2;
import redstonepipes.pipes.PipeItemsRedstone;
import buildcraft.core.CreativeTabBuildCraft;
import buildcraft.transport.BlockGenericPipe;
import buildcraft.transport.Pipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "RedstonePipes",name="RedstonePipes",version="1.0",dependencies="required-after:BuildCraft|Transport")
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
	public static Item pipeFluidRedstone;
	public static Item pipeAdvancedInsertion;
	/*
	 * Misc
	 */
	public static boolean enabled;
	public static boolean includeGate;
	public static boolean connectAny;
	public static boolean aiConnectAny;

	@Mod.Instance("RedstonePipes")
	public static RedstonePipes instance;
	@SidedProxy(clientSide="redstonepipes.ClientProxy",serverSide="redstonepipes.CommonProxy")
	public static CommonProxy proxy;
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
		connectAny = config.getBoolean("connectAny","general",false,"If true,this mod's pipes will connect to any pipes/machines");
		aiConnectAny = config.getBoolean("aiConnectCobblestone","general",false,"If true,Advanced insertion pipe will connect to cobblestone pipes.");
		config.save();
		if(!enabled)
		{
			return;
		}
		pipeRedstone = registerPipe(PipeItemsRedstone.class,"RedstonePipe");
		pipeGlass = registerPipe(PipeItemsGlass.class,"GlassPipe");
		pipeGoldMk2 = registerPipe(PipeItemsGoldenMk2.class,"GoldenPipeMk2");
		pipeFluidRedstone = registerPipe(PipeFluidRedstone.class,"FluidRedstonePipe");
		pipeAdvancedInsertion = registerPipe(PipeAdvancedInsertion.class,"AdvancedInsertionPipe");
		proxy.onPreInit(e);
	}
	public Item registerPipe(Class<? extends Pipe<?>> pipeClass,String unlocalizedName)
	{
		Item pipe = BlockGenericPipe.registerPipe(pipeClass,CreativeTabBuildCraft.PIPES);
		pipe.setUnlocalizedName(unlocalizedName);
		return pipe;
	}
	public static int divideAndCeil(int dividend, int divisor)
	{
		//切り上げ
	    return (dividend + divisor - 1) / divisor;
	}
}