package top.hookan.cocoa.gui.control;

import java.util.ArrayList;

/**
 * 选择框
 */
public class CChoiceBox extends CControl
{
    public ArrayList<String> items = new ArrayList<>();

    public CChoiceBox(int x, int y, int width, int height)
    {
        super("choicebox");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void addItems(String item)
    {
        items.add(item);
    }

    public void setItems(ArrayList<String> items)
    {
        this.items = items;
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
