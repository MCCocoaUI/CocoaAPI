package top.hookan.cocoa.gui;

import net.minecraft.client.Minecraft;
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
    protected Minecraft mc;

    public CComponent(String type)
    {
        this.type = type;
        mc = Minecraft.getMinecraft();
    }

    public abstract void doDraw();

    void onMouseEntered(int mouseX, int mouseY)
    {
        didMouseEnter = true;
        mc.addScheduledTask(() -> CocoaGuiUtils.EVENT_BUS.post(new CComponentEvent.MouseEntered(this, mouseX, mouseY)));
    }

    void onMouseExited(int mouseX, int mouseY)
    {
        didMouseEnter = false;
        mc.addScheduledTask(() -> CocoaGuiUtils.EVENT_BUS.post(new CComponentEvent.MouseEntered(this, mouseX, mouseY)));
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

    boolean isMouseIn(int mouseX, int mouseY)
    {
        return mouseX >= x && mouseY <= y && mouseX - x <= width && mouseY - y <= height;
    }
}
