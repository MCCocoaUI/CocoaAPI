package top.hookan.cocoa.gui.control;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

/**
 * 按钮
 */
public class CButton extends CControl
{
    public String text;

    public ResourceLocation buttonImage;

    public CButton(String text, int x, int y, int width, int height)
    {
        super("button");
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * 设置按钮背景
     *
     * @param buttonImage 图片资源
     */
    public void setButtonImage(ResourceLocation buttonImage)
    {
        this.buttonImage = buttonImage;
    }

    /**
     * 渲染按钮背景
     */
    public void drawButtonImage(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight)
    {
        mc.renderEngine.bindTexture(buttonImage);
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
    }
}
