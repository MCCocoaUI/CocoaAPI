package top.hookan.cocoa.core;

import net.minecraftforge.fml.common.discovery.asm.ASMModParser;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import top.hookan.cocoa.core.annotation.CocoaHook;
import top.hookan.cocoa.registry.RegistryHandler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;

public class CoreHooks extends CocoaTransformer
{
    @CocoaHook(owner = "net.minecraftforge.fml.common.discovery.asm.ASMModParser",
            mcp = "<init>:(Ljava/io/InputStream;)V",
            notch = "<init>:(Ljava/io/InputStream;)V",
            type = CocoaHook.HookType.BEGIN)
    public static void checkClass(ASMModParser parser, InputStream input)
    {
        try
        {
            if (input instanceof FileInputStream)
            {
                Class clazz = input.getClass();
                Field field = clazz.getDeclaredField("path");
                field.setAccessible(true);
                String path = (String) field.get(input);
                System.out.println(path);
                input = new FileInputStream(path);
                ClassNode classNode = new ClassNode();
                ClassReader reader = new ClassReader(input);
                reader.accept(classNode, 0);
                
                RegistryHandler.checkClass(classNode);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
