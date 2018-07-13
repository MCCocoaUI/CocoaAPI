package top.hookan.cocoa.gui;

import com.google.common.eventbus.EventBus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class CocoaGuiUtils
{
    private static IResourcePack mcPack = Minecraft.getMinecraft().mcDefaultResourcePack;
    
    public static final EventBus EVENT_BUS = new EventBus();
    
    public static TTFRender loadTTF(InputStream input, String name) throws IOException, FontFormatException
    {
        return new TTFRender(input, name);
    }
    
    public static TTFRender loadTTF(ResourceLocation location, String name) throws IOException, FontFormatException
    {
        return loadTTF(mcPack.getInputStream(location), name);
    }
    
    public static GifRender loadGif(InputStream input, String name) throws IOException
    {
        return new GifRender(input, name);
    }
    
    public static GifRender loadGif(ResourceLocation location, String name) throws IOException
    {
        return loadGif(mcPack.getInputStream(location), name);
    }
    
    public static void drawPic(double x, double y, double w, double h, double u, double v, double tw, double th, double twm, double thm)
    {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2d(u / twm, v / twm);
        GL11.glVertex2d(x, y);
        GL11.glTexCoord2d(u / twm, (v + th) / thm);
        GL11.glVertex2d(x, y + h);
        GL11.glTexCoord2d((u + tw) / twm, (v + th) / thm);
        GL11.glVertex2d(x + w, y + h);
        GL11.glTexCoord2d((u + tw) / twm, v / thm);
        GL11.glVertex2d(x + w, y);
        GL11.glEnd();
    }
}
