package top.hookan.cocoa.gui;

import top.hookan.cocoa.gui.event.CComponentEvent;

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
    
    void onMouseEntered(int mouseX, int mouseY)
    {
        didMouseEnter = true;
        CocoaGuiUtils.EVENT_BUS.post(new CComponentEvent.MouseEntered(this, mouseX, mouseY));
    }
    
    void onMouseExited(int mouseX, int mouseY)
    {
        didMouseEnter = false;
        CocoaGuiUtils.EVENT_BUS.post(new CComponentEvent.MouseEntered(this, mouseX, mouseY));
    }
    
    protected void onMousePressed(int mouseX, int mouseY)
    {
        CocoaGuiUtils.EVENT_BUS.post(new CComponentEvent.MousePressed(this, mouseX, mouseY));
    }
    
    void onMouseReleased(int mouseX, int mouseY)
    {
        if (!didMouseEnter) return;
        CComponentEvent.MouseReleased event = new CComponentEvent.MouseReleased(this, mouseX, mouseY);
        CocoaGuiUtils.EVENT_BUS.post(event);
        if (event.isCanceled()) return;
        onMouseClicked(mouseX, mouseY);
    }
    
    protected void onMouseClicked(int mouseX, int mouseY)
    {
    
    }
}
