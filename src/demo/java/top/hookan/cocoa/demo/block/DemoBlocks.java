package top.hookan.cocoa.demo.block;

import net.minecraft.block.Block;
import top.hookan.cocoa.demo.Demo;
import top.hookan.cocoa.registry.annotation.CocoaReg;

@CocoaReg(Demo.class)
public class DemoBlocks
{
    @CocoaReg.Reg("demo")
    //@CocoaReg.ModelReg("demo")
    @CocoaReg.OreDicReg("demo")
    public static Block demoBlock = new BlockDemo();
}