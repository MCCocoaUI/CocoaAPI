package top.hookan.cocoa.core;

import jdk.internal.org.objectweb.asm.tree.ClassNode;
import net.minecraftforge.fml.common.discovery.asm.ASMModParser;
import top.hookan.cocoa.core.annotation.CocoaHook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;

public class CoreHooks extends CocoaTransformer
{
    @CocoaHook(owner = "net.minecraftforge.fml.common.discovery.asm.ASMModParser",
            mcp = "<init>:(Ljava/io/InputStream;)V",
            notch = "<init>:(Ljava/io/InputStream;)V",
            type = CocoaHook.HookType.BEGIN)
    public static void checkRegClass(ASMModParser parser, InputStream input)
    {
        try
        {
            if (input instanceof FileInputStream)
            {
                Class clazz = input.getClass();
                Field field = clazz.getDeclaredField("path");
                field.setAccessible(true);
                String path = (String) field.get(input);
                input = new FileInputStream(path);
                ClassNode classNode = new ClassNode();
                
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
