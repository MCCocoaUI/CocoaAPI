package top.hookan.cocoa.demo.core;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

import java.util.Arrays;

public class Container extends DummyModContainer
{
    public Container()
    {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "cocoa_api_demo_core";
        meta.name = "Cocoa API Demo Core";
        meta.authorList = Arrays.asList("huangtian_hookan","KevinWalker");
    }
    
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        return true;
    }
}
