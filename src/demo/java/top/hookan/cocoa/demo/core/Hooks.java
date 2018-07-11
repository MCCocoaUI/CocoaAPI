package top.hookan.cocoa.demo.core;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import top.hookan.cocoa.core.CocoaTransformer;
import top.hookan.cocoa.core.annotation.CocoaHook;

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
}
