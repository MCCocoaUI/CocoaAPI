package top.hookan.cocoa.core;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import top.hookan.cocoa.core.annotation.CocoaHook;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public abstract class CocoaTransformer implements IClassTransformer
{
    private Set<Hook> hookSet;
    private Set<String> transformClassSet;
    
    public CocoaTransformer()
    {
        hookSet = new HashSet<>();
        transformClassSet = new HashSet<>();
        String path = getClass().getName().replace('.', '/');
        try (InputStream input = getClass().getResourceAsStream("/" + path + ".class"))
        {
            ClassNode classNode = new ClassNode();
            ClassReader reader = new ClassReader(input);
            reader.accept(classNode, 0);
            for (MethodNode methodNode : classNode.methods)
            {
                if (methodNode.visibleAnnotations == null) continue;
                for (AnnotationNode annNode : methodNode.visibleAnnotations)
                {
                    if (annNode.desc.equals("Ltop/hookan/cocoa/core/annotation/CocoaHook;"))
                    {
                        Object objs[] = annNode.values.toArray();
                        Hook hook = new Hook();
                        hook.hookOwner = path;
                        hook.hookName = methodNode.name;
                        hook.hookDesc = methodNode.desc;
                        String args[];
                        for (int i = 0; i < objs.length; i += 2)
                        {
                            switch (objs[i].toString())
                            {
                                case "owner":
                                    hook.owner = objs[i + 1].toString();
                                    transformClassSet.add(objs[i + 1].toString());
                                    break;
                                case "mcp":
                                    args = objs[i + 1].toString().split(":");
                                    hook.mcpName = args[0];
                                    hook.mcpDesc = args[1];
                                    break;
                                case "notch":
                                    args = objs[i + 1].toString().split(":");
                                    hook.notchName = args[0];
                                    hook.notchDesc = args[1];
                                    break;
                                case "type":
                                    args = (String[]) objs[i + 1];
                                    if (args[1].equals("BEGIN_WITH_RETURN"))
                                    {
                                        hook.type = CocoaHook.HookType.BEGIN_WITH_RETURN;
                                    }
                                    else if (args[1].equals("BEGIN"))
                                    {
                                        hook.type = CocoaHook.HookType.BEGIN;
                                    }
                                    else if (args[1].equals("END"))
                                    {
                                        hook.type = CocoaHook.HookType.END;
                                    }
                                    else
                                    {
                                        hook.type = CocoaHook.HookType.EXTRA_LOAD;
                                    }
                                    break;
                                case "extra":
                                    hook.extra = objs[i + 1].toString();
                                    break;
                            }
                        }
                        hookSet.add(hook);
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public byte[] transform(String name, String transformedName, byte[] basicClass)
    {
        if (!transformClassSet.contains(name)) return basicClass;
        
        ClassNode classNode = new ClassNode();
        ClassReader reader = new ClassReader(basicClass);
        reader.accept(classNode, 0);
        
        Set<Hook> acHookSet = new HashSet<>();
        for (Hook hook : hookSet)
        {
            if (hook.owner.equals(transformedName)) acHookSet.add(hook);
        }
        
        for (MethodNode methodNode : classNode.methods)
        {
            for (Hook hook : acHookSet)
            {
                if (hook.mcpName.equals(methodNode.name) && hook.mcpDesc.equals(methodNode.desc) ||
                        hook.notchName.equals(methodNode.name) && hook.notchDesc.equals(methodNode.desc))
                {
                    InsnList insnList = new InsnList();
                    if ((methodNode.access & Opcodes.ACC_STATIC) != 0)
                    {
                        String desc = methodNode.desc;
                        char[] chars = desc.toCharArray();
                        int var = 0;
                        for (int i = 1; i < chars.length; i++)
                        {
                            if (chars[i] == 'L')
                            {
                                insnList.add(new VarInsnNode(Opcodes.ALOAD, var));
                                var++;
                                while (chars[i] != ';') i++;
                            }
                            else if (chars[i] == 'B' || chars[i] == 'S' || chars[i] == 'I' || chars[i] == 'Z' || chars[i] == 'C')
                            {
                                insnList.add(new VarInsnNode(Opcodes.ILOAD, var));
                                var++;
                            }
                            else if (chars[i] == 'J')
                            {
                                insnList.add(new VarInsnNode(Opcodes.LLOAD, var));
                                var += 2;
                            }
                            else if (chars[i] == 'F')
                            {
                                insnList.add(new VarInsnNode(Opcodes.FLOAD, var));
                                var++;
                            }
                            else if (chars[i] == 'D')
                            {
                                insnList.add(new VarInsnNode(Opcodes.DLOAD, var));
                                var += 2;
                            }
                            else if (chars[i] == ')') break;
                        }
                        if (var > methodNode.maxStack) methodNode.maxStack = var;
                    }
                    else
                    {
                        insnList.add(new VarInsnNode(Opcodes.ALOAD, 0));
                        String desc = methodNode.desc;
                        char[] chars = desc.toCharArray();
                        int var = 1;
                        for (int i = 1; i < chars.length; i++)
                        {
                            if (chars[i] == 'L')
                            {
                                insnList.add(new VarInsnNode(Opcodes.ALOAD, var));
                                var++;
                                while (chars[i] != ';') i++;
                            }
                            else if (chars[i] == 'B' || chars[i] == 'S' || chars[i] == 'I' || chars[i] == 'Z' || chars[i] == 'C')
                            {
                                insnList.add(new VarInsnNode(Opcodes.ILOAD, var));
                                var++;
                            }
                            else if (chars[i] == 'J')
                            {
                                insnList.add(new VarInsnNode(Opcodes.LLOAD, var));
                                var += 2;
                            }
                            else if (chars[i] == 'F')
                            {
                                insnList.add(new VarInsnNode(Opcodes.FLOAD, var));
                                var++;
                            }
                            else if (chars[i] == 'D')
                            {
                                insnList.add(new VarInsnNode(Opcodes.DLOAD, var));
                                var += 2;
                            }
                            else if (chars[i] == ')') break;
                        }
                        if (var > methodNode.maxStack) methodNode.maxStack = var;
                    }
                    MethodInsnNode mInsnNode = new MethodInsnNode(Opcodes.INVOKESTATIC, hook.hookOwner, hook.hookName, hook.hookDesc, false);
                    insnList.add(mInsnNode);
                    switch (hook.type)
                    {
                        case BEGIN_WITH_RETURN:
                            AbstractInsnNode returnNode = new InsnNode(methodNode.instructions.getLast().getPrevious().getOpcode());
                            insnList.add(returnNode);
                            methodNode.instructions = insnList;
                            break;
                        case BEGIN:
                            if (methodNode.name.equals("<init>"))
                            {
                                for (AbstractInsnNode aNode : methodNode.instructions.toArray())
                                {
                                    if (aNode.getOpcode() == Opcodes.INVOKESPECIAL)
                                    {
                                        methodNode.instructions.insert(aNode, insnList);
                                        break;
                                    }
                                }
                            }
                            else
                            {
                                methodNode.instructions.insert(methodNode.instructions.getFirst().getNext(), insnList);
                            }
                            break;
                        case END:
                            methodNode.instructions.insertBefore(methodNode.instructions.getLast().getPrevious(), insnList);
                            break;
                        case EXTRA_LOAD:
                            String args[] = hook.extra.split("_");
                            int opc = Integer.MIN_VALUE;
                            switch (args[0])
                            {
                                case "ALOAD":
                                    opc = Opcodes.ASTORE;
                                    insnList.insertBefore(insnList.getLast(), new VarInsnNode(Opcodes.ALOAD, Integer.parseInt(args[1])));
                                    break;
                                case "ILOAD":
                                    opc = Opcodes.ISTORE;
                                    insnList.insertBefore(insnList.getLast(), new VarInsnNode(Opcodes.ILOAD, Integer.parseInt(args[1])));
                                    break;
                                case "LLOAD":
                                    opc = Opcodes.LSTORE;
                                    insnList.insertBefore(insnList.getLast(), new VarInsnNode(Opcodes.LLOAD, Integer.parseInt(args[1])));
                                    break;
                                case "FLOAD":
                                    opc = Opcodes.FSTORE;
                                    insnList.insertBefore(insnList.getLast(), new VarInsnNode(Opcodes.FLOAD, Integer.parseInt(args[1])));
                                    break;
                                case "DLOAD":
                                    opc = Opcodes.DSTORE;
                                    insnList.insertBefore(insnList.getLast(), new VarInsnNode(Opcodes.DLOAD, Integer.parseInt(args[1])));
                                    break;
                            }
                            for (AbstractInsnNode aNode : methodNode.instructions.toArray())
                            {
                                if (aNode.getOpcode() == opc)
                                {
                                    methodNode.instructions.insert(aNode, insnList);
                                    break;
                                }
                            }
                            break;
                    }
                }
            }
        }
        ClassWriter writer = new ClassWriter(Opcodes.ASM5);
        classNode.accept(writer);
        byte[] transformedClass = writer.toByteArray();
        return transformedClass;
    }
    
    private static class Hook
    {
        String hookName;
        String hookOwner;
        String hookDesc;
        String owner;
        String mcpName;
        String mcpDesc;
        String notchName;
        String notchDesc;
        String extra;
        CocoaHook.HookType type;
    }
}


