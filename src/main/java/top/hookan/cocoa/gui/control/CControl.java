package top.hookan.cocoa.gui.control;

import net.minecraft.client.Minecraft;
import top.hookan.cocoa.gui.CComponent;

/**
 * 控件基类
 */
public abstract class CControl extends CComponent
{
    public Minecraft mc;

    public int x, y, width, height;

    public CControl(String type)
    {
        super(type);
        mc = Minecraft.getMinecraft();
        initGui();
    }

    /**
     * 初始化界面
     */
    public void initGui()
    {
    }

    @Override
    public void doDraw()
    {
    }
}
