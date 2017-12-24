package djsrv.geneticengineering;

import djsrv.geneticengineering.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = GeneticEngineering.MODID, version = GeneticEngineering.VERSION)
public class GeneticEngineering {

    public static final String MODID = "geneticengineering";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "djsrv.geneticengineering.client.ClientProxy", serverSide = "djsrv.geneticengineering.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static GeneticEngineering instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
