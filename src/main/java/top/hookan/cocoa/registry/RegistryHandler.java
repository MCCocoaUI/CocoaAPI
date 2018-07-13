package top.hookan.cocoa.registry;

import com.google.common.eventbus.Subscribe;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.command.ICommand;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import top.hookan.cocoa.registry.annotation.CocoaReg;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistryHandler
{
    private static List<String> regClassNameList = new ArrayList<>();
    private List<Class> regClassList = new ArrayList<>();
    private Map<Class, String> regClassModidMap = new HashMap<>();
    private Map<Class, Object> regClassModObjList = new HashMap<>();
    
    public static void checkClass(ClassNode classNode)
    {
        if (classNode.visibleAnnotations == null) return;
        for (AnnotationNode annNode : classNode.visibleAnnotations)
        {
            if (annNode.desc.equals("Ltop/hookan/cocoa/registry/annotation/CocoaReg;"))
            {
                regClassNameList.add(classNode.name.replaceAll("/", "."));
                break;
            }
        }
    }
    
    @Subscribe
    public void preInit(FMLPreInitializationEvent event)
    {
        for (String name : regClassNameList)
        {
            try
            {
                Class clazz = Class.forName(name);
                clazz.getClassLoader().loadClass(name);
                regClassList.add(clazz);
                CocoaReg reg = (CocoaReg) clazz.getAnnotation(CocoaReg.class);
                Mod modinfo = (Mod) reg.value().getAnnotation(Mod.class);
                if (modinfo == null) continue;
                regClassModidMap.put(clazz, modinfo.modid());
                Object modObj = null;
                for (Field field : reg.value().getFields())
                {
                    if (field.getAnnotation(Mod.Instance.class) != null)
                    {
                        modObj = field.get(null);
                        break;
                    }
                }
                regClassModObjList.put(clazz, modObj);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        for (Class clazz : regClassList)
        {
            try
            {
                for (Field field : clazz.getFields())
                {
                    String modid = regClassModidMap.get(clazz);
                    if (field.getAnnotation(CocoaReg.Reg.class) != null)
                    {
                        CocoaReg.Reg regInfo = field.getAnnotation(CocoaReg.Reg.class);
                        Object obj = field.get(null);
                        if (obj instanceof IForgeRegistryEntry.Impl)
                        {
                            
                            GameRegistry.findRegistry(((IForgeRegistryEntry.Impl) obj).getRegistryType()).register(((IForgeRegistryEntry.Impl) obj).setRegistryName(modid + ":" + regInfo.value()));
                            System.out.println(((IForgeRegistryEntry.Impl) obj).getRegistryName());
                            System.out.println(modid + ":" + regInfo.value());
                        }
                        if (obj instanceof Block)
                        {
                            ForgeRegistries.ITEMS.register(new ItemBlock((Block) obj).setRegistryName(modid + ":" + regInfo.value()));
                        }
                    }
                    if (event.getSide().isClient())
                    {
                        if (field.getAnnotation(CocoaReg.ModelReg.class) != null)
                        {
                            CocoaReg.ModelReg regInfo = field.getAnnotation(CocoaReg.ModelReg.class);
                            Object obj = field.get(null);
                            if (obj instanceof Block)
                            {
                                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock((Block) obj), 0,
                                        new ModelResourceLocation(modid + ":" + regInfo.value(), "inventory"));
                            }
                            if (obj instanceof Item)
                            {
                                ModelLoader.setCustomModelResourceLocation((Item) obj, 0,
                                        new ModelResourceLocation(modid + ":" + regInfo.value(), "inventory"));
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        
    }
    
    @Subscribe
    public void init(FMLInitializationEvent event)
    {
        for (Class clazz : regClassList)
        {
            try
            {
                for (Field field : clazz.getFields())
                {
                    if (field.getAnnotation(CocoaReg.OreDicReg.class) != null)
                    {
                        CocoaReg.OreDicReg oredicInfo = field.getAnnotation(CocoaReg.OreDicReg.class);
                        Object obj = field.get(null);
                        if (obj instanceof Item)
                        {
                            OreDictionary.registerOre(oredicInfo.value(), (Item) obj);
                        }
                        else if (obj instanceof Block)
                        {
                            OreDictionary.registerOre(oredicInfo.value(), (Block) obj);
                        }
                        else if (obj instanceof ItemStack)
                        {
                            OreDictionary.registerOre(oredicInfo.value(), (ItemStack) obj);
                        }
                    }
                    if (field.getAnnotation(CocoaReg.DimReg.class) != null)
                    {
                        CocoaReg.DimReg dimInfo = field.getAnnotation(CocoaReg.DimReg.class);
                        DimensionManager.registerDimension(dimInfo.value(), (DimensionType) field.get(null));
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    @Subscribe
    public void serverStarting(FMLServerStartingEvent event)
    {
        for (Class clazz : regClassList)
        {
            try
            {
                for (Field field : clazz.getFields())
                {
                    if (field.getAnnotation(CocoaReg.CommandReg.class) != null)
                    {
                        event.registerServerCommand((ICommand) field.get(null));
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
