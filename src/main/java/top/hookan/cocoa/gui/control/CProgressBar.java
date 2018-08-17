package top.hookan.cocoa.gui.control;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

/**
 * 进度条
 */
public class CProgressBar extends CControl
{
    public double progress;

    public ResourceLocation barImage, progressImage;

    public CProgressBar(int x, int y, int width, int height)
    {
        this(0, x, y, width, height);
    }

    public CProgressBar(double progress, int x, int y, int width, int height)
    {
        super("progressbar");
        this.progress = progress;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * 设置当前进度
     * @param progress
     */
    public void setProgress(double progress)
    {
        this.progress = progress;
    }

    public void setBarImage(ResourceLocation barImage)
    {
        this.barImage = barImage;
    }

    public void setProgressImage(ResourceLocation progressImage)
    {
        this.progressImage = progressImage;
    }

    /**
     * 渲染进度条背景
     */
    public void drawBar(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight)
    {
        mc.renderEngine.bindTexture(barImage);
        Gui.drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, textureWidth, textureHeight);
    }

    /**
     * 渲染进度背景
     */
    public void drawProgress(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight)
    {
        mc.renderEngine.bindTexture(progressImage);
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
