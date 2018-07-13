package top.hookan.cocoa.demo;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import top.hookan.cocoa.demo.proxy.CommonProxy;

@Mod(modid = Demo.MODID, name = Demo.NAME, dependencies = "required-after:cocoa_api;")
public class Demo
{
    public static final String MODID = "cocoa_api_demo";
    public static final String NAME = "Cocoa API Demo";
    
    @Mod.Instance(MODID)
    public static Demo instance;
    
    @SidedProxy(clientSide = "top.hookan.cocoa.demo.proxy.ClientProxy",
            serverSide = "top.hookan.cocoa.demo.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }
}
