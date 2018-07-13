package top.hookan.cocoa.demo.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockDemo extends Block
{
    public BlockDemo()
    {
        super(Material.ROCK);
        setUnlocalizedName("demo");
        setCreativeTab(CreativeTabs.FOOD);
    }
}
