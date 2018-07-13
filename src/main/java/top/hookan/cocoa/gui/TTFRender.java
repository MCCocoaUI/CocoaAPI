package top.hookan.cocoa.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import top.hookan.cocoa.CocoaAPI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SideOnly(Side.CLIENT)
public class TTFRender extends FontRenderer
{
    private Font ttf;
    private String fontName;
    private TextureManager renderEngine = Minecraft.getMinecraft().renderEngine;
    private Map<Character, ResourceLocation> charMap = new HashMap<>();
    private FontMetrics fontMetrics;
    
    TTFRender(InputStream ttfInput, String fontName) throws IOException, FontFormatException
    {
        super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().renderEngine, true);
        ttf = Font.createFont(Font.TRUETYPE_FONT, ttfInput);
        ttfInput.close();
        ttf = ttf.deriveFont(48.0f);
        this.fontName = fontName;
        fontMetrics = new Label().getFontMetrics(ttf);
    }
    
    private ResourceLocation getResourceLocation(char c)
    {
        if (charMap.containsKey(c)) return charMap.get(c);
        String str = String.valueOf(c);
        
        int width = 64;
        int height = 64;
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setClip(0, 0, width, height);
        g.setColor(Color.white);
        g.setFont(ttf);
        Rectangle clip = g.getClipBounds();
        
        int ascent = fontMetrics.getAscent();
        int descent = fontMetrics.getDescent();
        int y = (clip.height - (ascent + descent)) / 2 + ascent;
        
        g.drawString(str, 0, y);
        g.dispose();
        
        ResourceLocation rl = new ResourceLocation(CocoaAPI.MODID, "font_" + fontName + "_" + UUID.randomUUID());
        DynamicTexture dt = new DynamicTexture(image.getWidth(), image.getHeight());
        renderEngine.loadTexture(rl, dt);
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), dt.getTextureData(), 0, image.getWidth());
        dt.updateDynamicTexture();
        charMap.put(c, rl);
        return rl;
    }
    
    
    protected float renderUnicodeChar(char ch, boolean italic)
    {
        boolean f = ttf.canDisplay(ch);
        if (!f) return super.renderUnicodeChar(ch, italic);
        
        renderEngine.bindTexture(getResourceLocation(ch));
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        
        CocoaGuiUtils.drawPic(this.posX, this.posY, 15, 15, 0, 0, 1, 1, 1, 1);
        
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        
        return 15f * fontMetrics.charWidth(ch) / 64.0f;
    }
}
