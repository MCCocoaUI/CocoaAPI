package top.hookan.cocoa.gui;

import java.util.HashSet;
import java.util.Set;

public abstract class CContainer extends CComponent
{
    protected CContainerManager manager;

    protected Set<CComponent> components;

    public CContainer(String type)
    {
        super(type);
        components = new HashSet<>();
    }

    void setManager(CContainerManager manager)
    {
        this.manager = manager;
    }

    public void doDraw()
    {
        manager.pushContainer(this);

        if (!components.isEmpty())
        {
            for (CComponent component : components)
            {
                if (component instanceof CContainer)
                    ((CContainer) component).setManager(manager);
                component.doDraw();
            }
        }

        manager.popContainer();
    }
}
