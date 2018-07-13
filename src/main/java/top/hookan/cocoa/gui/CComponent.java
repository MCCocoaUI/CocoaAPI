package top.hookan.cocoa.gui;

public abstract class CComponent
{
    public String name;
    public double x;
    public double y;
    public double width;
    public double height;
    public String type;
    public  boolean hover;

    abstract void doDraw();

    abstract void drawTip();

    abstract void clickComponent();

    abstract void stopClickComponent();

    abstract void onMouseHover();
}
