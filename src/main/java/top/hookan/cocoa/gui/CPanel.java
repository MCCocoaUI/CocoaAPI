package top.hookan.cocoa.gui;

import java.util.ArrayList;
import java.util.List;

public class CPanel extends CComponent {
    //控件的List
    public List<CComponent> componentList = new ArrayList<>();

    //容器是否可视
    private boolean visible;

    //容器是否禁用
    private boolean disable;


    public CPanel(String name, double x, double y, double width, double height)
    {
        this.type = "Panel";
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    void doDraw()
    {
        if (isVisible())
        {
            drawBackground();
            for (CComponent component : componentList)
            {
                component.doDraw();
            }
            if(hover)
            {
                drawTip();
            }
        }
    }

    @Override
    void drawTip()
    {
        //TODO 渲染控件提示
    }

    @Override
    void clickComponent()
    {
        //TODO 鼠标点击控件
    }

    @Override
    void stopClickComponent()
    {
        //TODO 鼠标松开点击
    }

    @Override
    void onMouseHover()
    {
        this.hover = true;
        //TODO 当鼠标放在控件上时
    }

    protected void drawBackground()
    {
        //TODO 渲染容器背景
    }

    public void addComponent(CComponent component)
    {
        this.componentList.add(component);
    }

    protected void componentClicked(double x, double y, String componentName)
    {
        if(!isDisable())
        {
            //TODO 控件点击
        }
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
