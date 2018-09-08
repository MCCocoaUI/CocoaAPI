package top.hookan.cocoa.gui;

import net.minecraft.client.gui.GuiScreen;

import java.util.List;

public class CGui extends GuiScreen
{
    public CGui()
    {

    }

    private CContainerManager containerManager;
    private List<CComponent> components;

    public void initGui()
    {
        containerManager = new CContainerManager();

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        for (CComponent component : components)
        {
            if (component.didMouseEnter)
            {
                if (!component.isMouseIn(mouseX, mouseY))
                    component.onMouseExited(mouseX, mouseY);
            }
            else
            {
                if (component.isMouseIn(mouseX, mouseY))
                    component.onMouseEntered(mouseX, mouseY);
            }
        }

        for (CComponent component : components)
        {
            if (component instanceof CContainer)
                ((CContainer) component).setManager(containerManager);
            component.doDraw();
        }
    }
}
