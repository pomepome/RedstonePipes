package redstonepipes;

import java.io.File;

import buildcraft.core.BCCreativeTab;
import buildcraft.transport.BlockGenericPipe;
import buildcraft.transport.Pipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.config.Configuration;
import redstonepipes.blocks.BlockTeleportether;
import redstonepipes.blocks.ForgeTicketHandler;
import redstonepipes.pipes.PipeAdvancedInsertion;
import redstonepipes.pipes.PipeFluidRedstone;
import redstonepipes.pipes.PipeItemsGlass;
import redstonepipes.pipes.PipeItemsGoldenMk2;
import redstonepipes.pipes.PipeItemsRedstone;

@Mod(modid = "RedstonePipes",name="RedstonePipes",version="1.0",dependencies="required-after:BuildCraft|Transport@[7.0.3,)")
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
	 * Blocks
	 */
	public static Block TeleportTether;
	/*
	 * Misc
	 */
	public static boolean enabled;
	public static boolean includeGate;
	public static boolean connectAny;
	public static boolean aiConnectAny;

	public BCCreativeTab tab;

	@Mod.Instance("RedstonePipes")
	public static RedstonePipes instance;
	@SidedProxy(clientSide="redstonepipes.ClientProxy",serverSide="redstonepipes.CommonProxy")
	public static CommonProxy proxy;
	@EventHandler
	public void init(FMLInitializationEvent e)
	{
		proxy.onInit(e);
		ForgeChunkManager.setForcedChunkLoadingCallback(this,new ForgeTicketHandler());
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
		tab = new BCCreativeTab("RedstonePipes");
		pipeRedstone = registerPipe(PipeItemsRedstone.class,"RedstonePipe");
		pipeGlass = registerPipe(PipeItemsGlass.class,"GlassPipe");
		pipeGoldMk2 = registerPipe(PipeItemsGoldenMk2.class,"GoldenPipeMk2");
		pipeFluidRedstone = registerPipe(PipeFluidRedstone.class,"FluidRedstonePipe");
		pipeAdvancedInsertion = registerPipe(PipeAdvancedInsertion.class,"AdvancedInsertionPipe");
		TeleportTether = new BlockTeleportether();
		tab.setIcon(new ItemStack(pipeRedstone));
		proxy.onPreInit(e);
	}
	public Item registerPipe(Class<? extends Pipe<?>> pipeClass,String unlocalizedName)
	{
		Item pipe = BlockGenericPipe.registerPipe(pipeClass,tab);
		pipe.setUnlocalizedName(unlocalizedName);
		return pipe;
	}
	public static int divideAndCeil(int dividend, int divisor)
	{
		//切り上げ
	    return (dividend + divisor - 1) / divisor;
	}
}