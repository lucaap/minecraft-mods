package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockWood extends Block
{
    /** The type of tree this block came from. */
    public static final String[] woodType = new String[] {"oak", "spruce", "birch", "jungle"};
    @SideOnly(Side.CLIENT)
    private Icon[] field_94387_c;

    public BlockWood(int par1)
    {
        super(par1, Material.wood);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    public Icon func_71858_a(int par1, int par2)
    {
        if (par2 < 0 || par2 >= this.field_94387_c.length)
        {
            par2 = 0;
        }

        return this.field_94387_c[par2];
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public void func_71879_a(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_94387_c = new Icon[woodType.length];

        for (int var2 = 0; var2 < this.field_94387_c.length; ++var2)
        {
            this.field_94387_c[var2] = par1IconRegister.func_94245_a(this.func_111023_E() + "_" + woodType[var2]);
        }
    }
}
