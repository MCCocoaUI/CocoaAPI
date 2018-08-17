package top.hookan.cocoa.gui.control;

/**
 * 标签
 */
public class CLabel extends CControl
{
    public String text;

    public CLabel(String text, int x, int y, int width, int height)
    {
        super("label");
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
