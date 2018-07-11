package top.hookan.cocoa.core;

import net.minecraftforge.fml.common.discovery.asm.ASMModParser;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import top.hookan.cocoa.core.annotation.CocoaHook;
import top.hookan.cocoa.registry.RegistryHandler;

import java.io.InputStream;

public class CoreHooks extends CocoaTransformer
{
    @CocoaHook(owner = "net.minecraftforge.fml.common.discovery.asm.ASMModParser",
            mcp = "<init>:(Ljava/io/InputStream;)V",
            notch = "<init>:(Ljava/io/InputStream;)V",
            type = CocoaHook.HookType.EXTRA_LOAD,
            extra = "ALOAD_2")
    public static void checkClass(ASMModParser parser, InputStream input, ClassReader reader)
    {
        try
        {
            ClassNode classNode = new ClassNode();
            reader.accept(classNode, 0);
            
            RegistryHandler.checkClass(classNode);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
