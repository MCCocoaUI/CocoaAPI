package top.hookan.cocoa.gui;

public abstract class CComponent
{
    public String name;
    public double x;
    public double y;
    public double width;
    public double height;
    public boolean hover;
    public final String type;
    protected boolean didMouseEnter;
    
    public CComponent(String type)
    {
        this.type = type;
    }
    
    public abstract void doDraw();
    
    public void onMouseEntered()
    {
        didMouseEnter = true;
    }
    
    public void onMouseExited()
    {
        didMouseEnter = false;
    }
    
    public void onMousePressed()
    {
    
    }
    
    public void mouseReleased()
    {
    
    }
}
