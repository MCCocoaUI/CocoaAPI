package top.hookan.cocoa.gui;

import java.util.ArrayList;
import java.util.List;

public class CPanel extends CContainer {
    //控件的List
    public List<CComponent> componentList = new ArrayList<>();

    public CPanel()
    {
        super("panel");
    }

    public void doDraw()
    {
    
    }
}
