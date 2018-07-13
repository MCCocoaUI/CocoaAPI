package top.hookan.cocoa.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import top.hookan.cocoa.CocoaAPI;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class GifRender
{
    private int frames = 0;
    private ResourceLocation framesRL[];
    private TextureManager renderEngine;
    
    GifRender(InputStream input, String name) throws IOException
    {
        ImageInputStream stream = ImageIO.createImageInputStream(input);
        Iterator<ImageReader> readers = ImageIO.getImageReaders(stream);
        renderEngine = Minecraft.getMinecraft().renderEngine;
        if (readers.hasNext())
        {
            ImageReader reader = readers.next();
            reader.setInput(stream);
            frames = reader.getNumImages(true);
            framesRL = new ResourceLocation[frames];
            for (int i = 0; i < frames; i++)
            {
                framesRL[i] = new ResourceLocation(CocoaAPI.MODID, "gif_" + name + "_" + i);
                BufferedImage image = reader.read(i);
                DynamicTexture dt = new DynamicTexture(image.getWidth(), image.getHeight());
                renderEngine.loadTexture(framesRL[i], dt);
                image.getRGB(0, 0, image.getWidth(), image.getHeight(), dt.getTextureData(), 0, image.getWidth());
                dt.updateDynamicTexture();
            }
        }
    }
    
    public int getFrames()
    {
        return frames;
    }
    
    public void bindFrame(int frame)
    {
        if (frame >= frames) return;
        renderEngine.bindTexture(framesRL[frame]);
    }
    
    public void doDraw(int frame, double x, double y, double w, double h, double u, double v, double tw, double th, double twm, double thm)
    {
        if (frame >= frames) return;
        bindFrame(frame);
        CocoaGuiUtils.drawPic(x, y, w, h, u, v, tw, th, twm, thm);
    }
}
