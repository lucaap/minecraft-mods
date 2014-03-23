package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockColored extends Block
{
    @SideOnly(Side.CLIENT)
    private Icon[] field_94349_a;

    public BlockColored(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    public Icon func_71858_a(int par1, int par2)
    {
        return this.field_94349_a[par2 % this.field_94349_a.length];
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }

    /**
     * Takes a dye damage value and returns the block damage value to match
     */
    public static int getBlockFromDye(int par0)
    {
        return ~par0 & 15;
    }

    /**
     * Takes a block damage value and returns the dye damage value to match
     */
    public static int getDyeFromBlock(int par0)
    {
        return ~par0 & 15;
    }

    @SideOnly(Side.CLIENT)
    public void func_71879_a(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < 16; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_94349_a = new Icon[16];

        for (int var2 = 0; var2 < this.field_94349_a.length; ++var2)
        {
            this.field_94349_a[var2] = par1IconRegister.func_94245_a(this.func_111023_E() + "_" + ItemDye.dyeItemNames[getDyeFromBlock(var2)]);
        }
    }
}
