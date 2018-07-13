package top.hookan.cocoa.core;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import top.hookan.cocoa.registry.RegistryHandler;

import java.util.Arrays;

public class CoreContainer extends DummyModContainer
{
    public CoreContainer()
    {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "cocoa_api_core";
        meta.name = "Cocoa API Core";
        meta.version = "0.1.0";
        meta.authorList = Arrays.asList("huangtian_hookan");
        meta.description = "Cocoa API Core.";
    }
    
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        bus.register(new RegistryHandler());
        return true;
    }
}
