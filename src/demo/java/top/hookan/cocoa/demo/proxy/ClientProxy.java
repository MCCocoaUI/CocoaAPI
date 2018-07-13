package top.hookan.cocoa.demo.proxy;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import top.hookan.cocoa.demo.Demo;
import top.hookan.cocoa.gui.CocoaGuiUtils;
import top.hookan.cocoa.gui.GifRender;
import top.hookan.cocoa.gui.TTFRender;

import java.awt.*;
import java.io.IOException;

public class ClientProxy extends CommonProxy
{
    public static TTFRender testTTF;
    public static GifRender testGif;
    
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        try
        {
            testTTF = CocoaGuiUtils.loadTTF(new ResourceLocation(Demo.MODID, "ttf/test.ttc"), "test");
            testGif = CocoaGuiUtils.loadGif(new ResourceLocation(Demo.MODID, "texture/test.gif"), "test");
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
