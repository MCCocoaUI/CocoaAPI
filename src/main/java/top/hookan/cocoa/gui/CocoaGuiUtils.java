package top.hookan.cocoa.gui;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class CocoaGuiUtils
{
    public static TTFRender loadTTF(InputStream ttfInput, String fontName) throws IOException, FontFormatException
    {
        return new TTFRender(ttfInput, fontName);
    }
    
    public static void drawPic(double x, double y, double w, double h, double u, double v, double tw, double th, double twm, double thm)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, y, 0.0).tex(u / twm, v / thm).endVertex();
        bufferbuilder.pos(x, y + h, 0.0).tex(u / twm, (v + th) / thm).endVertex();
        bufferbuilder.pos(x + w, y + h, 0.0).tex((u + tw) / twm, (v + th) / thm).endVertex();
        bufferbuilder.pos(x + w, y, 0.0).tex((u + tw) / twm, v / thm).endVertex();
        tessellator.draw();
    }
}
