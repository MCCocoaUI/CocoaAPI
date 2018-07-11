package top.hookan.cocoa.demo.core;

import net.minecraft.client.Minecraft;
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
        System.out.println("玩家攻击！END");
    }
}
