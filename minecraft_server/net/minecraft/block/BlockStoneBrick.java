package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockStoneBrick extends Block
{
    public static final String[] STONE_BRICK_TYPES = new String[] {"default", "mossy", "cracked", "chiseled"};
    public static final String[] field_94407_b = new String[] {null, "mossy", "cracked", "carved"};
    @SideOnly(Side.CLIENT)
    private Icon[] field_94408_c;

    public BlockStoneBrick(int par1)
    {
        super(par1, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    public Icon func_71858_a(int par1, int par2)
    {
        if (par2 < 0 || par2 >= field_94407_b.length)
        {
            par2 = 0;
        }

        return this.field_94408_c[par2];
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
        for (int var4 = 0; var4 < 4; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_94408_c = new Icon[field_94407_b.length];

        for (int var2 = 0; var2 < this.field_94408_c.length; ++var2)
        {
            String var3 = this.func_111023_E();

            if (field_94407_b[var2] != null)
            {
                var3 = var3 + "_" + field_94407_b[var2];
            }

            this.field_94408_c[var2] = par1IconRegister.func_94245_a(var3);
        }
    }
}
