package top.hookan.cocoa.core;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.Name("cocoa_api_core")
public class CorePlugin implements IFMLLoadingPlugin
{
    public String[] getASMTransformerClass()
    {
        return new String[]{"top.hookan.cocoa.core.CoreHooks"};
    }

    public String getModContainerClass()
    {
        return "top.hookan.cocoa.core.CoreContainer";
    }

    public String getSetupClass()
    {
        return null;
    }

    public void injectData(Map<String, Object> data)
    {

    }

    public String getAccessTransformerClass()
    {
        return null;
    }
}
