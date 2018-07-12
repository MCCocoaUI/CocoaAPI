package top.hookan.cocoa.demo.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import top.hookan.cocoa.gui.CocoaGuiUtils;
import top.hookan.cocoa.gui.TTFRender;

import java.awt.*;
import java.io.IOException;

public class ClientProxy extends CommonProxy
{
    public static TTFRender testTTF;
    
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        try
        {
            testTTF = CocoaGuiUtils.loadTTF(this.getClass().getResourceAsStream("/assets/cocoa_api_demo/ttf/test.ttf"), "test");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (FontFormatException e)
        {
            e.printStackTrace();
        }
    }
}
