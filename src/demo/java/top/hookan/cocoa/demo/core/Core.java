package top.hookan.cocoa.demo.core;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.Name("cocoa_api_demo_core")
@IFMLLoadingPlugin.DependsOn("cocoa_api_core")
public class Core implements IFMLLoadingPlugin
{
    @Override
    public String[] getASMTransformerClass()
    {
        return new String[]{"top.hookan.cocoa.demo.core.Hooks"};
    }
    
    @Override
    public String getModContainerClass()
    {
        return "top.hookan.cocoa.demo.core.Container";
    }
    
    @Nullable
    @Override
    public String getSetupClass()
    {
        return null;
    }
    
    @Override
    public void injectData(Map<String, Object> data)
    {
    
    }
    
    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }
}
