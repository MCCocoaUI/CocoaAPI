package top.hookan.cocoa.demo.core;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;
import org.lwjgl.opengl.GL11;
import top.hookan.cocoa.core.CocoaTransformer;
import top.hookan.cocoa.core.annotation.CocoaHook;
import top.hookan.cocoa.demo.proxy.ClientProxy;

public class Hooks extends CocoaTransformer
{
    @CocoaHook(owner = "net.minecraft.client.Minecraft",
            mcp = "clickMouse:()V",
            notch = "aA:()V",
            type = CocoaHook.HookType.END)
    public static void testFun1(Minecraft mc)
    {
        if (mc.player != null) mc.player.sendMessage(new TextComponentString("玩家点击鼠标 END"));
    }
    
    @CocoaHook(owner = "net.minecraft.client.Minecraft",
            mcp = "clickMouse:()V",
            notch = "aA:()V",
            type = CocoaHook.HookType.BEGIN)
    public static void testFun2(Minecraft mc)
    {
        if (mc.player != null) mc.player.sendMessage(new TextComponentString("玩家点击鼠标 BEGIN"));
    }
    
    @CocoaHook(owner = "net.minecraft.client.gui.GuiMainMenu",
            mcp = "drawScreen:(IIF)V",
            notch = "drawScreen:(IIF)V",
            type = CocoaHook.HookType.END)
    public static void testDraw(GuiMainMenu mainMenu, int mouseX, int mouseY, float partialTicks)
    {
        ClientProxy.testTTF.drawString("沉寂大佬欺负我嘤嘤嘤QAQ\u00A7aABCDEFG\u00A7cabcdefg", 0, 0, 0xFFFFFF00);
        
        /*GL11.glDisable(GL11.GL_TEXTURE_2D);
        
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(0.0f, 0.0f);
        GL11.glVertex2f(0.0f, 20.0f);
        GL11.glVertex2f(20.0f, 20.0f);
        GL11.glVertex2f(20.0f, 0.0f);
        GL11.glEnd();
        
        GL11.glEnable(GL11.GL_TEXTURE_2D);*/
    }
}
