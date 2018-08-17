package top.hookan.cocoa.gui.control;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

/**
 * 复选框
 */
public class CCheckBox extends CControl
{
    public boolean check;

    public ResourceLocation checkImage, uncheckImage;

    public CCheckBox(boolean check, int x, int y, int width, int height)
    {
        super("checkbox");
        this.check = check;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * 设置是否选择
     *
     * @param check
     */
    public void setCheck(boolean check)
    {
        this.check = check;
    }

    /**
     * 渲染复选按钮背景,如果check为true，则渲染选择的图，反之渲染未选择的图。
     */
    public void drawCheckBox(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight)
    {
        if (check)
        {
            mc.renderEngine.bindTexture(checkImage);
            Gui.drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, textureWidth, textureHeight);
        }
        else
        {
            mc.renderEngine.bindTexture(uncheckImage);
            Gui.drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, textureWidth, textureHeight);
        }
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
