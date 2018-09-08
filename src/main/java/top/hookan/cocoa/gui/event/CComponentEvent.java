package top.hookan.cocoa.gui.event;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import top.hookan.cocoa.gui.CComponent;

public class CComponentEvent extends CocoaGuiEvent
{
    public final CComponent component;

    public CComponentEvent(CComponent component)
    {
        this.component = component;
    }

    public static class MousePressed extends CComponentEvent
    {
        public final int mouseX;
        public final int mouseY;

        public MousePressed(CComponent component, int mouseX, int mouseY)
        {
            super(component);
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }

    @Cancelable
    public static class MouseReleased extends CComponentEvent
    {
        public final int mouseX;
        public final int mouseY;

        public MouseReleased(CComponent component, int mouseX, int mouseY)
        {
            super(component);
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }

    public static class MouseEntered extends CComponentEvent
    {
        public final int mouseX;
        public final int mouseY;

        public MouseEntered(CComponent component, int mouseX, int mouseY)
        {
            super(component);
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }

    public static class MouseExited extends CComponentEvent
    {
        public final int mouseX;
        public final int mouseY;

        public MouseExited(CComponent component, int mouseX, int mouseY)
        {
            super(component);
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }
}
