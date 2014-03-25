package firstmod;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "FirstMod", name = "First Mod", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class FirstMod
{
   @SidedProxy(clientSide = "firstmod.ClientProxy", serverSide = "firstmod.CommonProxy")
   public static CommonProxy proxy;
   
   public static final Item itemPotion = new LucaPotion();
   
   @Init
   public void load(FMLInitializationEvent event)
   {
	   proxy.addNames();
	   proxy.addRecipes();
   }
}
