package top.hookan.cocoa.gui;

import java.util.Stack;

public class CContainerManager
{
    private Stack<CContainer> containerStack = new Stack<>();


    public void pushContainer(CContainer container)
    {
        containerStack.push(container);

    }

    public void popContainer()
    {
        containerStack.pop();
    }
}
