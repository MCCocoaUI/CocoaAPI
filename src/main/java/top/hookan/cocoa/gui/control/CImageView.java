package top.hookan.cocoa.gui.control;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

/**
 * 图片
 */
public class CImageView extends CControl
{
    public ResourceLocation image;

    public CImageView(String type, int x, int y, int width, int height)
    {
        super("imageview");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setImage(ResourceLocation image)
    {
        this.image = image;
    }

    public void drawImage(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight)
    {
        mc.renderEngine.bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, textureWidth, textureHeight);
    }

    @Override
    public void initGui()
    {
        super.initGui();
    }

    @Override
    public void doDraw()
    {
        super.doDraw();
    }
}
